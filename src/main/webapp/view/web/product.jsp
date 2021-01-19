<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website - Sản phẩm</title>
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
                        <li class="breadcrumb-item"><a href="/category?id=${categoryId}">${categoryName}</a></li>
                        <li class="breadcrumb-item"><a href="/brand?id=${brandId}">${brandName}</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Tên Sp</li>
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
                              <div id="carousel-thumb" class="carousel slide carousel-fade carousel-thumbnails"
                                   data-ride="carousel">
                                 <!--Slides-->
                                 <div class="carousel-inner" role="listbox">
                                    <div class="carousel-item active">
                                       <img class="rounded product-detail__img"
                                            src="https://cf.shopee.vn/file/1668027414633fb149ad814abb70be62" alt="First slide">
                                    </div>
                                    <div class="carousel-item">
                                       <img class="rounded product-detail__img"
                                            src="https://cf.shopee.vn/file/ffff728b2d6776a3f5703a1d204ecc5e" alt="Second slide">
                                    </div>
                                    <div class="carousel-item">
                                       <img class="rounded product-detail__img"
                                            src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6" alt="Third slide">
                                    </div>
                                 </div>
                                 <!--/.Slides-->
                                 <!--Controls-->
                                 <a class="carousel-control carousel-control-prev" href="#carousel-thumb" role="button"
                                    data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                 </a>
                                 <a class="carousel-control carousel-control-next" href="#carousel-thumb" role="button"
                                    data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                 </a>
                              </div>
                           </div>
                        </div>


                        <div class="row mt-3">
                           <div class="col px--4">
                              <!--/.Controls-->
                              <ol class="p-0 carousel-img-thumb justify-content-around">
                                 <li data-target="#carousel-thumb" class="mx-2 active" data-slide-to="0">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/1668027414633fb149ad814abb70be62" alt="s">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="1">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/ffff728b2d6776a3f5703a1d204ecc5e">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>

                                 <li data-target="#carousel-thumb" class="mx-2" data-slide-to="2">
                                    <img class="rounded product-detail__img--thumb"
                                         src="https://cf.shopee.vn/file/9e01f9224154ccffaabf5b0b743e44b6">
                                 </li>
                              </ol>
                           </div>
                        </div>
                     </div>

                     <!--Product info-->
                     <div class="col-md-5 col-sm-12">
                        <form id="add-to-card-form">
                           <h1 class="product-item__name" style="font-size: 1.25rem; line-height: 2rem;">
                              Tên sp ---
                              Lorem ipsum dolor sit amet consectetur adipisicing elit. Eligendi,
                              voluptas incidunt! Nulla tenetur consequatur nostrum dignissimos temporibus iure assumenda
                              iste quia, quam voluptates totam. Libero laboriosam ipsa voluptates voluptatem ad!
                           </h1>

                           <div class="my-5 btn-group btn-group-toggle d-block" data-toggle="buttons"
                                style="flex-direction: column;">
                              <label class="btn btn-secondary d-block">
                                 <input type="radio" name="product-types" id="product-type-1" value="product-type-1" checked="">
                                 Loại 1
                              </label>
                              <label class="btn btn-secondary d-block">
                                 <input type="radio" name="product-types" id="product-type-2" value="product-type-2"> Loại 2
                              </label>
                              <label class="btn btn-secondary d-block">
                                 <input type="radio" name="product-types" id="product-type-3" value="product-type-3"> Loại 3
                              </label>
                           </div>

                           <p class="my-2">Còn <span class="font-weight-bold">5</span> sản phẩm</p>

                           <div class="row">
                              <div class="col-4">
                                 <input class="input-numeric" id="quantity-number" name="quantity-number" type="number" min="1"
                                        max="20" value="1" maxlength="2" />
                              </div>
                              <div class="col-8">
                                 <button type="submit" class="btn btn-primary btn-block text-uppercase">Thêm vào giỏ hàng <em
                                         class="ml-2 fa fa-cart-plus"></em></button>
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
                     <div class="col">
                        <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolore consequatur asperiores eos a
                           maxime natus perspiciatis nemo nostrum laudantium quasi, assumenda atque veniam animi vel. Possimus
                           eos nemo fuga ea!</p>
                     </div>
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
                        <h3 class="mb-0 text-uppercase"><em class="fa fa-store text-success mr-2"></em>Các sản phẩm khác của
                           shop</h3>
                     </div>
                     <div class="col text-right">
                        <a href="javascript:void(0)" class="btn btn-sm btn-primary">
                           Xem tất cả
                           <em class="fa fa-angle-double-right ml-2"></em>
                        </a>
                     </div>
                  </div>
               </div>
               <!--Product main content-->
               <div class="card-body">
                  <div class="row" id="shop-product-list">
                     <div class="col-lg-3 col-md-6">
                        <a class="product-item" href="javascript:void(0)">
                           <!--Product image-->
                           <div class="row">
                              <div class="col text-center">
                                 <img src="https://cdn.pixabay.com/photo/2018/03/30/15/11/deer-3275594_960_720.jpg"
                                      class="rounded product-item__img" alt="...">
                              </div>
                           </div>
                           <!--Product name-->
                           <div class="row">
                              <div class="col">
                                 <p class="product-item__name">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eligendi,
                                    voluptas incidunt! Nulla tenetur consequatur nostrum dignissimos temporibus iure assumenda
                                    iste quia, quam voluptates totam. Libero laboriosam ipsa voluptates voluptatem ad!</p>
                              </div>
                           </div>
                           <!--Price and add to card-->
                           <div class="row mr-0">
                              <!--Price-->
                              <div class="col-8 p-0 text-left">
                                 <!--Order price-->
                                 <span class="product-item__price product-item__price--order">160000</span>
                                 <!--Origin price-->
                                 <span class="product-item__price product-item__price--origin">200000</span>
                              </div>
                              <!--Add to card button-->
                              <div class="col-4 p-0 text-right">
                                 <button type="button" onclick="addToCart('000000000')" class="btn btn-primary"
                                         data-toggle="tooltip" data-placement="top" title="Thêm vào giỏ hàng">
                                    <em class="fa fa-cart-plus text-white" style="font-size: 1.2rem;"></em>
                                 </button>
                              </div>
                           </div>
                           <!--Deal Percent-->
                           <span class="product-item__price-percent">10%</span>
                        </a>
                     </div>
                  </div>
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
                        <a href="javascript:void(0)" class="btn btn-sm btn-primary">
                           Xem tất cả
                           <em class="fa fa-angle-double-right ml-2"></em>
                        </a>
                     </div>
                  </div>
               </div>
               <!--Product main content-->
               <div class="card-body">
                  <div class="row" id="relative-product-list">
                     <div class="col-lg-3 col-md-6">
                        <a class="product-item" href="javascript:void(0)">
                           <!--Product image-->
                           <div class="row">
                              <div class="col text-center">
                                 <img src="https://cdn.pixabay.com/photo/2018/03/30/15/11/deer-3275594_960_720.jpg"
                                      class="rounded product-item__img" alt="...">
                              </div>
                           </div>
                           <!--Product name-->
                           <div class="row">
                              <div class="col">
                                 <p class="product-item__name">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eligendi,
                                    voluptas incidunt! Nulla tenetur consequatur nostrum dignissimos temporibus iure assumenda
                                    iste quia, quam voluptates totam. Libero laboriosam ipsa voluptates voluptatem ad!</p>
                              </div>
                           </div>
                           <!--Price and add to card-->
                           <div class="row mr-0">
                              <!--Price-->
                              <div class="col-8 p-0 text-left">
                                 <!--Order price-->
                                 <span class="product-item__price product-item__price--order">160000</span>
                                 <!--Origin price-->
                                 <span class="product-item__price product-item__price--origin">200000</span>
                              </div>
                              <!--Add to card button-->
                              <div class="col-4 p-0 text-right">
                                 <button type="button" onclick="addToCart('000000000')" class="btn btn-primary"
                                         data-toggle="tooltip" data-placement="top" title="Thêm vào giỏ hàng">
                                    <em class="fa fa-cart-plus text-white" style="font-size: 1.2rem;"></em>
                                 </button>
                              </div>
                           </div>
                           <!--Deal Percent-->
                           <span class="product-item__price-percent">10%</span>
                        </a>
                     </div>
                  </div>
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
   const PRODUCT_ID = ${productId};
   console.log(PRODUCT_ID);
</script>
</body>
</html>