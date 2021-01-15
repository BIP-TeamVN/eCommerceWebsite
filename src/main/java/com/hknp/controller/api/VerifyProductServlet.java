package com.hknp.controller.api;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.ProductEntity;
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
      resp.setContentType("text/html; charset=UTF-8");
      Map<String,Object> parameterMap = ServletUtils.getParametersMap(req);
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
      } else {
         ServletUtils.printWrite(resp, "false\nAuthentication");
      }
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      if (isAuthentication(req)) {
         String idPara = req.getParameter("id");
         ServletUtils.printWrite(resp, (ProductDAO.getInstance().getStatusById(StringUtils.toLong(idPara))).toString());
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
