const FORM_ID = 'brand-edit-form';

let id = document.getElementById('id');

let brandName = document.getElementById('name');

let brandOrigin = document.getElementById('brand-origin');


let isValidate = true;

function checkInputs() {
  // trim to remove the whitespaces
  let brandNameValue = brandName.value.trim();

  let brandOriginValue = brandName.value.trim();

  isValidate = true;

  if (brandNameValue === '') {
    setErrorFor(brandName, 'Vui lòng nhập tên thương hiệu');
  } else {
    setSuccessFor(brandName);
  }

  if (brandOriginValue === '') {
    setErrorFor(brandOrigin, 'Vui lòng nhập nơi xuất xứ');
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

  let paras = JSON.stringify({
    'id': id.value,
    'name': brandName.value.trim(),
    'brand-origin': brandOrigin.value.trim(),
    'image': $('#img-upload').attr('src')
  });

  if (isValidate) {
    $.ajax({
      url: '/api/brands',
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          $('#successful-modal').modal('show');
          $('#successful-modal').on('hidden.bs.modal', function () {
            window.location.href = window.location.origin +  '/employee/brand';
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
  $('#' + FORM_ID).trigger("reset");
});