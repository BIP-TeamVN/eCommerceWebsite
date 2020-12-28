<%@ page import="java.util.Date" %>
<%@ page import="com.hknp.utils.DateTimeUtils" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.Duration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%;
    String minDob = DateTimeUtils.dateToString(Date.from(Instant.now().minus(Duration.ofDays(36520))), "yyyy-MM-dd");
    String defaultDob = DateTimeUtils.dateToString(Date.from(Instant.now().minus(Duration.ofDays(3652))), "yyyy-MM-dd");
    String today = DateTimeUtils.dateToString(Date.from(Instant.now()), "yyyy-MM-dd");
%>>

<!-- Modal -->
<div class="modal fade bd-example-modal-lg" id="modal-add-product-category" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header p-3">
                <h5 class="display-3 mx-3 my-2 text-uppercase">Thêm ngành hàng mới</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body p-3">
                <div class="row">
                    <div class="col mx-3">
                        <form id="product-category-form" method="POST" enctype="multipart/form-data">
                            <!--Họ và tên-->
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label for="last-name" class="form-control-label">Họ và tên đệm</label>
                                        <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary" data-toggle="popover" data-placement="right" data-content="Trường bắt buộc - Tối đa 40 ký tự">?</a>
                                        <div>
                                            <input class="form-control" type="text" placeholder="VD: Nguyễn Văn" id="last-name" name="last-name" maxlength="40">
                                        </div>
                                        <small class="error-input text-danger">Vui lòng nhập họ và tên đệm</small>
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
                        </form>
                    </div>
                </div>
            </div>
            <div class="modal-footer p-3 text-uppercase">
                <button class="btn btn-secondary pl-6 pr-6" type="button" id="btn-cancel" data-dismiss="modal">Hủy</button>
                <button class="btn btn-primary pl-6 pr-6" type="submit" form="product-category-form">Lưu</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="import-js.jsp" %>
<script src="../../assets/js/dynamic-admin-unit-drop-down.js"></script>
<script src="../assets/js/validate/validate-product-category-form.js"></script>
