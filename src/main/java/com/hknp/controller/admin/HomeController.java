package com.hknp.controller.admin;

import com.hknp.model.dao.ProvinceDAO;
import com.hknp.model.dto.ProvinceDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/admin"})
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*ProvinceDTO provinceDTO = new ProvinceDTO("00","Test2","C");
        ProvinceDAO.getInstance().insert(provinceDTO);

        ArrayList<ProvinceDTO> listProvince = ProvinceDAO.getInstance().gets();
        req.setAttribute("listProvince", listProvince);*/

        ProvinceDAO.getInstance().delete("_9");

        RequestDispatcher reqDispatcher = req.getRequestDispatcher("/view/admin/home.jsp");
        reqDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}