<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- Modal -->
<div id="modal-add-product-category" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-lg modal-dialog-scrollable modal-dialog-centered" role="document">
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
                     <!--Tên ngành hàng-->
                     <div class="row">
                        <div class="col">
                           <div class="form-group">
                              <label for="name" class="form-control-label">Tên ngành hàng</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="Trường bắt buộc - Tối đa 100 ký tự">?</a>
                              <div>
                                 <input class="form-control" type="text" placeholder="VD: Tên ngành hàng ..." id="name"
                                        name="name" maxlength="100">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập tên ngành hàng</small>
                           </div>
                        </div>
                     </div>
                     <!--Ảnh-->
                     <div class="form-group">
                        <label for="up-image" class="form-control-label d-inline-block w-100">Ảnh</label>
                        <img id="img-upload" class="d-none"/>
                        <div class="custom-file">
                           <label class="custom-file-label custom-file-img-label" for="up-image">Select file</label>
                           <input type="file" class="custom-file-input" id="up-image" name="up-image" accept="image/*"
                                  onchange="encodeImgToBase64(this)">
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

<!-- Modal add successful -->
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
            Thêm ngành hàng thành công !
         </div>
         <div class="modal-footer">
            <button class="btn btn-primary px-4" type="button" data-dismiss="modal">OK</button>
         </div>
      </div>
   </div>
</div>

<script src="../assets/js/dynamic-admin-unit-drop-down.js"></script>
<script src="../assets/js/validate/validate-product-category-form-em.js"></script>
