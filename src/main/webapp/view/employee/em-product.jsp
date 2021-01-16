<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website - Admin</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="em--side-nav.jsp">
   <jsp:param name="selectedIndex" value="6"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="../../common/em-top-nav.jsp" %>

   <!-- Page content -->
   <div class="container-fluid">
      <!-- Breadcrumb -->
      <div class="row mt-4">
         <div class="col-md-10 ml-auto mr-auto">
            <nav aria-label="breadcrumb" role="navigation">
               <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="/seller"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Sản phẩm</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 text-center text-uppercase my-5">Danh sách Sản phẩm</h2>
         </div>
      </div>

      <%@ include file="/common/product-filter.jsp" %>

      <!-- Table -->
      <div class="row">
         <div class="col m-auto table-responsive">
            <table class="table">
               <thead>
               <tr>
                  <th scope="col" class="text-center">Ảnh</th>
                  <th scope="col" class="text-center">Mã</th>
                  <th scope="col" class="text-center">Tên sản phẩm</th>
                  <th scope="col" class="text-center">Nhãn hiệu</th>
                  <th scope="col" class="text-center">Cửa hàng</th>
                  <th scope="col" class="text-center">Đánh giá</th>
                  <th scope="col" class="text-center">Nước sản xuất</th>
                  <th scope="col" class="text-center">Ngày tạo</th>
                  <th scope="col" class="text-center">Giá bán</th>
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
    console.log(totalPage);
    console.log(currentPage);
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
        'status': $('#status').val()
      },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        console.log(data);
        let list = $.parseJSON(data);
        console.log(list);
        $('#tb-list').find('tr').remove();
        $.each(list, function (index, item) {
          console.log(item.status);
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
            '<button class="btn btn-primary px-2 py-1" onclick="showProductDetail('+ item.id +')">' +
            '<i class="fa fa-edit"></i>' +
            '</button>' +
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
        });
      }
    });
  }
</script>
<script>
  let id;
   function showProductDetail(idPara) {
     id = idPara;
     console.log(id);
     $.ajax({
       url: '/employee/product/detail',
       method: 'GET',
       data: {'id': id},
       cache: false,
       success: function (data) {
         let list = JSON.parse(data)[0];
         $('#modal-product-detail').modal('show');
         $('#id').html('<b>Mã:</b> ' + list.id);
         $('#product-name').html('<b>Tên sản phẩm:</b> ' + list.productName);
         $('#brand').html('<b>Nhãn hiệu:</b> ' + list.brand);
         $('#product-origin').html('<b>Nơi sản xuất:</b> ' + list.productOrigin);
         $('#product-desc').html('<b>Mô tả:</b> <br>' + list.productDesc);
         $('#price-order').html('<b>Nơi sản xuất:</b> ' + list.priceOrder);
         $('#price-origin').html('<b>Nơi sản xuất:</b> ' + list.priceOrigin);
         setImage("img-upload-0", list.image0);
         setImage("img-upload-1", list.image1);
         setImage("img-upload-2", list.image2);
         setImage("img-upload-3", list.image3);
         setImage("img-upload-4", list.image4);
         countPara = list.countType;
         typesPara = list.types.split('@$3,');
         quantitiesPara = list.quantities.split('@$3,');
         imagesPara = list.images.split('@$3,');
         $('#categories').html('<b>Ngành hàng: </b>' + list.categories);
         addType();
         showButton();
       }
     });
   }
</script>
<script>
  function setImage(id, src) {
      $('#' + id).attr('src',src);
  }
</script>
<%@ include file="./em-product-detail.jsp" %>
</body>
</html>

