const FORM_ID = 'customer-edit-form';
console.log("vo");

let id = document.getElementById('id');
let lastNamee = document.getElementById('last-name');
let firstNamee = document.getElementById('first-name');

let genderr = document.getElementById('gender');
let dobb = document.getElementById('dob')

let phoneNumberr = document.getElementById('phone-number');
let ssnn = document.getElementById('ssn');
let emaill = document.getElementById('email');

let provincee = document.getElementById('province');
let districtt = document.getElementById('district');
let communee = document.getElementById('commune');
let addressStreett = document.getElementById('address-street');

let isValidatee = true;

function checkInputs() {
  // trim to remove the whitespaces
  let lastNameValue = lastNamee.value.trim();
  let firstNameValue = firstNamee.value.trim();

  let phoneNumberValue = phoneNumberr.value.trim();
  let ssnValue = ssnn.value.trim();
  let emailValue = emaill.value.trim();

  let provinceValue = provincee.value;
  let districtValue = districtt.value;
  let communeValue = communee.value;
  let addressStreetValue = addressStreett.value.trim();

  isValidatee = true;

  if (lastNameValue === '') {
    setErrorFor(lastNamee, 'Vui lòng nhập họ và tên đệm');
  } else {
    setSuccessFor(lastNamee);
  }

  if (firstNameValue === '') {
    setErrorFor(firstNamee, 'Vui lòng nhập tên');
  } else {
    setSuccessFor(firstNamee);
  }

  if (phoneNumberValue === '') {
    setErrorFor(phoneNumberr, 'Vui lòng nhập số điện thoại');
  } else if (!isPhoneNumber(phoneNumberValue)) {
    setErrorFor(phoneNumberr, 'Số điện thoại phải có 10 chữ số, bắt đầu từ số 0');
  } else {
    setSuccessFor(phoneNumberr);
  }

  if (ssnValue === '') {
    setErrorFor(ssnn, 'Vui lòng nhập căn cước công dân');
  } else if (!isSsn(ssnValue)) {
    setErrorFor(ssnn, 'Số trên thẻ căn cước công dân có 9 hoặc 12 chữ số');
  } else {
    setSuccessFor(ssnn);
  }

  if (emailValue === '') {
    setErrorFor(emaill, 'Vui lòng nhập email');
  } else if (!isEmail(emailValue)) {
    setErrorFor(emaill, 'Email không đúng định dạng');
  } else {
    setSuccessFor(emaill);
  }

  if (provinceValue === '00') {
    setErrorFor(provincee, 'Vui lòng chọn tỉnh/ thành phố');
  } else {
    setSuccessFor(provincee);
  }

  if (districtValue === '000') {
    setErrorFor(districtt, 'Vui lòng chọn quận/ huyện');
  } else {
    setSuccessFor(districtt);
  }

  if (communeValue === '00000') {
    setErrorFor(communee, 'Vui lòng chọn xã/ phường');
  } else {
    setSuccessFor(communee);
  }

  if (addressStreetValue === '') {
    setErrorFor(addressStreett, 'Vui lòng nhập địa chỉ giao hàng');
  } else {
    setSuccessFor(addressStreett);
  }
}

function setErrorFor(input, message) {
  if (isValidatee) {
    input.focus();
  }

  isValidatee = false;

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
        setErrorFor(phoneNumberr, "Số điện thoại đã được sử dụng cho tài khoản khác");
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
      if (data.toString() === 'false') {
        setErrorFor(emaill, "Email đã được sử dụng cho tài khoản khác");
      }
    },
    cache: false
  });

  let paras = JSON.stringify({
    'id': id.value.toString(),
    'last-name': lastNamee.value.trim(),
    'first-name': firstNamee.value.trim(),
    'gender': genderr.value,
    'dob': dobb.value,
    'phone-number': phoneNumberr.value,
    'ssn': ssnn.value,
    'email': emaill.value,
    'province': provincee.value,
    'district': districtt.value,
    'commune': communee.value,
    'address-street': addressStreett.value.trim(),
    'image': $('#img-upload').attr('src')
  });

  if (isValidatee) {
    $.ajax({
      url: '/api/customers',
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          $('#successful-modal').modal('show');
          $('#successful-modal').on('hidden.bs.modal', function () {
            window.location.href = window.location.origin +  '/home';
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