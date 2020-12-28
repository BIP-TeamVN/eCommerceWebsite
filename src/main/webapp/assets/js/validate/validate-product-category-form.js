const name = document.getElementById('name');



let isValidate = true;

function checkInputs() {
  // trim to remove the whitespaces
  const nameValue = name.value.trim();

  isValidate = true;

  if (nameValue === '') {
    setErrorFor(name, 'Vui lòng nhập tên ngành hàng');
  } else {
    setSuccessFor(name);
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


$('#product-category-form').submit(function (e) {
  checkInputs();

  if (!isValidate) {
    e.preventDefault();
  } else {
    $.ajax({
      url: '/api/product-categories',
      method: 'POST',
      async: false,
      data: {
        'name': name.value.trim()
      },
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          alert("Thêm ngành hàng mới thành công !");
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
  $('#product-category-form')[0].reset();
});