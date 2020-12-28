const lastName = document.getElementById('last-name');
const firstName = document.getElementById('first-name');

const gender = document.getElementById('gender');
const dob = document.getElementById('dob')

const phoneNumber = document.getElementById('phone-number');
const ssn = document.getElementById('ssn');
const email = document.getElementById('email');

const salary = document.getElementById('salary');
const startDate = document.getElementById('start-date');

const province = document.getElementById('province');
const district = document.getElementById('district');
const commune = document.getElementById('commune');
const addressStreet = document.getElementById('address-street');


let isValidate = true;

function checkInputs() {
  // trim to remove the whitespaces
  const lastNameValue = lastName.value.trim();
  const firstNameValue = firstName.value.trim();

  const phoneNumberValue = phoneNumber.value.trim();
  const ssnValue = ssn.value.trim();
  const emailValue = email.value.trim();

  const provinceValue = province.value;
  const districtValue = district.value;
  const communeValue = commune.value;
  const addressStreetValue = addressStreet.value.trim();

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
  var match = ssn.match(/\d/g);
  return match != null && (match.length === 9 || match.length === 12);
}

function isEmail(email) {
  return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}


$('#employee-form').submit(function (e) {
  checkInputs();

  // check phone number exits
  $.ajax({
    url: "/api/users/check-info",
    method: "GET",
    data: {
      type: "phone-number",
      chkValue: $('#phone-number').val()
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
      type: "email",
      chkValue: $('#email').val()
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

  if (!isValidate) {
    e.preventDefault();
  } else {
    $.ajax({
      url: '/api/employees',
      method: 'POST',
      async: false,
      data: {
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
        'salary': salary.value
      },
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          alert("Thêm nhân viên mới thành công !");
        } else {
          alert("Lỗi: " + result[1]);
          e.preventDefault();
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert("Lỗi: " + errorThrown);
        e.preventDefault();
      }
    });
  }
});

$('#btn-cancel').click(function () {
  $('#employee-form')[0].reset();
});