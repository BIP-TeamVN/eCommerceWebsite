<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Kênh người bán</title>
   <%@ include file="../../common/link-css.jsp" %>
   <!--Javascript-->
   <%@ include file="../../common/import-js.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="sh--side-nav.jsp">
   <jsp:param name="selectedIndex" value="6"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./sh--top-nav.jsp" %>

   <!--Header and breadcrumb-->
   <div class="header bg-primary pb-6">
      <div class="container-fluid">
         <div class="header-body">
            <div class="row align-items-center py-4">
               <div class="col-lg-6 col-7">
                  <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
                     <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                        <li class="breadcrumb-item"><a href="/seller"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="/seller/product">Sản phẩm</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Sửa thông tin</li>
                     </ol>
                  </nav>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Page content -->
   <div class="container-fluid mt--6">
      <!--List employee card-->
      <div class="row">
         <div class="col">
            <div class="card">
               <!-- Card header -->
               <div class="card-header border-0">
                  <h2 class="mb-0 text-center text-uppercase display-4">Chỉnh sửa thông tin sản phẩm</h2>
               </div>

               <!-- form edit -->
               <form id="product-edit-form" class="px-5">
                  <!--Mã-->
                  <div class="form-group">
                     <label for="id" class="form-control-label">Mã</label>
                     <div>
                        <input class="form-control" type="email" id="id" name="id" readonly
                               value="${productEntity.getProductId()}">
                     </div>
                     <small class="error-input text-danger">sai định dạng Id</small>
                  </div>

                  <!--form edit-->
                  <!--Tên sản phẩm-->
                  <div class="form-group">
                     <label for="product-name" class="form-control-label">Tên sản phẩm</label>
                     <div>
                        <input class="form-control" type="text" placeholder="VD: Iphone 7.5" id="product-name"
                               name="product-name" maxlength="100" value="${productEntity.getProductName()}">
                     </div>
                     <small class="error-input text-danger">Vui lòng nhập tên sản phẩm</small>
                  </div>

                  <!--Nhãn hiệu, xuất xứ-->
                  <div class="row">
                     <div class="col-md-6 form-group">
                        <label for="brand" class="form-control-label">Nhãn hiệu</label>
                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                           data-toggle="popover" data-placement="right"
                           data-content="Hãng sản xuất">?</a>
                        <div>
                           <select class="form-control" id="brand" name="brand" required></select>
                        </div>
                        <small class="error-input text-danger">Vui lòng chọn nhãn hiệu</small>
                     </div>
                     <div class="col-md-6 form-group">
                        <div class="form-group">
                           <label for="product-origin" class="form-control-label">Nơi sản xuất</label>
                           <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                              data-toggle="popover" data-placement="right"
                              data-content="Quốc gia sản xuất">?</a>
                           <div>
                              <input class="form-control" type="text" id="product-origin" name="product-origin"
                                     maxlength="30" placeholder="VD: Việt Nam" value="${productEntity.getProductOrigin()}">
                           </div>
                           <small class="error-input text-danger">Vui lòng nhập Quốc gia sản xuất</small>
                        </div>
                     </div>
                  </div>

                  <!--Mô tả-->
                  <div class="form-group">
                     <label for="product-desc" class="form-control-label">Mô tả</label>
                     <div>
                     <textarea class="form-control" id="product-desc" name="product-desc"
                               placeholder="VD: nghe, gọi" rows="3">${productEntity.productDesc}</textarea>
                     </div>
                     <small class="error-input text-danger">Vui lòng nhập mô tả</small>
                  </div>

                  <!--giá-->
                  <div class="row">
                     <div class="col-md-6 form-group">
                        <div class="form-group">
                           <label for="price-origin" class="form-control-label">Giá thị trường</label>
                           <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                              data-toggle="popover" data-placement="right"
                              data-content="Giá bán ngoài thị trường">?</a>
                           <div>
                              <input class="form-control" type="number" id="price-origin" name="price-origin"
                                     maxlength="30" placeholder="VD: 100000" value="${productEntity.getPriceOrigin()}">
                           </div>
                           <small class="error-input text-danger">Giá không hợp lệ</small>
                        </div>
                     </div>
                     <div class="col-md-6 form-group">
                        <div class="form-group">
                           <label for="price-order" class="form-control-label">Giá bán</label>
                           <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                              data-toggle="popover" data-placement="right"
                              data-content="Giá bán online">?</a>
                           <div>
                              <input class="form-control" type="number" id="price-order" name="price-order"
                                     maxlength="30" placeholder="VD: 90000" value="${productEntity.getPriceOrder()}">
                           </div>
                           <small class="error-input text-danger">Giá không hợp lệ</small>
                        </div>
                     </div>
                  </div>

                  <!--liệt kê loại sản phẩm và số lượng -->
                  <div id="list-types">
                     <div class="row">
                        <div class="col">
                           <div class="form-group">
                              <label class="form-control-label">Loại sản phẩm</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="Loại sản phẩm">?</a>
                           </div>
                        </div>
                     </div>
                  </div>
                  <div class="row">
                     <div class="col-md-1 form-group">
                        <a class="btn btn-default px-2 py-1" data-toggle="tooltip" data-placement="top"
                           title="Xóa loại sản phẩm" onclick="removeType()"><i class="fa fa-minus"></i></a>
                     </div>
                     <div class="col-md-1 form-group">
                        <a class="btn btn-google-plus px-2 py-1" data-toggle="tooltip" data-placement="top"
                           title="Thêm loại sản phẩm" onclick="addType()"><i class="fa fa-plus"></i></a>
                     </div>
                  </div>

                  <!--chọn ngành hàng-->
                  <div class="row form-group">
                     <div class="col form-group">
                        <label for="categories" class="form-control-label">Ngành hàng</label>
                        <div>
                           <select class="form-control" name="categories" id="categories" multiple rows="5">
                           </select>
                        </div>
                        <small class="error-input text-danger">Vui lòng chọn ngành hàng</small>
                     </div>
                  </div>

                  <!--Ảnh-->
                  <div class="row">
                     <div class="col-md-4 form-group ">
                        <label for="up-image-0" class="form-control-label d-inline-block w-100">Ảnh bìa</label>
                        <img id="img-upload-0" class="mb-2 rounded avatar-img" src="${productEntity.getImage0()}"/>
                        <div class="custom-file">
                           <input type="file" class="custom-file-input" id="up-image-0" name="up-image-0"
                                  accept="image/*"
                                  onchange="encodeImgToBase64(this, 'img-upload-0')">
                        </div>
                        <small class="error-input text-danger">Vui lòng chọn Ảnh bìa</small>
                     </div>
                     <div class="col-md-4 form-group">
                        <label for="up-image-1" class="form-control-label d-inline-block w-100">Ảnh 1</label>
                        <img id="img-upload-1" class="mb-2 rounded avatar-img" src="${productEntity.getImage1()}"/>
                        <div class="custom-file">
                           <input type="file" class="custom-file-input" id="up-image-1" name="up-image-1"
                                  accept="image/*"
                                  onchange="encodeImgToBase64(this, 'img-upload-1')">
                        </div>
                     </div>
                     <div class="col-md-4 form-group">
                        <label for="up-image-2" class="form-control-label d-inline-block w-100">Ảnh 2</label>
                        <img id="img-upload-2" class="mb-2 rounded avatar-img" src="${productEntity.getImage2()}"/>
                        <div class="custom-file">
                           <input type="file" class="custom-file-input" id="up-image-2" name="up-image-2"
                                  accept="image/*"
                                  onchange="encodeImgToBase64(this, 'img-upload-2')">
                        </div>
                     </div>
                     <div class="col-md-4 form-group">
                        <label for="up-image-3" class="form-control-label d-inline-block w-100">Ảnh 3</label>
                        <img id="img-upload-3" class="mb-2 rounded avatar-img" src="${productEntity.getImage3()}"/>
                        <div class="custom-file">
                           <input type="file" class="custom-file-input" id="up-image-3" name="up-image-3"
                                  accept="image/*"
                                  onchange="encodeImgToBase64(this, 'img-upload-3')">
                        </div>
                     </div>
                     <div class="col-md-4 form-group">
                        <label for="up-image-4" class="form-control-label d-inline-block w-100">Ảnh 4</label>
                        <img id="img-upload-4" class="mb-2 rounded avatar-img" src="${productEntity.getImage4()}"/>
                        <div class="custom-file">
                           <input type="file" class="custom-file-input" id="up-image-4" name="up-image-4"
                                  accept="image/*"
                                  onchange="encodeImgToBase64(this, 'img-upload-4')">
                        </div>
                     </div>
                  </div>
               </form>

               <!-- Card footer -->
               <div class="card-footer py-4">
                  <div class="row">
                     <div class="col-md-6 text-md-right text-center mb-sm-3">
                        <button type="submit" form="product-edit-form" class="btn btn-primary px-6">
                           LƯU
                        </button>
                     </div>
                     <div class="col-md-6 text-md-left text-center">
                        <button type="button" data-toggle="modal" data-target="#conform-modal"
                                class="btn btn-secondary px-6">
                           HỦY
                        </button>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>

      <!-- Modal conform close -->
      <div class="modal fade" id="conform-modal" tabindex="-1" role="dialog" aria-labelledby="conform-modal-lb"
           aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="conform-modal-lb">Cảnh báo</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  Những thay đổi sẽ không được cập nhật !<br>
                  Bạn có muốn hủy thay đổi ?
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-secondary px-3" data-dismiss="modal">KHÔNG</button>
                  <a href="/seller/product" class="btn btn-primary px-4">CÓ</a>
               </div>
            </div>
         </div>
      </div>

      <!-- Modal update successful -->
      <div class="modal fade" id="successful-modal" tabindex="-1" role="dialog" aria-labelledby="conform-modal-lb"
           aria-hidden="true">
         <div class="modal-dialog" role="document">
            <div class="modal-content">
               <div class="modal-header">
                  <h5 class="modal-title" id="successful-modal-lb">Thông báo</h5>
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                     <span aria-hidden="true">&times;</span>
                  </button>
               </div>
               <div class="modal-body">
                  Cập nhật thông tin sản phẩm thành công !
               </div>
               <div class="modal-footer">
                  <a href="/seller/product" class="btn btn-primary px-4">OK</a>
               </div>
            </div>
         </div>
      </div>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<script src="../../assets/js/validate/validate-product-edit-form.js"></script>
<script>
  $.ajax({
    url: "/api/seller-units",
    method: "GET",
    success: function (data) {
      let obj = $.parseJSON(data);
      console.log(obj);
      $.each(obj, function (key, value) {
        if (value.id === '${productEntity.getBrandEntity().getBrandId()}') {
          $('#brand').append('<option selected value="' + value.id + '">' + value.brandName + '</option>');
        } else {
          $('#brand').append('<option value="' + value.id + '">' + value.brandName + '</option>');
        }
      });
    },
    cache: false
  });
</script>
<link rel="stylesheet" href="../../assets/vendor/slim-select/dist/slimselect.min.css" type="text/css">
<script src="../../assets/vendor/slim-select/dist/slimselect.js"></script>
<script>
  new SlimSelect({
    select: '#categories'
  });
</script>
<script>
  $.ajax({
    url: "/api/get-id-categories",
    method: "GET",
    cache: false,
    data: {
      id: $('#id').val()
    },
    success: function (data) {
      $('#categories').append(data);
    }
  });
</script>
<script>
  //load loại sản phẩm
  let countType = 0
  $.ajax({
    url: "/api/get-product-types",
    method: "GET",
    cache: false,
    data: {
      id: $('#id').val()
    },
    success: function (data) {
      let obj = $.parseJSON(data);
      $.each(obj, function (key, value) {
        let html =
          '<div class="row" id="div-type-' + countType + '">' +
          '<div class="col-md-8">' +
          '<div class="form-group">' +
          '<label for="quantity-' + countType + '" class="form-control-label">Số lượng</label>' +
          '<div>' +
          '<input class="form-control" type="text" id="type-name-' + countType + '" name="type-name-' + countType +  '"' +
          'maxlength="30" placeholder="VD: Xanh" value="' + value.name + '">' +
          '</div>' +
          '<small class="error-input text-danger">Loại sản phẩm không hợp lệ</small>' +
          '</div>' +
          '<div class="form-group">' +
          '<label for="quantity-' + countType + '" class="form-control-label">Số lượng</label>' +
          '<div>' +
          '<input class="form-control" type="number" id="quantity-' + countType + '" name="quantity-' + countType + '"' +
          'maxlength="50" placeholder="VD: 99" value="' + value.quantity + '">' +
          '</div>' +
          '<small class="error-input text-danger">Số lượng không hợp lệ</small>' +
          '</div>' +
          '</div>' +
          '<div class="col-md-4">' +
          '<div class="form-group">' +
          '<img id="img-upload-type-' + countType + '" class="mb-2 rounded avatar-img" src="' + value.image + '"/>' +
          '<div class="custom-file">' +
          '<input type="file" class="custom-file-input" id="up-image-type-' + countType + '" name="up-image-type-' + countType + '"' +
          'accept="image/*"' +
          'onchange="encodeImgToBase64(this, \'img-upload-type-' + countType + '\')">' +
          '</div>' +
          '<small class="error-input text-danger">Vui lòng chọn Ảnh bìa</small>' +
          '</div>' +
          '</div>' +
          '</div>';
        $('#list-types').append(html);
        countType += 1;
      });
    }
  });
</script>
<script>
  function addType() {
    let html =
      '<div class="row" id="div-type-' + countType + '">' +
      '<div class="col-md-8">' +
      '<div class="form-group">' +
      '<label for="quantity-' + countType + '" class="form-control-label">Tên loại</label>' +
      '<div>' +
      '<input class="form-control" type="text" id="type-name-' + countType + '" name="type-name-' + countType +  '"' +
      'maxlength="30" placeholder="VD: Xanh">' +
      '</div>' +
      '<small class="error-input text-danger">Loại sản phẩm không hợp lệ</small>' +
      '</div>' +
      '<div class="form-group">' +
      '<label for="quantity-' + countType + '" class="form-control-label">Số lượng</label>' +
      '<div>' +
      '<input class="form-control" type="number" id="quantity-' + countType + '" name="quantity-' + countType + '"' +
      'maxlength="50" placeholder="VD: 99">' +
      '</div>' +
      '<small class="error-input text-danger">Số lượng không hợp lệ</small>' +
      '</div>' +
      '</div>' +
      '<div class="col-md-4">' +
      '<div class="form-group">' +
      '<img id="img-upload-type-' + countType + '" class="d-none"/>' +
      '<div class="custom-file">' +
      '<input type="file" class="custom-file-input" id="up-image-type-' + countType + '" name="up-image-type-' + countType + '"' +
      'accept="image/*"' +
      'onchange="encodeImgToBase64(this, \'img-upload-type-' + countType + '\')">' +
      '</div>' +
      '<small class="error-input text-danger">Vui lòng chọn Ảnh bìa</small>' +
      '</div>' +
      '</div>' +
      '</div>';
    $('#list-types').append(html);
    countType += 1;
  }

  function removeType() {
    if (countType > 1) {
      $("#div-type-" + (countType - 1)).remove();
      countType -= 1;
    }
  }
</script>
</body>
</html>
