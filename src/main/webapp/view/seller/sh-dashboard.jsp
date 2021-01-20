<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<html>
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>eCommerce Website - Admin</title>
   <%@ include file="../../common/link-css.jsp" %>
</head>

<body>

<!--Left side nav-->
<jsp:include page="./sh--side-nav.jsp">
   <jsp:param name="selectedIndex" value="0"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <%@include file="./sh--top-nav.jsp" %>

   <!-- Header -->
   <!-- Header -->
   <div class="header bg-primary pb-6">
      <div class="container-fluid">
         <div class="header-body">
            <div class="row align-items-center py-4">
               <div class="col-lg-6 col-7">
                  <h6 class="h2 text-white d-inline-block mb-0">Default</h6>
                  <nav aria-label="breadcrumb" class="d-none d-md-inline-block ml-md-4">
                     <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                        <li class="breadcrumb-item"><a href="#"><i class="fas fa-home"></i></a></li>
                        <li class="breadcrumb-item"><a href="#">Dashboards</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Shop</li>
                     </ol>
                  </nav>
               </div>
               <div class="col-lg-6 col-5 text-right">
                  <a href="#" class="btn btn-sm btn-neutral">New</a>
                  <a href="#" class="btn btn-sm btn-neutral">Filters</a>
               </div>
            </div>
            <!-- Card stats -->
            <div class="row">
               <div class="col-xl-3 col-md-6">
                  <div class="card card-stats">
                     <!-- Card body -->
                     <div class="card-body">
                        <div class="row">
                           <div class="col">
                              <h5 class="card-title text-uppercase text-muted mb-0">Tổng số sản phẩm</h5>
                              <span class="h2 font-weight-bold mb-0">${totalProduct}</span>
                           </div>
                           <div class="col-auto">
                              <div class="icon icon-shape bg-gradient-red text-white rounded-circle shadow">
                                 <i class="fa fa-box text-default"></i>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-3 col-md-6">
                  <div class="card card-stats">
                     <!-- Card body -->
                     <div class="card-body">
                        <div class="row">
                           <div class="col">
                              <h5 class="card-title text-uppercase text-muted mb-0">Tổng số đơn hàng</h5>
                              <span class="h2 font-weight-bold mb-0">${totalBill}</span>
                           </div>
                           <div class="col-auto">
                              <div class="icon icon-shape bg-gradient-orange text-white rounded-circle shadow">
                                 <i class="fa fa-file-invoice text-white"></i>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-3 col-md-6">
                  <div class="card card-stats">
                     <!-- Card body -->
                     <div class="card-body">
                        <div class="row">
                           <div class="col">
                              <h5 class="card-title text-uppercase text-muted mb-0">Tổng số khách hàng</h5>
                              <span class="h2 font-weight-bold mb-0">${totalCustomer}</span>
                           </div>
                           <div class="col-auto">
                              <div class="icon icon-shape bg-gradient-green text-white rounded-circle shadow">
                                 <i class="fa fa-users text-white"></i>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="col-xl-3 col-md-6">
                  <div class="card card-stats">
                     <!-- Card body -->
                     <div class="card-body">
                        <div class="row">
                           <div class="col">
                              <h5 class="card-title text-uppercase text-muted mb-0">Tổng doanh số</h5>
                              <span class="h2 font-weight-bold mb-0">${totalSale}</span>
                           </div>
                           <div class="col-auto">
                              <div class="icon icon-shape bg-gradient-info text-white rounded-circle shadow">
                                 <i class="ni ni-money-coins"></i>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
   <!-- Page content -->
   <div class="container-fluid mt--6">
      <div class="row">
         <div class="col-xl-8">
            <div class="card bg-default">
               <div class="card-header bg-transparent">
                  <div class="row align-items-center">
                     <div class="col">
                        <h6 class="text-light text-uppercase ls-1 mb-1">Biểu đồ</h6>
                        <h5 class="h3 text-white mb-0">Doanh số</h5>
                     </div>
                     <div class="col">
                        <ul class="nav nav-pills justify-content-end">
                           <li class="nav-item mr-2 mr-md-0" data-toggle="chart" data-target="#chart-sales-dark"
                               data-update='{"data":{"datasets":[{"data":[${T2}, ${T3}, ${T4}, ${T5}, ${T6} ${T7}, ${T8}, ${T9}]}]}}'
                               data-prefix="$" data-suffix="k">
                              <a href="#" class="nav-link py-2 px-3 active" data-toggle="tab">
                                 <span class="d-none d-md-block">Năm</span>
                                 <span class="d-md-none">M</span>
                              </a>
                           </li>
                        </ul>
                     </div>
                  </div>
               </div>
               <div class="card-body">
                  <!-- Chart -->
                  <div class="chart">
                     <!-- Chart wrapper -->
                     <canvas id="myChart"></canvas>
                  </div>
               </div>
            </div>
         </div>
         <div class="col-xl-4">
            <div class="card">
               <div class="card-header bg-transparent">
                  <div class="row align-items-center">
                     <div class="col">
                        <h6 class="text-uppercase text-muted ls-1 mb-1">Đơn hàng</h6>
                        <h5 class="h3 mb-0">Thống kê</h5>
                     </div>
                  </div>
               </div>
               <div class="card-body">
                  <!-- Chart -->
                  <div class="chart">
                     <canvas id="myCharttt" height="128px" width="128px"></canvas>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>
<%@ include file="../../common/import-js.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
  $.ajax({
    url: '/api/statists-each-seller',
    method: "GET",
    data: {
      year: '2021',
      type: 'total',
      sellerId: ${sellerId},
    },
    cache: false,
    success: function (data) {
      let obj = $.parseJSON(data);
      console.log(obj);
      chart.data =  {
        labels: ['T1', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'T8', 'T9', 'T10', 'T11', 'T12'],
        datasets: [{
          label: 'Doanh số',
          backgroundColor: 'rgb(255, 99, 132)',
          barPercentage: 1,
          barThickness: 8,
          maxBarThickness: 8,
          minBarLength: 2,
          data: obj,
        }]
      };
      chart.update();
    }
  });
  var ctx = document.getElementById('myChart').getContext('2d');
  var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',

    // The data for our dataset
    data: {
      labels: ['T1', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'T8', 'T9', 'T10', 'T11', 'T12'],
      datasets: [{
        label: 'Doanh số',
        backgroundColor: 'rgb(255, 99, 132)',
        borderColor: 'rgb(255, 99, 132)',
        data: [1,2,3,4,5,6,7,8,9,10,11,12],
      }]
    },

    // Configuration options go here
    options: {}
  });
</script>
<script>
  var ctx = document.getElementById('myCharttt').getContext('2d');
  var myBarChart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['T1', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'T8', 'T9', 'T10', 'T11', 'T12'],
      datasets: [{
        label: 'Số lượng đơn hàng',
        backgroundColor: 'rgb(255, 99, 132)',
        barPercentage: 1,
        barThickness: 8,
        maxBarThickness: 8,
        minBarLength: 2,
        data: [1,2,3,4,5,6,7,8,9,10,11,12],
      }]
    },
  });
  $.ajax({
    url: '/api/statists-each-seller',
    method: "GET",
    data: {
      year: '2021',
      type: 'count',
      sellerId: ${sellerId},
    },
    cache: false,
    success: function (data) {
      let obj = $.parseJSON(data);
      console.log(obj);
      myBarChart.data =  {
        labels: ['T1', 'T2', 'T3', 'T4', 'T5', 'T6', 'T7', 'T8', 'T9', 'T10', 'T11', 'T12'],
        datasets: [{
          label: 'Số lượng đơn hàng',
          backgroundColor: 'rgb(255, 99, 132)',
          barPercentage: 1,
          barThickness: 8,
          maxBarThickness: 8,
          minBarLength: 2,
          data: obj,
        }]
      };
      myBarChart.update();
    }
  });
</script>
</body>
</html>
