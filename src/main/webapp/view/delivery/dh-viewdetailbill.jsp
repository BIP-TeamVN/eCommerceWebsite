<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Kênh giao hàng</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="dh--side-nav.jsp">
   <jsp:param name="selectedIndex" value="1"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./dh--top-nav.jsp" %>

   <!-- Page content -->
   <div class="container-fluid">
      <!-- Breadcrumb -->
      <div class="row mt-4">
         <div class="col-md-10 ml-auto mr-auto">
            <nav aria-label="breadcrumb" role="navigation">
               <ol class="breadcrumb">
                  <li class="breadcrumb-item"><a href="/delivery"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Giao hàng</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 text-center text-uppercase my-5">Chi tiết đơn hàng</h2>
         </div>
      </div>

         <!-- From add product-category -->
         <div class="row">
            <div class="col-md-6">
               <div class="form-group">
                  <label for="total" class="form-control-label">Tổng giá trị đơn hàng</label>
                  <div>
                     <input class="form-control" type="text" id="total"
                            name="total" maxlength="40" value="${total}">
                  </div>
               </div>
            </div>
            <div class="col-md-6">
               <div class="form-group">
                  <label for="discount" class="form-control-label">Giảm giá</label>
                  <input class="form-control" type="text" id="discount" name="discount" maxlength=10 value="${discount}">
               </div>
            </div>
         </div>
         <%@ include file="../../common/form-add-seller.jsp" %>
      </div>

      <!-- Table -->
      <div class="row">
         <div class="col m-auto table-responsive">
            <table class="table">
               <thead>
               <tr>
                  <th scope="col" class="text-center">Mã sản phẩm</th>
                  <th scope="col" class="text-center">Ảnh sản phẩm</th>
                  <th scope="col" class="text-center">Tên sản phẩm</th>
                  <th scope="col" class="text-center">Màu sắc - Size</th>
                  <th scope="col" class="text-center">Số lượng</th>
                  <th scope="col" class="text-center">Giá tiền/ 1 sản phẩm</th>
                  <th scope="col" class="text-center">Thành tiền</th>
               </tr>
               </thead>
               <form id="getbill">
                  <tbody class="list" id="tb-list">
                  </tbody>
               </form>
            </table>
         </div>
      </div>
      <div class="modal-footer p-3 text-uppercase">
         <button class="btn btn-secondary pl-6 pr-6" type="button" id="btn-cancel" data-dismiss="modal">Trở về</button>
         <button class="btn btn-primary pl-6 pr-6" type="submit" form="getbill">Nhận đơn</button>
      </div>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>

<script>
  function getbill(){
    showMessageModal('fa fa-check text-success', 'Thông báo', 'Nhận đơn thành công !', 'OK', () => {
      $('#tb-list').trigger("reset");
    });
  }
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
      url: '/api/bill/view/detail',
      method: 'GET',
      data: { id: totalPage },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
        $('#tb-list').find('tr').remove();
        $.each(list, function (index, item) {
          let html =
            '<tr>' +
            '<td>' + item.id + '</td>' +
            '<td>' +
            '<a href="#" class="media align-items-center">' +
            '<img class="m-auto avatar rounded-circle" src="' + item.productImage + '" alt="avatar_image" >' +
            '</a>' +
            '</td>' +
            '<td>' + item.productName + '</td>' +
            '<td>' + item.productTypeName + '</td>' +
            '<td>' + item.quantity + '</td>' +
            '<td>' + item.price + '</td>' +
            '<td>' + item.Amount + '</td>' +
            '</tr>';
          $('#tb-list').append(html);
        });
      }
    });
  }
</script>
</body>
</html>