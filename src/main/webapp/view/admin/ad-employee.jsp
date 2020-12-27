<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                  <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Nhân viên</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 text-center text-uppercase my-5">Danh sách nhân viên</h2>
         </div>
      </div>

      <!--Button thêm-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto text-right">
            <button type="button" data-toggle="modal" data-target="#modal-add-employee" class="text-uppercase btn btn-primary pl-4 pr-4 mb-4">Thêm nhân viên</button>
         </div>
         <!-- From add employee -->
         <%@ include file="../../common/form-add-employee.jsp" %>
      </div>

      <!-- Table -->
      <div class="row">
         <div class="col m-auto table-responsive">
            <table class="table">
               <thead>
               <tr>
                  <th scope="col" class="text-center">Ảnh</th>
                  <th scope="col" class="text-center">Mã</th>
                  <th scope="col" class="text-center">Họ và tên</th>
                  <th scope="col" class="text-center">Giới tính</th>
                  <th scope="col" class="text-center">Ngày sinh</th>
                  <th scope="col" class="text-center">Số điện thoại</th>
                  <th scope="col" class="text-center">Email</th>
                  <th scope="col" class="text-center">Lương</th>
                  <th scope="col" class="text-center">Ngày bắt đầu</th>
                  <th scope="col" class="text-center">Tùy chọn</th>
               </tr>
               </thead>
               <tbody class="list" id="tb-list">
               </tbody>
            </table>
         </div>
      </div>

      <!-- Pagination -->
      <nav aria-label="...">
         <ul class="pagination justify-content-center mt-5">
            <li class="page-item disabled text-default">
               <a class="page-link" href="#" tabindex="-1">
                  <i class="fa fa-angle-left"></i>
                  <span class="sr-only">Previous</span>
               </a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active">
               <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
               <a class="page-link" href="#">
                  <i class="fa fa-angle-right"></i>
                  <span class="sr-only">Next</span>
               </a>
            </li>
         </ul>
      </nav>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
<script>
  $(document).ready(function () {
    const apiUrl = "/api/employees";

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
            '<td>' +
            '<a href="#" class="media align-items-center">' +
            '<img class="avatar rounded-circle" src="' + item.imgSrc + '" alt="avatar_image" >' +
            '</a>' +
            '</td>' +
            '<td>' + item.id + '</td>' +
            '<td>' + item.fullName + '</td>' +
            '<td>' + item.gender + '</td>' +
            '<td>' + item.dob + '</td>' +
            '<td>' + item.phone + '</td>' +
            '<td>' + item.email + '</td>' +
            '<td>' + item.salary + '</td>' +
            '<td>' + item.startDate + '</td>' +
            '<td class="td-actions text-center">' +
            '<a href="#" class="btn btn-primary px-2 py-1" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa thông tin">' +
            '<i class="fa fa-edit"></i>' +
            '</a>' +

            ( item.status === "true" ?
               '<a href="#" class="btn btn-danger px-2 py-1" data-toggle="tooltip" data-placement="top" title="Thôi việc">' +
               '<i class="fa fa-lock"></i>' +
               '</a>'
            :
               '</a>' +
               '<a href="#" class="btn btn-success px-2 py-1" data-toggle="tooltip" data-placement="top" title="Làm việc lại">' +
               '<i class="fa fa-lock-open"></i>' +
               '</a>'
            ) +

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
