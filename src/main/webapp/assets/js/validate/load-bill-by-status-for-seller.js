/*
 * Script gọi Servlet `GetAdminUnitServlet` tạo combobox động chọn đơn vị hành chính
 * Cách dùng: trên trang jsp (html), tạo 3 thẻ select có id là `province`, `district`, 'commune' và add script này vào
 */

$(document).ready(function () {
  const apiUrl = "/api/delivery-bill";
  let status = $('#status').val();

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
            '<a href="/delivery/detailbill?id=' + item.id +'" class="btn btn-primary px-2 py-1" data-toggle="tooltip" data-placement="top" title="Chi Tiết Đơn Hàng">' +
            '<i class="fa fa-edit"></i>' +
            '</a>' +
          '</td>' +
          '</tr>';
          console.log(html);
          $('#tb-list').append(html);
        });
      },
      cache: false
    });
  });
});