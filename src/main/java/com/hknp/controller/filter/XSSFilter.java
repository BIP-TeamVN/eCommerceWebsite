package com.hknp.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class XSSFilter implements Filter {
   @Override
   public void init(FilterConfig filterConfig) throws ServletException { }

   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      XSSRequestWrapper filterRes = new XSSRequestWrapper((HttpServletRequest) req);

      chain.doFilter(filterRes, resp);
   }

   @Override
   public void destroy() { }
}
