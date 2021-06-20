package com.hknp.controller.filter;

import com.hknp.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

   public XSSRequestWrapper(HttpServletRequest request) {
      super(request);
   }

   @Override
   public String getHeader(String name) {
      return super.getHeader(name);
   }

   @Override
   public Enumeration<String> getHeaderNames() {
      return super.getHeaderNames();
   }

   @Override
   public int getIntHeader(String name) {
      return super.getIntHeader(name);
   }

   @Override
   public String getParameter(String name) {
      String para = StringUtils.stripXSS(super.getParameter(name));
      if (para != null && para.length() > 5000) {
         para = para.substring(0, 5000);
      }
      return para;
   }

   @Override
   public Map<String, String[]> getParameterMap() {
      Map<String, String[]> filteredMap = new HashMap<>(super.getParameterMap());

      for (Map.Entry<String, String[]> entry : filteredMap.entrySet()) {
         String[] values = filteredMap.get(entry.getKey());

         for (int i = 0; i < values.length; i++) {
            String para = StringUtils.stripXSS(values[i]);
            if (para != null && para.length() > 5000) {
               para = para.substring(0, 5000);
            }
            values[i] = StringUtils.stripXSS(para);
         }

         filteredMap.put(entry.getKey(), values);
      }

      return filteredMap;
   }
}
