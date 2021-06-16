package com.hknp.controller.web;

import com.hknp.model.dao.BrandDAO;
import com.hknp.model.entity.BrandEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/brand"})
public class BrandController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String idPara = req.getParameter("id");
      Long brandId = StringUtils.toLong(idPara);
      BrandEntity brandEntity = BrandDAO.getInstance().getById(brandId);

      if (brandEntity != null) {
         /* Set attribute */
         ServletUtils.forward(req, resp, "/view/web/brand.jsp");
      } else {
         ServletUtils.forward(req, resp, "/view/web/home.jsp");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}