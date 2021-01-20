<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website</title>
   <%@ include file="../../common/link-css.jsp" %>
   <!--Javascript-->
   <%@ include file="../../common/import-js.jsp" %>
</head>

<body>
<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./--top-nav.jsp" %>

   <!-- Page content -->
   <div class="container">
      <!--Filter-->
      <div class="row">
         <div class="col">
            <div class="card">
               <!-- Card header -->
               <div class="card-header border-0">
                  <h2 class="mb-0 text-center text-uppercase display-4">Danh sách địa chỉ</h2>
                  <div class="col-lg-12 col-5 text-right">
                     <button type="button" data-toggle="modal" data-target="#modal-add-seller"
                             href="#" class="btn btn-secondary text-uppercase">Thêm cửa hàng mới
                     </button>
                  </div>
               </div>

               <!--Filter-->
               <%@ include file="../../common/form-add-address-customer.jsp" %>

               <!--Loading-->
               <div id="loading" class="d-none">
                  <p class="text-dark text-center">Đang load dữ liệu</p>
                  <div class="dots-loading">
                     <div></div>
                     <div></div>
                     <div></div>
                     <div></div>
                  </div>
               </div>
               <!-- Light table -->
               <div class="table-responsive">
                  <table class="table align-items-center table-flush">
                     <thead class="thead-light">
                     <tr>
                        <th scope="col" class="text-center">Mã địa chỉ</th>
                        <th scope="col" class="text-center">Địa chỉ</th>
                        <th scope="col" class="text-center">Mã người dùng</th>
                        <th scope="col" class="text-center">Họ và tên</th>
                        <th scope="col" class="text-center">Loại địa chỉ</th>
                        <th scope="col" class="text-center">Số điện thoại</th>
                     </tr>
                     </thead>
                     <tbody class="list" id="tb-list">
                     </tbody>
                  </table>
               </div>

               <!-- Card footer -->
            </div>
         </div>
      </div>

      <!--Product-->

      <!--Scroll to top button-->
      <button onclick="window.scrollTo({ top: 0, left: 0, behavior: 'smooth' });" class="btn btn-icon-only btn-primary btn-circle btn-float">
         <em class="fa fa-arrow-up"></em>
      </button>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>
<script>
   function reloadPage(){
     $.ajax({
       url: '/api/info/address',
       method: 'GET',
       cache: false,
       beforeSend: function(){
         $('#loading').removeClass('d-none');
         $('div.table-responsive').addClass('d-none');
       },
       success: function (data, textStatus, jqXHR) {
         let list = $.parseJSON(data);
         $('#tb-list').find('tr').remove();
         $.each(list, function (index, item) {
           let html =
             '<tr>' +
             '<td>' + item.addressId + '</td>' +
             '<td>' + item.fullAddress + '</td>' +
             '<td>' + item.userId + '</td>' +
             '<td>' + item.fullName + '</td>' +
             '<td>' + item.addressName + '</td>' +
             '<td>' + item.phoneNumber + '</td>';
           $('#tb-list').append(html);
         });
       },
       complete: function () {
         $('#loading').addClass('d-none');
         $('div.table-responsive').removeClass('d-none');
       }
     });
   }

   reloadPage();
</script>
</body>
</html>