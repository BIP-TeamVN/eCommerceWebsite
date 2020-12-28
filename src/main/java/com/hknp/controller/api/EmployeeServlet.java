package com.hknp.controller.api;

import com.hknp.model.cons.AddressCons;
import com.hknp.model.cons.UserCons;
import com.hknp.model.dao.*;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.EmployeeEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.Base64Utils;
import com.hknp.utils.DateTimeUtils;
import com.hknp.utils.HashUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebServlet(urlPatterns = {"/api/employees"})
public class EmployeeServlet extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      ArrayList<EmployeeEntity> listEmployee = EmployeeDAO.getInstance().gets();
      List<String> listJsonStr = new ArrayList<>();

      for (EmployeeEntity employee : listEmployee) {
         listJsonStr.add(employee.toJson());
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write("[" + String.join(", ", listJsonStr) + "]");
      }
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      /*String methodName = "doPost";

      // checks if the request actually contains upload file
      if (!ServletFileUpload.isMultipartContent(req)) {
      }

      // configures upload settings
      DiskFileItemFactory factory = new DiskFileItemFactory();
      factory.setSizeThreshold(THRESHOLD_SIZE);
      factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

      ServletFileUpload upload = new ServletFileUpload(factory);
      upload.setFileSizeMax(MAX_FILE_SIZE);
      upload.setSizeMax(MAX_REQUEST_SIZE);

      // constructs the directory path to store upload file
      String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
      // creates the directory if it does not exist
      File uploadDir = new File(uploadPath);
      if (!uploadDir.exists()) {
         uploadDir.mkdir();
      }

      try {
         // parses the request's content to extract file data
         List formItems = upload.parseRequest(request);
         Iterator iter = formItems.iterator();

         // iterates over form's fields
         while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            // processes only fields that are not form fields
            if (!item.isFormField()) {
               String fileName = new File(item.getName()).getName();
               String filePath = uploadPath + File.separator + fileName;
               File storeFile = new File(filePath);

               // saves the file on disk
               item.write(storeFile);
            }
         }
         request.setAttribute("message",
                 "Upload has been done successfully!");
         logger.debug("[{}] Upload has been done successfully! ", methodName);
      } catch (Exception ex) {
         request.setAttribute("message",
                 "There was an error: " + ex.getMessage());
         logger.debug("[{}] There was an error: {} ", methodName, ex);
      }
      getServletContext().getRequestDispatcher(
              "/WEB-INF/web/csrCustomerLists/message.jsp").forward(request,
              response);*/

      try {
         String lastName = req.getParameter("last-name");
         String firstName = req.getParameter("first-name");

         String gender = req.getParameter("gender");
         String dateOfBirth = req.getParameter("dob");
         String phoneNumber = req.getParameter("phone-number");
         String ssn = req.getParameter("ssn");
         String email = req.getParameter("email");

         String provinceId = req.getParameter("province");
         String districtId = req.getParameter("district");
         String communeId = req.getParameter("commune");
         String addressStreet = req.getParameter("address-street");

         String startDate = req.getParameter("start-date");
         String salary = req.getParameter("salary");

         UserEntity newUser = new UserEntity();

         newUser.setFirstName(firstName);
         newUser.setLastName(lastName);
         newUser.setGender(gender);
         newUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "yyyy-MM-dd"));
         newUser.setPhoneNumber(phoneNumber);
         newUser.setSsn(ssn);
         newUser.setEmail(email);

         newUser.setUserType(UserCons.USER_TYPE_EMPLOYEE);
         newUser.setUserName(phoneNumber);
         newUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
         newUser.setStatus(true);

         Long newUserId = UserDAO.getInstance().insert(newUser);

         if (newUserId != 0) {
            AddressEntity newAddress = new AddressEntity();

            newAddress.setPhoneNumber(phoneNumber);
            newAddress.setStreet(addressStreet);
            newAddress.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
            newAddress.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
            newAddress.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));

            newAddress.setFullName(lastName + " " + firstName);
            newAddress.setAddressName(AddressCons.DEFAULT_ADDRESS_NAME);
            newAddress.setUserId(newUserId);

            Long newAddressId = AddressDAO.getInstance().insert(newAddress);

            if (newAddressId != 0) {
               UserEntity user = UserDAO.getInstance().getById(newUserId);
               user.setAddressEntities(Collections.singletonList(AddressDAO.getInstance().getById(newAddressId)));

               UserDAO.getInstance().update(user);

               EmployeeEntity newEmployee = new EmployeeEntity();
               newEmployee.setUserId(newUserId);
               newEmployee.setUserEntity(user);
               newEmployee.setSalary(StringUtils.toBigDecimal(salary));
               newEmployee.setStartDate(DateTimeUtils.stringToDate(startDate, "yyyy-MM-dd"));

               EmployeeDAO.getInstance().insert((newEmployee));

               result += "true\n" + newUserId.toString();
            } else {
               result += "false\nError while insert address";
            }
         } else {
            result += "false\nError while insert user";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");
      String result = "";

      try {
         String id = req.getParameter("id");
         String lastName = req.getParameter("last-name");
         String firstName = req.getParameter("first-name");

         String gender = req.getParameter("gender");
         String dateOfBirth = req.getParameter("dob");
         String phoneNumber = req.getParameter("phone-number");
         String ssn = req.getParameter("ssn");
         String email = req.getParameter("email");

         String provinceId = req.getParameter("province");
         String districtId = req.getParameter("district");
         String communeId = req.getParameter("commune");
         String addressStreet = req.getParameter("address-street");

         String startDate = req.getParameter("start-date");
         String salary = req.getParameter("salary");

         UserEntity updateUser = UserDAO.getInstance().getById(StringUtils.toLong(id));

         updateUser.setFirstName(firstName);
         updateUser.setLastName(lastName);
         updateUser.setGender(gender);
         updateUser.setDateOfBirth(DateTimeUtils.stringToDate(dateOfBirth, "dd/MM/yyyy"));
         updateUser.setPhoneNumber(phoneNumber);
         updateUser.setSsn(ssn);
         updateUser.setEmail(email);

         updateUser.setUserType(UserCons.USER_TYPE_EMPLOYEE);
         updateUser.setUserName(phoneNumber);
         updateUser.setPassword(HashUtils.getMd5(Base64Utils.encodeFromString(phoneNumber)));
         updateUser.setStatus(true);

         Boolean updateResult = UserDAO.getInstance().update(updateUser);

         if (updateResult != false) {
            AddressEntity updateAddress = updateUser.getAddressEntities().get(0);

            updateAddress.setPhoneNumber(phoneNumber);
            updateAddress.setStreet(addressStreet);
            updateAddress.setProvinceEntity(ProvinceDAO.getInstance().getById(provinceId));
            updateAddress.setDistrictEntity(DistrictDAO.getInstance().getById(districtId));
            updateAddress.setCommuneEntity(CommuneDAO.getInstance().getById(communeId));

            updateAddress.setFullName(lastName + " " + firstName);
            updateAddress.setAddressName(AddressCons.DEFAULT_ADDRESS_NAME);
            updateAddress.setUserId(updateUser.getUserId());

            Boolean updateResltAddress = AddressDAO.getInstance().update(updateAddress);

            if (updateResltAddress != false) {
               EmployeeEntity employeeEntity = EmployeeDAO.getInstance().getById(updateUser.getUserId());

               employeeEntity.setSalary(StringUtils.toBigDecimal(salary));
               employeeEntity.setStartDate(DateTimeUtils.stringToDate(startDate, "dd/MM/yyyy"));

               EmployeeDAO.getInstance().update((employeeEntity));

               result += "true\n" + updateUser.getUserId().toString();
            } else {
               result += "false\nError while insert address";
            }
         } else {
            result += "false\nError while insert user";
         }
      } catch (Exception e) {
         result += "false\n" + e.getMessage();
      }

      try (PrintWriter out = resp.getWriter()) {
         out.write(result);
      }
   }

   @Override
   protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html; charset=UTF-8");

      try (PrintWriter out = resp.getWriter()) {
         out.write("false");
      }
   }
}
