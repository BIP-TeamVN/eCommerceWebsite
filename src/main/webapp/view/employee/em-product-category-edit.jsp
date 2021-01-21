<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="vi">
<head>
    <%@ include file="../../common/meta-info.jsp" %>
    <title>BIP - Trang nhân viên</title>
    <%@ include file="../../common/link-css.jsp" %>
    <!--Javascript-->
    <%@ include file="../../common/import-js.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="./em--side-nav.jsp">
    <jsp:param name="selectedIndex" value="0"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
    <!--Top navigation-->
    <%@include file="./em--top-nav.jsp" %>

    <!--Header and breadcrumb-->
    <div class="header bg-primary pb-6">
        <div class="container-fluid">
            <div class="header-body">
                <div class="row align-items-center py-4">
                    <div class="col-lg-6 col-7">
                        <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
                            <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                                <li class="breadcrumb-item"><a href="/employee"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                                <li class="breadcrumb-item"><a href="/employee/category">Ngành hàng</a></li>
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
        <!--List category card-->
        <div class="row">
            <div class="col">
                <div class="card">
                    <!-- Card header -->
                    <div class="card-header border-0">
                        <h2 class="mb-0 text-center text-uppercase display-4">Chỉnh sửa thông tin ngành hàng</h2>
                    </div>

                    <!-- form edit -->
                    <form id="product-category-edit-form" class="px-5">
                        <!--Mã-->
                        <div class="form-group">
                            <label for="id" class="form-control-label">Mã</label>
                            <div>
                                <input class="form-control" type="text" id="id" name="id" readonly
                                       value="${productCategoryEdit.getProductCategoryId()}">
                            </div>
                        </div>

                        <!--Tên ngành hàng-->
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <label for="name" class="form-control-label">Tên ngành hàng</label>
                                    <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right" data-content="Trường bắt buộc - Tối đa 100 ký tự">?</a>
                                    <div>
                                        <input class="form-control" type="text" placeholder="VD: Tên ngành hàng ..." id="name" name="name" maxlength="100"
                                               value="${productCategoryEdit.getProductCategoryName()}">
                                    </div>
                                    <small class="error-input text-danger">Vui lòng nhập tên ngành hàng</small>
                                </div>
                            </div>
                        </div>
                        <!--Ảnh-->
                        <div class="form-group">
                            <label for="up-image" class="form-control-label d-inline-block w-100">Ảnh</label>
                            <img id="img-upload" class="mb-2 rounded avatar-img" src="${productCategoryEdit.getImageSrc()}"/>
                            <div class="custom-file">
                                <label class="custom-file-label custom-file-img-label" for="up-image">Select file</label>
                                <input type="file" class="custom-file-input" id="up-image" name="up-image" accept="image/*"
                                       onchange="encodeImgToBase64(this)">
                            </div>
                        </div>
                    </form>

                    <!-- Card footer -->
                    <div class="card-footer py-4">
                        <div class="row">
                            <div class="col-md-6 text-md-right text-center mb-sm-3">
                                <button type="submit" form="product-category-edit-form" class="btn btn-primary px-6">
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
                        <a href="/employee/category" class="btn btn-primary px-4">CÓ</a>
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
                        Cập nhật thông tin ngành hàng thành công !
                    </div>
                    <div class="modal-footer">
                        <a href="/employee/category" class="btn btn-primary px-4">OK</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="../../common/footer.jsp" %>
    </div>
</div>

<script src="../../assets/js/validate/validate-product-category-edit-form-em.js"></script>
</body>
</html>
