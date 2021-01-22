const password = document.getElementById('current-password');
const newPassword = document.getElementById('new-password');
const rePassword = document.getElementById('retype-password');

let isValidatePassword = true;

function setErrorForPassword(input, message) {
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

function setSuccessForPassword(input) {
  input.parentElement.className = 'has-success';
  input.className = 'form-control is-valid';

  let small = input.parentElement.parentElement.querySelector('small');
  small.innerText = '';
  small.setAttribute("style", "display: none;");
}

function checkInputPassword() {
  const passwordValue = password.value.trim();
  const newPasswordValue = newPassword.value.trim();
  const rePasswordValue = rePassword.value.trim();

  isValidatePassword = true;

  if (passwordValue === '') {
    setErrorForPassword(password, 'Vui lòng nhập mật khẩu cũ');
  } else {
    setSuccessForPassword(password);
  }

  if (newPasswordValue === '') {
    setErrorForPassword(newPassword, 'Vui lòng nhập mật khẩu mới');
  } else {
    setSuccessForPassword(newPassword);
  }
  var flag = 0;
  if (rePasswordValue === '') {
    flag = 1 ;
    setErrorForPassword(rePassword, 'Vui lòng nhập mật khẩu mới');
  } else {
    setSuccessForPassword(rePassword);
  }

  if (rePasswordValue != newPasswordValue && flag == 1) {
    setErrorForPassword(rePassword, 'Mật khẩu xác nhận phải trùng với mật khẩu mới');
  } else {
    setSuccessForPassword(rePassword);
  }
}

$('#change-password-form').submit(function (e) {
  e.preventDefault();
  checkInputPassword();

  let paras = JSON.stringify({
    'current-password': password.value.trim(),
    'new-password': newPassword.value.trim()
  });

  if (isValidatePassword) {
    $.ajax({
      url: '/api/change-password',
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          showMessageModal('fa fa-check text-success', 'Thông báo', 'Thay đổi mật khẩu thành công !', 'OK', () => {
            $('#change-password-form').trigger('reset');
            $('#modal-change-password').modal('hide');
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

$('#btn-cancel-password').click(function () {
  $('#change-password-form').trigger("reset");
});