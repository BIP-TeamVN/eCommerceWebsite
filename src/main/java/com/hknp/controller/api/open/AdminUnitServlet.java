package com.hknp.controller.api.open;

import com.hknp.model.dao.CommuneDAO;
import com.hknp.model.dao.DistrictDAO;
import com.hknp.model.dao.ProvinceDAO;
import com.hknp.model.entity.CommuneEntity;
import com.hknp.model.entity.DistrictEntity;
import com.hknp.model.entity.ProvinceEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/admin-units"})
public class AdminUnitServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String type = request.getParameter("type");
      List<String> listJsonStr = new ArrayList<>();

      if (type.equals("province")) {
         for (ProvinceEntity province : ProvinceDAO.getInstance().gets()) {
            listJsonStr.add(province.toJson());
         }
      } else if (type.equals("district")) {
         String id = request.getParameter("id");
         for (DistrictEntity district : DistrictDAO.getInstance().getByProvinceId(id)) {
            listJsonStr.add(district.toJson());
         }
      } else if (type.equals("commune")) {
         String id = request.getParameter("id");
         for (CommuneEntity commune : CommuneDAO.getInstance().getByDistrictId(id)) {
            listJsonStr.add(commune.toJson());
         }
      }

      ServletUtils.printWrite(response, "[" + String.join(", ", listJsonStr) + "]");
   }
}
