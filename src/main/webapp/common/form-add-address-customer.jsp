<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- Modal -->
<div class="modal fade" id="modal-add-address" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-lg modal-dialog-scrollable modal-dialog-centered" role="document">
      <div class="modal-content">
         <div class="modal-header p-3">
            <h2 class="mx-3 my-2 text-center text-uppercase display-4">Thêm địa chỉ mới</h2>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body p-3">
            <div class="row">
               <div class="col mx-3">
                  <form id="address-add-form" enctype="multipart/form-data">
                     <!--Họ và tên-->
                     <div class="row">
                        <div class="col">
                           <div class="form-group">
                              <label for="full-name" class="form-control-label">Họ và tên</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="Trường bắt buộc - Tối đa 40 ký tự">?</a>
                              <div>
                                 <input class="form-control" type="text" placeholder="VD: Nguyễn Văn" id="full-name"
                                        name="full-name" maxlength="40">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập họ và tên</small>
                           </div>
                        </div>
                     </div>

                     <!--SDT và loại địa chỉ-->
                     <div class="row">
                        <div class="col-md-6">
                           <!--SDT-->
                           <div class="form-group">
                              <label for="phone-number" class="form-control-label">Số điện thoại</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="10 số, bắt đầu từ số 0">?</a>
                              <div>
                                 <input class="form-control" type="tel" id="phone-number" name="phone-number"
                                        maxlength="10" placeholder="VD: 0987654321">
                              </div>
                              <small class="error-input text-danger">Số điện thoại không hợp lệ</small>
                           </div>
                        </div>
                        <div class="col-md-6">
                           <!--Loại địa chỉ-->
                           <div class="form-group">
                              <label for="type-address" class="form-control-label">Loại địa chỉ</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="Phải là chuỗi hợp lệ">?</a>
                              <div>
                                 <input class="form-control" type="text" id="type-address"
                                        placeholder="VD:Nhà hoặc công ty">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập loại địa chỉ</small>
                           </div>
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
                           <textarea class="form-control" id="address-street" name="address-street"
                                     placeholder="VD: Số 1 Đường ABC" rows="3"></textarea>
                        </div>
                        <small class="error-input text-danger">Vui lòng nhập địa chỉ</small>
                     </div>
                  </form>
               </div>
            </div>
         </div>
         <div class="modal-footer p-3 text-uppercase">
            <button class="btn btn-secondary pl-6 pr-6" type="button" id="btn-cancel" data-dismiss="modal">Hủy</button>
            <button class="btn btn-primary pl-6 pr-6" type="submit" form="address-add-form">Lưu</button>
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
            Thêm địa chỉ thành công !
         </div>
         <div class="modal-footer">
            <button class="btn btn-primary px-4" type="button" data-dismiss="modal">OK</button>
         </div>
      </div>
   </div>
</div>

<script src="../../assets/js/dynamic-admin-unit-drop-down.js"></script>
<script src="../../assets/js/validate/validate-address-customer-form.js"></script>