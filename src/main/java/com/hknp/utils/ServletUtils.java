package com.hknp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ServletUtils {
   public static void forward(HttpServletRequest req, HttpServletResponse resp, String url)
           throws ServletException, IOException {
      RequestDispatcher reqDispatcher = req.getRequestDispatcher(url);
      reqDispatcher.forward(req, resp);
   }

   public static void printWrite(HttpServletResponse resp, String content) throws IOException {
      try (PrintWriter out = resp.getWriter()) {
         out.write(content);
      }
   }

   public static Map<String, Object> getParametersMap(HttpServletRequest req) throws IOException {
      InputStream inputStream = req.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String parameterJson = bufferedReader.readLine();

      Map<String, Object> parameterMap = new ObjectMapper().readValue(parameterJson, HashMap.class);
      return parameterMap;
   }
}