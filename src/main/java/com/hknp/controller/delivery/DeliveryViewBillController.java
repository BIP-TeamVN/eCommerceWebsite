package com.hknp.controller.delivery;

import com.hknp.model.dao.AddressDAO;
import com.hknp.model.dao.EmployeeDAO;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = {"/delivery/detailbill"})
public class DeliveryViewBillController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String employeeIdPara = req.getParameter("id");
      Long employeeId = StringUtils.toLong(employeeIdPara);
      EmployeeEntity employeeEdit = null;

      if(employeeId != 0) {
         employeeEdit = EmployeeDAO.getInstance().getById(employeeId);
         if (employeeEdit != null) {
            if (employeeEdit.getUserEntity().getAddressEntities().size() == 0) {
               AddressEntity newAddress = new AddressEntity();
               newAddress.setUserId(employeeId);
               AddressDAO.getInstance().insert(newAddress);
               employeeEdit.getUserEntity().setAddressEntities(Collections.singletonList(newAddress));
               EmployeeDAO.getInstance().update(employeeEdit);
            }
            req.setAttribute("employeeEdit", employeeEdit);
            ServletUtils.forward(req, resp, "/view/delivery/dh-info.jsp");
            return;
         }
      }

      //ServletUtils.forward(req, resp, "/admin/employee");
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
}
