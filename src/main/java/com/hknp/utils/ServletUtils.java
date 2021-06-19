package com.hknp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provide methods that help you to work with<b>HttpServlet</b>
 */
public class ServletUtils {
   /**
    * Forwards a request from a servlet to another resource
    * (servlet, JSP file, or HTML file) on the server
    * This method allows one servlet to do preliminary processing of
    * a request and another resource to generate the response.
    *
    * @param req  {@link HttpServletRequest}
    *             request information for HTTP servlets.
    * @param resp {@link HttpServletResponse}
    *             HTTP-specific functionality in sending a response.
    * @param url  forward URL
    * @throws ServletException Defines a general exception a servlet can throw when it
    *                          encounters difficulty.
    * @throws IOException      Signals that an I/O exception of some sort has occurred
    * @see HttpServletRequest#getRequestDispatcher(String)
    * @see RequestDispatcher#forward(ServletRequest, ServletResponse)
    */
   public static void forward(HttpServletRequest req, HttpServletResponse resp, String url)
           throws ServletException, IOException {
      RequestDispatcher reqDispatcher = req.getRequestDispatcher(url);
      reqDispatcher.forward(req, resp);
   }

   /**
    * Sets the content type of the response being sent to
    * the client to UTF-8 and write content to response
    *
    * @param resp    {@link HttpServletResponse}
    *                HTTP-specific functionality in sending a response.
    * @param content String to be written
    * @throws IOException Signals that an I/O exception of some sort has occurred
    * @see PrintWriter#write(String)
    * @see HttpServletResponse#setContentType(String)
    */
   public static void printWrite(HttpServletResponse resp, String content) throws IOException {
      resp.setContentType("text/html; charset=UTF-8");

      try (PrintWriter out = resp.getWriter()) {
         content = StringUtils.stripXSS(content);
         out.write(content);
      }
   }

   /**
    * Get Parameters map from Servlet request
    *
    * @param req {@link HttpServletRequest}
    *            request information for HTTP servlets.
    * @return <code>Map<String, Object></code> if get successfully<br>
    * <code>empty map</code> otherwise
    * @throws IOException Signals that an I/O exception of some sort has occurred
    * @see HttpServletRequest#getInputStream()
    * @see ObjectMapper#readValue(String, Class)
    */
   public static Map<String, Object> getParametersMap(HttpServletRequest req) throws IOException {
      InputStream inputStream = req.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String parameterJson = bufferedReader.readLine();

      Map<String, Object> parameterMap = new HashMap<>();
      try {
         parameterMap = new ObjectMapper().readValue(parameterJson, HashMap.class);
      } catch (Exception e) {
         e.printStackTrace();
      }

      if (parameterMap.size() > 0) {
         for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            parameterMap.put(entry.getKey(), StringUtils.stripXSS((String) entry.getValue()));
         }
      }

      return parameterMap;
   }
}