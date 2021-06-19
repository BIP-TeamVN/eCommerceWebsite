package com.hknp.controller.filter;

import com.hknp.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class XSSRequestWrapper extends HttpServletRequestWrapper {

   public XSSRequestWrapper(HttpServletRequest request) {
      super(request);
   }

   @Override
   public String getParameter(String name) {
      return StringUtils.stripXSS(super.getParameter(name));
   }

   @Override
   public Map<String, String[]> getParameterMap() {
      Map<String, String[]> filteredMap = new HashMap<>(super.getParameterMap());

      for (Map.Entry<String, String[]> entry : filteredMap.entrySet()) {
         String[] values = filteredMap.get(entry.getKey());

         for (int i = 0; i < values.length; i++) {
            values[i] = StringUtils.stripXSS(values[i]);
         }

         filteredMap.put(entry.getKey(), values);
      }

      return filteredMap;
   }
}
