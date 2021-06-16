package com.hknp.controller.api.open;

import com.hknp.utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = {"/api/register-account"})
public class RegisterAccountServlet extends HttpServlet {


   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      try {
         String email = req.getParameter("signup-email");

         String otp = GenerateUtils.oneTimePassword(6);
         String sVerify = "OTP: " + otp;
         MailUtils.sendPlanText(email, "Xác thực tài khoản", sVerify);
         otp = HashUtils.getMd5(otp);
         CookieUtils.addCookie(resp, "otp", otp, 60 * 5);
         result += "true\n";
         ServletUtils.printWrite(resp, result);
      } catch (Exception e1) {
         result += "false\n";
         ServletUtils.printWrite(resp, result);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String result = "";
      Map<String, Object> parameterMap = ServletUtils.getParametersMap(req);
      try {
         String code = (String) parameterMap.get("signup-verified-code");
         String otp = CookieUtils.getCookieValue(req, "otp");
         if (HashUtils.getMd5(code).equals(otp)) {
            result += "true\n";
         } else {
            result += "false\n";
         }
         ServletUtils.printWrite(resp, result);
      } catch (Exception e1) {
         result += "false\n";
         ServletUtils.printWrite(resp, result);
      }
   }
}
