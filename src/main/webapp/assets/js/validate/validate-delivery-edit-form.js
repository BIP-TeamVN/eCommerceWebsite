const FORM_ID = 'delivery-edit-form';

let id = document.getElementById('id');
let lastName = document.getElementById('last-name');
let firstName = document.getElementById('first-name');

let gender = document.getElementById('gender');
let dob = document.getElementById('dob')

let phoneNumber = document.getElementById('phone-number');
let ssn = document.getElementById('ssn');
let email = document.getElementById('email');

let salary = document.getElementById('salary');
let startDate = document.getElementById('start-date');

let province = document.getElementById('province');
let district = document.getElementById('district');
let commune = document.getElementById('commune');
let addressStreet = document.getElementById('address-street');

let isValidate = true;

function checkInputs() {
  // trim to remove the whitespaces
  let lastNameValue = lastName.value.trim();
  let firstNameValue = firstName.value.trim();

  let phoneNumberValue = phoneNumber.value.trim();
  let ssnValue = ssn.value.trim();
  let emailValue = email.value.trim();

  let provinceValue = province.value;
  let districtValue = district.value;
  let communeValue = commune.value;
  let addressStreetValue = addressStreet.value.trim();

  isValidate = true;

  if (lastNameValue === '') {
    setErrorFor(lastName, 'Vui lòng nhập họ và tên đệm');
  } else {
    setSuccessFor(lastName);
  }

  if (firstNameValue === '') {
    setErrorFor(firstName, 'Vui lòng nhập tên');
  } else {
    setSuccessFor(firstName);
  }

  if (phoneNumberValue === '') {
    setErrorFor(phoneNumber, 'Vui lòng nhập số điện thoại');
  } else if (!isPhoneNumber(phoneNumberValue)) {
    setErrorFor(phoneNumber, 'Số điện thoại phải có 10 chữ số, bắt đầu từ số 0');
  } else {
    setSuccessFor(phoneNumber);
  }

  if (ssnValue === '') {
    setErrorFor(ssn, 'Vui lòng nhập căn cước công dân');
  } else if (!isSsn(ssnValue)) {
    setErrorFor(ssn, 'Số trên thẻ căn cước công dân có 9 hoặc 12 chữ số');
  } else {
    setSuccessFor(ssn);
  }

  if (emailValue === '') {
    setErrorFor(email, 'Vui lòng nhập email');
  } else if (!isEmail(emailValue)) {
    setErrorFor(email, 'Email không đúng định dạng');
  } else {
    setSuccessFor(email);
  }

  if (provinceValue === '00') {
    setErrorFor(province, 'Vui lòng chọn tỉnh/ thành phố');
  } else {
    setSuccessFor(province);
  }

  if (districtValue === '000') {
    setErrorFor(district, 'Vui lòng chọn quận/ huyện');
  } else {
    setSuccessFor(district);
  }

  if (communeValue === '00000') {
    setErrorFor(commune, 'Vui lòng chọn xã/ phường');
  } else {
    setSuccessFor(commune);
  }

  if (addressStreetValue === '') {
    setErrorFor(addressStreet, 'Vui lòng nhập địa chỉ giao hàng');
  } else {
    setSuccessFor(addressStreet);
  }
}

function setErrorFor(input, message) {
  if (isValidate) {
    input.focus();
  }

  isValidate = false;

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

function isPhoneNumber(phoneNumber) {
  return phoneNumber[0] === '0' && phoneNumber.match(/\d/g).length === 10;
}

function isSsn(ssn) {
  let match = ssn.match(/\d/g);
  return match != null && (match.length === 9 || match.length === 12);
}

function isEmail(email) {
  return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}

function encodeImgToBase64(element) {
  let img = element.files[0];
  let imgReader = new FileReader();
  imgReader.onloadend = function() {
    $('#img-upload').attr('class','mb-2 rounded avatar-img');
    $('#img-upload').attr('src',imgReader.result);
  }
  imgReader.readAsDataURL(img);
}

$('#' + FORM_ID).submit(function (e) {
  e.preventDefault();
  checkInputs();

  // check phone number exits
  $.ajax({
    url: "/api/users/check-info",
    method: "GET",
    data: {
      'type': "phone-number",
      'chk-value': $('#phone-number').val(),
      'id': $('#id').val()
    },
    async: false,   // wait until done this scope
    success: function (data) {
      console.log(data);
      if (data.toString() === 'false') {
        setErrorFor(phoneNumber, "Số điện thoại đã được sử dụng cho tài khoản khác");
      }
    },
    cache: false
  });

  // check email exist
  $.ajax({
    url: "/api/users/check-info",
    method: "GET",
    data: {
      'type': "email",
      'chk-value': $('#email').val(),
      'id': $('#id').val()
    },
    async: false,   // wait until done this scope
    success: function (data) {
      console.log(data);
      if (data.toString() === 'false') {
        setErrorFor(email, "Email đã được sử dụng cho tài khoản khác");
      }
    },
    cache: false
  });

  let paras = JSON.stringify({
    'id': id.value.toString(),
    'last-name': lastName.value.trim(),
    'first-name': firstName.value.trim(),
    'gender': gender.value,
    'dob': dob.value,
    'phone-number': phoneNumber.value,
    'ssn': ssn.value,
    'email': email.value,
    'province': province.value,
    'district': district.value,
    'commune': commune.value,
    'address-street': addressStreet.value.trim(),
    'start-date': startDate.value,
    'salary': salary.value,
    'image': $('#img-upload').attr('src')
  });

  if (isValidate) {
    $.ajax({
      url: '/api/deliveries',
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          $('#successful-modal').modal('show');
          $('#successful-modal').on('hidden.bs.modal', function () {
            window.location.href = window.location.origin +  '/delivery';
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