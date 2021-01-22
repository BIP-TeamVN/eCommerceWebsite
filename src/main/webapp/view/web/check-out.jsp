<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Mua hàng online giá tốt, hàng chuẩn, ship bao nhanh</title>
   <%@ include file="../../common/link-css.jsp" %>
   <!--Javascript-->
   <%@ include file="../../common/import-js.jsp" %>
   <script src="../../assets/vendor/bootstrap-input-spinner/src/bootstrap-input-spinner.js"></script>
   <script>
     function updateInputNumeric() {
       $('.input-numeric').inputSpinner({
         buttonsClass: "btn-outline-primary p-2",
         buttonsWidth: "1rem",
         template: // the template of the input
           '<div class="input-group ${groupClass}">' +
           '<div class="input-group-prepend">' +
           '<button style="min-width: ${buttonsWidth}" class="btn btn-decrement btn-outline-primary p-2 btn-minus" type="button"><em class=\"fa fa-minus\"></em></button>' +
           '</div>' +
           '<input type="text" inputmode="decimal" style="text-align: ${textAlign}; width: 3rem; height: 2.5rem; line-height: 2.5rem;" class="form-control form-control-text-input"/>' +
           '<div class="input-group-append">' +
           '<button style="min-width: ${buttonsWidth}" class="btn btn-increment btn-outline-primary p-2 btn-plus" type="button"><em class=\"fa fa-plus\"></em></button>' +
           '</div>' +
           '</div>'
       })
     }

     $(document).ready(function () {
       updateInputNumeric();
     });
   </script>
</head>

<body>
<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./--top-nav.jsp" %>

   <div class="header bg-primary pb-6">
      <div class="container">
         <div class="header-body">
            <div class="row align-items-center py-4">
               <div class="col">
                  <nav aria-label="breadcrumb" class="d-md-block">
                     <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                        <li class="breadcrumb-item"><a href="/home"><em class="fa fa-home mr-2"></em>Trang chủ</a></li>
                        <li id="cart-quantity-product" class="breadcrumb-item active" aria-current="page"></li>
                     </ol>
                  </nav>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Page content -->
   <div class="container">
      <div class="row mt--7 justify-content-center">
         <div class="col-md-8 col-sm-12" id="list-shop-bills">
            <div class="card align-self-center">
               <div class="mt-3 mb-5 text-center">
                  <div class="circle-loading2">
                     <div></div>
                     <div></div>
                  </div>
               </div>
            </div>
         </div>

         <div class="col-md-4 col-sm-12">
            <div id="request-login" class="card">
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase">Vui lòng đăng nhập để đặt hàng</h3>
               </div>
               <div class="card-footer text-right">
                  <button data-toggle="modal" data-target="#modal-login" class="btn btn-primary text-uppercase">
                     Đăng nhập/ Đăng kí
                  </button>
               </div>
            </div>

            <div id="address-card" class="card d-none">
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase">Chọn địa chỉ nhận hàng</h3>
               </div>

               <div class="card-body">
                  <div class="row">
                     <div class="col">
                        <h5 id="selected-add-name" class="card-title text-uppercase text-muted mb-2"></h5>
                        <p id="selected-add-phone" class="h2 my-1 pb-0 font-weight-bold"></p>
                        <p id="selected-add-desc" class="text-wrap text-justify text-dark"></p>
                     </div>
                  </div>
               </div>
               <div class="card-footer text-right">
                  <button class="btn btn-primary text-uppercase" type="button" data-toggle="modal"
                          data-target="#modal-list-address">Thay đổi</button>
               </div>
            </div>

            <div id="pay-card" class="card">
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase">Tổng các đơn</h3>
               </div>
               <div class="card-body">
                  <div class="row">
                     <div class="col">
                        <div class="col text-right">
                          <span id="all-bill-total" class="text-right product-detail__price product-detail__price--order">
                          </span>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="card-footer text-right">
                  <button type="button" id="btn-pay" class="btn btn-primary text-uppercase">Thanh toán</button>
               </div>
            </div>
         </div>
      </div>

      <!--Modal list address-->
      <div id="modal-list-address" class="modal fade show" tabindex="-1" role="dialog" aria-modal="true">
         <div class="modal-dialog modal-lg modal-dialog-scrollable modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header p-3">
                  <h2 class="mx-3 my-2 text-center text-uppercase display-4 w-100">Sổ địa chỉ</h2>
                  <button type="button" class="close position-absolute" style="top: 1rem; right: 1rem;"
                          data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div id="list-all-addresses" class="modal-body px-3 py-0">
<%--                  <div class="row">--%>
<%--                     <div class="col">--%>
<%--                        <label>--%>
<%--                           <input type="radio" name="list-addresses" class="card-input-element"/>--%>
<%--                           <div class="p-2 panel panel-default card-input">--%>
<%--                              <h5 class="card-title text-uppercase text-muted mb-2">Nguyễn Văn A</h5>--%>
<%--                              <p class="h2 my-1 pb-0 font-weight-bold">0986552563</p>--%>
<%--                              <p class="text-wrap text-justify text-dark">Địa chỉ ABC</p>--%>
<%--                           </div>--%>
<%--                        </label>--%>
<%--                     </div>--%>
<%--                  </div>--%>
               </div>
               <div class="modal-footer p-3 text-uppercase row">
                  <div class="col text-left">
                     <button type="button" data-toggle="modal" data-target="#modal-add-address"
                             class="btn btn-success m-auto text-uppercase mb-3">
                        thêm địa mới
                     </button>
                  </div>
                  <div class="col text-right">
                     <button class="btn btn-secondary pl-6 pr-6" type="button" id="btn-cancel" data-dismiss="modal">Hủy</button>
                     <button class="btn btn-primary pl-6 pr-6" type="button" id="btn-save-select">Lưu</button>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!--Form add new Address-->
      <%@ include file="../../common/form-add-address-customer.jsp" %>

      <!--Scroll to top button-->
      <button onclick="window.scrollTo({ top: 0, left: 0, behavior: 'smooth' });"
              class="btn btn-icon-only btn-primary btn-circle btn-float">
         <em class="fa fa-arrow-up"></em>
      </button>

      <!-- Modal update successful -->
      <div class="modal fade" id="order-successful-modal" tabindex="-1" role="dialog" aria-labelledby="conform-modal-lb"
           aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="successful-modal-lb">Đặt hàng thành công !</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  Đơn hàng đã được chuyển đến shop<br>
                  Bạn sẽ nhận được email thông báo khi shop gửi hàng!
               </div>
               <div class="modal-footer">
                  <button type="button" data-dismiss="modal" class="btn btn-primary px-4">OK</button>
               </div>
            </div>
         </div>
      </div>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<script>
  let listCartGroupByShop = [];
  let listUserAddress = [];
  let totalAllBillSelected = 0;
  let countBillItemSelected = 0;

  function checkUserLogin() {
    if (isLogined) {
      $('#request-login').addClass('d-none');
      $('#address-card').removeClass('d-none');
      $('#btn-pay').removeClass('disabled');

      loadUserAddress();
      updateSelectedAddress();
    } else {
      $('#request-login').removeClass('d-none');
      $('#address-card').addClass('d-none');
      $('#btn-pay').addClass('disabled');
    }
  }

  function updateTotalAllBillSelected() {
    totalAllBillSelected = 0;
    countBillItemSelected = 0;

    listCartGroupByShop.filter(item => item.isSelected)
      .forEach(x => {
        countBillItemSelected += x.carts.length;
        totalAllBillSelected += x.totalBill;
      });

    $('#cart-quantity-product').html('Giỏ hàng (' + countBillItemSelected + ' sản phẩm)');
    $('#all-bill-total').html(parseFloat(totalAllBillSelected)
      .toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.'));
  }

  function addEventSelectShop() {
    $(".custom-control-input").change(function () {
      let sellerId = $(this)[0].id;
      sellerId = sellerId.replace('seller-', '');

      listCartGroupByShop.filter(item => item.sellerId === sellerId)[0].isSelected = this.checked;
      updateTotalAllBillSelected();
    });
  }

  function addEventNumeric() {
    $('input[name="quantity-number-product-type"]').on("change paste keyup", function (e) {
      let quantity = $('#' + e.target.id).val();
      let productTypeId = e.target.id.replace('quantity-number-', '');
      let boolAdd = "them";

      let paras = JSON.stringify({
        'product-type-id': productTypeId,
        'quantity': quantity,
        'bool-add': boolAdd
      });
      $.ajax({
        url: '/api/carts',
        method: 'PUT',
        async: false,
        cache: false,
        data: paras,
        success: function (data, textStatus, jqXHR) {
          let result = data.toString().split('\n');
          if (result[0] === 'true') {
            loadBill();
          } else {
            showMessageModal('fa fa-times text-danger', 'Xảy ra lỗi', result[1]);
            e.preventDefault();
          }
        },
        error: function (jqXHR, textStatus, errorThrown) {
          showMessageModal('fa fa-times text-danger', 'Lỗi kết nối server', errorThrown);
          e.preventDefault();
        }
      });
    });
  }

  function loadUserAddress() {
    $.ajax({
      url: '/api/info/address',
      method: 'GET',
      cache: false,
      async: false,
      success: function (data, textStatus, jqXHR) {
        try {
          listUserAddress = $.parseJSON(data);
        }
        catch (err) {}

        $('#list-all-addresses').find('div').remove();

        listUserAddress.forEach(x => x.isSelected = false);
        listUserAddress[0].isSelected = true;

        for (let i = 0; i < listUserAddress.length; i++) {
          $('#list-all-addresses').append(
            '<div class="row">' +
            '<div class="col">' +
            '<label class="w-100">' +
            '<input ' + (listUserAddress[i].isSelected ? 'checked' : '') + ' type="radio" value="' + listUserAddress[i].addressId +'" name="list-addresses" class="card-input-element" />' +
            '<div class="p-2 panel panel-default card-input">' +
            '<h5 class="card-title text-uppercase text-muted mb-2">' + listUserAddress[i].fullName + '</h5>' +
            '<p class="h2 my-1 pb-0 font-weight-bold">' + listUserAddress[i].phoneNumber + '</p>' +
            '<p class="text-wrap text-justify text-dark"><b>[' + listUserAddress[i].addressName + '</b>] ' + listUserAddress[i].fullAddress + '</p>' +
            '</div>' +
            '</label>' +
            '</div>' +
            '</div>'
          );
        }
      },
    });
  }

  function updateSelectedAddress() {
      let selectedAdd = listUserAddress.filter(add => add.isSelected)[0];
      $('#selected-add-name').html(selectedAdd.fullName);
      $('#selected-add-phone').html(selectedAdd.phoneNumber);
      $('#selected-add-desc').html('<b>[' + selectedAdd.addressName + ']</b> ' + selectedAdd.fullAddress);
  }

  function loadBill() {
    $.ajax({
      url: '/api/check-out',
      method: 'GET',
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list;
        try {
          list = $.parseJSON(data);
        } catch (err) {
          list = [];
        }
        listCartGroupByShop = list;

        $('#list-shop-bills').find('div').remove();

        if (list.length == 0) {
          $('#list-shop-bills').append('<div class="card align-self-center"><h1 class="p-4">Giỏ hàng trống</h1><div>');
        }

        for (let i = 0; i < list.length; i++) {
          let totalBill = 0;
          let allCartItemHtml = '';

          for (let j = 0; j < list[i].carts.length; j++) {
            let itemAmount = list[i].carts[j].price * list[i].carts[j].quantity;
            totalBill += itemAmount;

            allCartItemHtml = allCartItemHtml + (j != 0 ? '<div class="separate-dashed"></div>' : '') +
              '<div class="row">' +
              '<div class="col-md-2 col-sm-6 align-self-center"><img class="rounded" src="' + list[i].carts[j].image + '" alt="product img"></div>' +
              '<div class="col-md-10 col-sm-12">' +
              '<div class="row">' +
              '<div class="col-8">' +
              '<div class="row">' +
              '<div class="col">' +
              '<a href="/product?id=' + list[i].carts[j].productId + '" class="product-item__name">' + list[i].carts[j].name + '</a>' +
              '</div>' +
              ' </div>' +

              '<div class="row">' +
              '<div class="col">' +
              '<p class="product-item__type">' + list[i].carts[j].nameDetail + '</p>' +
              '</div>' +

              '<div class="col text-right">' +
              '<div class="d-inline-block">' +
              '<input class="input-numeric" id="quantity-number-' + list[i].carts[j].productTypeId +
              '" type="number" name="quantity-number-product-type"' +
              'min = "0"  max = "20" value = "' + list[i].carts[j].quantity + '" maxlength = "2" />' +
              '</div>' +
              '</div>' +
              '</div>' +
              '</div>' +

              '<div class="col-4 text-right">' +
              '<p class="product-item__price product-item__price--item">' +
              parseFloat(list[i].carts[j].price).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.') +
              '</p>' +
              '<p class="product-item__price product-item__price--total product-item__price--order">' +
              parseFloat(itemAmount).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.') +
              '</p>' +
              '</div>' +
              '</div>' +
              '</div>' +
              '</div>';
          }

          $('#list-shop-bills').append(
            '<div class="card">' +
            '<div class="card-header bg-transparent">' +
            '<div class="custom-control custom-checkbox">' +
            '<input type="checkbox" class="custom-control-input" id="seller-' + list[i].sellerId + '" checked>' +
            '<label class="custom-control-label" for="seller-' + list[i].sellerId + '">' +
            '<h3 class="mb-0 text-uppercase">' + list[i].shopName + '</h3>' +
            '</label>' +
            '</div>' +
            '</div>' +
            '<div class="card-body">' +
            allCartItemHtml +
            '</div>' +
            '<div class="card-footer">' +
            '<div class="row">' +
            '<div class="col text-right">' +
            '<p class="d-inline mr-2 font-weight-light style="line-height: 2.6rem">Tổng cộng</p>' +
            '<span class="product-detail__price product-detail__price--order">' +
            parseFloat(totalBill).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.') +
            '</span>' +
            '</div></div></div></div>'
          );

          listCartGroupByShop[i].totalBill = totalBill;
          listCartGroupByShop[i].isSelected = true;
        }

        updateInputNumeric();
        addEventSelectShop();
        addEventNumeric();
        updateTotalAllBillSelected();

        checkUserLogin();
      }
    });
  }

  fUpdateLogin = function () {
    loadBill();
  }

  $('#btn-save-select').click(function() {
    let addId = $('input[name="list-addresses"]:checked').val();
    listUserAddress.forEach(x => x.isSelected = false);
    listUserAddress.filter(add => add.addressId == addId)[0].isSelected = true;
    $('#modal-list-address').modal('hide');
    updateSelectedAddress();
  });

  $('#btn-pay').click(function() {
    let addressId = listUserAddress.filter(add => add.isSelected)[0].addressId;
    let listSellerId = [];
    listCartGroupByShop.filter(item => item.isSelected)
      .forEach(x => listSellerId.push(x.sellerId));

    $.ajax({
      url: '/api/home-order',
      method: 'POST',
      cache: false,
      data: {
        'address-id': addressId,
        'list-seller-id': listSellerId.join("@ab")
      },
      success: function (data, textStatus, jqXHR) {
        if(data.substring(0, 4) === 'true') {
          $('#order-successful-modal').modal('show');
          loadBill();
        }
      }
    });
  });
</script>
</body>
</html>