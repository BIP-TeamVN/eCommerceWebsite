package com.hknp.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtils {
   public static void forward(HttpServletRequest req, HttpServletResponse resp, String url)
           throws ServletException, IOException {
      RequestDispatcher reqDispatcher = req.getRequestDispatcher(url);
      reqDispatcher.forward(req, resp);
   }
}
