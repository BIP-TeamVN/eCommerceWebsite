<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!--Top nav 1-->
<nav class="pb-0 navbar navbar-expand-lg navbar-dark bg-primary">
   <div class="container">
      <!--Brand logo-->
      <a class="navbar-brand" href="#">
         <img class="d-inline-block" src="../../assets/img/brand/white.png" style="max-width: 100%;height: 2.2rem;" />
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
               <div class="col-6 collapse-brand">
                  <a href="javascript:void(0)">
                     <img src="../../assets/img/brand/blue.png">
                  </a>
               </div>
               <div class="col-6 collapse-close">
                  <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbar-default"
                          aria-controls="navbar-default" aria-expanded="false" aria-label="Toggle navigation">
                     <span></span>
                     <span></span>
                  </button>
               </div>
            </div>
         </div>

         <ul class="navbar-nav ml-lg-auto">
            <li class="nav-item">
               <a class="nav-link nav-link-icon" href="#">
                  <i class="ni ni-favourite-28"></i>
                  <span class="nav-link-inner--text d-lg-none">Discover</span>
               </a>
            </li>
            <li class="nav-item">
               <a class="nav-link nav-link-icon" href="#">
                  <i class="ni ni-notification-70"></i>
                  <span class="nav-link-inner--text d-lg-none">Profile</span>
               </a>
            </li>
            <li class="nav-item dropdown">
               <a class="nav-link nav-link-icon" href="#" id="navbar-default_dropdown_1" role="button"
                  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="ni ni-settings-gear-65"></i>
                  <span class="nav-link-inner--text d-lg-none">Settings</span>
               </a>
               <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbar-default_dropdown_1">
                  <a class="dropdown-item" href="#">Action</a>
                  <a class="dropdown-item" href="#">Another action</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="#">Something else here</a>
               </div>
            </li>
         </ul>

      </div>
   </div>
</nav>

<!-- Topnav 2-->
<nav class="px-1 navbar navbar-top navbar-expand navbar-dark bg-primary border-bottom">
   <div class="container">
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
         <!-- Search form -->
         <form class="w-50 navbar-search navbar-search-light form-inline mr-sm-3" id="navbar-search-main">
            <div class="w-100 form-group mb-0">
               <div class="w-100 input-group input-group-alternative input-group-merge">
                  <div class="input-group-prepend">
                     <span class="input-group-text"><i class="fas fa-search"></i></span>
                  </div>
                  <input class="form-control" placeholder="Tìm kiếm sản phẩm" type="text">
               </div>
            </div>
            <button type="button" class="close" data-action="search-close" data-target="#navbar-search-main"
                    aria-label="Close">
               <span aria-hidden="true">×</span>
            </button>
         </form>
         <!-- Navbar links -->
         <ul class="navbar-nav align-items-center ml-md-auto ">
            <li class="nav-item d-sm-none">
               <a class="nav-link" href="#" data-action="search-show" data-target="#navbar-search-main">
                  <i class="ni ni-zoom-split-in"></i>
               </a>
            </li>
            <li class="nav-item dropdown">
               <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fa fa-shopping-cart mr-1"></i>
                  <span>Giỏ hàng</span>
                  <span class="ml-1 badge badge-md badge-circle badge-floating badge-secondary border-white">1</span>
               </a>
               <div class="dropdown-menu dropdown-menu-xl  dropdown-menu-right  py-0 overflow-hidden">
                  <!-- Dropdown header -->
                  <div class="px-3 py-3">
                     <h6 class="text-sm text-muted m-0">Bạn có <strong class="text-primary">1</strong> sản phẩm trong giỏ
                        hàng. </h6>
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
         <ul class="navbar-nav align-items-center  ml-auto ml-md-0 ">
            <li class="nav-item dropdown">
               <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false">
                  <div class="media align-items-center">
                  <span class="avatar avatar-sm rounded-circle">
                    <img alt="Image placeholder" src="../../assets/img/theme/team-4.jpg">
                  </span>
                     <div class="media-body  ml-2  d-none d-lg-block">
                        <span class="mb-0 text-sm  font-weight-bold">John Snow</span>
                     </div>
                  </div>
               </a>
               <div class="dropdown-menu  dropdown-menu-right ">
                  <div class="dropdown-header noti-title">
                     <h6 class="text-overflow m-0">Welcome!</h6>
                  </div>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-single-02"></i>
                     <span>My profile</span>
                  </a>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-settings-gear-65"></i>
                     <span>Settings</span>
                  </a>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-calendar-grid-58"></i>
                     <span>Activity</span>
                  </a>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-support-16"></i>
                     <span>Support</span>
                  </a>
                  <div class="dropdown-divider"></div>
                  <a href="#!" class="dropdown-item">
                     <i class="ni ni-user-run"></i>
                     <span>Logout</span>
                  </a>
               </div>
            </li>
         </ul>
      </div>
   </div>
</nav>
