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
         case "400":
            title = "Yêu cầu không hợp lệ";
            detail = "Server không xác định được yêu cầu của bạn<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "401":
            title = "Không được phép truy cập";
            detail = "Trang được yêu câu cầu được cấp quyền để truy cập.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "402":
            title = "Không thể sử dụng";
            detail = "Bạn chưa thể sử dụng mã này.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "403":
            title = "Trang cấm";
            detail = "Trang yêu cầu truy cập bị cấm.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "404":
            title = "Trang bạn tìm kiếm không tồn tại";
            detail = "URL có thể bị hỏng hoặc do quản trị viên xóa.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "405":
            title = "Phương thức không được phép";
            detail = "Phương thức được chỉ định trong yêu cầu thì không được phép.<br>Nhấp vào nút bên dưới để quay lại";
            break;
         case "406":
            title = "Không được chấp nhận";
            detail = "Phản hồi không được máy chủ chấp nhận.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "407":
            title = "Yêu cầu xác thực proxy";
            detail = "Bạn phải xác thực proxy trước khi trước khi gửi yêu cầu này.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "408":
            title = "Hết thời gian yêu cầu";
            detail = "Yêu cầu mất nhiều thời gian hơn dự kiến ở Server.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "409":
            title = "Xung đột";
            detail = "Không thể hoàn thành do yêu cầu bị xung đột.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "410":
            title = "Không tồn tại";
            detail = "Trang yêu cầu đã bị xóa.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "411":
            title = "Quá độ dài quy định";
            detail = "Độ dài nội dung không xác định.<br>Máy chủ không chấp nhận yêu cầu này.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "412":
            title = "Điều kiện tiên quyết không đúng";
            detail = "Điều kiện được đưa ra trong yêu cầu được Server đánh giá là sai.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "413":
            title = "Đối tượng yêu cầu quá lớn";
            detail = "Server không chấp nhận yêu cầu vì thực thể yêu cầu quá lớn.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "414":
            title = "Url quá dài";
            detail = "Nhấn vào nút bên dưới để quay lại!";
            break;
         case "415":
            title = "Không được hỗ trợ";
            detail = "Serve không chấp nhận loại yêu câu này.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "417":
            title = "Expectation Failed";
            detail = "Nhấn vào nút bên dưới để quay lại!";
            break;
         case "500":
            title = "Lỗi kết nối Server";
            detail = "Đường truyền đến server hiện đang không truy cập được.<br>Vui long truy cập sau.<br>Nhấp vào nút bên dưới để quay lại";
            break;
         case "501":
            title = "Chưa được thực hiện";
            detail = "Yêu cầu chưa hoàn thành.<br>Server không hỗ trợ chức năng được yêu cầu.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "502":
            title = "Lỗi cỗng yêu cầu";
            detail = "Server không nhận được phản hồi yêu cầu hợp lệ.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "503":
            title = "Dịch vụ không khả dụng";
            detail = "Server tạm thời quá tải hoặc ngưng hoạt động.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "504":
            title = "Hết thời gian kết nối";
            detail = "Cổng kết nối hết thời gian chờ.<br>Nhấn vào nút bên dưới để quay lại!";
            break;
         case "505":
            title = "HTTP không hỗ trợ";
            detail = "Server không hỗ trợ giao thức HTTP.<br>Nhấn vào nút bên dưới để quay lại!";
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
