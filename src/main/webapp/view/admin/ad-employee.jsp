<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="vi">
<head>
   <%@ include file="../../common/meta-info.jsp" %>
   <title>BIP - Trang quản trị</title>
   <%@ include file="../../common/link-css.jsp" %>
   <!--Javascript-->
   <%@ include file="../../common/import-js.jsp" %>
</head>

<body>
<!--Left side nav-->
<jsp:include page="./ad--side-nav.jsp">
   <jsp:param name="selectedIndex" value="1"/>
</jsp:include>

<!-- Main content -->
<div class="main-content" id="panel">
   <!--Top navigation-->
   <%@include file="./ad--top-nav.jsp" %>

   <!--Header and breadcrumb-->
   <div class="header bg-primary pb-6">
      <div class="container-fluid">
         <div class="header-body">
            <div class="row align-items-center py-4">
               <div class="col-lg-6 col-7">
                  <nav aria-label="breadcrumb" class="d-none d-md-inline-block">
                     <ol class="breadcrumb breadcrumb-links breadcrumb-dark">
                        <li class="breadcrumb-item"><a href="/admin"><i class="fa fa-home mr-2"></i>Trang chủ</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Nhân viên</li>
                     </ol>
                  </nav>
               </div>
               <div class="col-lg-6 col-5 text-right">
                  <button type="button" data-toggle="modal" data-target="#modal-add-employee"
                          class="btn btn-secondary text-uppercase">Thêm nhân viên mới
                  </button>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- From add employee -->
   <%@ include file="../../common/form-add-employee.jsp" %>

   <!-- Page content -->
   <div class="container-fluid mt--6">
      <!--List employee card-->
      <div class="row">
         <div class="col">
            <div class="card">
               <!-- Card header -->
               <div class="card-header border-0">
                  <h2 class="mb-0 text-center text-uppercase display-4">Danh sách nhân viên</h2>
                  <div class="position-absolute row" style="right: 2rem; top: 1.5rem;">
                     <div class="col-3 text-right">
                        <label class="custom-toggle">
                           <input type="checkbox" id="chk-show-filter">
                           <span class="custom-toggle-slider rounded-circle"></span>
                        </label>
                     </div>
                     <div class="col-9 text-right">
                        <p class="m-0 font-weight-bold">Lọc và tìm kiếm</p>
                     </div>
                  </div>
               </div>

               <!--Filter-->
               <div id="tb-filter" class="card-header border-0 d-none pt-0">
                  <div class="container-fluid">
                     <div class="row">
                        <div class="col">
                           <div class="line-text"><span>Tìm kiếm và tùy chọn hiển thị</span></div>
                        </div>
                     </div>

                     <div class="row m-0">
                        <div class="col pl-0">
                           <form class="m-auto w-100 navbar-search navbar-search-light form-inline" id="tb-search">
                              <div class="form-group w-100 mb-0">
                                 <div style="border: 1px solid #EFF1F3;"
                                      class="w-100 input-group input-group-alternative input-group-merge">
                                    <div class="input-group-prepend">
                                       <span class="input-group-text"><em class="fas fa-search"></em></span>
                                    </div>
                                    <input class="form-control" id="tb-input-search" placeholder="Tìm kiếm" type="text">
                                 </div>
                              </div>
                              <button type="button" class="close" data-action="search-close" data-target="#tb-search"
                                      aria-label="Close" onclick="search()">
                                 <span aria-hidden="true">×</span>
                              </button>
                           </form>
                        </div>

                        <div class="col">
                           <div class="row">
                              <div class="col p-0">
                                 <div class="form-group m-1">
                                    <label for="sort-by-column" class="floating-label">Sắp xếp theo</label>
                                    <select class="form-control input-border" id="sort-by-column" onchange="search()">
                                       <option value="userId" selected>Mã</option>
                                       <option value="userEntity.firstName">Họ và tên</option>
                                       <option value="userEntity.gender">Giới tính</option>
                                       <option value="userEntity.dateOfBirth">Ngày sinh</option>
                                       <option value="userEntity.phoneNumber">Số điện thoại</option>
                                       <option value="userEntity.email">Email</option>
                                       <option value="salary">Lương</option>
                                       <option value="startDate">Ngày bắt đầu</option>
                                    </select>
                                 </div>
                              </div>

                              <div class="col p-0">
                                 <div class="form-group m-1">
                                    <label for="sort-type" class="floating-label">Kiểu sắp xếp</label>
                                    <select class="form-control input-border" id="sort-type" onchange="search()">
                                       <option value="ASC" selected>Tăng dần</option>
                                       <option value="DESC">Giảm dần</option>
                                    </select>
                                 </div>
                              </div>

                              <div class="col p-0">
                                 <div class="form-group m-1">
                                    <label for="filter-status" class="floating-label">Trạng thái</label>
                                    <select class="form-control input-border" id="filter-status" onchange="search()">
                                       <option value="2">Tất cả trạng thái</option>
                                       <option value="1">Đang làm việc</option>
                                       <option value="0">Đã nghĩ</option>
                                    </select>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="row my-4">
                        <div class="col">
                           <div class="line-text"></div>
                        </div>
                     </div>
                  </div>
               </div>

               <!--Loading-->
               <div id="loading" class="d-none">
                  <p class="text-dark text-center">Đang load dữ liệu</p>
                  <div class="dots-loading">
                     <div></div>
                     <div></div>
                     <div></div>
                     <div></div>
                  </div>
               </div>

               <!-- Light table -->
               <div class="table-responsive">
                  <table class="table align-items-center table-flush">
                     <thead class="thead-light">
                     <tr>
                        <th scope="col" class="text-center">Ảnh</th>
                        <th scope="col" class="text-center">Mã</th>
                        <th scope="col" class="text-center">Họ và tên</th>
                        <th scope="col" class="text-center">Phái</th>
                        <th scope="col" class="text-center">Ngày sinh</th>
                        <th scope="col" class="text-center">Số điện thoại</th>
                        <th scope="col" class="text-center">Email</th>
                        <th scope="col" class="text-center">Lương</th>
                        <th scope="col" class="text-center">Ngày bắt đầu</th>
                        <th scope="col" class="text-center">Tùy chọn</th>
                     </tr>
                     </thead>
                     <tbody class="list" id="tb-list">
                     </tbody>
                  </table>
               </div>

               <!-- Card footer -->
               <div class="card-footer py-2">
                  <!-- Pagination -->
                  <nav aria-label="...">
                     <ul id="page-pagination" class="pagination justify-content-center mt-3">
                        <li class="page-item">
                           <button type="button" class="page-link" onclick="goPrev()">
                              <i class="fa fa-angle-left"></i>
                              <span class="sr-only">Trang trước</span>
                           </button>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                        <li class="page-item active">
                           <a class="page-link" href="#">2</a>
                        </li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                           <button type="button" class="page-link" onclick="goNext()">
                              <i class="fa fa-angle-right"></i>
                              <span class="sr-only">Trang sau</span>
                           </button>
                        </li>
                     </ul>
                  </nav>
               </div>
            </div>
         </div>
      </div>

      <!-- Footer -->
      <%@ include file="../../common/footer.jsp" %>
   </div>
</div>

<script>
  $('#chk-show-filter').change(function () {
    if ($(this).is(':checked')) {
      $('#tb-filter').removeClass('d-none');
    } else {
      $('#tb-filter').addClass('d-none');
    }
  });

  let firstPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goFirst()"><i class="fa fa-angle-double-left"></i><span class="sr-only">Trang đầu tiên</span></button></li>';
  let prevPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goPrev()"><i class="fa fa-angle-left"></i><span class="sr-only">Trang trước</span></button></li>';
  let nextPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goNext()"><i class="fa fa-angle-right"></i><span class="sr-only">Trang sau</span></button></li>';
  let lastPageButton = '<li class="page-item"><button type="button" class="page-link" onclick="goLast()"><i class="fa fa-angle-double-right"></i><span class="sr-only">Trang cuối</span></button></li>';

  let totalPage = ${totalPage};
  let currentPage = ${currentPage};

  reloadPage();

  function goFirst() {
    if (currentPage > 1) {
      currentPage = 1;
      reloadPage();
    }
  }

  function goPrev() {
    if (currentPage > 1) {
      currentPage = currentPage - 1;
      reloadPage();
    }
  }

  function goNext() {
    if (currentPage < totalPage) {
      currentPage = currentPage + 1;
      reloadPage();
    }
  }

  function goLast() {
    if (currentPage < totalPage) {
      currentPage = totalPage;
      reloadPage();
    }
  }

  function goToPage(page) {
    currentPage = page;
    reloadPage();
  }

  function updatePagination() {
    $('#page-pagination').find('li').remove();

    $('#page-pagination').append(currentPage > 2 ? firstPageButton : '');
    $('#page-pagination').append(currentPage > 1 ? prevPageButton : '');

    let startIndex = currentPage - 3 > 1 ? currentPage - 3 : 1;
    for (let i = startIndex; i < currentPage; i++) {
      $('#page-pagination').append('<li class="page-item"><button type="button" class="page-link" onclick="goToPage(' + i + ')">' + i + '</but></li>');
    }

    $('#page-pagination').append('<li class="page-item active"><a class="page-link" href="javascript:void(0)">' + currentPage + '</a></li>');

    for (let i = currentPage + 1; i < currentPage + 4 && i <= totalPage; i++) {
      $('#page-pagination').append('<li class="page-item"><button type="button" class="page-link" onclick="goToPage(' + i + ')">' + i + '</but></li>');
    }

    $('#page-pagination').append(currentPage < totalPage ? nextPageButton : '');
    $('#page-pagination').append(currentPage < totalPage - 1 ? lastPageButton : '');
  }

  function reloadPage() {
    updatePagination();
    console.log("load");

    $.ajax({
      url: '/api/employees',
      method: 'GET',
      data: {
        'page': currentPage,
        'status': $('#filter-status').val(),
        'keyword': $('#tb-input-search').val(),
        'columnName': $('#sort-by-column').val(),
        'typeSort': $('#sort-type').val()
      },
      cache: false,
      beforeSend: function () {
        $('#loading').removeClass('d-none');
        $('div.table-responsive').addClass('d-none');
      },
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
        $('#tb-list').find('tr').remove();
        $.each(list, function (index, item) {
          let html =
            '<tr>' +
            '<td>' +
            '<a href="#" class="media align-items-center">' +
            '<img class="m-auto avatar rounded-circle" src="' + item.imgSrc + '" alt="avatar_image" >' +
            '</a>' +
            '</td>' +
            '<td>' + item.id + '</td>' +
            '<td>' + item.fullName + '</td>' +
            '<td>' + item.gender + '</td>' +
            '<td>' + item.dob + '</td>' +
            '<td><a href="tel:' + item.phone + '">' + item.phone + '</a></td>' +
            '<td><a href="mailto:' + item.email + '">' + item.email + '</a></td>' +
            '<td>' + item.salary + '</td>' +
            '<td>' + item.startDate + '</td>' +
            '<td class="td-actions text-center">' +
            '<a href="/admin/employee/edit?id=' + item.id + '" class="btn btn-primary px-2 py-1" data-toggle="tooltip" data-placement="top" title="Chỉnh sửa thông tin">' +
            '<i class="fa fa-edit"></i>' +
            '</a>' +
            (item.status === "true" ?
              '<a href="#" class="btn btn-danger px-2 py-1" data-toggle="tooltip" data-placement="top" title="Thôi việc">' +
              '<i class="fa fa-lock"></i>' +
              '</a>' :
              '<a href="#" class="btn btn-success px-2 py-1" data-toggle="tooltip" data-placement="top" title="Làm việc lại">' +
              '<i class="fa fa-lock-open"></i>' +
              '</a>') +
            '</td>' +
            '</tr>';
          $('#tb-list').append(html);
        });

        $('#loading').addClass('d-none');
        $('div.table-responsive').removeClass('d-none');
      }
    });
  }
</script>

<script>
  $('#tb-search').submit(function (e) {
    e.preventDefault();
    search();
  });

  function search(){
    $.ajax({
      url: '/api/count-employee',
      method: 'GET',
      data: {
        'page': currentPage,
        'status': $('#filter-status').val(),
        'keyword': $('#tb-input-search').val()
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
</body>
</html>
