<%@ page import="java.util.Date" %>
<%@ page import="com.hknp.utils.DateTimeUtils" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.Duration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
   String minDob = DateTimeUtils.dateToString(Date.from(Instant.now().minus(Duration.ofDays(36520))), "yyyy-MM-dd");
   String defaultDob = DateTimeUtils.dateToString(Date.from(Instant.now().minus(Duration.ofDays(3652))), "yyyy-MM-dd");
   String today = DateTimeUtils.dateToString(Date.from(Instant.now()), "yyyy-MM-dd");
%>

<!-- Modal -->
<div id="modal-add-delivery" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-lg modal-dialog-scrollable modal-dialog-centered" role="document">
      <div class="modal-content">
         <div class="modal-header p-3">
            <h2 class="mx-3 my-2 text-center text-uppercase display-4">Thêm giao hàng mới</h2>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body p-3">
            <div class="row">
               <div class="col mx-3">
                  <form id="delivery-form" method="POST" enctype="multipart/form-data">
                     <!--Họ và tên-->
                     <div class="row">
                        <div class="col-md-6">
                           <div class="form-group">
                              <label for="last-name" class="form-control-label">Họ và tên đệm</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="Trường bắt buộc - Tối đa 40 ký tự">?</a>
                              <div>
                                 <input class="form-control" type="text" placeholder="VD: Nguyễn Văn" id="last-name"
                                        name="last-name" maxlength="40">
                              </div>
                              <small class="error-input text-danger">Vui lòng nhập họ và tên đệm</small>
                           </div>
                        </div>
                        <div class="col-md-6">
                           <div class="form-group">
                              <label for="first-name" class="form-control-label">Tên</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="Trường bắt buộc - Tối đa 10 ký tự">?</a>
                              <div>
                                 <input class="form-control" type="text" placeholder="VD: A" id="first-name"
                                        name="first-name" maxlength=10>
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
                           <input class="form-control" type="date" min="<%=minDob%>" value="<%=defaultDob%>" id="dob"
                                  name="dob">
                        </div>
                     </div>

                     <!--SDT và CMND-->
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
                           <!--CMND-->
                           <div class="form-group">
                              <label for="ssn" class="form-control-label">Căn cước công dân</label>
                              <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                                 data-toggle="popover" data-placement="right"
                                 data-content="9 hoặc 12 số">?</a>
                              <div>
                                 <input class="form-control" type="text" id="ssn" maxlength="12"
                                        placeholder="VD: 123456789 hoặc 123456789012">
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
                                  placeholder="VD: user@gmail.com">
                        </div>
                        <small class="error-input text-danger">Email sai định dạng</small>
                     </div>

                     <div class="row">
                        <div class="col-md-6 form-group">
                           <label for="salary" class="form-control-label">Mức lương</label>
                           <div>
                              <input class="form-control" type="number" id="salary" name="salary" min="1000" maxlength="20">
                           </div>
                           <small class="error-input text-danger">...</small>
                        </div>
                        <div class="col-md-6 form-group">
                           <label for="start-date" class="form-control-label">Ngày bắt đầu</label>
                           <a tabindex="-1" href="javascript:void(0)" class="badge badge-secondary"
                              data-toggle="popover" data-placement="right"
                              data-content="Mặc định là hôm nay">?</a>
                           <input class="form-control" type="date" min="2000-01-01" value="<%=today%>" id="start-date"
                                  name="start-date">
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
            <button class="btn btn-primary pl-6 pr-6" type="submit" form="delivery-form">Lưu</button>
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
            Thêm giao hàng thành công !
         </div>
         <div class="modal-footer">
            <button class="btn btn-primary px-4" type="button" data-dismiss="modal">OK</button>
         </div>
      </div>
   </div>
</div>

<script src="../../assets/js/dynamic-admin-unit-drop-down.js"></script>
<script src="../../assets/js/validate/validate-delivery-form.js"></script>
