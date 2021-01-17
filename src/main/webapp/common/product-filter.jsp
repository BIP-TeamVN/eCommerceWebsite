<%--
  Created by IntelliJ IDEA.
  User: namtr
  Date: 1/16/2021
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
<script>
  function changeStatus(){
    $.ajax({
      url: '/api/count-product-count',
      method: 'GET',
      data: {
        'page': currentPage,
        'status': $('#status').val(),
        'keyword': $('#search-keyword').val()
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