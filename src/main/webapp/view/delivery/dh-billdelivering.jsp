<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Kênh giao hàng</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="./dh--side-nav.jsp">
   <jsp:param name="selectedIndex" value="${nav}"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./dh--top-nav.jsp" %>

   <!--Header and breadcrumb-->
   <div class="header bg-primary pb-6">
      <div class="container-fluid">
         <div class="header-body">
            <div class="row align-items-center py-4">
               <div class="col-lg-6 col-7">
                  <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
                     <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                        <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Danh sách đơn hàng</li>
                     </ol>
                  </nav>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- From add employee -->
   <%@ include file="../../common/form-add-employee.jsp" %>

   <!-- Page content -->
   <div class="container-fluid mt--6">
      <!--List employee card-->
      <div class="row">
         <div class="col">
            <div class="card">
               <!-- Card header -->
               <div class="card-header border-0">
                  <h2 class="mb-0 text-center text-uppercase display-4" id="title">Danh sách đơn hàng</h2>
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
                        <th scope="col" class="text-center">Mã đơn hàng</th>
                        <th scope="col" class="text-center">Tên khách hàng</th>
                        <th scope="col" class="text-center">Số điện thoại</th>
                        <th scope="col" class="text-center">Địa chỉ</th>
                        <th scope="col" class="text-center">Tổng giá trị</th>
                        <th scope="col" class="text-center">Tùy chọn</th>
                     </tr>
                     </thead>
                     <tbody class="list" id="tb-list">
                     </tbody>
                  </table>
               </div>

               <!-- Card footer -->
               <div class="card-footer py-2">
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
  let type_bill = ${type};

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

  console.log(type_bill);
  if(type_bill === 4)
  {
    $('#title').html('Danh sách đơn hàng đang đợi nhận hàng');
  }
  else if(type_bill === 5)
  {
    $('#title').html('Danh sách đơn hàng đang giao');
  }
  else if(type_bill === 6)
  {
    $('#title').html('Danh sách đơn hàng đã giao xong');
  }
  else if(type_bill === 2)
  {
    $('#title').html('Danh sách đơn hàng');
  }



  function reloadPage() {
    updatePagination();


    const apiUrl = "/api/delivery-bill-delivering";

    console.log(type_bill);
    $.ajax({
      url: apiUrl,
      method: 'GET',
      data: {
        page: currentPage,
        type: type_bill,
      },
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
        $('#tb-list').find('tr').remove();
        $.each(list, function (index, item) {
          let html =
            '<tr id="hay'+item.id+'">' +
            '<td>' + item.id + '</td>' +
            '<td>' + item.fullName + '</td>' +
            '<td>' + item.phone + '</td>' +
            '<td>' + item.fullAddress + '</td>' +
            '<td>' + item.total + '</td>' +
            '<td class="td-actions text-center">' +
            (item.status === "5" ?
              '<button class="btn btn-primary px-4 py-2" title="Đơn giao thành công" onclick="DoneBill('+ item.id +')">' +
              '<i class="fa fa-check"></i>' +
              '</button>' +
              '<button class="btn btn-primary px-4 py-2" title="Đơn giao thất bại" onclick="FailBill('+ item.id +')">' +
              '<i class="fa fa-window-close"></i>' +
              '</button>' : (item.status === "2" ?
                '<button class="btn btn-primary px-4 py-2" title="Nhận đơn" onclick="GetBill('+ item.id +')">' +
                '<i class="fa fa-plus-circle"></i>' : (item.status === "4" ?
                  '<button class="btn btn-primary px-4 py-2" title="Nhận hàng" onclick="RecieveBill('+ item.id +')">' +
                  '<i class="fa fa-cart-arrow-down"></i>' +
                  '</button>'   :
                  '<label class="btn btn-danger px-4 py-2 mt-4" title="Đã giao" id="status-' + item.id + '">' +
                  '<i class="fa fa-check-square"></i>' +
                  '</label>' ))) +
            '</td>' +
            '</tr>';
          console.log(html);
          $('#tb-list').append(html);
        });
      },
      cache: false
    });
  }
</script>

<script>
  function GetBill(billId){
    let paras = JSON.stringify({
      'id': billId.toString(),
      'status': 4
    })
    $.ajax({
      url: "/api/bill/view/detail",
      method: 'PUT',
      cache: false,
      data: paras,
      success: function (){
        $("#hay" + billId).remove();
        reloadPage();
      }
    })
    showMessageModal('fa fa-check text-success', 'Thông báo', 'Nhận đơn thành công !');
  }
  function DoneBill(billId){
    let paras = JSON.stringify({
      'id': billId.toString(),
      'status': 6
    })
    $.ajax({
      url: "/api/bill/view/detail",
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (){
        $("#hay" + billId).remove();
        reloadPage();
      }
    })
    showMessageModal('fa fa-check text-success', 'Thông báo', 'Xác nhận đã giao thành công!');
  }
  function FailBill(billId){
    let paras = JSON.stringify({
      'id': billId.toString(),
      'status': 7
    })
    $.ajax({
      url: "/api/bill/view/detail",
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (){
        $("#hay" + billId).remove();
        reloadPage();
      }
    })
    showMessageModal('fa fa-exclamation text-warning', 'Thông báo', 'Xác nhận giao thất bại thành công !');
  }
  function RecieveBill(billId){
    let paras = JSON.stringify({
      'id': billId.toString(),
      'status': 5
    })
    $.ajax({
      url: "/api/bill/view/detail",
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (){
        $("#hay" + billId).remove();
        reloadPage();
      }
    })
    showMessageModal('fa fa-check text-success', 'Thông báo', 'Nhận hàng thành công !');
  }
</script>
<script src="../../assets/js/validate/validate-get-bill-for-delivery.js"></script>
</body>
</html>
