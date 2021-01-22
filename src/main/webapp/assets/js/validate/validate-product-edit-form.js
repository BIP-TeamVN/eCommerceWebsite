const FORM_ID = 'product-edit-form';

let productName = document.getElementById('product-name')

let brand = document.getElementById('brand');
let productOrigin = document.getElementById('product-origin');

let productDesc = document.getElementById('product-desc');

let priceOrigin = document.getElementById('price-origin');
let priceOrder = document.getElementById('price-order');

let categories = document.getElementById('categories');

let image0 = document.getElementById('up-image-0');

let isValidate = true;

function checkInputs() {
  // trim to remove the whitespaces
  let productNameValue = productName.value.trim();

  let brandValue = brand.value.trim();
  let productOriginValue = productOrigin.value.trim();

  let priceOriginValue = priceOrder.value.trim();
  let priceOrderValue = priceOrder.value.trim();

  let productDescValue = productDesc.value.trim();

  let categoriesValue = categories.value.trim();

  let image0Value = image0.value;

  isValidate = true;

  if (productNameValue === '') {
    setErrorFor(productName, 'Vui lòng nhập tên sản phẩm');
  } else {
    setSuccessFor(productName);
  }

  if (brandValue === '') {
    setErrorFor(brand, 'Vui lòng chọn nhãn hiệu');
  } else {
    setSuccessFor(brand);
  }

  if (productOriginValue === '') {
    setErrorFor(productOrigin, 'Vui lòng nhập xuất xứ sản phẩm');
  } else {
    setSuccessFor(productOrigin);
  }

  if (productDescValue === '') {
    setErrorFor(productDesc, 'Vui lòng nhập mô tả sản phẩm');
  } else {
    setSuccessFor(productDesc);
  }

  if (priceOriginValue === '') {
    setErrorFor(priceOrigin, 'Vui lòng nhập giá thị trường');
  } else {
    setSuccessFor(priceOrigin);
  }

  if (priceOrderValue === '') {
    setErrorFor(priceOrder, 'Vui lòng nhập giá bán');
  } else if (parseFloat(priceOrder.value) > parseFloat(priceOrigin.value)) {
    setErrorFor(priceOrder, 'Giá bán phải thấp hơn hoặc bằng giá thị trường');
  } else {
    setSuccessFor(priceOrder);
  }

  for (var i = 0; i < countType; i++) {
    type = document.getElementById('type-name-' + i);
    quantity = document.getElementById('quantity-' + i);
    image = document.getElementById('img-upload-type-' + i);

    if (type.value.trim() === '') {
      setErrorFor(type, 'Vui lòng nhập loại sản phẩm');
    } else {
      setSuccessFor(type);
    }
    if (quantity.value.trim() === '') {
      setErrorFor(quantity, 'Vui lòng nhập loại sản phẩm');
    } else {
      setSuccessFor(quantity);
    }
    if (image.src === '') {
      setErrorFor(image, 'Vui lòng Chọn ảnh của loại sản phẩm');
    } else {
      setSuccessFor(image);
    }
  }

  if (categoriesValue === '') {
    setErrorFor(categories, 'Vui lòng chọn ít nhất một ngành hàng');
  } else {
    setSuccessFor(categories);
  }
/*
  if (image0Value === "") {
    setErrorFor(image0, 'Vui lòng chọn chọn ảnh bìa sản phẩm');
  } else {
    setSuccessFor(image0);
  }
 */
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

function encodeImgToBase64(element, id) {
  let img = element.files[0];
  let imgReader = new FileReader();
  imgReader.onloadend = function() {
    $('#' + id).attr('class','mb-2 rounded avatar-img');
    $('#' + id).attr('src',imgReader.result);
  }
  imgReader.readAsDataURL(img);
}

let paras;

$('#' + FORM_ID).submit(function (e) {
  e.preventDefault();

  let types = [];
  let quantities = [];
  let images = [];
  for (let i = 0; i < countType; i++) {
    types.push($('#type-name-' + i).val());
    quantities.push($('#quantity-' + i).val());
    images.push($('#img-upload-type-' + i).attr('src'));
  }

  checkInputs();
  if (isValidate) {
    paras = JSON.stringify({
      'id': $('#id').val(),
      'product-name': productName.value.trim(),
      'brand-id': brand.value,
      'product-origin': productOrigin.value.trim(),
      'product-desc': productDesc.value,
      'price-origin': priceOrigin.value,
      'price-order': priceOrder.value,
      'product-types': types.join('@#&'),
      'quantities': quantities.join('@#&'),
      'images': images.join('@#&'),
      'product-categories': $('#categories').val().join('@#&'),
      'image-0': $('#img-upload-0').attr('src'),
      'image-1': $('#img-upload-1').attr('src'),
      'image-2': $('#img-upload-2').attr('src'),
      'image-3': $('#img-upload-3').attr('src'),
      'image-4': $('#img-upload-4').attr('src')
    });
    console.log(paras);
    $.ajax({
      url: '/api/product',
      method: 'PUT',
      async: false,
      cache: false,
      data: paras,
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        console.log("'" + result[0] + "'");
        if (result[0] === 'true') {
          $('#successful-modal').modal('show');
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
  $('#conform-modal').modal('show');
});