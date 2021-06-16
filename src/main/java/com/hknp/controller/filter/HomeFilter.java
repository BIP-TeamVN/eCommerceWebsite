package com.hknp.controller.filter;

import com.hknp.model.dao.UserDAO;
import com.hknp.model.entity.Cons;
import com.hknp.model.entity.UserEntity;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/home", "/product-search", "/product", "/info/*"})
public class HomeFilter implements Filter {
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {

   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpReq = (HttpServletRequest) request;
      HttpServletResponse httpResp = (HttpServletResponse) response;

      HttpSession session = httpReq.getSession(false);
      Long id = (Long) session.getAttribute("id");
      if (id != null) {
         String userType = UserDAO.getInstance().getUserType(id);
         if (userType.equals(Cons.User.USER_TYPE_CUSTOMER)) {
            UserEntity user = UserDAO.getInstance().getById(id);
            httpReq.setAttribute("result", ("true@ab" + user.getFullName() + "@ab" + user.getImageSrc()));
         } else {
            httpReq.setAttribute("result", "false");
         }
      }
      chain.doFilter(httpReq, httpResp);
   }

   @Override
   public void destroy() {

   }
}
