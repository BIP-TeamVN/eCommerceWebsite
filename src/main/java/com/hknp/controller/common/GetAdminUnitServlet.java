package com.hknp.controller.common;

import com.google.gson.Gson;
import com.hknp.model.dao.CommuneDAO;
import com.hknp.model.dao.DistrictDAO;
import com.hknp.model.dao.ProvinceDAO;
import com.hknp.model.entity.CommuneEntity;
import com.hknp.model.entity.DistrictEntity;
import com.hknp.model.entity.ProvinceEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/GetAdminUnitServlet"})
public class GetAdminUnitServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html; charset=UTF-8");
      response.setCharacterEncoding("UTF-8");

      try (PrintWriter out = response.getWriter()) {
         String op = request.getParameter("operation");
         Gson gson = new Gson();
         String jsonData = "";

         if (op.equals("get-province")) {
            List<ProvinceEntity> listProvince = ProvinceDAO.getInstance().gets();
            jsonData = gson.toJson(listProvince);
         } else if (op.equals("get-district")) {
            String id = request.getParameter("id");
            List<DistrictEntity> districtList = DistrictDAO.getInstance().getByProvinceId(id);
            jsonData = gson.toJson(districtList);
         } else if (op.equals("get-commune")) {
            String id = request.getParameter("id");
            List<CommuneEntity> communeList = CommuneDAO.getInstance().getByDistrictId(id);
            jsonData = gson.toJson(communeList);
         }

         out.write(jsonData);
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}
