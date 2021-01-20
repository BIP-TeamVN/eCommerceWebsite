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
         <option id="option-status-0" value="0">Đợi duyệt</option>
         <option id="option-status-2" value="2">Đã duyệt</option>
         <option id="option-status-4" value="4">Đang đợi shipper nhận hàng</option>
         <option id="option-status-5" value="5">Đang giao</option>
         <option id="option-status-6" value="6">Đã giao xong</option>
         <option id="option-status-7" value="7">Giao thất bại</option>
      </select>
   </div>
</div>
<script>
  function changeStatus(){
    $.ajax({
      url: '/api/count-bill-count',
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