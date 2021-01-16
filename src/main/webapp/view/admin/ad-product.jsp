<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   <jsp:param name="selectedIndex" value="6"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./ad--top-nav.jsp" %>

   <!--Header and breadcrumb-->
   <div class="header bg-primary pb-6">
      <div class="container-fluid">
         <div class="header-body">
            <div class="row align-items-center py-4">
               <div class="col-lg-6 col-7">
                  <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
                     <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                        <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Sản phẩm</li>
                     </ol>
                  </nav>
               </div>
               <%@ include file="/common/product-filter.jsp" %>
            </div>
         </div>
      </div>
   </div>

   <!-- Page content -->
   <div class="container-fluid mt--6">

      <!--List employee card-->
      <div class="row">
         <div class="col">
            <div class="card">
               <!-- Card header -->
               <div class="card-header border-0">
                  <h2 class="mb-0 text-center text-uppercase display-4">Danh sách Sản phẩm</h2>
               </div>

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
                        <th scope="col" class="text-center">Ảnh</th>
                        <th scope="col" class="text-center">Mã</th>
                        <th scope="col" class="text-center">Tên sản phẩm</th>
                        <th scope="col" class="text-center">Nhãn hiệu</th>
                        <th scope="col" class="text-center">Nhà cung cấp</th>
                        <th scope="col" class="text-center">Đánh giá</th>
                        <th scope="col" class="text-center">Xuất xứ</th>
                        <th scope="col" class="text-center">Ngày tạo</th>
                        <th scope="col" class="text-center">Giá bán</th>
                        <th scope="col" class="text-center">Tùy chọn</th>
                     </tr>
                     </thead>
                     <tbody class="list" id="tb-list">
                     </tbody>
                  </table>
               </div>

               <!-- Card footer -->
               <div class="card-footer py-3">
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
               </div>
            </div>
         </div>
      </div>

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
    if (currentPage > 1) {
      currentPage = 1;
      reloadPage();
    }
  }

  function goPrev() {
    if (currentPage > 1) {
      currentPage = currentPage - 1;
      reloadPage();
    }
  }

  function goNext() {
    if (currentPage < totalPage) {
      currentPage = currentPage + 1;
      reloadPage();
    }
  }

  function goLast() {
    if (currentPage < totalPage) {
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
      url: '/api/product',
      method: 'GET',
      data: {
        'page': currentPage,
        'status': $('#status').val(),
        'keyword': $('#search-keyword').val()
      },
      cache: false,
      beforeSend: function(){
        $('#loading').removeClass('d-none');
        $('div.table-responsive').addClass('d-none');
      },
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
        $('#tb-list').find('tr').remove();
        $.each(list, function (index, item) {
          let html =
            '<tr>' +
            '<td>' +
            '<a href="#" class="media align-items-center">' +
            '<img class="m-auto avatar rounded-circle" src="' + item.image0 + '" alt="product_image" >' +
            '</a>' +
            '</td>' +
            '<td>' + item.id + '</td>' +
            '<td>' + item.productName + '</td>' +
            '<td>' + item.brand + '</td>' +
            '<td>' + item.seller + '</td>' +
            '<td>' + item.productRate + '</td>' +
            '<td>' + item.productOrigin + '</td>' +
            '<td>' + item.createDate + '</td>' +
            '<td>' + item.priceOrder + '</td>' +
            '<td class="td-actions text-center">' +
            (item.status === "0" ?
              '<label class="btn btn-danger px-2 py-1 mt-2" title="Chưa xác nhận" id="status-' + item.id + '">' +
              '<i class="fa fa-lock"></i>' +
              '</label>' : (item.status === "1" ?
                '<label class="btn btn-success px-2 py-1 mt-2" title="Đã xác nhận" id="status-' + item.id + '">' +
                '<i class="fa fa-lock-open"></i>' +
                '</label>' :
                '<label class="btn btn-warning px-2 py-1 mt-2" title="Đã từ chối" id="status-' + item.id + '">' +
                '<i class="fa fa-exclamation-triangle"></i>' +
                '</label>')) +
            '</td>' +
            '</tr>';
          $('#tb-list').append(html);

          $('#loading').addClass('d-none');
          $('div.table-responsive').removeClass('d-none');
        });
      }
    });
  }
</script>
</body>
</html>
