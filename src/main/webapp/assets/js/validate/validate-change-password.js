const password = document.getElementById('password');
const newPassword = document.getElementById('password-new');
const rePassword = document.getElementById('password-re');

let isValidateChange = true;

function checkInputs() {


  const passwordValue = password.value.trim();
  const newPasswordValue = newPassword.value.trim();
  const rePasswordValue = rePassword.value.trim();

  isValidateChange = true;

  if (passwordValue === '') {
    setErrorFor(password, 'Vui lòng nhập mật khẩu cũ');
  } else {
    setSuccessFor(password);
  }

  if (newPasswordValue === '') {
    setErrorFor(newPassword, 'Vui lòng nhập mật khẩu mới');
  } else {
    setSuccessFor(newPassword);
  }
  if (rePasswordValue === '') {
    setErrorFor(rePassword, 'Vui lòng nhập mật khẩu mới');
  } else {
    setSuccessFor(rePassword);
  }
  var number = newPasswordValue.localeCompare(rePasswordValue);

  if (number != 0) {
    setErrorFor(rePassword, 'Mật khẩu mới và mật khẩu xác nhận không giống nhau!!!');
  } else {
    setSuccessFor(rePassword);
  }
}

function setErrorFor(input, message) {
  if (isValidateChange) {
    input.focus();
  }

  isValidateChange = false;

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

$('#change-password-form').submit(function (e) {
  e.preventDefault();
  checkInputs();

  // if (isValidate) {
  //   $.ajax({
  //     url: '/api/',
  //     method: 'POST',
  //     async: false,
  //     data: {
  //       'brandName': brandName.value.trim(),
  //       'brandOrigin': brandOrigin.value.trim(),
  //       'imageBase64': $('#img-upload').attr('src')
  //     },
  //     success: function (data, textStatus, jqXHR) {
  //       let result = data.toString().split('\n');
  //       if (result[0] === 'true') {
  //         $('#successful-modal').modal('show');
  //         $('#successful-modal').on('hidden.bs.modal', function () {
  //           window.location.href = window.location.origin +  '/admin';
  //         });
  //       } else {
  //         alert("Lỗi: " + result[1]);
  //         e.preventDefault();
  //       }
  //     },
  //     error: function (jqXHR, textStatus, errorThrown) {
  //       alert("Lỗi: " + errorThrown);
  //       e.preventDefault();
  //     }
  //   });
  // }
});

$('#btn-cancel').click(function () {
  $('#change-password-form').trigger("reset");
});