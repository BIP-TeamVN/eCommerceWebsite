<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
   int countItem = 10;
   String paraSelectedIndex = request.getParameter("selectedIndex");
   int selectedIndex = 0;
   try {
      selectedIndex = Integer.parseInt(paraSelectedIndex);
   }
   catch (Exception e) {
      e.printStackTrace();
   }

   boolean[] isActive = new boolean[countItem];
   isActive[selectedIndex] = true;
%>
<!-- Sidenav -->
<nav class="sidenav navbar navbar-vertical fixed-left navbar-expand-xs navbar-light bg-white" id="sidenav-main">
   <div class="scrollbar-inner">
      <!-- Brand -->
      <div class="sidenav-header  align-items-center">
         <a class="navbar-brand" href="javascript:void(0)">
            <img src="../../assets/img/brand/blue.png" class="navbar-brand-img" alt="...">
         </a>
      </div>
      <div class="navbar-inner">
         <!-- Collapse -->
         <div class="collapse navbar-collapse" id="sidenav-collapse-main">
            <!-- Nav items -->
            <ul class="navbar-nav">
               <li class="nav-item">
                  <a class="nav-link <%=isActive[0] ? "active" : ""%>" href="<%=isActive[0] ? "javascript:void(0)" : "/admin"%>">
                     <i class="ni ni-tv-2 text-primary"></i>
                     <span class="nav-link-text">Dashboard</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[1] ? "active" : ""%>" href="<%=isActive[1] ? "javascript:void(0)" : "/admin/employee"%>">
                     <i class="fa fa-user-circle text-success"></i>
                     <span class="nav-link-text">Nhân viên</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[2] ? "active" : ""%>" href="<%=isActive[2] ? "javascript:void(0)" : "/admin/seller"%>">
                     <i class="fa fa-store text-darker"></i>
                     <span class="nav-link-text">Cửa hàng</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[3] ? "active" : ""%>" href="<%=isActive[3] ? "javascript:void(0)" : "/admin/delivery"%>">
                     <i class="fa fa-user-ninja text-yellow"></i>
                     <span class="nav-link-text">Giao hàng</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[4] ? "active" : ""%>" href="<%=isActive[4] ? "javascript:void(0)" : "/admin/customer"%>">
                     <i class="fa fa-users text-danger"></i>
                     <span class="nav-link-text">Khách hàng</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[5] ? "active" : ""%>" href="<%=isActive[5] ? "javascript:void(0)" : "/admin/category"%>">
                     <i class="ni ni-bullet-list-67 text-default"></i>
                     <span class="nav-link-text">Ngành hàng</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[6] ? "active" : ""%>" href="<%=isActive[6] ? "javascript:void(0)" : "/employee/product"%>">
                     <i class="fa fa-box text-default"></i>
                     <span class="nav-link-text">Sản phẩm</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[7] ? "active" : ""%>" href="<%=isActive[7] ? "javascript:void(0)" : "/admin/brand"%>">
                     <i class="fa fa-copyright text-default"></i>
                     <span class="nav-link-text">Nhãn hiệu</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[8] ? "active" : ""%>" href="<%=isActive[8] ? "javascript:void(0)" : "/admin/message"%>">
                     <i class="fa fa-comment-dots text-blue"></i>
                     <span class="nav-link-text">Tin nhắn</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[9] ? "active" : ""%>" href="<%=isActive[9] ? "javascript:void(0)" : "/admin/feedback"%>">
                     <i class="fa fa-flag text-pink"></i>
                     <span class="nav-link-text">Phản hồi</span>
                  </a>
               </li>
            </ul>
         </div>
      </div>
   </div>
</nav>