<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
   int countItem = 10;
   String paraSelectedIndex = request.getParameter("selectedIndex");
   int selectedIndex = 0;
   try {
      selectedIndex = Integer.parseInt(paraSelectedIndex);
   } catch (Exception e) {
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
                  <a class="nav-link <%=isActive[0] ? "active" : ""%>" href="<%=isActive[0] ? "javascript:void(0)" : "/delivery/info"%>">
                     <i class="fa fa-address-card text-blue"></i>
                     <span class="nav-link-text">Thông tin cá nhân</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[1] ? "active" : ""%>" href="<%=isActive[1] ? "javascript:void(0)" : "/delivery"%>?type=2">
                     <i class="fa fa-list-alt text-danger"></i>
                     <span class="nav-link-text">Danh Sách đơn hàng</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[2] ? "active" : ""%>" href="<%=isActive[2] ? "javascript:void(0)" : "/delivery"%>?type=4">
                     <i class="fa fa-people-carry text-default"></i>
                     <span class="nav-link-text">Đơn chưa nhận hàng</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[3] ? "active" : ""%>" href="<%=isActive[3] ? "javascript:void(0)" : "/delivery"%>?type=5">
                     <i class="fa fa-motorcycle text-green"></i>
                     <span class="nav-link-text">Đơn đang giao</span>
                  </a>
               </li>
               <li class="nav-item">
                  <a class="nav-link <%=isActive[4] ? "active" : ""%>" href="<%=isActive[4] ? "javascript:void(0)" : "/delivery"%>?type=6">
                     <i class="fa fa-clipboard-check text-orange"></i>
                     <span class="nav-link-text">Đơn đã giao</span>
                  </a>
               </li>
            </ul>
         </div>
      </div>
   </div>
</nav>