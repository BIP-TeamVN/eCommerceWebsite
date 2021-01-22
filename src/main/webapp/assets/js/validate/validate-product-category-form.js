const FORM_ID = 'product-category-form';

const name = document.getElementById('name');

let isValidate = true;

function checkInputs() {

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

  if (isValidate) {
    $.ajax({
      url: '/api/product-categories',
      method: 'POST',
      async: false,
      data: {
        'name': name.value.trim(),
        'imageBase64': $('#img-upload').attr('src')
      },
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          $('#modal-add-product-category').modal('hide');
          $('#successful-modal').modal('show');
          $('#successful-modal').on('hidden.bs.modal', function () {
            window.location.href = window.location.origin +  '/admin/category?page=99999999999999999';
          });
        } else {
          showMessageModal('fa fa-times text-danger', 'Xảy ra lỗi', result[1]);
        }
      },
      error: function (jqXHR, textStatus, errorThrown) {
        alert("Lỗi: " + errorThrown);
      }
    });
  }
});

$('#btn-cancel').click(function () {
  $('#product-category-form').trigger("reset");
});