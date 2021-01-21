<%@ page import="com.hknp.model.entity.UserEntity" %>
<%@ page import="com.hknp.model.dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%
   HttpSession sessionHttp = request.getSession(true);
   Long id = (Long) sessionHttp.getAttribute("id");
   UserEntity user = UserDAO.getInstance().getById(id);
   String image = user.getImageSrc();
   String fullName = user.getFullName();
%>
<!-- Top nav -->
<nav class="navbar navbar-top navbar-expand navbar-dark bg-primary border-bottom">
   <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
         <!-- Search form -->
         <form class="navbar-search navbar-search-light form-inline my-auto mr-sm-3" id="navbar-search-main">
            <div class="form-group mb-0">
               <div class="input-group input-group-alternative input-group-merge">
                  <div class="input-group-prepend">
                     <span class="input-group-text"><i class="fas fa-search"></i></span>
                  </div>
                  <input class="form-control" placeholder="Tìm kiếm" type="text" id="search-keyword">
               </div>
            </div>
            <button class="close" data-action="search-close" aria-label="Close" onclick="changeStatus()">
               <span aria-hidden="true">×</span>
            </button>
         </form>
         <!-- Navbar links -->
         <ul class="navbar-nav align-items-center ml-md-auto ">
            <li class="nav-item d-xl-none">
               <!-- Sidenav toggler -->
               <div class="pr-3 sidenav-toggler sidenav-toggler-dark" data-action="sidenav-pin"
                    data-target="#sidenav-main">
                  <div class="sidenav-toggler-inner">
                     <i class="sidenav-toggler-line"></i>
                     <i class="sidenav-toggler-line"></i>
                     <i class="sidenav-toggler-line"></i>
                  </div>
               </div>
            </li>
            <li class="nav-item d-sm-none">
               <a class="nav-link" href="#" data-action="search-show" data-target="#navbar-search-main">
                  <i class="ni ni-zoom-split-in"></i>
               </a>
            </li>
            <li class="nav-item dropdown">
               <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false">
                  <i class="ni ni-bell-55"></i>
               </a>
               <div class="dropdown-menu dropdown-menu-xl  dropdown-menu-right  py-0 overflow-hidden">
                  <!-- Dropdown header -->
                  <div class="px-3 py-3">
                     <h6 class="text-sm text-muted m-0">Bạn có <strong class="text-primary">1</strong> thông báo mới.
                     </h6>
                  </div>
                  <!-- List group -->
                  <div class="list-group list-group-flush">
                     <a href="javascript:void(0)" class="list-group-item list-group-item-action">
                        <div class="row align-items-center">
                           <div class="col-auto">
                              <!-- Avatar -->
                              <img alt="Image placeholder" src="../../assets/img/theme/team-1.jpg"
                                   class="avatar rounded-circle">
                           </div>
                           <div class="col ml--2">
                              <div class="d-flex justify-content-between align-items-center">
                                 <div>
                                    <h4 class="mb-0 text-sm">Shop ABC</h4>
                                 </div>
                                 <div class="text-right text-muted">
                                    <small>2 hrs ago</small>
                                 </div>
                              </div>
                              <p class="text-sm mb-0">Có sản phẩm cần duyệt</p>
                           </div>
                        </div>
                     </a>
                  </div>
                  <!-- View all -->
                  <a href="javascript:void(0)" class="dropdown-item text-center text-primary font-weight-bold py-3">Xem tất cả</a>
               </div>
            </li>
            <li class="nav-item dropdown">
               <a class="nav-link" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false">
                  <i class="ni ni-ungroup"></i>
               </a>
               <div class="dropdown-menu dropdown-menu-lg dropdown-menu-dark bg-default  dropdown-menu-right ">
                  <div class="row shortcuts px-4">
                     <a href="javascript:void(0)" class="col-4 shortcut-item">
                        <span class="shortcut-media avatar rounded-circle bg-gradient-red">
                           <i class="ni ni-calendar-grid-58"></i>
                        </span>
                        <small>Lịch</small>
                     </a>
                     <a href="javascript:void(0)" class="col-4 shortcut-item">
                        <span class="shortcut-media avatar rounded-circle bg-gradient-orange">
                           <i class="ni ni-email-83"></i>
                        </span>
                        <small>Email</small>
                     </a>
                     <a href="javascript:void(0)" class="col-4 shortcut-item">
                        <span class="shortcut-media avatar rounded-circle bg-gradient-info">
                           <i class="ni ni-credit-card"></i>
                        </span>
                        <small>Thanh toán</small>
                     </a>
                     <a href="javascript:void(0)" class="col-4 shortcut-item">
                        <span class="shortcut-media avatar rounded-circle bg-gradient-green">
                           <i class="ni ni-books"></i>
                        </span>
                        <small>Thống kê</small>
                     </a>
                     <a href="javascript:void(0)" class="col-4 shortcut-item">
                        <span class="shortcut-media avatar rounded-circle bg-gradient-purple">
                           <i class="ni ni-pin-3"></i>
                        </span>
                        <small>Bản đồ</small>
                     </a>
                     <a href="javascript:void(0)" class="col-4 shortcut-item">
                        <span class="shortcut-media avatar rounded-circle bg-gradient-yellow">
                           <i class="ni ni-basket"></i>
                        </span>
                        <small>Cửa hàng</small>
                     </a>
                  </div>
               </div>
            </li>
         </ul>
         <ul class="navbar-nav align-items-center ml-auto ml-md-0">
            <li class="nav-item dropdown">
               <a class="nav-link pr-0" href="#" role="button" data-toggle="dropdown" aria-haspopup="true"
                  aria-expanded="false">
                  <div class="media align-items-center">
                  <span class="avatar avatar-sm rounded-circle">
                    <img alt="Image placeholder" src="<%=image%>">
                  </span>
                     <div class="media-body ml-2 d-none d-lg-block">
                        <span class="mb-0 text-sm font-weight-bold"><%=fullName%></span>
                     </div>
                  </div>
               </a>
               <div class="dropdown-menu dropdown-menu-right ">
                  <a href="/admin/information/edit" class="dropdown-item">
                     <i class="ni ni-single-02"></i>
                     <span>Tài khoản của tôi</span>
                  </a>
                  <a href="javascript:void(0)" class="dropdown-item">
                     <i class="ni ni-settings-gear-65"></i>
                     <span>Cài đặt</span>
                  </a>
                  <a href="javascript:void(0)" class="dropdown-item">
                     <i class="ni ni-calendar-grid-58"></i>
                     <span>Lịch sử</span>
                  </a>
                  <a href="javascript:void(0)" class="dropdown-item">
                     <i class="ni ni-support-16"></i>
                     <span>Hỗ trợ</span>
                  </a>
                  <a href="javascript:void(0)" class="dropdown-item" data-toggle="modal" data-target="#modal-change-password">
                     <i class="fa fa-key"></i>
                     <span>Đổi mật khẩu</span>
                  </a>
                  <div class="dropdown-divider"></div>
                  <a href="${javax.servlet.ServletRequest.getServerName()}/logout" class="dropdown-item">
                     <i class="fa fa-sign-out-alt"></i>
                     <span>Đăng xuất</span>
                  </a>
               </div>
            </li>
         </ul>
      </div>
   </div>
</nav>

<!-- Modal modal-change-password-->
<div class="modal fade bd-example-modal-lg" id="modal-change-password" tabindex="-1" role="dialog" aria-labelledby="modal-change-password" aria-hidden="true">
   <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
      <div class="modal-content">
         <div class="modal-header p-3">
            <h5 class="display-4 mx-3 my-2 text-uppercase">Thay đổi mật khẩu</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body p-3">
            <div class="row">
               <div class="col mx-3">
                  <form id="change-password-form">
                     <!--Mật khẩu hiện tại-->
                     <div class="row">
                        <div class="col-md-12">
                           <div class="form-group">
                              <label for="current-password" class="form-control-label">Mật khẩu hiện tại</label>
                              <div>
                                 <input class="form-control" placeholder="************" type="password" id="current-password" name="current-password">
                              </div>
                              <small class="error-input text-danger"></small>
                           </div>
                        </div>
                     </div>
                     <!--Mật khẩu mới-->
                     <div class="row">
                        <div class="col-md-12">
                           <div class="form-group">
                              <label for="new-password" class="form-control-label">Mật khẩu mới</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right" data-content="Tối đa 16 ký tự">?</a>
                              <div>
                                 <input class="form-control" placeholder="************" type="password" id="new-password" name="new-password" maxlength="16">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập mật khẩu mới</small>
                           </div>
                        </div>
                     </div>
                     <!--Nhập lại mật khẩu-->
                     <div class="row">
                        <div class="col-md-12">
                           <div class="form-group">
                              <label for="retype-password" class="form-control-label">Xác nhận mật khẩu</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right" data-content="Tối đa 16 ký tự">?</a>
                              <div>
                                 <input class="form-control" placeholder="************" type="password" id="retype-password" name="retype-password" maxlength="16">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập mật khẩu mới</small>
                           </div>
                        </div>
                     </div>
                  </form>
               </div>
            </div>
         </div>
         <div class="modal-footer p-3 text-uppercase">
            <button class="btn btn-secondary pl-6 pr-6" type="button" id="btn-cancel-password" data-dismiss="modal">Hủy</button>
            <button class="btn btn-primary pl-6 pr-6" type="submit" form="change-password-form">Lưu</button>
         </div>
      </div>
   </div>
</div>

<!-- Modal changed-password-successful-modal -->
<div class="modal fade" id="changed-password-successful-modal" tabindex="-1" role="dialog" aria-labelledby="conform-modal-lb"
     aria-hidden="true">
   <div class="modal-dialog" role="document">
      <div class="modal-content">
         <div class="modal-header">
            <h5 class="modal-title" id="successful-modal-lb">Thông báo</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body">
            Thay đổi mật khẩu thành công !
         </div>
         <div class="modal-footer">
            <button class="btn btn-secondary pl-6 pr-6" type="button" data-dismiss="modal">OK</button>
         </div>
      </div>
   </div>
</div>

<script src="../../assets/js/validate/validate-change-password.js"></script>