const brandName = document.getElementById('brandName');
const brandOrigin = document.getElementById('brandOrigin');


let isValidate = true;

function checkInputs() {
  // trim to remove the whitespaces
  const brandNameValue = brandName.value.trim();
  const brandOriginValue = brandOrigin.value.trim();

  isValidate = true;

  if (brandNameValue === '') {
    setErrorFor(brandName, 'Vui lòng nhập thương hiệu');
  } else {
    setSuccessFor(brandName);
  }

  if (brandOriginValue === '') {
    setErrorFor(brandOrigin, 'Vui lòng xuất xứ');
  } else {
    setSuccessFor(brandOrigin);
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


$('#brand-form').submit(function (e) {
  checkInputs();

  if (!isValidate) {
    e.preventDefault();
  } else {
    $.ajax({
      url: '/api/brands',
      method: 'POST',
      async: false,
      data: {
        'brandName': brandName.value.trim(),
        'brandOrigin': brandOrigin.value.trim()
      },
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          alert("Thêm thương hiệu mới thành công !");
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
  $('#brand-form')[0].reset();
});