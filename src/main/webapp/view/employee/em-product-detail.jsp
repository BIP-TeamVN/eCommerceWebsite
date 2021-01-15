<%--
  Created by IntelliJ IDEA.
  User: namtr
  Date: 1/14/2021
  Time: 1:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<!-- Modal -->
<div div class="modal fade bd-example-modal-lg"  id="modal-product-detail" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
      <div class="modal-content">
         <div class="modal-header p-3">
            <h5 class="display-3 mx-3 my-2 text-uppercase">Xác nhận sản phẩm</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
            </button>
         </div>
         <div class="modal-body p-3">
            <div class="row">
               <div class="col mx-3">
                  <form id="product-detail-form">
                     <!--Mã-->
                     <div class="form-group">
                        <div id="id"></div>
                     </div>

                     <!--form edit-->
                     <!--Tên sản phẩm-->
                     <div class="form-group">
                        <div id="product-name"></div>
                     </div>

                     <!--Nhãn hiệu, xuất xứ-->
                     <div class="row">
                        <div class="col-md-6 form-group">
                           <div class="form-group">
                              <div id="brand"></div>
                           </div>
                        </div>
                        <div class="col-md-6 form-group">
                           <div class="form-group">
                              <div id="product-origin"></div>
                           </div>
                        </div>
                     </div>

                     <!--Mô tả-->
                     <div class="form-group">
                        <div id="product-desc"></div>
                     </div>

                     <!--giá-->
                     <div class="row">
                        <div class="col-md-6 form-group">
                           <div class="form-group">
                              <div id="price-origin"></div>
                           </div>
                        </div>
                        <div class="col-md-6 form-group">
                           <div class="form-group">
                              <div id="price-order"></div>
                           </div>
                        </div>
                     </div>

                     <!--liệt kê loại sản phẩm và số lượng -->
                     <div class="row">
                        <div class="col">
                           <label class="form-control-label"><b>Loại sản phẩm</b></label>
                        </div>
                     </div>
                     <div class="row" id="list-types"></div>

                     <!--chọn ngành hàng-->
                     <div class="row form-group">
                        <div class="col form-group">
                           <label id="categories" class="form-control-label"></label>
                        </div>
                     </div>

                     <!--Ảnh-->
                     <div class="row">
                        <div class="col-md-4 form-group">
                           <label for="img-upload-0" class="form-control-label d-inline-block w-100">Ảnh bìa</label>
                           <img id="img-upload-0" class="mb-2 rounded avatar-img"/>
                        </div>
                        <div class="col-md-4 form-group">
                           <label for="img-upload-1" class="form-control-label d-inline-block w-100">Ảnh 1</label>
                           <img id="img-upload-1" class="mb-2 rounded avatar-img"/>
                        </div>
                        <div class="col-md-4 form-group">
                           <label for="img-upload-2" class="form-control-label d-inline-block w-100">Ảnh 2</label>
                           <img id="img-upload-2" class="mb-2 rounded avatar-img"/>
                        </div>
                        <div class="col-md-4 form-group">
                           <label for="img-upload-3" class="form-control-label d-inline-block w-100">Ảnh 3</label>
                           <img id="img-upload-3" class="mb-2 rounded avatar-img"/>
                        </div>
                        <div class="col-md-4 form-group">
                           <label for="img-upload-4" class="form-control-label d-inline-block w-100">Ảnh 4</label>
                           <img id="img-upload-4" class="mb-2 rounded avatar-img"/>
                        </div>
                     </div>

                     <!--Button-->
                     <div class="row mt-4">
                        <div class="col-md-6 text-md-right text-center">
                           <button type="submit" class="btn btn-primary px-6">XÁC NHẬN</button>
                        </div>
                        <div class="col-md-6 text-md-left text-center">
                           <button type="button" data-toggle="modal" data-target="#conform-modal" class="btn btn-secondary px-6">TỪ CHỐI</button>
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
                        Bạn có muốn từ chối sản phẩm này không ?
                     </div>
                     <div class="modal-footer">
                        <button type="button" class="btn btn-secondary px-3" data-dismiss="modal">KHÔNG</button>
                        <a href="/employee/product" class="btn btn-primary px-4">CÓ</a>
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
                        Xác nhận sản phẩm thành công !
                     </div>
                     <div class="modal-footer">
                        <a href="/employee/product" class="btn btn-primary px-4">OK</a>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
<%@ include file="../../common/import-js.jsp" %>
<script>
  let countPara = 0;
  let typesPara = [];
  let quantitiesPara = [];
  let imagesPara = [];
  function addType() {
    $('#list-types').find('div').remove();
    for (let countType = 0; countType < countPara; countType++) {
      let html =
           '<div class="col-md-4 form-group">' +
               '<label for="img-upload-type-' + countType + '" class="form-control-label d-inline-block w-100">' +
                 '  <b>Tên loại:</b> ' + typesPara[countType] + '' +
                 '<br>  <b>Số lượng:</b> ' + quantitiesPara[countType] +
               '</label>' +
               '<img id="img-upload-type-' + countType + '" class="mb-2 rounded avatar-img" src="' + imagesPara[countType] + '"/>' +
           '</div>';
      $('#list-types').append(html);
    }
  }
</script>
<script>
   $('#product-detail-form').submit(function (e) {
     e.preventDefault();
     console.log(id);
     let para = JSON.stringify({
            'id': id.toString(),
            'status': '1'});
     $.ajax({
       url: '/api/product/verify',
       method: 'PUT',
       data: para,
       cache: false,
       success: function (data) {
         e.preventDefault();
         $('#successful-modal').modal('show');
       }
     });
   });
</script>


