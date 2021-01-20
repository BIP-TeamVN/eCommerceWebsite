package com.hknp.controller.api.open;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.entity.ProductEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api/product-search-customer"})
public class ProductSearchForCustomer extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String pagePara = req.getParameter("page");

      String keyword = req.getParameter("keyword").trim();
      String columnName = req.getParameter("columnName");
      String typeSort = req.getParameter("typeSort");
      String categoriesReq = req.getParameter("categories");
      String brandsReq = req.getParameter("brands");
      String[] categoriesPara = null;
      int lenCategories = 0;
      if (!categoriesReq.equals("")) {
         categoriesPara = categoriesReq.split("@nq");
         lenCategories = categoriesPara.length;
      }
      String[] brandsPara = null;
      int lenBrands = 0;
      if (!brandsReq.equals("")) {
         brandsPara = brandsReq.split("@nq");
         lenBrands = brandsPara.length;
      }
      List<Long> categories = new ArrayList<>();
      for (int i = 0; i < lenCategories; i++) {
         categories.add(StringUtils.toLong(categoriesPara[i]));
      }
      List<Long> brands = new ArrayList<>();
      for (int i = 0; i < lenBrands; i++) {
         brands.add(StringUtils.toLong(brandsPara[i]));
      }

      if (keyword == null) {
         keyword = "";
      }
      Integer page = StringUtils.toInt(pagePara);
      if (page <= 0) {
         page = 1;
      }

      List<ProductEntity> listProduct = new ArrayList<>();
      List<String> listJsonStr = new ArrayList<>();

      listProduct = ProductDAO.getInstance().gets((page - 1) * 12, 12, keyword, columnName, typeSort, categories, brands);

      for (ProductEntity product : listProduct) {
         listJsonStr.add(product.toJson());
      }

      ServletUtils.printWrite(resp, "[" + String.join(", ", listJsonStr) + "]");
   }
}
