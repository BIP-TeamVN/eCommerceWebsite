<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website - Admin</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>
<%@ include file="../../common/ad-side-nav.jsp" %>

<jsp:include page="../../common/ad-side-nav.jsp" >
   <jsp:param name="selectedIndex" value="5" />
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="../../common/ad-top-nav.jsp" %>

   <!--Header-->
   <%@include file="../../common/ad-header.jsp" %>

   <!-- Page content -->
   <div class="container-fluid mt--6">
      <div class="row">
      </div>
      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<!--Javascript-->
<%@ include file="../../common/import-js.jsp" %>
</body>
</html>
