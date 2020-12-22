/*
 * Script gọi Servlet `GetAdminUnitServlet` tạo combobox động chọn đơn vị hành chính
 * Cách dùng: trên trang jsp (html), tạo 3 thẻ select có id là `province`, `district`, 'commune' và add script này vào
 */

$(document).ready(function () {
  const apiUrl = "/api/admin-units";

  $.ajax({
    url: apiUrl,
    method: "GET",
    data: {
      type: 'province'
    },
    success: function (data) {
      let obj = $.parseJSON(data);
      console.log(obj);
      $.each(obj, function (key, value) {
        $('#province').append('<option value="' + value.id + '">' + value.name + '</option>');
      });
    },
    cache: false
  });

  $('#province').change(function () {
    $('#district').find('option').remove();
    $('#district').append('<option value="000">Chọn quận/ huyện</option>');
    $('#commune').find('option').remove();
    $('#commune').append('<option value="00000">Chọn xã/ phường</option>');

    $.ajax({
      url: apiUrl,
      method: "GET",
      data: {
        type: "district",
        id: $('#province').val()
      },
      success: function (data) {
        let obj = $.parseJSON(data);
        console.log(obj);
        $.each(obj, function (key, value) {
          $('#district').append('<option value="' + value.id + '">' + value.name + '</option>');
        });
      },
      cache: false
    });
  });

  $('#district').change(function () {
    $('#commune').find('option').remove();
    $('#commune').append('<option value="00000">Chọn xã/ phường</option>');

    $.ajax({
      url: apiUrl,
      method: "GET",
      data: {
        type: "commune",
        id: $('#district').val()
      },
      success: function (data) {
        let obj = $.parseJSON(data);
        console.log(obj);
        $.each(obj, function (key, value) {
          $('#commune').append('<option value="' + value.id + '">' + value.name + '</option>');
        });
      },
      cache: false
    });
  });
});