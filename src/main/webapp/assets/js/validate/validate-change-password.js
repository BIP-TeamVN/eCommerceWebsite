const password = document.getElementById('current-password');
const newPassword = document.getElementById('new-password');
const rePassword = document.getElementById('retype-password');

let isValidatePassword = true;

function setErrorFor(input, message) {
  if (isValidatePassword) {
    input.focus();
  }

  isValidatePassword = false;

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

function checkInputs() {
  const passwordValue = password.value.trim();
  const newPasswordValue = newPassword.value.trim();
  const rePasswordValue = rePassword.value.trim();

  isValidatePassword = true;

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
}

$('#change-password-form').submit(function (e) {
  e.preventDefault();
  checkInputs();

  // if (isValidatePassword) {
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
  //           window.location.href = window.location.origin +  '/admin/brand';
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