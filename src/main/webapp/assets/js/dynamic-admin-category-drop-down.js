/*
 * Script gọi Servlet `GetAdminUnitServlet` tạo combobox động chọn đơn vị hành chính
 * Cách dùng: trên trang jsp (html), tạo 3 thẻ select có id là `province`, `district`, 'commune' và add script này vào
 */

$(document).ready(function () {
  const apiUrl = "/api/admin-category";

  $.ajax({
    url: apiUrl,
    method: "GET",
    data: {
      type: 'Category'
    },
    success: function (data) {
      let obj = $.parseJSON(data);
      console.log(obj);
      $.each(obj, function (key, value) {
        $('#seller-category-id').append('<option value="' + value.id + '">' + value.name + '</option>');
      });
    },
    cache: false
  });
});