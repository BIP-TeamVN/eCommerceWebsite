<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website - Admin</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<%@ include file="../../common/ad-side-nav.jsp" %>

<jsp:include page="../../common/ad-side-nav.jsp">
   <jsp:param name="selectedIndex" value="1"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="../../common/ad-top-nav.jsp" %>

   <!-- Page content -->
   <div class="container-fluid">
      <!-- Breadcrumb -->
      <div class="row mt-4">
         <div class="col-md-10 ml-auto mr-auto">
            <nav aria-label="breadcrumb" role="navigation">
               <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="#"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                  <li class="breadcrumb-item"><a href="#">Nhân viên</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Thêm nhân viên</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 page-title">Thêm nhân viên mới</h2>
         </div>
      </div>

      <!-- Form -->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <form id="add-employee-form" action="/admin/employee/add" method="get">
               <!--Họ và tên-->
               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="last-name" class="form-control-label">Họ và tên đệm</label>
                        <a href="javascript:void(0)" class="badge badge-primary" data-toggle="popover"
                           data-placement="right" data-content="Trường bắt buộc - Tối đa 40 ký tự">?</a>
                        <input class="form-control" type="text" placeholder="VD: Nguyễn Văn" id="last-name"
                               maxlength="40" required>
                        <small class="error-input text-danger">Vui lòng nhập họ và tên đệm</small>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="first-name" class="form-control-label">Tên</label>
                        <a href="javascript:void(0)" class="badge badge-primary" data-toggle="popover"
                           data-placement="right"
                           data-content="Trường bắt buộc - Tối đa 10 ký tự">?</a>
                        <input class="form-control" type="text" placeholder="VD: A" id="first-name" maxlength=10
                               required>
                        <small class="error-input text-danger">Vui lòng nhập tên</small>
                     </div>
                  </div>
               </div>

               <!--Giới tính và ngày sinh-->
               <div class="row">
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="gender" class="form-control-label">Giới tính</label>
                        <select class="form-control" id="gender" required>
                           <option value="M">Nam</option>
                           <option value="F">Nữ</option>
                           <option value="O" selected>Khác</option>
                        </select>
                     </div>
                  </div>
                  <div class="col-md-6">
                     <div class="form-group">
                        <label for="dt-dob" class="form-control-label">Ngày sinh (không bắt buộc)</label>
                        <input class="form-control" type="date" min="1900-01-01" id="dt-dob">
                     </div>
                  </div>
               </div>

               <!--SDT-->
               <div class="form-group">
                  <label for="phone-number" class="form-control-label">Số điện thoại</label>
                  <a href="javascript:void(0)" class="badge badge-primary" data-toggle="popover" data-placement="right"
                     data-content="10 số, bắt đầu từ số 0">?</a>
                  <input class="form-control" type="tel" id="phone-number" maxlength="10" placeholder="VD: 0987654321"
                         required>
                  <small class="error-input text-danger">Số điện thoại không hợp lệ</small>
               </div>

               <!--Tỉnh/ thành phố-->
               <div class="form-group">
                  <label for="province" class="form-control-label">Tỉnh/ Thành phố</label>
                  <select class="form-control" id="province" required>
                     <option value="00">Chọn tỉnh/ thành phố</option>
                  </select>
                  <small class="error-input text-danger">Vui lòng chọn tỉnh/ thành phố</small>
               </div>

               <!--Quận/ huyện-->
               <div class="form-group">
                  <label for="district" class="form-control-label">Quận/ Huyện</label>
                  <select class="form-control" id="district" required>
                     <option value="000">Chọn quận/ huyện</option>
                  </select>
                  <small class="error-input text-danger">Vui lòng chọn quận/ huyện</small>
               </div>

               <!--Xã phường-->
               <div class="form-group">
                  <label for="commune" class="form-control-label">Xã/ Phường</label>
                  <select class="form-control" id="commune" required>
                     <option value="00000">Chọn xã/ phường</option>
                  </select>
                  <small class="error-input text-danger">Vui lòng chọn xã/ phường</small>
               </div>

               <!--Số nhà-->
               <div class="form-group">
                  <label for="address-street" class="form-control-label">Tên đường số nhà</label>
                  <textarea required class="form-control" id="address-street" placeholder="VD: Số 1 Đường ABC"
                            rows="2"></textarea>
                  <small class="error-input text-danger">Vui lòng nhập địa chỉ giao hàng</small>
               </div>

               <!--CMND-->
               <div class="form-group">
                  <label for="ssn" class="form-control-label">Căn cước công dân</label>
                  <a href="javascript:void(0)" class="badge badge-primary" data-toggle="popover" data-placement="right"
                     data-content="9 hoặc 12 số">?</a>
                  <input class="form-control" type="text" id="ssn" maxlength="12"
                         placeholder="VD: 123456789 hoặc 123456789012" required>
                  <small class="error-input text-danger">Vui lòng nhập địa chỉ giao hàng</small>
               </div>

               <!--Mail-->
               <div class="form-group">
                  <label for="email" class="form-control-label">Email</label>
                  <input class="form-control" type="email" maxlength="40" placeholder="VD: user@gmail.com" id="email">
                  <small class="error-input text-danger">Email sai định dạng</small>
               </div>

               <!--Ảnh-->
               <div class="form-group">
                  <label for="up-image" class="form-control-label">Ảnh</label>
                  <div class="custom-file">
                     <input type="file" class="custom-file-input" id="up-image" accept="image/*" lang="vi">
                     <label class="custom-file-label custom-file-img-label" for="up-image">Select file</label>
                  </div>
               </div>

               <!--Button-->
               <div class="row mt-6">
                  <div class="col-md-6 text-md-right text-center">
                     <button type="submit" class="btn btn-primary pl-6 pr-6">LƯU</button>
                  </div>
                  <div class="col-md-6 text-md-left text-center">
                     <a href="/admin/employee" class="btn btn-secondary pl-6 pr-6">HỦY</a>
                  </div>
               </div>
            </form>
         </div>
      </div>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
<script src="../../assets/js/dynamic-admin-unit-drop-down.js"></script>
<script src="../../assets/js/validate/add-employee-form.js"></script>
</body>
</html>
