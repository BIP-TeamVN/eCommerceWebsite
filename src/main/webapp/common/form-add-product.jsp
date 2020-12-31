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
                                    <select class="form-control" id="brand" name="brand" required></select>
                                </div>
                                <div class="col-md-6 form-group">
                                    <div class="form-group">
                                        <label for="product-origin" class="form-control-label">Xuất xứ</label>
                                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                           data-content="Quốc gia sản xuất">?</a>
                                        <div>
                                            <input class="form-control" type="text" id="product-origin" name="product-origin" maxlength="30" placeholder="VD: Việt Nam">
                                        </div>
                                        <small class="error-input text-danger">Quốc gia không hợp lệ</small>
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

                            <div class="row">
                                <div class="col-md-6 form-group">
                                    <div class="form-group">
                                        <label for="type-name" class="form-control-label">
                                            Loại sản phẩm
                                            <a href="#" class="btn btn-danger px-2 py-1" data-toggle="tooltip" data-placement="top" title="Thêm">
                                                <i class="fa fa-plus"></i>
                                            </a>
                                        </label>
                                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                           data-content="Loại sản phẩm">?</a>
                                        <div>
                                            <input class="form-control" type="text" id="type-name" name="type-name" maxlength="30" placeholder="VD: Xanh">
                                        </div>
                                        <small class="error-input text-danger">Loại sản phẩm không hợp lệ</small>
                                    </div>
                                </div>
                                <div class="col-md-6 form-group">
                                    <div class="form-group">
                                        <label for="quantity" class="form-control-label">Số lượng</label>
                                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                           data-content="Số lượng của loại sản phẩm">?</a>
                                        <div>
                                            <input class="form-control" type="number" id="quantity" name="quantity" maxlength="50" placeholder="VD: 99">
                                        </div>
                                        <small class="error-input text-danger">Số lượng không hợp lệ</small>
                                    </div>
                                </div>
                                <tbody class="list" id="list-type">
                                </tbody>
                            </div>

                            <!--Ảnh-->
                            <div class="form-group">
                                <label for="up-image1" class="form-control-label d-inline-block w-100">Ảnh 1</label>
                                <img id="img-upload1" class="d-none"/>
                                <div class="custom-file">
                                    <label class="custom-file-label custom-file-img-label" for="up-image1">Select file</label>
                                    <input type="file" class="custom-file-input" id="up-image1" name="up-image1" accept="image/*"
                                           onchange="encodeImgToBase64(this)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="up-image2" class="form-control-label d-inline-block w-100">Ảnh 2</label>
                                <img id="img-upload2" class="d-none"/>
                                <div class="custom-file">
                                    <label class="custom-file-label custom-file-img-label" for="up-image2">Select file</label>
                                    <input type="file" class="custom-file-input" id="up-image2" name="up-image2" accept="image/*"
                                           onchange="encodeImgToBase64(this)">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="up-image3" class="form-control-label d-inline-block w-100">Ảnh 3</label>
                                <img id="img-upload3" class="d-none"/>
                                <div class="custom-file">
                                    <label class="custom-file-label custom-file-img-label" for="up-image1">Select file</label>
                                    <input type="file" class="custom-file-input" id="up-image3" name="up-image3" accept="image/*"
                                           onchange="encodeImgToBase64(this)">
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
<script src="../../assets/js/validate/validate-employee-form.js"></script>
<script>
  let countType = 1;
  function addType() {
    countType += 1;
    let list = $.parseJSON(data);
    console.log(list);
    $.each(list, function (index, item) {
      let html =
          '<tr>' +
          '<td>' +
          '<a href="#" class="media align-items-center">' +
          '<img class="m-auto avatar rounded-circle" src="' + item.image + '" alt="product_image" >' +
          '</a>' +
          '</td>' +
          '<td>' + item.id + '</td>' +
          '<td>' + item.productName + '</td>' +
          '<td>' + item.brand + '</td>' +
          '<td>' + item.seller + '</td>' +
          '<td>' + item.productRate + '</td>' +
          '<td>' + item.productOrigin + '</td>' +
          '<td>' + item.productDesc + '</td>' +
          '<td>' + item.priceOrigin + '</td>' +
          '<td>' + item.priceOrder + '</td>' +
          '<td class="td-actions text-center">' +
          '<a href="/admin/employee/edit?id=' + item.id +'" class="btn btn-primary px-2 py-1" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa thông tin">' +
          '<i class="fa fa-edit"></i>' +
          '</a>' +
          (item.status === "true" ?
              '<a href="#" class="btn btn-danger px-2 py-1" data-toggle="tooltip" data-placement="top" title="Chưa xác nhận">' +
              '<i class="fa fa-lock"></i>' +
              '</a>' :
              '<a href="#" class="btn btn-success px-2 py-1" data-toggle="tooltip" data-placement="top" title="Đã xác nhận">' +
              '<i class="fa fa-lock-open"></i>' +
              '</a>') +
          '</td>' +
          '</tr>';
      $('#list-type').append(html);
    });
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
