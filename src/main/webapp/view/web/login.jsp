<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Trang đăng nhập</title>
   <%@ include file="../../common/link-css.jsp" %>

</head>
<body class="bg-default">
<!-- Navbar -->
<nav id="navbar-main" class="navbar navbar-horizontal navbar-transparent navbar-main navbar-expand-lg navbar-light">
   <div class="container">
      <a class="navbar-brand" href="dashboard.html">
         <img src="../../assets/img/brand/white.png">
      </a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-collapse"
              aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
      </button>
      <div class="navbar-collapse navbar-custom-collapse collapse" id="navbar-collapse">
         <div class="navbar-collapse-header">
            <div class="row">
               <div class="col-6 collapse-brand">
                  <a href="./home">
                     <img src="../../assets/img/brand/blue.png">
                  </a>
               </div>
               <div class="col-6 collapse-close">
                  <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-collapse"
                          aria-controls="navbar-collapse" aria-expanded="false" aria-label="Toggle navigation">
                     <span></span>
                     <span></span>
                  </button>
               </div>
            </div>
         </div>
         <ul class="navbar-nav mr-auto">
            <li class="nav-item">
               <a href="/home" class="nav-link">
                  <span class="nav-link-inner--text">Trang chủ</span>
               </a>
            </li>
         </ul>
         <hr class="d-lg-none"/>
      </div>
   </div>
</nav>
<!-- Main content -->
<div class="main-content" style="overflow-x: hidden; overflow-y: hidden">
   <!-- Header -->
   <div class="header m--6 bg-gradient-primary py-7 py-lg-8 pt-lg-9">
      <div class="container">
         <div class="header-body text-center mb-7">
            <div class="row justify-content-center">
               <div class="col-xl-5 col-lg-6 col-md-8 px-5">
                  <h1 class="text-white">Xin chào!</h1>
               </div>
            </div>
         </div>
      </div>
      <div class="separator separator-bottom separator-skew zindex-100">
         <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
              xmlns="http://www.w3.org/2000/svg">
            <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
         </svg>
      </div>
   </div>
   <!-- Page content -->
   <div class="container mt--8 pb-5">
      <div class="row justify-content-center">
         <div class="col-lg-4S col-md-10">
            <div class="card bg-secondary border-0 mb-0">
               <div class="card-header bg-transparent pb-5">
                  <div class="card shadow">
                     <div class="card-body">
                        <div class="tab-content" id="myTabContent">
                           <div class="tab-pane fade show active" id="tabs-icons-text-1" role="tabpanel"
                                aria-labelledby="tabs-icons-text-1-tab">
                              <form role="form" action="login" method="post">
                                 <div class="form-group mb-3">
                                    <div class="input-group input-group-merge input-group-alternative">
                                       <div class="input-group-prepend">
                                          <span class="input-group-text"><i class="ni ni-email-83"></i></span>
                                       </div>
                                       <input class="form-control" placeholder="Tài khoản" type="text" name="username"
                                              value="<c:out value="${user}"/>">
                                    </div>
                                 </div>
                                 <div class="form-group">
                                    <div class="input-group input-group-merge input-group-alternative">
                                       <div class="input-group-prepend">
                                          <span class="input-group-text"><i class="ni ni-lock-circle-open"></i></span>
                                       </div>
                                       <input class="form-control" placeholder="Mật khẩu" type="password"
                                              name="password" value="<c:out value="${pass}"/>">
                                    </div>
                                 </div>
                                 <div class="text-center">
                                    <button type="submit" class="btn btn-primary my-4 btn-sign">Đăng nhập</button>
                                 </div>
                              </form>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Footer -->
   <div class="w-100 bg-white" style="height: 100vh">
      <div class="container">
         <%@ include file="../../common/footer.jsp" %>
      </div>
   </div>
</div>

</body>
</html>
