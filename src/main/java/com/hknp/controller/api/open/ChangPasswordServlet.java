package com.hknp.controller.api.open;

import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.Base64Utils;
import com.hknp.utils.HashUtils;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = {"/api/change-password"})
public class ChangPasswordServlet extends HttpServlet {

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      Long id = (Long) session.getAttribute("id");

      String result = "";
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);

      try {
         String currentPassword = (String) parameterMap.get("current-password");
         String newPassword = (String) parameterMap.get("new-password");

         UserEntity updateUser = UserDAO.getInstance().getById(id);

         String currentPasswordMd5 = HashUtils.getMd5(Base64Utils.encodeFromString(currentPassword));
         if (updateUser.getPassword().equals(currentPasswordMd5)) {
            updateUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(newPassword)));

            Boolean updateResult = UserDAO.getInstance().update(updateUser);

            if (updateResult != false) {
               result += "true\n" + updateUser.getUserId().toString();

            } else {
               result += "false\nXãy ra lỗi khi lưu mật khẩu mới";
            }

         } else {
            result += "false\nMật khẩu hiện tại sai, vui lòng kiểm tra lại !";
         }

      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      ServletUtils.printWrite(resp, result);
   }
}
