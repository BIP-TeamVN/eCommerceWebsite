package com.hknp.controller.filter;

import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/seller/*"})
public class SellerFilter implements Filter {

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {

   }

   @Override
   public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpReq = (HttpServletRequest) req;
      HttpServletResponse httpResp = (HttpServletResponse) resp;
      HttpSession session = httpReq.getSession();
      try {
         Long id = (Long) session.getAttribute("id");
         String userType = UserDAO.getInstance().getById(id).getUserType();
         if (userType.equals(Cons.User.USER_TYPE_SELLER)) {
            chain.doFilter(req, resp);
         } else {
            ServletUtils.forward(httpReq, httpResp, "/logout");
         }
      } catch (Exception e) {
         ServletUtils.forward(httpReq, httpResp, "/logout");
      }
   }

   @Override
   public void destroy() {

   }
}
