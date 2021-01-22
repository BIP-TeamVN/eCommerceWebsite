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
      <div class="row mt--6">
         <div class="col-xl-4 order-xl-2">
            <div class="card card-profile">
               <img src="../../assets/img/theme/img-1-1000x600.jpg" alt="Image placeholder" class="card-img-top">
               <div class="row justify-content-center">
                  <div class="col-lg-3 order-lg-2">
                     <div class="card-profile-image">
                        <a href="#">
                           <img src="../../assets/img/theme/team-4.jpg" class="rounded-circle">
                        </a>
                     </div>
                  </div>
               </div>
               <div class="card-header text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4">
                  <div class="d-flex justify-content-between">
                     <a href="#" class="btn btn-sm btn-info mr-4">Theo dõi</a>
                     <a href="#" class="btn btn-sm btn-default float-right">Nhắn tin</a>
                  </div>
               </div>
               <div class="card-body pt-0">
                  <div class="row">
                     <div class="col">
                        <div class="card-profile-stats d-flex justify-content-center">
                           <div>
                              <span class="heading text-capitalize">Bán hàng</span>
                              <span class="description">1 năm</span>
                           </div>
                           <div>
                              <span class="heading text-capitalize">Sản phẩm</span>
                              <span class="description">1000</span>
                           </div>
                           <div>
                              <span class="heading text-capitalize">Đơn hàng</span>
                              <span class="description">300</span>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="text-center">
                     <h5 class="h3">Jessica Jones </h5>
                     <div class="h5 font-weight-300">
                        <i class="ni location_pin mr-2"></i>Bucharest, Romania
                     </div>
                     <div class="h5 mt-4">
                        <i class="ni business_briefcase-24 mr-2"></i>Solution Manager - Creative Tim Officer
                     </div>
                     <div>
                        <i class="ni education_hat mr-2"></i>University of Computer Science
                     </div>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-xl-8 order-xl-1">
            <div class="card">
               <div class="card-header">
                  <div class="row align-items-center">
                     <h3 class="mb-0 text-uppercase ml-3">Tất cả sản phẩm</h3>
                  </div>
               </div>
               <div class="card-body">
                  <a href="javascript:void(0)" class="product-item container">

                     <div class="row">
                        <!--Product image-->
                        <div class="col text-center">
                           <img src="https://cdn.pixabay.com/photo/2018/03/30/15/11/deer-3275594_960_720.jpg"
                                class="rounded product-item__img" alt="...">
                        </div>

                        <!--Product name-->
                        <div class="col pl-0">
                           <p class="product-item__name">
                              Lorem ipsum dolor sit amet consectetur adipisicing elit. Eligendi,
                              voluptas incidunt! Nulla tenetur consequatur nostrum dignissimos temporibus iure assumenda
                              iste quia, quam voluptates totam. Libero laboriosam ipsa voluptates voluptatem ad!
                           </p>
                        </div>
                     </div>
                  </a>
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

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
<script>
  function loadProductCategory() {
    let listCategory = [];
    $.ajax({
      url: '/api/product-categories',
      method: 'GET',
      data: {type: 'all'},
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
              + '<a class="card product-category-item" href="/category?id=' + listCategory[i][j].id + '">'
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
  }

  function loadProduct() {
  }

  loadProductCategory();
</script>
</body>
</html>