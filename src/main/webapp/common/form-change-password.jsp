<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- Modal modal-change-password-->
<div class="modal fade bd-example-modal-lg" id="modal-change-password" tabindex="-1" role="dialog" aria-labelledby="modal-change-password" aria-hidden="true">
   <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
      <div class="modal-content">
         <div class="modal-header p-3">
            <h5 class="display-4 mx-3 my-2 text-uppercase">Thay đổi mật khẩu</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body p-3">
            <div class="row">
               <div class="col mx-3">
                  <form id="change-password-form">
                     <!--Mật khẩu hiện tại-->
                     <div class="row">
                        <div class="col-md-12">
                           <div class="form-group">
                              <label for="current-password" class="form-control-label">Mật khẩu hiện tại</label>
                              <div>
                                 <input class="form-control" placeholder="************" type="password" id="current-password" name="current-password">
                              </div>
                              <small class="error-input text-danger"></small>
                           </div>
                        </div>
                     </div>
                     <!--Mật khẩu mới-->
                     <div class="row">
                        <div class="col-md-12">
                           <div class="form-group">
                              <label for="new-password" class="form-control-label">Mật khẩu mới</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right" data-content="Tối đa 16 ký tự">?</a>
                              <div>
                                 <input class="form-control" placeholder="************" type="password" id="new-password" name="new-password" maxlength="16">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập mật khẩu mới</small>
                           </div>
                        </div>
                     </div>
                     <!--Nhập lại mật khẩu-->
                     <div class="row">
                        <div class="col-md-12">
                           <div class="form-group">
                              <label for="retype-password" class="form-control-label">Xác nhận mật khẩu</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right" data-content="Tối đa 16 ký tự">?</a>
                              <div>
                                 <input class="form-control" placeholder="************" type="password" id="retype-password" name="retype-password" maxlength="16">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập mật khẩu mới</small>
                           </div>
                        </div>
                     </div>
                  </form>
               </div>
            </div>
         </div>
         <div class="modal-footer p-3 text-uppercase">
            <button class="btn btn-secondary pl-6 pr-6" type="button" id="btn-cancel-password" data-dismiss="modal">Hủy</button>
            <button class="btn btn-primary pl-6 pr-6" type="submit" form="change-password-form">Lưu</button>
         </div>
      </div>
   </div>
</div>

<script src="../../assets/js/validate/validate-change-password.js"></script>