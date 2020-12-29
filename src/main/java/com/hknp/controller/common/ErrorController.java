package com.hknp.controller.common;

import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/error"})
public class ErrorController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String code = req.getParameter("code");
      String title = "Có một lỗi gì đó đã xảy ra";
      String detail = "Liên hệ Lâm Khánh - 0949494029";

      switch (code) {
         case "404":
            title = "Trang bạn tìm kiếm không tồn tại";
            detail = "URL có thể bị hỏng hoặc do quản trị viên xóa.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "500":
            title = "Lỗi kết nối Server";
            detail = "Đường truyền đến server hiện đang không truy cập được.<br>Vui long truy cập sau.<br>Nhấp vào nút bên dưới để quay lại";
            break;
         case "405":
            title = "Phương thức không được hổ trợ";
            detail = "Nhấp vào nút bên dưới để quay lại";
            break;
         default:
            break;
      }

      req.setAttribute("errorCode", code);
      req.setAttribute("errorTitle", title);
      req.setAttribute("errorDetail", detail);
      ServletUtils.forward(req, resp, "/common/error-page.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}
