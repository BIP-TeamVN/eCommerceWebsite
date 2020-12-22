package com.hknp.controller.admin;

import com.hknp.model.dao.EmployeeDAO;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin/employee"})
public class AdEmployeeController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      ArrayList<EmployeeEntity> listEmployee = EmployeeDAO.getInstance().gets();
      req.setAttribute("listEmployee", listEmployee);

      ServletUtils.forward(req, resp, "/view/admin/ad-employee.jsp");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}
