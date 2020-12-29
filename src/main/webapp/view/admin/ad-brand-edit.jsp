<%@ page import="java.util.Date" %>
<%@ page import="com.hknp.utils.DateTimeUtils" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.Duration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="../../common/meta-info.jsp" %>
    <title>eCommerce Website - Admin</title>
    <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="./ad--side-nav.jsp">
    <jsp:param name="selectedIndex" value="7"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
    <!--Top navigation-->
    <%@include file="../../common/ad-top-nav.jsp" %>

    <!-- Page content -->
    <div class="container-fluid">
        <!-- Breadcrumb -->
        <div class="row mt-4">
            <div class="col-md-10 ml-auto mr-auto">
                <nav aria-label="breadcrumb" role="navigation">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Thương hiệu</li>
                    </ol>
                </nav>
            </div>
        </div>

        <!--Title-->
        <div class="row">
            <div class="col-md-10 ml-auto mr-auto">
                <h2 class="display-3 text-center text-uppercase my-5">Chỉnh sửa thông tin thương hiệu</h2>
            </div>
        </div>

        <!-- form edit -->
        <div class="row">
            <div class="col-md-10 ml-auto mr-auto">
                <form id="brand-edit-form" method="POST" enctype="multipart/form-data">
                    <!--Tên thương hiệu-->
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="brandName" class="form-control-label">Tên thương hiệu</label>
                                <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right" data-content="Trường bắt buộc - Tối đa 100 ký tự">?</a>
                                <div>
                                    <input class="form-control" type="text" placeholder="VD: Tên thương hiệu ..." id="brandName" name="brandName" maxlength="100"
                                           value="${brandEdit.getBrandName()}">
                                </div>
                                <small class="error-input text-danger">Vui lòng nhập tên thương hiệu</small>
                            </div>
                        </div>
                    </div>
                    <!--Xuất xứ-->
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="brandOrigin" class="form-control-label">Xuất xứ</label>
                                <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right" data-content="Trường bắt buộc - Tối đa 30 ký tự">?</a>
                                <div>
                                    <input class="form-control" type="text" placeholder="VD: Xuất xứ ..." id="brandOrigin" name="brandOrigin" maxlength="30"
                                           value="${brandEdit.getBrandOrigin()}">
                                </div>
                                <small class="error-input text-danger">Vui lòng nhập xuất xứ</small>
                            </div>
                        </div>
                    </div>
                    <!--Ảnh-->
                    <div class="form-group">
                        <label for="up-image" class="form-control-label">Ảnh</label>
                        <div class="custom-file">
                            <label class="custom-file-label custom-file-img-label" for="up-image">Select file</label>
                            <input type="file" class="custom-file-input" id="up-image" name="up-image" accept="image/*"
                                   lang="vi">
                        </div>
                    </div>

                    <!--Button-->
                    <div class="row mt-6">
                        <div class="col-md-6 text-md-right text-center">
                            <button type="submit" class="btn btn-primary pl-6 pr-6">LƯU</button>
                        </div>
                        <div class="col-md-6 text-md-left text-center">
                            <a href="/admin/brand" class="btn btn-secondary pl-6 pr-6">HỦY</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="../../common/footer.jsp" %>
    </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
<script>

  $(document).ready(function () {
    const apiUrl = "/api/brands";

    $.ajax({
      url: apiUrl,
      method: 'PUT',
      data: {
        page: '1',
      },
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
      },
      cache: false
    });
  });
</script>
</body>
</html>
