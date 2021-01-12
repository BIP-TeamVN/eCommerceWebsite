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
    for (int i = 0; i < isActive.length; i++) {
        isActive[i] = false;
    }
    isActive[selectedIndex] = true;

    selectedIndex = selectedIndex;
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
                        <a class="nav-link <%=isActive[3] ? "active" : ""%>" href="<%=isActive[3] ? "javascript:void(0)" : "/delivery/dh-info"%>">
                            <i class="fa fa-user-ninja text-yellow"></i>
                            <span class="nav-link-text">Thông tin cá nhân</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=isActive[4] ? "active" : ""%>" href="<%=isActive[4] ? "javascript:void(0)" : "/admin/customer"%>">
                            <i class="fa fa-users text-danger"></i>
                            <span class="nav-link-text">Đơn hàng</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=isActive[6] ? "active" : ""%>" href="<%=isActive[6] ? "javascript:void(0)" : "/seller/product"%>">
                            <i class="fa fa-box text-default"></i>
                            <span class="nav-link-text">Sản phẩm</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>