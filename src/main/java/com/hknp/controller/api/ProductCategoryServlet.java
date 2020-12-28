package com.hknp.controller.api;

import com.hknp.model.dao.AddressDAO;
import com.hknp.model.dao.EmployeeDAO;
import com.hknp.model.dao.ProductCategoryDAO;
import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.*;
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

@WebServlet(urlPatterns = {"/api/product-categories"})
public class ProductCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");

        ArrayList<ProductCategoryEntity> listProductCategory = ProductCategoryDAO.getInstance().gets();

        List<String> listJsonStr = new ArrayList<>();

        for (ProductCategoryEntity productCategory : listProductCategory) {
            listJsonStr.add(productCategory.toJson());
        }

        try (PrintWriter out = resp.getWriter()) {
            out.write("[" + String.join(", ", listJsonStr) + "]");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String result = "";

        try {
            String name = req.getParameter("name");

            ProductCategoryEntity newProductCategory = new ProductCategoryEntity();
            newProductCategory.setProductCategoryName(name);

            Long newProductCategoryId = ProductCategoryDAO.getInstance().insert(newProductCategory);

            if (newProductCategoryId != 0) {
                result += "true\n" + newProductCategoryId.toString();
            } else {
                result += "false\nError while insert product-category";
            }
        } catch (Exception e) {
            result += "false\n" + e.getMessage();
        }

        try (PrintWriter out = resp.getWriter()) {
            out.write(result);
        }
    }
}
