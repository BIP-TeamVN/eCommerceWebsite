package com.hknp.controller.admin;

import com.hknp.model.db.AddressDB;
import com.hknp.model.db.ProvinceDB;
import com.hknp.model.db.UserDB;
import com.hknp.model.entity.AddressEntity;
import com.hknp.model.entity.DistrictEntity;
import com.hknp.model.entity.ProvinceEntity;
import com.hknp.model.entity.UserEntity;
import com.hknp.utils.FormatUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {"/admin"})
public class HomeController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

     /* EntityManagerFactory eMF = Persistence.createEntityManagerFactory("eCommerceDb");
      EntityManager em = eMF.createEntityManager();
      EntityTransaction et = null;
      try {
         et = em.getTransaction();
         et.begin();
         ProvinceEntity p = new ProvinceEntity();
        *//* p.setProvinceId("00");
         p.setProvinceName("Test");
         p.setProvinceType("P");*//*

         em.persist(p);
         et.commit();
      } catch (Exception e) {
         if (et != null) {
            et.rollback();
         }
         e.printStackTrace();
      } finally {
         em.close();
      }
*/


      /*ProvinceDTO provinceDTO = new ProvinceDTO("00", "Test2", "C");
      ProvinceDAO.getInstance().insert(provinceDTO);*/


      List<ProvinceEntity> listProvince = ProvinceDB.getInstance().gets();
      req.setAttribute("listProvince", listProvince);

      DistrictEntity districtEntity = new DistrictEntity("000", "Test", "D","05");
      ProvinceEntity provinceEntity = new ProvinceEntity("05","Test 2","P");
      provinceEntity.setListDistrict(Collections.singletonList(districtEntity));

      boolean test = ProvinceDB.getInstance().insert(provinceEntity);

      /*ProvinceDTO dtoP = ProvinceDAO.getInstance().getById("01");

      Date dob = FormatUtils.stringToDate("2000-02-09", "yyyy-dd-MM");
      UserDTO dto = new UserDTO(0l, "Lam", "Khanh", "Nam", dob, "ssn", "path", "0956663", "email", "username", "pass", "type", true);
      Long newId = (Long) UserDAO.getInstance().insert(dto);

      req.setAttribute("newId", newId);*/

      UserEntity update = new UserEntity(1L, "Khanh","Lam","Nam", FormatUtils.stringToDate("2000-02-09", "yyyy-dd-MM"), "072245030000","","0949494029","quockhanhdev@gmail.com","quockhanh","pass","BOSS",true);
      Set<AddressEntity> adds = new HashSet<>();
      adds.add(AddressDB.getInstance().getById(1L));
      adds.add(AddressDB.getInstance().getById(2L));
      adds.add(AddressDB.getInstance().getById(3L));
      update.setAddressSet(adds);

      boolean result = UserDB.getInstance().update(update);

      UserEntity addU = new UserEntity(null, "Khanh","Lam","Nam", FormatUtils.stringToDate("2000-02-09", "yyyy-dd-MM"), "072245030000","","0949494029","quockhanhdev@gmail.com","quockhanh","pass","BOSS",true);
      adds = new HashSet<>();
      adds.add(AddressDB.getInstance().getById(8L));
      adds.add(AddressDB.getInstance().getById(9L));
      //addU.setAddressSet(adds);

      Long newId = UserDB.getInstance().insert(addU);

      RequestDispatcher reqDispatcher = req.getRequestDispatcher("/view/admin/home.jsp");
      reqDispatcher.forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }
}