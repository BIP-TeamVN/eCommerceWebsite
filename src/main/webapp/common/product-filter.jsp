<%--
  Created by IntelliJ IDEA.
  User: namtr
  Date: 1/16/2021
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="row mb-3">
   <div class="col-md-6">
      <form class="navbar-search navbar-search-light form-inline mr-sm-3 d-inline-block" id="navbar-search-main">
         <div class="form-group mb-0">
            <div class="input-group input-group-alternative input-group-merge">
               <div class="input-group-prepend">
                  <span class="input-group-text"><i class="fas fa-search"></i></span>
               </div>
               <input class="form-control" placeholder="Tìm kiếm" type="text">
            </div>
         </div>
         <button type="button" class="close" data-action="search-close" data-target="#navbar-search-main"
                 aria-label="Close">
            <span aria-hidden="true">×</span>
         </button>
      </form>
   </div>
   <div class="col-md-6 text-right">
      <div class="d-inline-block ml-2">
         <select class="form-control" id="status" name="status" required onchange="changeStatus()">
            <option value="0">Chưa xác nhận</option>
            <option value="1">Đã xác nhận</option>
            <option value="2">Đã từ chối</option>
            <option selected value="3">Tất cả</option>
         </select>
      </div>
   </div>
</div>
<script>
  function changeStatus(){
    $.ajax({
      url: '/api/count-product-count',
      method: 'GET',
      data: {
        'page': currentPage,
        'status': $('#status').val()
      },
      cache: false,
      async: false,
      success: function (data) {
        let result = data.toString().split(",");
        totalPage = parseInt(result[0]);
        currentPage = parseInt(result[1]);
        reloadPage();
      }
    });
  }
</script>