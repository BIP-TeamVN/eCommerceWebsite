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
      <!--Carousel Banner-->
      <div class="row mt-5 mb-5 justify-content-center">
         <div class="col">
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
               <ol class="carousel-indicators">
                  <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                  <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
               </ol>
               <div class="carousel-inner">
                  <div class="carousel-item active">
                     <img class="d-block w-100" src="../../assets/img/theme/bg1.jpg" alt="First slide">
                  </div>
                  <div class="carousel-item">
                     <img class="d-block w-100" src="../../assets/img/theme/bg2.jpg" alt="Second slide">
                  </div>
                  <div class="carousel-item">
                     <img class="d-block w-100" src="../../assets/img/theme/bg3.jpg" alt="Third slide">
                  </div>
               </div>
               <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                  <span class="sr-only">Previous</span>
               </a>
               <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                  <span class="carousel-control-next-icon" aria-hidden="true"></span>
                  <span class="sr-only">Next</span>
               </a>
            </div>
         </div>
      </div>

      <!--Category-->
      <div class="row mt--3 justify-content-center">
         <div class="col">
            <div class="card">
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase">Ngành hàng</h3>
               </div>
               <div class="card-body mt-4 py-0">
                  <div id="category-list" class="row justify-content-center">
                     <!--Carousel Wrapper-->
                     <div id="category-item-indicator" class="col carousel slide carousel-multi-item"
                          data-ride="carousel">
                        <!--Controls-->
                        <div>
                           <a class="carousel-button carousel-button-prev" href="#category-item-indicator"
                              data-slide="prev">
                              <em class="fa fa-chevron-left"></em>
                           </a>
                           <a class="carousel-button carousel-button-next" href="#category-item-indicator"
                              data-slide="next">
                              <em class="fa fa-chevron-right"></em>
                           </a>
                        </div>

                        <!--Slides-->
                        <div id="category-list--slides" class="carousel-inner" role="listbox">
                           <div class="carousel-item active">
                              <div class="row">
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <div class="mt-3 mb-5 text-center">
                                       <div class="circle-loading2"><div></div><div></div></div>
                                    </div>
                                 </div>
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <div class="mt-3 mb-5 text-center">
                                       <div class="circle-loading2"><div></div><div></div></div>
                                    </div>
                                 </div>
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <div class="mt-3 mb-5 text-center">
                                       <div class="circle-loading2"><div></div><div></div></div>
                                    </div>
                                 </div>
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <div class="mt-3 mb-5 text-center">
                                       <div class="circle-loading2"><div></div><div></div></div>
                                    </div>
                                 </div>
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <div class="mt-3 mb-5 text-center">
                                       <div class="circle-loading2"><div></div><div></div></div>
                                    </div>
                                 </div>
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <div class="mt-3 mb-5 text-center">
                                       <div class="circle-loading2"><div></div><div></div></div>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>
                        <!--/.Slides-->

                        <!--Indicators-->
                        <ol id="category-list--ol" class="carousel-indicators mb-1" style="position: static;">
                        </ol>
                        <!--/.Indicators-->
                     </div>
                     <!--/.Carousel Wrapper-->
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!--Brand-->
      <div class="row mt-0 justify-content-center">
         <div class="col">
            <div class="card">
               <div class="card-header bg-transparent">
                  <div class="row">
                     <div class="col">
                        <h3 class="mb-0 text-uppercase">Nhãn hiệu</h3>
                     </div>
                     <div class="col text-right">
                        <button href="javascript:void(0)" class="btn btn-sm btn-primary" data-toggle="modal"
                                data-target="#modal-all-brands">Xem tất cả</button>
                     </div>
                  </div>
               </div>
               <div class="card-body">
                  <div class="row" id="brand-list">
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!-- Modal view all brand -->
      <div id="modal-all-brands" class="modal fade" tabindex="-1" role="dialog"  aria-labelledby="exampleModalLabel" aria-hidden="true">
         <div class="modal-dialog modal-xl modal-dialog-scrollable modal-dialog-centered" role="document">
            <div class="modal-content">
               <div class="modal-header p-3">
                  <h2 class="mx-3 my-2 text-center text-uppercase display-4 w-100">Tất cả nhãn hiệu</h2>
                  <button type="button" class="close position-absolute" style="top: 1rem; right: 1rem;" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body p-3">
                  <div class="row">
                     <div class="col">
                        <div class="form-group mt--3">
                           <div class="input-group border border-primary input-group-alternative mb-4">
                              <div class="input-group-prepend">
                                 <span class="input-group-text"><em class="fa fa-search"></em></span>
                              </div>
                              <input class="form-control form-control-alternative" id="search-brand" name="search-brand"
                                     placeholder="Tìm kiếm nhãn hiệu" type="text" onchange="searchBrand()"/>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="row" id="brand-list-search"></div>
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
  function loadProductCategory() {
    let listCategory = [];
    $.ajax({
      url: '/api/product-categories',
      method: 'GET',
      data: {'type': 'all'},
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
        for (let i = 0; i < list.length; i += 6) {
          let j = i;
          let sub = [];
          while (j < list.length && j < i + 6) {
            sub.push(list[j]);
            j++;
          }
          listCategory.push(sub);
        }
        $('#category-list--slides').find('div').remove();
        $('#category-list--ol').find('li').remove();
        for (let i = 0; i < listCategory.length; i++) {
          let slides = '<div class="carousel-item' + (i == 0 ? ' active' : '') + '"><div class="row">';
          for (let j = 0; j < listCategory[i].length; j++) {
            slides += '<div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">'
              + '<a class="card product-category-item" href="/product-search?categories=' + listCategory[i][j].id + '">'
              + '<img class="card-img-top" src="' + listCategory[i][j].image + '" alt="category_logo">'
              + '<div class="card-body text-center p-0 m-auto">'
              + '<h5 class="card-title m-auto py-1">' + listCategory[i][j].name + '</h5>'
              + '</div>'
              + '</a>'
              + '</div>';
          }
          slides += '</div></div>';
          $('#category-list--slides').append(slides);
          $('#category-list--ol').append('<li data-target="#category-item-indicator" data-slide-to="' + i + '" class="bg-primary"></li>');
        }
      }
    });
  }

  function loadBrand() {
    $.ajax({
      url: '/api/brands',
      method: 'GET',
      data: {
        'page': 1,
        'keyword': '',
        'columnName': 'brandId',
        'typeSort': 'asc'
      },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        $('#tb-list').find('tr').remove();
        $.each(list, function (index, item) {
          let html = '<div class="col-lg-3 col-md-6">' +
            '<a class="btn-icon-clipboard p-2" href="/product-search?brands=' + item.id + '">' +
            '<div>' +
            '<img src="' + item.image + '" class="rounded avatar" alt="...">' +
            '<h4 class="ml-3 my-auto">' + item.brandName + '</h4>' +
            '</div>' +
            '</a>' +
            '</div>';
          $('#brand-list').append(html);
        });
      }
    });
  }

  let page = 1;
  function loadProduct() {
    $.ajax({
      url: '/api/product-customer',
      method: 'GET',
      data: {
        'page': page,
        'keyword': '',
        'columnName': 'productCreateDate',
        'typeSort': 'asc'
      },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        if (data != "[]") {
          page += 1;
        }
        $('#tb-list').find('tr').remove();
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

  function addToCart(productId) {
    $.ajax({
      url: '/api/carts',
      method: 'POST',
      async: false,
      data: {
        'product-id': productId
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

  loadProductCategory();
  loadBrand();
  loadProduct();
</script>
<script>
  $('#modal-all-brands').on('shown.bs.modal', function(){
    $('#search-brand').val('');
    searchBrand();
  });

  $('#search-brand').on('input',function(e){
    searchBrand();
  });

   function searchBrand() {
     $('#brand-list-search').find('div').remove();
     $.ajax({
       url: '/api/brands',
       method: 'GET',
       data: {
         'page': 1,
         'keyword': $('#search-brand').val(),
         'columnName': 'brandId',
         'typeSort': 'asc'
       },
       cache: false,
       success: function (data, textStatus, jqXHR) {
         let list = $.parseJSON(data);
         $('#tb-list').find('tr').remove();
         $.each(list, function (index, item) {
           let html = '<div class="col-lg-3 col-md-6">' +
             '<a class="btn-icon-clipboard p-2" href="javascript:void(0)">' +
             '<div>' +
             '<img src="' + item.image + '" class="rounded avatar" alt="...">' +
             '<h4 class="ml-3 my-auto">' + item.brandName + '</h4>' +
             '</div>' +
             '</a>' +
             '</div>';
           $('#brand-list-search').append(html);
         });
       }
     });
   };
</script>
</body>
</html>