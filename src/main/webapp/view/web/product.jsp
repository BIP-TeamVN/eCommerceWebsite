<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.hknp.model.entity.ProductEntity" %>
<%@ page import="com.hknp.model.entity.ProductTypeEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<% ProductEntity p = (ProductEntity) request.getAttribute("product"); %>
<% List<ProductTypeEntity> types = new ArrayList<>(p.getProductTypeEntities());%>

<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Mua hàng online giá tốt, hàng chuẩn, ship bao nhanh</title>
   <%@ include file="../../common/link-css.jsp" %>
   <!--Javascript-->
   <%@ include file="../../common/import-js.jsp" %>
   <script src="../../assets/vendor/bootstrap-input-spinner/src/bootstrap-input-spinner.js"></script>
   <script src="../../assets/vendor/bootstrap-input-spinner/src/input-numeric.js"></script>
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
                        <li class="breadcrumb-item"><a href="/product-search?brands=${product.brandEntity.brandId}">${product.brandEntity.brandName}</a> </li>
                        <li class="breadcrumb-item active" aria-current="page">${product.productName}</li>
                     </ol>
                  </nav>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Page content -->
   <div class="container">

      <!--Product Info-->
      <div class="row mt--7 justify-content-center">
         <div class="col">
            <div class="card">
               <!--Title-->
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase"><em class="fa fa-info mr-2"></em>Thông tin sản phẩm</h3>
               </div>
               <!--Product main content-->
               <div class="card-body pt-0">
                  <div class="row px-3 pt-3">
                     <!--Product images-->
                     <div class="col-md-7 col-sm-12">
                        <div class="row">
                           <div class="col">
                              <!--Carousel Wrapper-->
                              <div id="carousel-thumb" class="carousel slide carousel-fade carousel-thumbnails" data-ride="carousel">
                                 <!--Slides-->
                                 <div class="carousel-inner" role="listbox" id="list-image"></div>
                                 <!--/.Slides-->
                                 <a class="carousel-control carousel-control-prev" href="#carousel-thumb" role="button" data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                 </a>
                                 <a class="carousel-control carousel-control-next" href="#carousel-thumb" role="button" data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                 </a>
                              </div>
                           </div>
                        </div>

                        <div class="row mt-3">
                           <div class="col px--4">
                              <ol class="p-0 carousel-img-thumb justify-content-around" id="list-image-min"></ol>
                           </div>
                        </div>
                     </div>

                     <!--Product info-->
                     <div class="col-md-5 col-sm-12">
                        <form id="add-to-card-form">
                           <h1 class="product-item__name" style="font-size: 1.25rem; line-height: 2rem;"><c:out value="${product.productName}"/> </h1>

                           <div class="my-5 btn-group btn-group-toggle d-block" data-toggle="buttons"
                                style="flex-direction: column;" id="list-type-product">
                           </div>

                           <p class="my-2">Còn <span class="font-weight-bold"><%= p.getCountStock()%></span> sản phẩm</p>
                           <p class="my-2">Đã bán <span class="font-weight-bold"><%= p.getCountSold()%></span> sản phẩm</p>

                           <!--Origin price and Deal Percent-->
                           <div class="row mt-5 mb-0">
                              <div class="col-8"><span class="product-detail__price product-detail__price--origin"><c:out value="${product.getPriceOriginDisplay()}"/></span></div>
                              <div class="col-3"><span class="product-detail__price--percent">${percent}%</span></div>
                           </div>

                           <!--Order price-->
                           <div class="row mt-0 mb-5">
                              <div class="col">
                                 <span class="product-detail__price product-detail__price--order"><c:out value="${product.getPriceOrderDisplay()}"/></span>
                              </div>
                           </div>

                           <div class="row">
                              <div class="col-4">
                                 <input type="number" class="input-numeric" maxlength="2" id="quantity-number" name="quantity-number"
                                        min="1" max="20" value="1"/>
                              </div>
                              <div class="col-8">
                                 <button type="button" onclick="addToCarts()" class="btn btn-primary btn-block text-uppercase">
                                    Thêm vào giỏ hàng <em class="ml-2 fa fa-cart-plus"></em>
                                 </button>
                              </div>
                           </div>
                        </form>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!--Product description-->
      <div class="row mt-0 justify-content-center">
         <div class="col">
            <div class="card">
               <!--Title-->
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase"><em class="fa fa-pen-fancy mr-2"></em>Mô tả sản phẩm</h3>
               </div>
               <!--Product main content-->
               <div class="card-body">
                  <div class="row">
                     <div class="col"><p><c:out value="${product.productDesc}"/></p></div>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!--Review Product-->
      <div class="row mt-0 justify-content-center">
         <div class="col">
            <div class="card">
               <!--Title-->
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase"><em class="fa fa-star mr-2 text-warning"></em>Đánh giá</h3>
               </div>
               <!--All comment-->
               <div class="card-body">

               </div>
               <!--Page-->
               <div class="card-footer">
                  <nav aria-label="...">
                     <ul id="page-pagination" class="pagination justify-content-center m-0">
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

      <!--Other's shop Product-->
      <div class="row mt-0 justify-content-center">
         <div class="col">
            <div class="card">
               <!--Product Title-->
               <div class="card-header bg-transparent">
                  <div class="row">
                     <div class="col">
                        <h3 class="mb-0 text-uppercase"><em class="fa fa-store text-success mr-2"></em>Các sản phẩm khác của shop</h3>
                     </div>
                     <div class="col text-right">
                        <a id="btn-show-all-shop" class="btn btn-sm btn-primary">
                           Xem tất cả <em class="fa fa-angle-double-right ml-2"></em>
                        </a>
                     </div>
                  </div>
               </div>
               <!--Product main content-->
               <div class="card-body">
                  <div class="row" id="shop-product-list"></div>
               </div>
            </div>
         </div>
      </div>

      <!--Relative Product-->
      <div class="row mt-0 justify-content-center">
         <div class="col">
            <div class="card">
               <!--Product Title-->
               <div class="card-header bg-transparent">
                  <div class="row">
                     <div class="col">
                        <h3 class="mb-0 text-uppercase"><em class="fa fa-equals text-pink mr-2"></em> Sản phẩm tương tự</h3>
                     </div>
                     <div class="col text-right">
                        <a id="btn-show-all-category" class="btn btn-sm btn-primary">
                           Xem tất cả<em class="fa fa-angle-double-right ml-2"></em>
                        </a>
                     </div>
                  </div>
               </div>
               <!--Product main content-->
               <div class="card-body">
                  <div class="row" id="relative-product-list"></div>
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
  const PRODUCT_ID = parseFloat('<%= p.getProductId()%>');
  const SELLER_ID = parseFloat('<%= p.getSellerEntity().getUserId()%>');

  $('#btn-show-all-shop').attr('href', '/product-search?shop=' + SELLER_ID);
  $('#btn-show-all-category').attr('href', '/product-search?product=' + PRODUCT_ID);

  let html1 =
    '<div class="carousel-item active">' +
    '<img class="rounded product-detail__img"' +
    'src="<%=p.getImage0()%>" alt="First slide">' +
    '</div>';
  $('#list-image').append(html1);

  let html2 =
    '<li data-target="#carousel-thumb" class="mx-2 active" data-slide-to="0">' +
    '<img class="rounded product-detail__img--thumb"' +
    'src="<%= p.getImage0()%>" alt="s">' +
    '</li>';
  $('#list-image-min').append(html2);

  if ('<%=p.getImage1()%>' != '../../assets/img/no-image-product.svg') {
    let html1 =
      '<div class="carousel-item">' +
      '<img class="rounded product-detail__img"' +
      'src="<%=p.getImage1()%>" alt="Second slide">' +
      '</div>';
    $('#list-image').append(html1);
    let html2 =
      '<li data-target="#carousel-thumb" class="mx-2" data-slide-to="1">' +
      '<img class="rounded product-detail__img--thumb"' +
      'src="<%= p.getImage1()%>" alt="s">' +
      '</li>';
    $('#list-image-min').append(html2);
  }
  if ('<%=p.getImage2()%>' != '../../assets/img/no-image-product.svg') {
    let html1 =
      '<div class="carousel-item">' +
      '<img class="rounded product-detail__img"' +
      'src="<%=p.getImage2()%>" alt="Third slide">' +
      '</div>';
    $('#list-image').append(html1);
    let html2 =
      '<li data-target="#carousel-thumb" class="mx-2 active" data-slide-to="2">' +
      '<img class="rounded product-detail__img--thumb"' +
      'src="<%= p.getImage2()%>" alt="s">' +
      '</li>';
    $('#list-image-min').append(html2);
  }
  if ('<%=p.getImage3()%>' != '../../assets/img/no-image-product.svg') {
    let html1 =
      '<div class="carousel-item">' +
      '<img class="rounded product-detail__img"' +
      'src="<%=p.getImage3()%>" alt="Third slide">' +
      '</div>';
    $('#list-image').append(html1);
    let html2 =
      '<li data-target="#carousel-thumb" class="mx-2 active" data-slide-to="3">' +
      '<img class="rounded product-detail__img--thumb"' +
      'src="<%= p.getImage3()%>" alt="s">' +
      '</li>';
    $('#list-image-min').append(html2);
  }
  if ('<%=p.getImage4()%>' != '../../assets/img/no-image-product.svg') {
    let html1 =
      '<div class="carousel-item">' +
      '<img class="rounded product-detail__img"' +
      'src="<%=p.getImage4()%>" alt="Third slide">' +
      '</div>';
    $('#list-image').append(html1);
    let html2 =
      '<li data-target="#carousel-thumb" class="mx-2 active" data-slide-to="4">' +
      '<img class="rounded product-detail__img--thumb"' +
      'src="<%= p.getImage4()%>" alt="s">' +
      '</li>';
    $('#list-image-min').append(html2);
  }
</script>
<script>
  <%
     String imageTypes = "";
     String nameTypes = "";
     imageTypes += "<div class=\"carousel-item\">" +
         "<img class=\"rounded product-detail__img\"" +
         "src=\"" + types.get(0).getImage() + "\" alt=\"First slide\">" +
         "</div> ";
        nameTypes += "<label class=\"btn btn-secondary d-block\">" +
         "<input type=\"radio\" name=\"product-types\" id=\"" + types.get(0).getProductTypeId() + "\"checked value=\"" + types.get(0).getProductTypeId() + "\">" + types.get(0).getProductTypeName() +
         "</label>";

     for (int i = 1; i < types.size(); i++) {
        imageTypes += "<div class=\"carousel-item\">" +
         "<img class=\"rounded product-detail__img\"" +
         "src=\"" + types.get(i).getImage() + "\" alt=\"First slide\">" +
         "</div> ";
        nameTypes += "<label class=\"btn btn-secondary d-block\">" +
         "<input type=\"radio\" name=\"product-types\" id=\"" + types.get(i).getProductTypeId() + "\" value=\"" + types.get(i).getProductTypeId() + "\">" + types.get(i).getProductTypeName() +
         "</label>";
     }
  %>
  $('#list-image').append('<%= imageTypes%>');
  $('#list-type-product').append('<%= nameTypes%>');
</script>
<script>
  const quantity = document.getElementById("quantity-number");
  const productTypeId = $('input[name="product-types"]:checked').val();

  function productOfShop() {
    $.ajax({
      url: '/api/product-by-seller',
      method: 'GET',
      data: {
        'type': 'min',
        'sellerId': SELLER_ID
      },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        $.each(list, function (index, item) {
          let priceOrder = parseFloat(item.priceOrder).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
          let priceOrigin = parseFloat(item.priceOrigin).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
          let percentDiscount = (100*(priceOrigin-priceOrder)/priceOrigin).toFixed(0);

          $('#shop-product-list').append(
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

  function addToCartIn(productId) {
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

  function addToCarts() {
    let boolAdd = "k";
    let paras = JSON.stringify({
      'product-type-id': productTypeId,
      'quantity': quantity.value.trim(),
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

  function productOfCategory() {
    $.ajax({
      url: '/api/product-by-category',
      method: 'GET',
      data: {
        'type': 'min',
        'productId': PRODUCT_ID
      },
      cache: false,
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        $.each(list, function (index, item) {
          let priceOrder = parseFloat(item.priceOrder).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
          let priceOrigin = parseFloat(item.priceOrigin).toFixed(0).replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
          let percentDiscount = (100*(priceOrigin-priceOrder)/priceOrigin).toFixed(0);

          $('#relative-product-list').append(
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

  productOfShop();
  productOfCategory();
</script>
<script>

</script>
</body>
</html>