<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <title>Admin - Home</title>
</head>
<body>

<h1>Admin - Home</h1>

<select name="category">
   <c:forEach items="${listProvince}" var="province">
      <option value="${province.getProvinceId()}">${province.getProvinceName()}</option>
   </c:forEach>

</select>

<p>New id ${newId}</p>


</body>
</html>
