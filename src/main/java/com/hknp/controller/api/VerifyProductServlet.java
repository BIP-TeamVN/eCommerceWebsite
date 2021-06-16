package com.hknp.controller.api;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductEntity;
import com.hknp.utils.MailUtils;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/product/verify"})
public class VerifyProductServlet extends HttpServlet {
   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);
      if (isAuthentication(req)) {
         String idPara = (String) parameterMap.get("id");
         String statusPara = (String) parameterMap.get("status");
         Long id = StringUtils.toLong(idPara);
         Integer status = StringUtils.toInt(statusPara);
         if (ProductDAO.getInstance().updateStatus(id, status)) {
            ServletUtils.printWrite(resp, "true");
         } else {
            ServletUtils.printWrite(resp, "false\nKhông cập nhập thành công");
         }
         mailVerify(status, id);
      } else {
         ServletUtils.printWrite(resp, "false\nAuthentication");
      }
   }

   public void mailVerify(Integer status, Long productId) {
      ProductEntity productEntity = ProductDAO.getInstance().getById(productId);
      if (status == Cons.Product.PRODUCT_STATUS_CREATE) {
         MailUtils.sendPlanText(productEntity.getSellerEntity().getUserEntity().getEmail(), "Khoa san pham", "Sản phẩm: " + productEntity.getProductName() + " Đã bị khóa.\n\nLiện hệ 0969696029 để biết thêm chi tiết");
      } else if (status == Cons.Product.PRODUCT_STATUS_ACCESS) {
         MailUtils.sendPlanText(productEntity.getSellerEntity().getUserEntity().getEmail(), "Xac thuc san pham", "Sản phẩm: " + productEntity.getProductName() + " Đã được xác thực.\n\nLiện hệ 0969696029 để biết thêm chi tiết");
      } else {
         MailUtils.sendPlanText(productEntity.getSellerEntity().getUserEntity().getEmail(), "Tu choi san pham", "Sản phẩm: " + productEntity.getProductName() + " Đã bị từ chối.\n\nLiện hệ 0969696029 để biết thêm chi tiết");
      }
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      if (isAuthentication(req)) {
         Long id = StringUtils.toLong(req.getParameter("id"));
         String result = ProductDAO.getInstance().getStatusById(id).toString();
         ServletUtils.printWrite(resp, result);
      }
   }

   public boolean isAuthentication(HttpServletRequest req) {
      HttpSession session = req.getSession(false);
      Long id = (Long) session.getAttribute("id");

      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         return userType.equals(Cons.User.USER_TYPE_EMPLOYEE);
      }
      return false;
   }
}
