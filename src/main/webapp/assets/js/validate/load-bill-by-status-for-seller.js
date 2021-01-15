/*
 * Script gọi Servlet `GetAdminUnitServlet` tạo combobox động chọn đơn vị hành chính
 * Cách dùng: trên trang jsp (html), tạo 3 thẻ select có id là `province`, `district`, 'commune' và add script này vào
 */

$(document).ready(function () {
  const apiUrl = "/api/deliverybill";

  $('#status').change(function () {

    $.ajax({
      url: apiUrl,
      method: 'GET',
      data: {
        page: '1',
        status: $('#status').val()
      },
      success: function (data, textStatus, jqXHR) {
        let list = $.parseJSON(data);
        console.log(list);
        $('#tb-list').find('tr').remove();

        $.each(list, function (index, item) {
          let html =
            '<tr id="hay' + item.id + '">' +
            '<td>' + item.id + '</td>' +
            '<td>' + item.fullName + '</td>' +
            '<td>' + item.phone + '</td>' +
            '<td>' + item.fullAddress + '</td>' +
            '<td>' + item.total + '</td>' +
            '<td class="td-actions text-center">' +
            '<button class="btn btn-primary pl-2 pr-1" onclick="GetBill(' + item.id + ')">Nhận đơn</button>'
          '</td>' +
          '</tr>';
          console.log(html);
          $('#tb-list').append(html);
        });
      },
      cache: false
    });
    alert($('#status').val());
  });
});