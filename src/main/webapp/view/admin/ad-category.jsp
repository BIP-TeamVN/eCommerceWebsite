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
   <jsp:param name="selectedIndex" value="5"/>
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
                  <li class="breadcrumb-item active" aria-current="page">Ngành hàng</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 text-center text-uppercase my-5">Danh sách ngành hàng</h2>
         </div>
      </div>

      <!--Button thêm-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto text-right">
            <button type="button" data-toggle="modal" data-target="#modal-add-product-category"
                    class="text-uppercase btn btn-primary pl-4 pr-4 mb-4">Thêm ngành hàng
            </button>
         </div>
         <!-- From add product-category -->
         <%@ include file="../../common/form-add-product-category.jsp" %>
      </div>

      <!-- Table -->
      <div class="row">
         <div class="col-md-10 m-auto table-responsive">
            <table class="table">
               <thead>
               <tr>
                  <th scope="col" class="text-center">Mã</th>
                  <th scope="col" class="text-center">Tên ngành hàng</th>
                  <th scope="col" class="text-center">Ảnh minh họa</th>
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
         url: '/api/product-categories',
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
                       '<td>' + item.id + '</td>' +
                       '<td>' + item.name + '</td>' +
                       '<td>' +
                       '<a href="#" class="media m-auto align-items-center">' +
                       '<img class="avatar m-auto rounded-circle" src="' + item.image + '" alt="category_image" >' +
                       '</a>' +
                       '</td>' +
                       '<td class="td-actions text-center">' +
                       '<a href="/admin/product-category/edit?id=' + item.id +'"class="btn btn-primary px-2 py-1" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa thông tin">' +
                       '<i class="fa fa-edit"></i>' +
                       '</a>' +
                       '</td>' +
                       '</tr>';
               $('#tb-list').append(html);
            });
         }
      });
   }
</script>
</body>
</html>
