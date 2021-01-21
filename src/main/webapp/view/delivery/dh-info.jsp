<%@ page import="java.util.Date" %>
<%@ page import="com.hknp.utils.DateTimeUtils" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.Duration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String minDob = DateTimeUtils.dateToString(Date.from(Instant.now().minus(Duration.ofDays(36520))), "yyyy-MM-dd");
%>

<html lang="vi">
<head>
    <%@ include file="../../common/meta-info.jsp" %>
    <title>BIP - Kênh giao hàng</title>
    <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="dh--side-nav.jsp">
    <jsp:param name="selectedIndex" value="0"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
    <!--Top navigation-->
    <%@include file="./dh--top-nav.jsp" %>

    <!-- Page content -->
    <div class="container-fluid">
        <!-- Breadcrumb -->
        <div class="row mt-4">
            <div class="col-md-10 ml-auto mr-auto">
                <nav aria-label="breadcrumb" role="navigation">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                        <li class="breadcrumb-item"><a href="/admin/delivery">Giao hàng</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Sửa thông tin</li>
                    </ol>
                </nav>
            </div>
        </div>

        <!--Title-->
        <div class="row">
            <div class="col-md-10 ml-auto mr-auto">
                <h2 class="display-3 text-center text-uppercase my-5">Chỉnh sửa thông tin cá nhân</h2>
            </div>
        </div>

        <!-- form edit -->
        <div class="row">
            <div class="col-md-10 ml-auto mr-auto">
                <form id="delivery-edit-form">
                    <!--Mã-->
                    <div class="form-group">
                        <label for="id" class="form-control-label">Mã</label>
                        <div>
                            <input class="form-control" type="email" id="id" name="id" readonly
                                   value="${deliveryEntity.getUserId()}">
                        </div>
                    </div>

                    <!--Họ và tên-->
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="last-name" class="form-control-label">Họ và tên đệm</label>
                                <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right" data-content="Trường bắt buộc - Tối đa 40 ký tự">?</a>
                                <div>
                                    <input class="form-control" type="text" placeholder="VD: Nguyễn Văn" id="last-name"
                                           name="last-name" maxlength="40" value="${deliveryEntity.getUserEntity().getLastName()}">
                                </div>
                                <small class="error-input text-danger">Vui lòng nhập họ và tên đệm</small>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="first-name" class="form-control-label">Tên</label>
                                <a tabindex="-1" tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right"
                                   data-content="Trường bắt buộc - Tối đa 10 ký tự">?</a>
                                <div>
                                    <input class="form-control" type="text" placeholder="VD: A" id="first-name" name="first-name" value="${deliveryEntity.getUserEntity().getFirstName()}" maxlength=10>
                                </div>
                                <small class="error-input text-danger">Vui lòng nhập tên</small>
                            </div>
                        </div>
                    </div>

                    <!--Giới tính và ngày sinh-->
                    <div class="row">
                        <div class="col-md-6 form-group">
                            <label for="gender" class="form-control-label">Giới tính</label>
                            <select class="form-control" id="gender" name="gender" required>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                                <option value="Khác" selected>Khác</option>
                            </select>
                        </div>
                        <div class="col-md-6 form-group">
                            <label for="dob" class="form-control-label">Ngày sinh (không bắt buộc)</label>
                            <input class="form-control" type="date" min="<%=minDob%>" value="${deliveryEntity.getUserEntity().getDateOfBirthStr("yyyy-MM-dd")}" id="dob" name="dob">
                        </div>
                    </div>

                    <!--SDT và CMND-->
                    <div class="row">
                        <div class="col-md-6">
                            <!--SDT-->
                            <div class="form-group">
                                <label for="phone-number" class="form-control-label">Số điện thoại</label>
                                <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover"
                                   data-placement="right"
                                   data-content="10 số, bắt đầu từ số 0">?</a>
                                <div>
                                    <input class="form-control" type="tel" id="phone-number" name="phone-number" maxlength="10"
                                           placeholder="VD: 0987654321" value="${deliveryEntity.getUserEntity().getPhoneNumber()}">
                                </div>
                                <small class="error-input text-danger">Số điện thoại không hợp lệ</small>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <!--CMND-->
                            <div class="form-group">
                                <label for="ssn" class="form-control-label">Căn cước công dân</label>
                                <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover"
                                   data-placement="right" data-content="9 hoặc 12 số">?</a>
                                <div>
                                    <input class="form-control" type="text" id="ssn" maxlength="12"
                                           placeholder="VD: 123456789 hoặc 123456789012" value="${deliveryEntity.getUserEntity().getSsn()}">
                                </div>
                                <small class="error-input text-danger">Vui lòng nhập chứng mình nhân dân</small>
                            </div>
                        </div>
                    </div>

                    <!--Mail-->
                    <div class="form-group">
                        <label for="email" class="form-control-label">Email</label>
                        <div>
                            <input class="form-control" type="email" id="email" name="email" maxlength="40"
                                   placeholder="VD: user@gmail.com" value="${deliveryEntity.getUserEntity().getEmail()}">
                        </div>
                        <small class="error-input text-danger">Email sai định dạng</small>
                    </div>

                    <div class="row">
                        <div class="col-md-6 form-group">
                            <label for="email" class="form-control-label">Mức lương</label>
                            <div>
                                <input class="form-control" type="text" id="salary" name="salary" maxlength="20" readonly
                                       value="${deliveryEntity.getSalary()}">
                            </div>
                            <small class="error-input text-danger">...</small>
                        </div>
                        <div class="col-md-6 form-group">
                            <label for="start-date" class="form-control-label">Ngày bắt đầu</label>
                            <a tabindex="-1" tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                               data-toggle="popover" data-placement="right"
                               data-content="Mặc định là hôm nay">?</a>
                            <input class="form-control" type="date" min="2000-01-01" value="${deliveryEntity.getStartDateStr("yyyy-MM-dd")}" id="start-date"
                                   name="start-date" readonly>
                        </div>
                    </div>

                    <!--Tỉnh - huyện - xã-->
                    <div class="row">
                        <!--Tỉnh/ thành phố-->
                        <div class="col-md-4 form-group">
                            <label for="province" class="form-control-label">Tỉnh/ Thành phố</label>
                            <div>
                                <select class="form-control" id="province" name="province" required>
                                    <option value="00">Chọn tỉnh/ thành phố</option>
                                </select>
                            </div>
                            <small class="error-input text-danger">Vui lòng chọn tỉnh/ thành phố</small>
                        </div>

                        <!--Quận/ huyện-->
                        <div class="col-md-4 form-group">
                            <label for="district" class="form-control-label">Quận/ Huyện</label>
                            <div>
                                <select class="form-control" id="district" name="district" required>
                                    <option value="000">Chọn quận/ huyện</option>
                                </select>
                            </div>
                            <small class="error-input text-danger">Vui lòng chọn quận/ huyện</small>
                        </div>

                        <!--Xã phường-->
                        <div class="col-md-4 form-group">
                            <label for="commune" class="form-control-label">Xã/ Phường</label>
                            <div>
                                <select class="form-control" id="commune" name="commune" required>
                                    <option value="00000">Chọn xã/ phường</option>
                                </select>
                            </div>
                            <small class="error-input text-danger">Vui lòng chọn xã/ phường</small>
                        </div>
                    </div>

                    <!--Số nhà-->
                    <div class="form-group">
                        <label for="address-street" class="form-control-label">Địa chỉ</label>
                        <div>
                            <textarea class="form-control" id="address-street" name="address-street" placeholder="VD: Số 1 Đường ABC" rows="3">${deliveryEntity.getUserEntity().getAddressEntities().get(0).getStreet()}</textarea>
                        </div>
                        <small class="error-input text-danger">Vui lòng nhập địa chỉ</small>
                    </div>

                    <!--Ảnh-->
                    <div class="form-group">
                        <label for="up-image" class="form-control-label d-inline-block w-100">Ảnh</label>
                        <img id="img-upload" class="mb-2 rounded avatar-img" src="${deliveryEntity.getUserEntity().getImageSrc()}"/>
                        <div class="custom-file">
                            <label class="custom-file-label custom-file-img-label" for="up-image">Select file</label>
                            <input type="file" class="custom-file-input" id="up-image" name="up-image" accept="image/*"
                                   onchange="encodeImgToBase64(this)">
                        </div>
                    </div>

                    <!--Button-->
                    <div class="row mt-6">
                        <div class="col-md-6 text-md-right text-center">
                            <button type="submit" class="btn btn-primary px-6">LƯU</button>
                        </div>
                        <div class="col-md-6 text-md-left text-center">
                            <button type="button" data-toggle="modal" data-target="#conform-modal" class="btn btn-secondary px-6">HỦY</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Modal conform close -->
        <div class="modal fade" id="conform-modal" tabindex="-1" role="dialog" aria-labelledby="conform-modal-lb" aria-hidden="true">
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
                        <a href="/delivery" class="btn btn-primary px-4">CÓ</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal update successful -->
        <div class="modal fade" id="successful-modal" tabindex="-1" role="dialog" aria-labelledby="conform-modal-lb" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="successful-modal-lb">Thông báo</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Cập nhật thông tin thành công !
                    </div>
                    <div class="modal-footer">
                        <a href="/delivery" class="btn btn-primary px-4">OK</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="../../common/footer.jsp" %>
    </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
<script>
  const ADMIN_UNIT_API_URL = "/api/admin-units";

  $.ajax({
    url: ADMIN_UNIT_API_URL,
    method: "GET",
    data: {
      type: 'province'
    },
    cache: false,
    success: function (data) {
      let obj = $.parseJSON(data);
      console.log(obj);
      $.each(obj, function (key, value) {
        if (value.id === '${deliveryEntity.getUserEntity().getAddressEntities().get(0).getProvinceEntity().getProvinceId()}') {
          $('#province').append('<option selected value="' + value.id + '">' + value.name + '</option>');
          $('#province').trigger('change');
        } else {
          $('#province').append('<option value="' + value.id + '">' + value.name + '</option>');
        }
      });
    }
  });

  $('#province').change(function () {
    $('#district').find('option').remove();
    $('#district').append('<option value="000">Chọn quận/ huyện</option>');
    $('#commune').find('option').remove();
    $('#commune').append('<option value="00000">Chọn xã/ phường</option>');

    $.ajax({
      url: ADMIN_UNIT_API_URL,
      method: "GET",
      data: {
        type: "district",
        id: $('#province').val()
      },
      cache: false,
      success: function (data) {
        let obj = $.parseJSON(data);
        console.log(obj);
        $.each(obj, function (key, value) {
          if (value.id === '${deliveryEntity.getUserEntity().getAddressEntities().get(0).getDistrictEntity().getDistrictId()}') {
            $('#district').append('<option selected value="' + value.id + '">' + value.name + '</option>');
            $('#district').trigger('change');
          } else {
            $('#district').append('<option value="' + value.id + '">' + value.name + '</option>');
          }
        });
      }
    });
  });

  $('#district').change(function () {
    $('#commune').find('option').remove();
    $('#commune').append('<option value="00000">Chọn xã/ phường</option>');

    $.ajax({
      url: ADMIN_UNIT_API_URL,
      method: "GET",
      data: {
        type: "commune",
        id: $('#district').val()
      },
      cache: false,
      success: function (data) {
        let obj = $.parseJSON(data);
        console.log(obj);
        $.each(obj, function (key, value) {
          if (value.id === '${deliveryEntity.getUserEntity().getAddressEntities().get(0).getCommuneEntity().getCommuneId()}') {
            $('#commune').append('<option selected value="' + value.id + '">' + value.name + '</option>');
          } else {
            $('#commune').append('<option value="' + value.id + '">' + value.name + '</option>');
          }
        });
      }
    });
  });
</script>
<script src="../../assets/js/validate/validate-delivery-edit-form.js"></script>
</body>
</html>
