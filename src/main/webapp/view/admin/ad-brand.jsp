<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website - Admin</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="./ad--side-nav.jsp">
   <jsp:param name="selectedIndex" value="7"/>
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
                  <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Thương hiệu</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 text-center text-uppercase my-5">Danh sách thương hiệu</h2>
         </div>
      </div>

      <!--Button thêm-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto text-right">
            <button type="button" data-toggle="modal" data-target="#modal-add-brand"
                    class="text-uppercase btn btn-primary pl-4 pr-4 mb-4">Thêm thương hiệu
            </button>
         </div>
         <!-- From add product-category -->
         <%@ include file="../../common/form-add-brand.jsp" %>
      </div>

      <!-- Table -->
      <div class="row">
         <div class="col ml--3 table-responsive">
            <table class="table">
               <thead>
               <tr>
                  <th scope="col" class="text-center">Mã thương hiệu</th>
                  <th scope="col" class="text-center">Tên thương hiệu</th>
                  <th scope="col" class="text-center">Xuất xứ</th>
                  <th scope="col" class="text-center">Ảnh minh hoại</th>
                  <th scope="col" class="text-center">Tùy chọn</th>
               </tr>
               </thead>
               <tbody class="list" id="tb-list">
               </tbody>
            </table>
         </div>
      </div>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
<script>
   $(document).ready(function () {
      const apiUrl = "/api/brands";

      $.ajax({
         url: apiUrl,
         method: 'GET',
         data: {
            page: '1',
         },
         success: function (data, textStatus, jqXHR) {
            let list = $.parseJSON(data);
            console.log(list);

            $.each(list, function (index, item) {
               let html =
                       '<tr>' +
                       '<td>' + item.id + '</td>' +
                       '<td>' + item.brandName + '</td>' +
                       '<td>' + item.brandOrigin + '</td>' +
                       '<td>' +
                       '<a href="#" class="media m-auto align-items-center">' +
                       '<img class="avatar m-auto rounded-circle" src="' + item.image + '" alt="product-category_image" >' +
                       '</a>' +
                       '</td>' +
                       '<td class="td-actions text-center">' +
                       '<a href="#" class="btn btn-primary px-2 py-1" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa thông tin">' +
                       '<i class="fa fa-edit"></i>' +
                       '</a>' +
                       '</td>' +
                       '</tr>';
               $('#tb-list').append(html);
            });
         },
         cache: false
      });
   });
</script>
</body>
</html>
