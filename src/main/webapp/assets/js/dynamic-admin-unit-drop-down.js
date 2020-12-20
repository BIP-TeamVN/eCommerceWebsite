/*
 * Script gọi Servlet `GetAdminUnitServlet` tạo combobox động chọn đơn vị hành chính
 * Cách dùng: trên trang jsp (html), tạo 3 thẻ select có id là `province`, `district`, 'commune' và add script này vào
 */

function initProvinceOption(provinceObj) {
    let provinceFullName = provinceObj.provinceName;
    switch (provinceObj.provinceType) {
        case 'C':
            provinceFullName = 'Thành phố ' + provinceFullName;
            break;
        default:
            provinceFullName = 'Tỉnh ' + provinceFullName;
    }
    return '<option value="' + provinceObj.provinceId + '">' + provinceFullName + '</option>';
}

function initDistrictOption(districtObj) {
    let districtFullName = districtObj.districtName;
    switch (districtObj.districtType) {
        case 'C':
            districtFullName = 'Thành phố ' + districtFullName;
            break;
        case 'D':
            districtFullName = 'Quận ' + districtFullName;
            break;
        case 'T':
            districtFullName = 'Thị xã ' + districtFullName;
            break;
        default:
            districtFullName = 'Huyện ' + districtFullName;
    }
    return '<option value="' + districtObj.districtId + '">' + districtFullName + '</option>';
}

function initCommuneOption(communeObj) {
    let communeFullName = communeObj.communeName;
    switch (communeObj.communeType) {
        case 'W':
            communeFullName = 'Phường ' + communeFullName;
            break;
        case 'T':
            communeFullName = 'Thị trấn ' + communeFullName;
            break;
        default:
            communeFullName = 'Xã ' + communeFullName;
    }
    return '<option value="' + communeObj.communeId + '">' + communeFullName + '</option>';
}

$(document).ready(function () {
    $.ajax({
        url: "./GetAdminUnitServlet",
        method: "GET",
        data: {
            operation: 'get-province'
        },
        success: function (data) {
            let obj = $.parseJSON(data);
            console.log(obj);
            $.each(obj, function (key, value) {
                $('#province').append(initProvinceOption(value));
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
            url: "./GetAdminUnitServlet",
            method: "GET",
            data: {
                operation: "get-district",
                id: $('#province').val()
            },
            success: function (data) {
                let obj = $.parseJSON(data);
                console.log(obj);
                $.each(obj, function (key, value) {
                    $('#district').append(initDistrictOption(value));
                });
            },
            cache: false
        });
    });

    $('#district').change(function () {
        $('#commune').find('option').remove();
        $('#commune').append('<option value="00000">Chọn xã/ phường</option>');

        $.ajax({
            url: "./GetAdminUnitServlet",
            method: "GET",
            data: data = {
                operation: "get-commune",
                id: $('#district').val()
            },
            success: function (data) {
                let obj = $.parseJSON(data);
                console.log(obj);
                $.each(obj, function (key, value) {
                    $('#commune').append(initCommuneOption(value));
                });
            },
            cache: false
        });
    });
});