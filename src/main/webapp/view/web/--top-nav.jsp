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
                  <a href="${pageContext.request.contextPath}">
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
            <button type="button" class="close" data-action="search-close" data-target="#navbar-search-main"
                    aria-label="close">
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
               <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false">
                  <em class="fa fa-shopping-cart mr-1"></em>
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

         <!--User login-->
         <ul id="nav-user-login" class="navbar-nav align-items-center ml-auto ml-md-0">
            <li class="nav-item dropdown">
               <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false">
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
            <li><button data-toggle="modal" data-target="#modal-login" class="btn btn-secondary my-1 ml-1 mr-0">
               Đăng nhập/ Tạo tài khoản
            </button></li>
         </ul>
      </div>
   </div>
</nav>

<!--Modal Sign up/Login-->
<div id="modal-login" class="modal fade show" tabindex="-1" role="dialog" aria-modal="true">
   <div class="modal-dialog modal-xl modal-dialog-scrollable modal-dialog-centered" role="document">
      <div class="modal-content">
         <div class="container">
            <div class="row">
               <!--Left side image-->
               <div class="col-md-5 d-md-inline-block d-sm-none py-3 pr-0 pl-3">
                  <img src="https://frontend.tikicdn.com/_desktop-next/static/img/graphic-map.png" alt="dd"
                       class="rounded w-100" style="height: 100%; object-fit: cover;">
               </div>

               <!--Tab-->
               <div class="col-md-7 col-sm-12">
                  <!--Tab nav-->
                  <div class="nav-wrapper py-3 text-uppercase">
                     <ul class="nav nav-pills nav-fill flex-column flex-md-row" id="tabs-icons-text" role="tablist">
                        <!--Nav button login-->
                        <li class="nav-item">
                           <a id="tabs-login-tab" class="nav-link mb-sm-3 mb-md-0 active" aria-selected="false"
                              data-toggle="tab" href="#tabs-login" role="tab" aria-controls="tabs-login">
                              <em class="fa fa-sign-in-alt mr-3"></em>Đăng nhập
                           </a>
                        </li>
                        <!--Nav button signup-->
                        <li class="nav-item">
                           <a id="tabs-signup-tab" class="nav-link mb-sm-3 mb-md-0" aria-selected="true" data-toggle="tab"
                              href="#tabs-signup" role="tab" aria-controls="tabs-signup">
                              <em class="fa fa-user-plus mr-3"></em>Tạo tài khoản
                           </a>
                        </li>
                     </ul>
                  </div>
                  <!--Tab content-->
                  <div class="card shadow p-0 mb-3" style="border-radius: 6px;">
                     <div class="card-body p-0">
                        <div class="tab-content" id="login-signup-tabcontent">
                           <!--Tab login-->
                           <div class="tab-pane fade active show" id="tabs-login" role="tabpanel"
                                aria-labelledby="tabs-login-tab">
                              <div class="row justify-content-center">
                                 <div class="col">
                                    <div class="card bg-secondary border-0 mb-0">

                                       <p class="mx-5 mt-5">
                                          Đăng nhập để theo dõi đơn hàng, lưu danh sách sản phẩm yêu thích, nhận nhiều ưu đãi hấp dẫn.
                                       </p>

                                       <div class="card-body px-5 pt-5 pb-3">
                                          <form id="form-login">
                                             <div class="form-group mb-3">
                                                <div class="input-group input-group-merge input-group-alternative">
                                                   <div class="input-group-prepend">
                                                      <span class="input-group-text"><em class="fa fa-user-alt"></em></span>
                                                   </div>
                                                   <input class="form-control" id="login-username" name="login-username" autofocus placeholder="Tên đăng nhập hoặc email" type="text" required>
                                                </div>
                                             </div>
                                             <div class="form-group">
                                                <div class="input-group input-group-merge input-group-alternative">
                                                   <div class="input-group-prepend">
                                                      <span class="input-group-text"><em class="fa fa-key"></em></span>
                                                   </div>
                                                   <input class="form-control" placeholder="Mật khẩu" type="password" name="login-password" id="login-password" required>
                                                </div>
                                             </div>

                                             <div><a href="#" class="text-primary"><small>Quên mật khẩu ?</small></a></div>

                                             <div class="text-center">
                                                <button type="submit" class="btn btn-primary my-4 px-5">Đăng nhập</button>
                                             </div>
                                          </form>
                                       </div>
                                    </div>

                                    <div class="text-center my-3">
                                       <a class="text-success" href="javascript:$('#tabs-signup-tab').trigger('click');">
                                          Tạo tài khoản
                                       </a>
                                    </div>
                                 </div>
                              </div>
                           </div>

                           <!--Tab sign up-->
                           <div class="tab-pane fade" id="tabs-signup" role="tabpanel" aria-labelledby="tabs-signup-tab">
                              <div class="row justify-content-center">
                                 <div class="col">
                                    <div class="card bg-secondary border-0 mb-0">

                                       <p class="mx-5 mt-5">
                                          Tạo tài khoản để theo dõi đơn hàng, lưu danh sách sản phẩm yêu thích, nhận nhiều ưu đãi hấp dẫn.
                                       </p>

                                       <div class="card-body px-5 pt-3 pb-0">
                                          <form id="form-signup">

                                             <div class="row">
                                                <div class="col-md-6">
                                                   <div class="form-group mb-3">
                                                      <div class="input-group input-group-merge input-group-alternative">
                                                         <div class="input-group-prepend">
                                                            <span class="input-group-text"><em class="fa fa-user-circle"></em></span>
                                                         </div>
                                                         <input class="form-control" placeholder="Họ và tên đệm" type="text" name="login-last-name" id="login-last-name" required="">
                                                      </div>
                                                   </div>
                                                </div>
                                                <div class="col-md-6">
                                                   <div class="form-group mb-3">
                                                      <div class="input-group input-group-merge input-group-alternative">
                                                         <div class="input-group-prepend">
                                                            <span class="input-group-text"><em class="fa fa-user-circle"></em></span>
                                                         </div>
                                                         <input class="form-control" placeholder="Tên" type="text" name="login-first-name" id="login-first-name" required="">
                                                      </div>
                                                   </div>
                                                </div>
                                             </div>

                                             <div class="form-group mb-3">
                                                <div class="input-group input-group-merge input-group-alternative">
                                                   <div class="input-group-prepend">
                                                      <span class="input-group-text"><em class="fa fa-envelope"></em></span>
                                                   </div>
                                                   <input class="form-control" id="signup-email" name="signup-email" autofocus="" placeholder="Email" type="text" required="">
                                                   <div class="input-group-append">
                                                      <button class="ml-1 btn btn-outline-primary" type="button" id="btn-signup-send-code" style="width: 8rem;">Gửi mã</button>
                                                   </div>
                                                </div>
                                             </div>

                                             <div class="form-group mb-3">
                                                <div class="input-group input-group-merge input-group-alternative">
                                                   <div class="input-group-prepend">
                                                      <span class="input-group-text"><em class="fa fa-keyboard"></em></span>
                                                   </div>
                                                   <input class="form-control" id="signup-verified-code" name="signup-verified-code" placeholder="Mã xác thực gửi tới email của bạn" type="text" required="">
                                                   <div class="input-group-append">
                                                      <button class="ml-1 btn btn-outline-primary" type="button" id="btn-signup-check-code" style="width: 8rem;">Kiểm tra</button>
                                                   </div>
                                                </div>
                                             </div>

                                             <div class="form-group mb-3">
                                                <div class="input-group input-group-merge input-group-alternative">
                                                   <div class="input-group-prepend">
                                                      <span class="input-group-text"><em class="fa fa-key"></em></span>
                                                   </div>
                                                   <input class="form-control" placeholder="Mật khẩu" type="password" name="signup-password" id="signup-password" required="">
                                                </div>
                                             </div>

                                             <div class="form-group mb-3">
                                                <div class="input-group input-group-merge input-group-alternative">
                                                   <div class="input-group-prepend">
                                                      <span class="input-group-text"><em class="fa fa-key"></em></span>
                                                   </div>
                                                   <input class="form-control" placeholder="Nhập lại mật khẩu" type="password" name="signup-re-password" id="signup-re-password" required="">
                                                </div>
                                             </div>

                                             <div class="text-center">
                                                <button type="submit" class="btn btn-primary my-4 px-5">Đăng ký</button>
                                             </div>
                                          </form>
                                       </div>
                                    </div>

                                    <div class="pb-5 mb-0 text-center">
                                       Đã có tài khoản. Đăng nhập
                                       <a class="text-success" href="javascript:$('#tabs-login-tab').trigger('click');">
                                          tại đây
                                       </a>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>

<script>
  $(document).ready(function() {
    console.log('ready');
  });
</script>