const FORM_ID = 'address-customer-form';

let id = document.getElementById('id');

let street = document.getElementById('address-street');
let commune = document.getElementById('commune');
let district = document.getElementById('district');
let province = document.getElementById('province')

let fullName = document.getElementById('full-name');
let addressName = document.getElementById('type-address');
let phoneNumber = document.getElementById('phone-number');

let isValidate1 = true;

function checkInputs() {
  // trim to remove the whitespaces
  let streetValue = street.value.trim();
  let communeValue = commune.value.trim();
  let districtValue = district.value.trim();
  let provinceValue = province.value.trim();

  let fullNameValue = fullName.value.trim();
  let addressNameValue = addressName.value.trim();
  let phoneNumberValue = phoneNumber.value.trim();


  isValidate1 = true;

  if (streetValue === '') {
    setErrorFor(street, 'Vui lòng nhập địa chị cụ thể');
  } else {
    setSuccessFor(street);
  }

  if (communeValue === '') {
    setErrorFor(commune, 'Vui lòng chọn xã');
  } else {
    setSuccessFor(commune);
  }

  if (districtValue === '') {
    setErrorFor(district, 'Vui lòng chọn huyện');
  } else {
    setSuccessFor(district);
  }

  if (provinceValue === '') {
    setErrorFor(province, 'Vui lòng chọn tỉnh');
  } else {
    setSuccessFor(province);
  }

  if (fullNameValue === '') {
    setErrorFor(fullName, 'Vui lòng nhập tên người nhận');
  } else {
    setSuccessFor(fullName);
  }

  if (addressNameValue === '00') {
    setErrorFor(addressName, 'Vui lòng nhập tên địa chỉ');
  } else {
    setSuccessFor(addressName);
  }

  if (phoneNumberValue === '000') {
    setErrorFor(phoneNumber, 'Vui lòng nhập số điện thoại');
  } else {
    setSuccessFor(phoneNumber);
  }
}

function setErrorFor(input, message) {
  if (isValidate1) {
    input.focus();
  }

  isValidate1 = false;

  input.parentElement.className = 'has-danger';
  input.className = 'form-control is-invalid';

  let small = input.parentElement.parentElement.querySelector('small');
  small.innerText = message;
  small.setAttribute("style", "display: inline;");
}

function setSuccessFor(input) {
  input.parentElement.className = 'has-success';
  input.className = 'form-control is-valid';

  let small = input.parentElement.parentElement.querySelector('small');
  small.innerText = '';
  small.setAttribute("style", "display: none;");
}

$('#' + FORM_ID).submit(function (e) {
  e.preventDefault();
  checkInputs();
  let paras = JSON.stringify({
    'id': id.value.toString(),
    'street': street.value.trim(),
    'commune': commune.value.trim(),
    'district': district.value.trim(),
    'province': province.value.trim(),
    'full-name': fullName.value.trim(),
    'address-name': addressName.value.trim(),
    'phone-number': phoneNumber.value.trim()
  });

  if (isValidate1) {
    $.ajax({
      url: '/api/info/address',
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          $('#successful-modal').modal('show');
          $('#successful-modal').on('hidden.bs.modal', function () {
            window.location.href = window.location.origin +  '/info/address';
          });
        } else {
          showMessageModal('fa fa-times text-danger', 'Xảy ra lỗi', result[1]);
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        showMessageModal('fa fa-times text-danger', 'Lỗi kết nối server', errorThrown);
      }
    });
  }
});

$('#btn-cancel').click(function () {
  $('#' + FORM_ID).trigger("reset");
});