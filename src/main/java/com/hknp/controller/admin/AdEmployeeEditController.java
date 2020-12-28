package com.hknp.controller.admin;

import com.hknp.model.dao.EmployeeDAO;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/employee/edit"})
public class AdEmployeeEditController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String employeeIdPara = req.getParameter("id");
      Long employeeId = StringUtils.toLong(employeeIdPara);
      EmployeeEntity employeeEdit = null;

      if(employeeId != 0) {
         employeeEdit = EmployeeDAO.getInstance().getById(employeeId);
         if (employeeEdit != null) {
            req.setAttribute("employeeEdit", employeeEdit);
            ServletUtils.forward(req, resp, "/view/admin/ad-employee-edit.jsp");
            return;
         }
      }

      ServletUtils.forward(req, resp, "/admin/employee");
   }
}
