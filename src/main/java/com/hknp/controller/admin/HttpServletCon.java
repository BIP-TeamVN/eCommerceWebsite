package com.hknp.controller.admin;

import com.hknp.model.dao.BrandDAO;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpServletCon {
        static void setPagesCountByPageParam(HttpServletRequest req) {
        Long totalRows = BrandDAO.getInstance().count();
        String page = req.getParameter("page");

        Long currentPage = StringUtils.toLong(page);
        Long totalPage = (totalRows / 10) + ((totalRows % 10 == 0) ? 0 : 1);

        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        if (currentPage < 1) {
            currentPage = 1L;
        }

        req.setAttribute("totalPage", totalPage);
        req.setAttribute("currentPage", currentPage);
    }

    void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
