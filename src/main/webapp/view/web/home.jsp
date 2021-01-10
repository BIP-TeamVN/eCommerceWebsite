<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./--top-nav.jsp" %>

   <!-- Page content -->
   <div class="container">
      <!--Top carousel-->
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
                     <img class="d-block w-100" src="../../assets/img/theme/slide-green.png" alt="First slide">
                  </div>
                  <div class="carousel-item">
                     <img class="d-block w-100" src="../../assets/img/theme/slide-red.png" alt="Second slide">
                  </div>
                  <div class="carousel-item">
                     <img class="d-block w-100" src="../../assets/img/theme/slide-purple.png" alt="Third slide">
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
                     <div id="multi-item-example" class="col carousel slide carousel-multi-item" data-ride="carousel">
                        <!--Controls-->
                        <div>
                           <a class="carousel-button carousel-button-prev" href="#multi-item-example" data-slide="prev">
                              <em class="fa fa-chevron-left"></em>
                           </a>
                           <a class="carousel-button carousel-button-next" href="#multi-item-example" data-slide="next">
                              <em class="fa fa-chevron-right"></em>
                           </a>
                        </div>

                        <!--Slides-->
                        <div class="carousel-inner" role="listbox">
                           <!--First slide-->
                           <div class="carousel-item">
                              <div class="row">
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="./category?id=1">
                                       <img class="card-img-top"
                                            src="https://cf.shopee.vn/file/75ea42f9eca124e9cb3cde744c060e4d_tn"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1">Thời trang nữ</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>
                              </div>

                           </div>
                           <!--/.First slide-->

                           <!--Second slide-->
                           <div class="carousel-item active">
                              <div class="row">
                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top" src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg" alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto"><h5 class="card-title m-auto py-1">Tên chuyên mục</h5></div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>
                              </div>

                           </div>
                           <!--/.Second slide-->

                           <!--Third slide-->
                           <div class="carousel-item">
                              <div class="row">

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascript:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>

                                 <div class="col-md-2 col-sm-4 col-xs-12 clearfix d-inline-block d-md-block">
                                    <a class="card" href="javascipt:void(0)">
                                       <img class="card-img-top"
                                            src="https://mdbootstrap.com/img/Photos/Horizontal/City/4-col/img%20(60).jpg"
                                            alt="category_logo">
                                       <div class="card-body text-center p-0 m-auto">
                                          <h5 class="card-title m-auto py-1"> Tên chuyên mục</h5>
                                       </div>
                                    </a>
                                 </div>
                              </div>

                           </div>
                           <!--/.Third slide-->

                        </div>
                        <!--/.Slides-->

                        <!--Indicators-->
                        <ol class="carousel-indicators mb-1" style="position: static;">
                           <li data-target="#multi-item-example" data-slide-to="0" class="bg-primary active"></li>
                           <li data-target="#multi-item-example" data-slide-to="1" class="bg-primary"></li>
                           <li data-target="#multi-item-example" data-slide-to="2" class="bg-primary"></li>
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
                        <a href="javascript:void(0)" class="btn btn-sm btn-primary">Xem tất cả</a>
                     </div>
                  </div>
               </div>
               <div class="card-body">
                  <div class="row" id="brand-list">
                     <div class="col-lg-3 col-md-6">
                        <a class="btn-icon-clipboard p-2" href="javascript:void(0)">
                           <div>
                              <img src="../unknown-brand.svg" class="rounded avatar" alt="...">
                              <h4 class="ml-3 my-auto">MSI</span>
                           </div>
                        </a>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!--Product-->
      <div class="row mt-0 justify-content-center">
         <div class="col">
            <div class="card">
               <div class="card-header bg-transparent">
                  <h3 class="mb-0 text-uppercase">Sản phẩm mới</h3>
               </div>
               <div class="card-body">
                  <div class="row" id="product-list">
                     <div class="col-lg-3 col-md-6">
                        <a class="btn-icon-clipboard p-2" href="javascript:void(0)">
                           <!--Product image-->
                           <div class="row">
                              <div class="col text-center">
                                 <img src="../no-image-product.svg" class="rounded product-image" alt="...">
                              </div>
                           </div>
                           <!--Product name-->
                           <div class="row">
                              <div class="col text-justify">
                                 <h4 class="product-title mx-2 mt-2">Lorem ipsum dolor sit amet consectetur adipisicing elit.
                                    Qui,
                                    adipisci!</h4>
                              </div>
                           </div>
                           <!--Product desc-->
                           <!-- <div class="row">
                             <div class="col m-2 text-justify">
                               <p class="product-desc">Lorem, ipsum dolor sit amet consectetur adipisicing elit. Obcaecati
                                 praesentium maiores amet harum fuga. Iste temporibus veniam fugiat repudiandae veritatis!</p>
                             </div>
                           </div> -->
                           <!--Price and add to card-->
                           <div class="row mr-0">
                              <!--Price-->
                              <div class="col-8 p-0 text-left">
                                 <!--Order price-->
                                 <span class="pl-4 pr--4 price order-price">200000</span>
                                 <!--Origin price-->
                                 <span class="price origin-price">
                          <s>200000</s>
                        </span>
                              </div>
                              <!--Add to card button-->
                              <div class="col-4 p-0 text-right">
                                 <button type="button" onclick="addToCart('000000000')" class="btn btn-primary">
                                    <i class="fa fa-cart-plus text-white"></i>
                                 </button>
                              </div>
                           </div>
                           <!--Deal Percent-->
                           <span class="badge badge-pill badge-danger price-percent">46%</span>
                        </a>
                     </div>
                  </div>
               </div>
               <!--View more product-->
               <div class="card-footer bg-transparent">
                  <div class="row text-center m-1">
                     <div class="col">
                        <a class="btn btn-outline-primary" style="width: 35%;" href="javascipt:void(0)">Xem thêm</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!--Scroll to top button-->
      <a href="#" class="float">
         <i class="fa fa-plus my-float"></i>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
<script>
   function loadProductCategory() {}
   function loadBrand() {}
   function loadProduct() { }
   function addToCart(productId) { alert(productId); }

   function scrollToTop() { window.scrollTo({ top: 0, left: 0, behavior: 'smooth' }); }

</script>
</body>
</html>
