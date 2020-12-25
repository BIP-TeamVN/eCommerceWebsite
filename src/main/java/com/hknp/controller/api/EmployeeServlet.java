package com.hknp.controller.api;

import com.google.gson.Gson;
import com.hknp.model.dao.CommuneDAO;
import com.hknp.model.dao.DistrictDAO;
import com.hknp.model.dao.EmployeeDAO;
import com.hknp.model.dao.ProvinceDAO;
import com.hknp.model.entity.CommuneEntity;
import com.hknp.model.entity.DistrictEntity;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.model.entity.ProvinceEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/employees"})
public class EmployeeServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      ArrayList<EmployeeEntity> listEmployee = EmployeeDAO.getInstance().gets();
      List<String> listJsonStr = new ArrayList<>();

      for (EmployeeEntity employee: listEmployee) {
         listJsonStr.add(employee.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }
}
