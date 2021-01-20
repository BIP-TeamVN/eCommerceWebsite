<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website</title>
   <%@ include file="../../common/link-css.jsp" %>
   <!--Javascript-->
   <%@ include file="../../common/import-js.jsp" %>
   <script src="../../assets/vendor/bootstrap-input-spinner/src/bootstrap-input-spinner.js"></script>
   <script>
     function updateInputNumeric() {
       $(".input-numeric").inputSpinner({
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
       });
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
                        <li id="quantity-product" class="breadcrumb-item active" aria-current="page"></li>
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
         <div class="col-8" id="list-shop-bills">


            <div class="card">
               <div class="card-header bg-transparent">
                  <div class="custom-control custom-checkbox">
                     <input type="checkbox" class="custom-control-input" id="seller-id" checked>
                     <label class="custom-control-label" for="seller-id">
                        <h3 class="mb-0 text-uppercase">Shop abc</h3>
                     </label>
                  </div>
               </div>
               <div class="card-body">
                  <div class="row">
                     <div class="col-md-2 col-sm-6 align-self-center"><img class="rounded" src="" alt="product img">
                     </div>
                     <div class="col-md-10 col-sm-12">
                        <div class="row">
                           <!--Name, type and quantity-->
                           <div class="col-8">
                              <div class="row">
                                 <div class="col">
                                    <a href="javascript:void(0)" class="product-item__name"></a>
                                 </div>
                              </div>

                              <div class="row">
                                 <div class="col">
                                    <p class="product-item__type">
                                       Load sp
                                    </p>
                                 </div>
                                 <div class="col text-right">
                                    <div class="d-inline-block">
                                       <input class="input-numeric" id="quantity-number-product-type-2"
                                              name="quantity-number-product-type-3" type="number"
                                              min="1" max="20" value="1" maxlength="2"/>
                                    </div>
                                 </div>
                              </div>
                           </div>

                           <!--Price-->
                           <div class="col-4 text-right">
                              <p class="product-item__price product-item__price--item">
                                 80000
                              </p>
                              <p class="product-item__price product-item__price--total product-item__price--order">
                                 80000
                              </p>
                           </div>
                        </div>
                     </div>
                  </div>

                  <div class="separate-dashed"></div>

                  <div class="row">
                     <!--Image-->
                     <div class="col-md-2 col-sm-6 align-self-center">
                        <img class="rounded" src="../../assets/img/theme/img-1-1000x600.jpg" alt="product img">
                     </div>

                     <!--Order info-->
                     <div class="col-md-10 col-sm-12">
                        <div class="row">

                           <!--Name, type and quantity-->
                           <div class="col-8">
                              <div class="row">
                                 <div class="col">
                                    <a href="javascript:void(0)" class="product-item__name">
                                       Lorem ipsum, dolor sit amet
                                       consectetur adipisicing elit. Recusandae facilis commodi earum enim! Fugiat,
                                       soluta et
                                       maiores, eius tempore quaerat nisi iusto at animi sapiente consequuntur
                                       voluptatem ipsa,
                                       dolorem repellat!
                                    </a>
                                 </div>
                              </div>

                              <div class="row">
                                 <div class="col">
                                    <p class="product-item__type">
                                       Load sp
                                    </p>
                                 </div>
                                 <div class="col text-right">
                                    <div class="d-inline-block">
                                       <input class="input-numeric" id="quantity-number-product-type-1"
                                              name="quantity-number-product-type-1" type="number"
                                              min="1" max="20" value="1" maxlength="2"/>
                                    </div>
                                 </div>
                              </div>
                           </div>

                           <!--Price-->
                           <div class="col-4 text-right">
                              <p class="product-item__price product-item__price--item">
                                 80000
                              </p>
                              <p class="product-item__price product-item__price--total product-item__price--order">
                                 80000
                              </p>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>

               <div class="card-footer">
                  <div class="row">
                     <div class="col text-right">
                        <p class="d-inline font-weight-light">Tổng cộng</p>
                        <span class="product-detail__price product-detail__price--order">
                    160000
                  </span>
                     </div>
                  </div>
               </div>
            </div>


         </div>

         <div class="col-4">
            <div class="card">
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
                  <button class="btn btn-primary text-uppercase">Thanh toán</button>
               </div>
            </div>
         </div>
      </div>

      <!--Scroll to top button-->
      <button onclick="window.scrollTo({ top: 0, left: 0, behavior: 'smooth' });"
              class="btn btn-icon-only btn-primary btn-circle btn-float">
         <em class="fa fa-arrow-up"></em>
      </button>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<script>
  // $.ajax({
  //   url: '/api/check-out',
  //   method: 'GET',
  //   cache: false,
  //   success: function (data, textStatus, jqXHR) {
  //     console.log(data);
  //     console.log(typeof data);
  //     let list = $.parseJSON(data);
  //     console.log(list);
  //     console.log(data);
  //
  //     let quantity = 0;
  //     $.each(list, function (index, item) {
  //       quantity = quantity + 1;
  //     });
  //     $('#quantity-product').append("Giỏ hàng (" + quantity + " Sản phẩm)");
  //   }
  // });

  let listCartGroupByShop;
  let totalAllBillSelected = 0;

  function addEventSelectShop() {
    $(".custom-control-input").change(function() {
      let sellerId = $(this)[0].id;
      sellerId = sellerId.replace('seller-', '');

      listCartGroupByShop.filter(item =>  item.sellerId === sellerId)[0].isSelected = this.checked;

      totalAllBillSelected = 0;
      listCartGroupByShop.filter(item => item.isSelected)
        .forEach(x => { totalAllBillSelected += x.totalBill; } );

      $('#all-bill-total').html(parseFloat(totalAllBillSelected).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.'));
    });
  }

  $.ajax({
    url: '/api/check-out',
    method: 'GET',
    cache: false,
    success: function (data, textStatus, jqXHR) {
      let list = $.parseJSON(data);
      listCartGroupByShop = list;

      $('#list-shop-bills').find('div').remove();
      for (let i = 0; i < list.length; i++) {
        let totalBill = 0;
        let allCartItemHtml = '';

        for (let j = 0; j < list[i].carts.length; j++) {
          let itemAmount = list[i].carts[j].price * list[i].carts[j].quantity;
          totalBill += itemAmount;

          let cartItemHtml =
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
            '<input class="input-numeric" id="quantity-number-' + list[i].carts[j].productTypeId + '" type="number" name="quantity-number-product-type-3"' +
            'min = "1"  max = "20" value = "' + list[i].carts[j].quantity + '" maxlength = "2" />' +
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

          if (j != 0) { cartItemHtml = '<div class="separate-dashed"></div>' + cartItemHtml; }

          allCartItemHtml = allCartItemHtml + cartItemHtml;
        }

        let shop =
          '<div class="card">' +
          '<div class="card-header bg-transparent">' +
          '<div class="custom-control custom-checkbox">' +
          '<input type="checkbox" class="custom-control-input" id="seller-' + list[i].sellerId + '" checked>' +
          '<label class="custom-control-label" for="seller-' + list[i].sellerId +'">' +
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
          '</div></div></div></div>';

        $('#list-shop-bills').append(shop);
        listCartGroupByShop[i].totalBill = totalBill;
        listCartGroupByShop[i].isSelected = true;
      }
      updateInputNumeric();
      addEventSelectShop();
    }
  });


</script>


</body>
</html>