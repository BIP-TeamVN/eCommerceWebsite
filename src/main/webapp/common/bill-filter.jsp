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
         <option value="0">Đợi duyệt</option>
         <option value="2">Đã duyệt</option>
         <option value="4">Đang đợi shipper nhận hàng</option>
         <option value="5">Đang giao</option>
         <option value="6">Đã giao xong</option>
         <option value="7">Giao thất bại</option>
      </select>
   </div>
</div>
<script>
</script>