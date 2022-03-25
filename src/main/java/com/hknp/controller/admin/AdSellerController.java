package com.hknp.controller.admin;

import com.hknp.model.dao.SellerDAO;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/seller"})
public class AdSellerController implements HttpServletCon {
   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpServletCon.setPagesCountByPageParam(req);
      ServletUtils.forward(req, resp, "/view/admin/ad-seller.jsp");
   }

   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}