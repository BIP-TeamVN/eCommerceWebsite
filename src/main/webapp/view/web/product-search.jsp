<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Mua hàng online giá tốt, hàng chuẩn, ship bao nhanh</title>
   <%@ include file="../../common/link-css.jsp" %>
   <!--Javascript-->
   <%@ include file="../../common/import-js.jsp" %>
</head>

<body>
<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./--top-nav.jsp" %>

   <!-- Page content -->
   <div class="container">
      <!--Filter-->
      <div id="tb-filter" class="card-header border-0 pt-0">
         <div class="container-fluid">
            <div class="row">
               <div class="col">
                  <div class="line-text"><span>Tìm kiếm và tùy chọn hiển thị</span></div>
               </div>
            </div>

            <div class="row m-0">
               <div class="col pl-0">
                  <form class="m-auto w-100 navbar-search navbar-search-light form-inline" id="form-keyword-search">
                     <div class="form-group w-100 mb-0">
                        <div style="border: 1px solid #EFF1F3;"
                             class="w-100 input-group input-group-alternative input-group-merge">
                           <div class="input-group-prepend">
                              <span class="input-group-text"><em class="fas fa-search"></em></span>
                           </div>
                           <input class="form-control" id="tb-input-search" placeholder="Tìm kiếm" type="text">
                        </div>
                     </div>
                  </form>
               </div>

               <div class="col">
                  <div class="row">
                     <div class="col p-0">
                        <div class="form-group m-1">
                           <label for="sort-by-column" class="floating-label">Sắp xếp theo</label>
                           <select class="form-control input-border" id="sort-by-column" onchange="search()">
                              <option value="productId" selected>Mã</option>
                              <option value="productName">Tên sản phẩm</option>
                              <option value="brandEntity.brandName">Nhãn hiệu</option>
                              <option value="sellerEntity.storeName">Cửa hàng</option>
                              <option value="productRate">Đánh giá</option>
                              <option value="productOrigin">Xuất xứ</option>
                              <option value="productCreateDate">Ngày tạo</option>
                              <option value="priceOrder">Giá bán</option>
                           </select>
                        </div>
                     </div>

                     <div class="col p-0">
                        <div class="form-group m-1">
                           <label for="sort-type" class="floating-label">Kiểu sắp xếp</label>
                           <select class="form-control input-border" id="sort-type" onchange="search()">
                              <option value="ASC" selected>Tăng dần</option>
                              <option value="DESC">Giảm dần</option>
                           </select>
                        </div>
                     </div>
                  </div>
               </div>
            </div>


            <div class="row m-0">
               <div class="col form-group">
                  <label for="categories" class="form-control-label">Ngành hàng</label>
                  <div>
                     <select class="form-control" name="categories" id="categories" multiple rows="5" onchange="search()"></select>
                  </div>
               </div>

               <div class="col form-group">
                  <label for="brands" class="form-control-label">Nhãn hiệu</label>
                  <div>
                     <select class="form-control" name="brands" id="brands" multiple rows="5" onchange="search()"></select>
                  </div>
               </div>
            </div>


            <div class="row my-4">
               <div class="col">
                  <div class="line-text"></div>
               </div>
            </div>
         </div>
      </div>

      <!--Product-->
      <div class="row mt-0 justify-content-center">
         <div class="col">
            <div class="card">
               <!--Product Title-->
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase">Sản phẩm mới</h3>
               </div>
               <!--Product main content-->
               <div class="card-body">
                  <div class="row" id="product-list"></div>
               </div>
               <!--View more product-->
               <div class="card-footer bg-transparent">
                  <div class="row text-center m-1">
                     <div class="col">
                        <a class="btn btn-outline-primary" style="width: 35%;" onclick="loadProduct()">Xem thêm</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!--Scroll to top button-->
      <button onclick="window.scrollTo({ top: 0, left: 0, behavior: 'smooth' });" class="btn btn-icon-only btn-primary btn-circle btn-float">
         <em class="fa fa-arrow-up"></em>
      </button>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<script>
  $('#categories').append('${categories}');
  $('#brands').append('${brands}');
  if ('${keyword}' === ""){
    $('#tb-input-search').val('${shopName}');
  } else {
    $('#tb-input-search').val('${keyword}');
  }

  let page = 1;
  function loadProduct() {
    $.ajax({
      url: '/api/product-search-customer',
      method: 'GET',
      data: {
        'page': page,
        'keyword': $('#tb-input-search').val(),
        'columnName': $('#sort-by-column').val(),
        'typeSort': $('#sort-type').val(),
        'categories': $('#categories').val().join('@nq'),
        'brands': $('#brands').val().join('@nq')
      },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        if (data != "[]") {
          page += 1;
        }
        $.each(list, function (index, item) {
          let priceOrder = parseFloat(item.priceOrder).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
          let priceOrigin = parseFloat(item.priceOrigin).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
          let percentDiscount = (100*(priceOrigin-priceOrder)/priceOrigin).toFixed(0);

          $('#product-list').append(
            '<div class="col-lg-3 col-md-6 mt-3">' +
            '  <div class="product-item" href="javacript:void(0)">' +
            '    <div class="row">' +
            '      <div class="col text-center">' +
            '        <img src="' + item.image0 + '" class="rounded product-item__img" alt="...">' +
            '      </div>' +
            '    </div>' +
            '    <!--Product name-->' +
            '    <div class="row">' +
            '      <div class="col">' +
            '          <a href="/product?id=' + item.id + '" class="product-item__name">' + item.productName + '</a>' +
            '      </div>' +
            '    </div>' +
            '    <!--Price and add to card-->' +
            '    <div class="row mr-0">' +
            '      <!--Price-->' +
            '      <div class="col-8 p-0 text-left">' +
            '        <!--Order price-->' +
            '        <span class="product-item__price product-item__price--order d-block">' + priceOrder + '</span>' +
            '        <!--Origin price-->' +
            '        <span class="product-item__price product-item__price--origin">' + priceOrigin + '</span>' +
            '      </div>' +
            '      <!--Add to card button-->' +
            '      <div class="col-4 p-0 text-right">' +
            '        <button type="button" onclick="addToCart(\'' + item.id + '\')" class="btn btn-primary" ' +
            '                data-toggle="tooltip" data-placement="top" title="Thêm vào giỏ hàng">' +
            '          <em class="fa fa-cart-plus text-white" style="font-size: 1.2rem;"></em>' +
            '        </button>' +
            '      </div>' +
            '    </div>' +
            '    <!--Deal Percent-->' +
            '    <span class="product-item__price-percent">' + percentDiscount + '%</span>' +
            '  </div>' +
            '</div>'
          );
        });
      }
    });
  }

  function addToCart(productTypeId) {
    $.ajax({
      url: '/api/carts',
      method: 'POST',
      async: false,
      data: {
        'product-id': productTypeId
      },
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          showMessageModal('fa fa-check text-success', 'Thông báo', 'Thêm sản phẩm thành công !');
        } else {
          showMessageModal('fa fa-times text-danger', 'Xảy ra lỗi', result[1]);
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        showMessageModal('fa fa-times text-danger', 'Lỗi kết nối server', errorThrown);
      }
    });
  }

  loadProduct();
</script>
<script>
  function search(){
    $('#product-list').find('div').remove();
    page = 1;
    loadProduct();
  }
</script>
<link rel="stylesheet" href="/assets/vendor/slim-select/dist/slimselect.min.css" type="text/css">
<script src="/assets/vendor/slim-select/dist/slimselect.js"></script>
<script>
  new SlimSelect({
    select: '#brands'
  });
  new SlimSelect({
    select: '#categories'
  });

  $('#form-keyword-search').submit(function (e) {
    e.preventDefault();
    search();
  })
</script>
</body>
</html>