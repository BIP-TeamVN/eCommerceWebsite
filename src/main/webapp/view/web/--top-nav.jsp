<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!--Top nav 1-->
<nav class="py-1 navbar navbar-expand-lg navbar-dark bg-primary">
   <div class="container">
      <!--Brand logo-->
      <a class="navbar-brand" href="javascript:void(0)">
         <img class="d-inline-block" src="../../assets/img/brand/white.png" style="max-width: 100%;height: 2.2rem;" alt="brand_logo" />
      </a>
      <!--Toggle button-->
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-default"
              aria-controls="navbar-default" aria-expanded="false" aria-label="Toggle navigation">
         <span class="navbar-toggler-icon"></span>
      </button>
      <!--Nav item-->
      <div class="collapse navbar-collapse" id="navbar-default">
         <div class="navbar-collapse-header">
            <div class="row">
               <!--Brand logo-->
               <div class="col-6 collapse-brand">
                  <a href="javascript:void(0)">
                     <img src="../../assets/img/brand/blue.png" alt="logo">
                  </a>
               </div>
               <!--Toggle menu button-->
               <div class="col-6 collapse-close">
                  <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-default"
                          aria-controls="navbar-default" aria-expanded="false" aria-label="Toggle navigation">
                     <span></span>
                     <span></span>
                  </button>
               </div>
            </div>
         </div>

         <!--Nav bar items-->
         <ul class="navbar-nav ml-lg-auto">
            <li class="nav-item">
               <a class="nav-link nav-link-icon" href="#">
                  <em class="fa fa-store-alt"></em>
                  <span class="nav-link-inner--text ml-1">Kênh người bán</span>
               </a>
            </li>

            <li class="nav-item">
               <a class="nav-link nav-link-icon pr-0" href="#">
                  <em class="ni ni-notification-70"></em>
                  <span class="nav-link-inner--text">Thông báo</span>
               </a>
            </li>
            <!-- <li class="nav-item dropdown">
              <a class="nav-link nav-link-icon" href="javascipt:void(0)" id="navbar-noti_dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <em class="ni ni-settings-gear-65"></em>
                <span class="nav-link-inner--text">Settings</span>
              </a>

              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbar-noti_dropdown">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </li> -->
         </ul>
      </div>
   </div>
</nav>

<!-- Top nav 2-->
<nav class="pt-0 pb-1 navbar navbar-top navbar-expand navbar-dark bg-primary border-bottom">
   <div class="container">
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
         <!-- Search form -->
         <form class="w-50 navbar-search navbar-search-light form-inline mr-sm-3" id="navbar-search-main">
            <div class="w-100 form-group mb-0">
               <div class="w-100 input-group input-group-alternative input-group-merge">
                  <div class="input-group-prepend">
                     <span class="input-group-text"><em class="fas fa-search"></em></span>
                  </div>
                  <input class="form-control" placeholder="Tìm kiếm sản phẩm" type="text">
               </div>
            </div>
            <button type="button" class="close" data-action="search-close" data-target="#navbar-search-main" aria-label="close">
               <span aria-hidden="true">×</span>
            </button>
         </form>

         <!-- Carts Navbar links -->
         <ul class="navbar-nav align-items-center ml-md-auto ">
            <li class="nav-item d-sm-none">
               <a class="nav-link" href="#" data-action="search-show" data-target="#navbar-search-main">
                  <em class="ni ni-zoom-split-in"></em>
               </a>
            </li>
            <li class="nav-item dropdown">
               <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <em class="fa fa-shopping-cart mr-1"></em>
                  <span>Giỏ hàng</span>
                  <span class="ml-1 badge badge-md badge-circle badge-floating badge-secondary border-white">1</span>
               </a>
               <div class="dropdown-menu dropdown-menu-xl  dropdown-menu-right  py-0 overflow-hidden">
                  <!-- Dropdown header -->
                  <div class="px-3 py-3">
                     <h6 class="text-sm text-muted m-0">Bạn có <strong class="text-primary">1</strong> sản phẩm trong giỏ hàng. </h6>
                  </div>
                  <!-- List group -->
                  <div class="list-group list-group-flush">
                     <a href="#!" class="list-group-item list-group-item-action">
                        <div class="row align-items-center">
                           <div class="col-auto">
                              <img alt="Image placeholder" src="../no-image-product.svg" class="avatar rounded">
                           </div>
                           <div class="col ml--2">
                              <div class="d-flex justify-content-between align-items-center">
                                 <div>
                                    <h4 class="mb-0 text-sm">Tên sản phẩm</h4>
                                 </div>
                                 <div class="text-right text-muted">
                                    <small>80000</small>
                                 </div>
                              </div>
                              <p class="text-sm mb-0">Let's meet at Starbucks at 11:30. Wdyt?</p>
                           </div>
                        </div>
                     </a>
                  </div>
                  <!-- View all -->
                  <a href="#!" class="dropdown-item text-center text-primary font-weight-bold py-3">Thanh toán</a>
               </div>
            </li>
         </ul>

         <!--User login-->
         <ul id="nav-user-login" class="d-none navbar-nav align-items-center ml-auto ml-md-0">
            <li class="nav-item dropdown">
               <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <div class="media align-items-center">
                  <span class="avatar avatar-sm rounded-circle">
                    <img alt="Image placeholder" src="../../assets/img/theme/team-4.jpg">
                  </span>
                     <div class="media-body ml-2 d-none d-lg-block">
                        <span class="mb-0 text-sm font-weight-bold">John Snow</span>
                     </div>
                  </div>
               </a>
               <div class="dropdown-menu  dropdown-menu-right ">
                  <div class="dropdown-header noti-title">
                     <h6 class="text-overflow m-0">Xin chào !</h6>
                  </div>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-single-02"></i>
                     <span>Thông tin tài khoản</span>
                  </a>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-settings-gear-65"></i>
                     <span>Sổ địa chỉ</span>
                  </a>
                  <a href="#!" class="dropdown-item">
                     <i class="fa fa-shopping-cart"></i>
                     <span>Đơn hàng</span>
                  </a>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-support-16"></i>
                     <span>Hỗ trợ</span>
                  </a>
                  <div class="dropdown-divider"></div>
                  <a href="#!" class="dropdown-item">
                     <i class="fa fa-sign-out-alt"></i>
                     <span>Đăng xuất</span>
                  </a>
               </div>
            </li>
         </ul>

         <!--User guest-->
         <ul id="nav-user-guest" class="navbar-nav align-items-center ml-auto ml-md-0 mr-0">
            <li><button class="text-uppercase btn btn-secondary m-1">Đăng ký</button></li>
            <li><button class="text-uppercase btn btn-secondary my-1 ml-1 mr-0">Đăng nhập</button></li>
         </ul>

      </div>
   </div>
</nav>
