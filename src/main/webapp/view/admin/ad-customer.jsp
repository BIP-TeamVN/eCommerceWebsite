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
   <jsp:param name="selectedIndex" value="4"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./ad--top-nav.jsp" %>

   <!-- Page content -->
   <div class="container-fluid">
      <!-- Breadcrumb -->
      <div class="row mt-4">
         <div class="col-md-10 ml-auto mr-auto">
            <nav aria-label="breadcrumb" role="navigation">
               <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Khách hàng</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 text-center text-uppercase my-5">Danh sách khách hàng</h2>
         </div>
      </div>

      <!-- Table -->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto table-responsive">
            <table class="table align-items-center">
               <thead class="thead-light">
               <tr>
                  <th scope="col" class="sort text-center">Ảnh</th>
                  <th scope="col" class="sort text-center">Mã</th>
                  <th scope="col" class="sort text-center">Họ và tên</th>
                  <th scope="col" class="sort text-center">Giới tính</th>
                  <th scope="col" class="sort text-center">Ngày sinh</th>
                  <th scope="col" class="sort text-center">Số điện thoại</th>
                  <th scope="col" class="sort text-center">Email</th>
                  <th scope="col" class="sort text-center">Ngày đăng ký</th>
               </tr>
               </thead>
               <tbody class="list" id="tb-list">

               </tbody>
            </table>
         </div>
      </div>

      <!-- Pagination -->
      <nav aria-label="...">
         <ul id="page-pagination" class="pagination justify-content-center mt-3">
            <li class="page-item">
               <button type="button" class="page-link" onclick="goPrev()">
                  <i class="fa fa-angle-left"></i>
                  <span class="sr-only">Trang trước</span>
               </button>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active">
               <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
               <button type="button" class="page-link" onclick="goNext()">
                  <i class="fa fa-angle-right"></i>
                  <span class="sr-only">Trang sau</span>
               </button>
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
   let firstPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goFirst()"><i class="fa fa-angle-double-left"></i><span class="sr-only">Trang đầu tiên</span></button></li>';
   let prevPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goPrev()"><i class="fa fa-angle-left"></i><span class="sr-only">Trang trước</span></button></li>';
   let nextPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goNext()"><i class="fa fa-angle-right"></i><span class="sr-only">Trang sau</span></button></li>';
   let lastPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goLast()"><i class="fa fa-angle-double-right"></i><span class="sr-only">Trang cuối</span></button></li>';

   let totalPage = ${totalPage};
   let currentPage = ${currentPage};

   reloadPage();

   function goFirst() {
      if(currentPage > 1) {
         currentPage = 1;
         reloadPage();
      }
   }

   function goPrev() {
      if(currentPage > 1) {
         currentPage = currentPage - 1;
         reloadPage();
      }
   }

   function goNext() {
      if(currentPage < totalPage) {
         currentPage = currentPage + 1;
         reloadPage();
      }
   }

   function goLast() {
      if(currentPage < totalPage) {
         currentPage = totalPage;
         reloadPage();
      }
   }

   function goToPage(page) {
      currentPage = page;
      reloadPage();
   }

   function updatePagination() {
      $('#page-pagination').find('li').remove();

      $('#page-pagination').append(currentPage > 2 ? firstPageButton : '');
      $('#page-pagination').append(currentPage > 1 ? prevPageButton : '');

      let startIndex = currentPage - 3 > 1 ? currentPage - 3 : 1;
      for (let i = startIndex; i < currentPage; i++) {
         $('#page-pagination').append('<li class="page-item"><button type="button" class="page-link" onclick="goToPage(' + i + ')">' + i + '</but></li>');
      }

      $('#page-pagination').append('<li class="page-item active"><a class="page-link" href="javascript:void(0)">' + currentPage + '</a></li>');

      for (let i = currentPage + 1; i < currentPage + 4 && i <= totalPage; i++) {
         $('#page-pagination').append('<li class="page-item"><button type="button" class="page-link" onclick="goToPage(' + i + ')">' + i + '</but></li>');
      }

      $('#page-pagination').append(currentPage < totalPage ? nextPageButton : '');
      $('#page-pagination').append(currentPage < totalPage - 1 ? lastPageButton : '');
   }

   function reloadPage() {
      updatePagination();

      $.ajax({
         url: '/api/customers',
         method: 'GET',
         data: { page: currentPage },
         cache: false,
         success: function (data, textStatus, jqXHR) {
            let list = $.parseJSON(data);
            console.log(list);
            $('#tb-list').find('tr').remove();
            $.each(list, function (index, item) {
               let html =
                       '<tr>' +
                       '<td>' +
                       '<a href="#" class="media align-items-center">' +
                       '<img class="m-auto avatar rounded-circle" src="' + item.imgSrc + '" alt="avatar_image" >' +
                       '</a>' +
                       '</td>' +
                       '<td>' + item.id + '</td>' +
                       '<td>' + item.fullName + '</td>' +
                       '<td>' + item.gender + '</td>' +
                       '<td>' + item.dob + '</td>' +
                       '<td>' + item.phone + '</td>' +
                       '<td>' + item.email + '</td>' +
                       '<td>' + item.registerDate + '</td>' +
                       '</tr>';
               $('#tb-list').append(html);
            });
         }
      });
   }
</script>
</body>
</html>
