package com.hknp.controller.employee;

import com.hknp.model.dao.ProductDAO;
import com.hknp.model.entity.ProductCategoryEntity;
import com.hknp.model.entity.ProductEntity;
import com.hknp.model.entity.ProductTypeEntity;
import com.hknp.utils.ServletUtils;
import com.hknp.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/employee/product/detail"})
public class EmProductDetailController extends HttpServlet {
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String productIdPara = req.getParameter("id");
      Long productId = StringUtils.toLong(productIdPara);
      ProductEntity productEntity = null;

      if (productId != 0) {
         productEntity = ProductDAO.getInstance().getById(productId);
         if (productEntity != null) {
            Integer countType = productEntity.getProductTypeEntities().size();
            String types = productEntity.getProductTypeEntities().stream()
                    .map(ProductTypeEntity::getProductTypeName)
                    .collect(Collectors.joining("@$3,"));
            ArrayList<String> listQuantities = new ArrayList<>(countType);
            for (ProductTypeEntity p : productEntity.getProductTypeEntities()) {
               listQuantities.add(String.valueOf(p.getQuantity()));
            }
            String quantities = String.join("@$3,", listQuantities);
            String images = productEntity.getProductTypeEntities().stream()
                    .map(ProductTypeEntity::getImage)
                    .collect(Collectors.joining("@$3,"));
            String categories = productEntity.getProductCategoryEntities().stream()
                    .map(ProductCategoryEntity::getProductCategoryName)
                    .collect(Collectors.joining(", "));
            String result = "[{" +
                    "\"id\":\"" + productEntity.getProductId() + "\"," +
                    "\"productName\":\"" + productEntity.getProductName() + "\"," +
                    "\"brand\":\"" + productEntity.getBrandEntity().getBrandName() + "\"," +
                    "\"productOrigin\":\"" + productEntity.getProductOrigin() + "\"," +
                    "\"productDesc\":\"" + productEntity.getProductDesc().replace("\n", "<br>") + "\"," +
                    "\"priceOrigin\":\"" + productEntity.getPriceOrigin() + "\"," +
                    "\"priceOrder\":\"" + productEntity.getPriceOrder() + "\"," +
                    "\"image0\":\"" + productEntity.getImage0() + "\"," +
                    "\"image1\":\"" + productEntity.getImage1() + "\"," +
                    "\"image2\":\"" + productEntity.getImage2() + "\"," +
                    "\"image3\":\"" + productEntity.getImage3() + "\"," +
                    "\"image4\":\"" + productEntity.getImage4() + "\"," +
                    "\"countType\":\"" + countType + "\"," +
                    "\"types\":\"" + types + "\"," +
                    "\"quantities\":\"" + quantities + "\"," +
                    "\"images\":\"" + images + "\"," +
                    "\"categories\":\"" + categories + "\"" +
                    "}]";
            ServletUtils.printWrite(resp, result);
         }
      }
   }
}
