package com.hknp.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class XSSFilter implements Filter {
   @Override
   public void init(FilterConfig filterConfig) throws ServletException { }

   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      XSSRequestWrapper filterRes = new XSSRequestWrapper((HttpServletRequest) req);
      HttpServletResponse res = (HttpServletResponse) resp;

      res.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
      res.setHeader("Pragma","no-cache");
      res.setDateHeader("Expires",0);

      res.setHeader("X-XSS-Protection","1; mode=block");

      res.setHeader("X-Content-Type-Options","nosniff");

      res.setHeader("X-Frame-Options","DENY");

      chain.doFilter(filterRes, resp);
   }

   @Override
   public void destroy() { }
}
