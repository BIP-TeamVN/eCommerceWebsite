<%--
  Created by IntelliJ IDEA.
  User: namtr
  Date: 1/16/2021
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="col-md-16 text-right">
   <div class="d-inline-block ml-2">
      <select class="form-control" id="status" name="status" required onchange="changeStatus()">
         <option value="2021">2021</option>
         <option value="2020">2020</option>
         <option value="2019">2019</option>
      </select>
   </div>
</div>
<script>
  function changeStatus(){
    reloadPage();
  }
</script>