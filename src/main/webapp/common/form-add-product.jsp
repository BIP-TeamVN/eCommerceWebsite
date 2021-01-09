<%@ page import="java.util.Date" %>
<%@ page import="com.hknp.utils.DateTimeUtils" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.Duration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%;
    String minDob = DateTimeUtils.dateToString(Date.from(Instant.now().minus(Duration.ofDays(36520))), "yyyy-MM-dd");
    String defaultDob = DateTimeUtils.dateToString(Date.from(Instant.now().minus(Duration.ofDays(3652))), "yyyy-MM-dd");
    String today = DateTimeUtils.dateToString(Date.from(Instant.now()), "yyyy-MM-dd");
%>

<!-- Modal -->
<div class="modal fade bd-example-modal-lg" id="modal-add-product" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header p-3">
                <h5 class="display-3 mx-3 my-2 text-uppercase">Thêm Sản phẩm mới</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body p-3">
                <div class="row">
                    <div class="col mx-3">
                        <form id="product-form" method="POST" enctype="multipart/form-data">
                            <!--Tên sản phẩm-->
                            <div class="form-group">
                                <label for="product-name" class="form-control-label">Tên sản phẩm</label>
                                <div>
                                    <input class="form-control" type="text" placeholder="VD: Iphone 7.5" id="product-name" name="product-name" maxlength="100">
                                </div>
                                <small class="error-input text-danger">Vui lòng nhập tên sản phẩm</small>
                            </div>

                            <!--Nhãn hiệu, xuất xứ-->
                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <label for="brand" class="form-control-label">Nhãn hiệu</label>
                                    <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                       data-content="Hãng sản xuất">?</a>
                                    <div>
                                        <select class="form-control" id="brand" name="brand" required></select>
                                    </div>
                                    <small class="error-input text-danger">Vui lòng chọn nhãn hiệu</small>
                                </div>
                                <div class="col-md-6 form-group">
                                    <div class="form-group">
                                        <label for="product-origin" class="form-control-label">Xuất xứ</label>
                                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                           data-content="Quốc gia sản xuất">?</a>
                                        <div>
                                            <input class="form-control" type="text" id="product-origin" name="product-origin" maxlength="30" placeholder="VD: Việt Nam">
                                        </div>
                                        <small class="error-input text-danger">Vui lòng nhập Quốc gia sản xuất</small>
                                    </div>
                                </div>
                            </div>

                            <!--Mô tả-->
                            <div class="form-group">
                                <label for="product-desc" class="form-control-label">Mô tả</label>
                                <div>
                                    <textarea class="form-control" id="product-desc" name="product-desc" placeholder="VD: nghe, gọi" rows="3"></textarea>
                                </div>
                                <small class="error-input text-danger">Vui lòng nhập mô tả</small>
                            </div>

                            <!--giá-->
                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <div class="form-group">
                                        <label for="price-origin" class="form-control-label">Giá thị trường</label>
                                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                           data-content="Giá bán ngoài thị trường">?</a>
                                        <div>
                                            <input class="form-control" type="number" id="price-origin" name="price-origin" maxlength="30" placeholder="VD: 100000">
                                        </div>
                                        <small class="error-input text-danger">Giá không hợp lệ</small>
                                    </div>
                                </div>
                                <div class="col-md-6 form-group">
                                    <div class="form-group">
                                        <label for="price-order" class="form-control-label">Giá bán</label>
                                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                           data-content="Giá bán online">?</a>
                                        <div>
                                            <input class="form-control" type="number" id="price-order" name="price-order" maxlength="30" placeholder="VD: 90000">
                                        </div>
                                        <small class="error-input text-danger">Giá không hợp lệ</small>
                                    </div>
                                </div>
                            </div>

                            <!--liệt kê loại sản phẩm và số lượng -->
                            <div id="list-types">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="form-control-label">Loại sản phẩm</label>
                                            <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                               data-content="Loại sản phẩm">?</a>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label class="form-control-label">Số lượng</label>
                                            <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                               data-content="Số lượng của loại sản phẩm">?</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-1 form-group">
                                    <a class="btn btn-default px-2 py-1" data-toggle="tooltip" data-placement="top" title="Xóa loại sản phẩm" onclick="removeType()"><i class="fa fa-minus"></i></a>
                                </div>
                                <div class="col-md-1 form-group">
                                    <a class="btn btn-google-plus px-2 py-1" data-toggle="tooltip" data-placement="top" title="Thêm loại sản phẩm" onclick="addType()"><i class="fa fa-plus"></i></a>
                                </div>
                            </div>

                            <!--chọn ngành hàng-->
                            <div class="row">
                                <div class="col-md-5 form-group">
                                    <label for="categories-all" class="form-control-label">Ngành hàng</label>
                                    <div>
                                        <select class="form-control" name="categories-all" id="categories-all" multiple rows="5">
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-2 form-group">
                                    <div class="row">
                                        <a><i class="fa fa-angle-left" id="btn-remove-categories"></i></a>
                                    </div>
                                    <div class="row">
                                        <a><i class="fa fa-angle-right" id="btn-add-categories"></i></a>
                                    </div>
                                </div>
                                <div class="col-md-5 form-group">
                                    <label for="categories-selected" class="form-control-label">Ngành hàng đã chọn</label>
                                    <div>
                                        <select class="form-control" name="categories-selected" id="categories-selected" multiple rows="5">
                                        </select>
                                    </div>
                                    <small class="error-input text-danger">Vui lòng chọn ít nhất một ngành hàng</small>
                                </div>
                            </div>

                            <!--Ảnh-->
                            <div class="row">
                                <div class="col-md-4 form-group ">
                                    <label for="up-image-1" class="form-control-label d-inline-block w-100">Ảnh 1</label>
                                    <img id="img-upload-1" class="d-none"/>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="up-image-1" name="up-image-1" accept="image/*"
                                               onchange="encodeImgToBase64(this, 'img-upload-1')">
                                    </div>
                                </div>
                                <div class="col-md-4 form-group">
                                    <label for="up-image-2" class="form-control-label d-inline-block w-100">Ảnh 2</label>
                                    <img id="img-upload-2" class="d-none"/>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="up-image-2" name="up-image-2" accept="image/*"
                                               onchange="encodeImgToBase64(this, 'img-upload-2')">
                                    </div>
                                </div>
                                <div class="col-md-4 form-group">
                                    <label for="up-image-3" class="form-control-label d-inline-block w-100">Ảnh 3</label>
                                    <img id="img-upload-3" class="d-none"/>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="up-image-3" name="up-image-3" accept="image/*"
                                               onchange="encodeImgToBase64(this, 'img-upload-3')">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer p-3 text-uppercase">
                <button class="btn btn-secondary pl-6 pr-6" type="button" id="btn-cancel" data-dismiss="modal">Hủy</button>
                <button class="btn btn-primary pl-6 pr-6" type="submit" form="product-form">Lưu</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="import-js.jsp" %>
<script src="../../assets/js/dynamic-admin-unit-drop-down.js"></script>
<script src="../../assets/js/validate/validate-product-form.js"></script>
<script>
  let countType = 0;
  function addType() {
    let html =
        '<div class="row" id="div-type-' + countType + '">' +
        '   <div class="col-md-6">' +
        '       <div class="form-group">' +
        '           <div>' +
        '               <input class="form-control" type="text" id="type-name-' + countType + '" name="type-name-' + countType + '" maxlength="30" placeholder="VD: Xanh">' +
        '           </div>' +
        '           <small class="error-input text-danger">Loại sản phẩm không hợp lệ</small>' +
        '       </div>' +
        '   </div>' +
        '   <div class="col-md-6">' +
        '       <div class="form-group">' +
        '           <div>' +
        '               <input class="form-control" type="number" id="quantity-' + countType + '" name="quantity-' + countType + '" maxlength="50" placeholder="VD: 99">' +
        '           </div>' +
        '           <small class="error-input text-danger">Số lượng không hợp lệ</small>' +
        '       </div>' +
        '   </div>' +
        '</div>';
    $('#list-types').append(html);
    countType += 1;
  }
  $(document).ready(function () {
    const apiUrl = "/api/seller-units";

    $.ajax({
      url: apiUrl,
      method: "GET",
      data: {},
      success: function (data) {
        let obj = $.parseJSON(data);
        console.log(obj);
        $.each(obj, function (key, value) {
          $('#brand').append('<option value="' + value.id + '">' + value.brandName + '</option>');
        });
      },
      cache: false
    });
  });
</script>
<script>
    function removeType() {
      if (countType > 0) {
        $("#div-type-" + (countType - 1)).remove();
        countType -= 1;
      }
    }
</script>
