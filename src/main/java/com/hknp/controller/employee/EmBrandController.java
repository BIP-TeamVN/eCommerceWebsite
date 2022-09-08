package com.hknp.controller.employee;

import com.hknp.controller.admin.AdBrandController;
import com.hknp.controller.admin.HttpServletCon;
import com.hknp.model.dao.BrandDAO;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/employee/brand"})
public class EmBrandController implements HttpServletCon {
   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       HttpServletCon.setPagesCountByPageParam(req);
       ServletUtils.forward(req, resp, "/view/employee/em-brand.jsp");
   }

   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}