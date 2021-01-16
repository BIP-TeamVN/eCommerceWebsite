<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website - Seller</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="dh--side-nav.jsp">
   <jsp:param name="selectedIndex" value="${nav}"/>
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
                  <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                  <li class="breadcrumb-item active" aria-current="page">Danh sách đơn hàng</li>
               </ol>
            </nav>
         </div>
      </div>

      <!--Title-->
      <div class="row">
         <div class="col-md-10 ml-auto mr-auto">
            <h2 class="display-3 text-center text-uppercase my-5" id="title">Danh sách đơn hàng đang giao của bạn</h2>
         </div>
      </div>

      <!-- From add product-category -->
      <%@ include file="../../common/form-add-product-category.jsp" %>
   </div>
   <!-- Table -->
   <div class="row">
      <div class="col ml--3 table-responsive">
         <table class="table">
            <thead>
            <tr>
               <th scope="col" class="text-center">Mã đơn hàng</th>
               <th scope="col" class="text-center">Tên khách hàng</th>
               <th scope="col" class="text-center">Số điện thoại</th>
               <th scope="col" class="text-center">Địa chỉ</th>
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
  let type = <%=request.getParameter("type")%>;
  if(type == 4)
  {
    $('#title').html("Danh sách đơn hàng của bạn đang đợi nhận hàng");
  }
  else if(type == 5)
  {
    $('#title').html("Danh sách đơn hàng đang giao của bạn");
  }
  else if(type == 6)
  {
    $('#title').html("Danh sách đơn hàng đã giao xong của bạn");
  }
  else if(type == 2)
  {
    $('#title').html("Danh sách đơn hàng");
  }

  $(document).ready(function () {
    const apiUrl = "/api/deliverybilldelivering";

    $.ajax({
      url: apiUrl,
      method: 'GET',
      data: {
        page: '1',
        type: ${type},
      },
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);

        $.each(list, function (index, item) {
          let html =
            '<tr id="hay'+item.id+'">' +
            '<td>' + item.id + '</td>' +
            '<td>' + item.fullName + '</td>' +
            '<td>' + item.phone + '</td>' +
            '<td>' + item.fullAddress + '</td>' +
            '<td class="td-actions text-center">' +
            (item.status === "5" ?
            '<button class="btn btn-primary pl-2 pr-2" onclick="DoneBill('+ item.id +')">Giao Thành công' +
              '</button>' +
              '<button class="btn btn-primary pl-2 pr-2" onclick="FailBill('+ item.id +')">Giao thất bại' +
              '</button>' : (item.status === "2" ?
              '<button class="btn btn-primary pl-2 pr-2" onclick="GetBill('+ item.id +')">Nhận đơn' +
              '</button>' : (item.status === "4" ?
                  '<button class="btn btn-primary pl-2 pr-2" onclick="RecieveBill('+ item.id +')">Nhận hàng' +
                  '</button>'   :
                  '<label class="btn btn-danger px-2 py-1 mt-2" title="Đã giao" id="status-' + item.id + '">' +
                  '<i class="fa fa-check-square"></i>' +
                  '</label>' ))) +
            '</td>' +
            '</tr>';
          console.log(html);
          $('#tb-list').append(html);
          //alert(item.status);
        });
      },
      cache: false
    });
  });
</script>
<script>
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
      }
    })
    alert("Xác nhận đã giao thành công!");
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
      }
    })
    alert("Xác nhận giao thất bại!");
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
      }
    })
    alert("Nhận hàng thành công!");
  }
</script>
</body>
</html>
