
let productName = document.getElementById('product-name')

let brand = document.getElementById('brand');
let productOrigin = document.getElementById('product-origin');

let productDesc = document.getElementById('product-desc');

let priceOrigin = document.getElementById('price-origin');
let priceOrder = document.getElementById('price-order');

let categoriesSelected = document.getElementById('categories-selected');

let types = [];
let quantities = [];
function getTypesValue() {
  for (let i = 0; i < countType; i++) {
    types.push($('#type-name-' + i).val());
    quantities.push($('#quantity-' + i).val());
  }
}

let categories = [];
function getCategoriesValue() {
  categories = $.map($('#categories-selected option'), function(e) { return e.value; });
}

let isValidate = true;

function checkInputs() {
  // trim to remove the whitespaces
  let productNameValue = productName.value.trim();

  let brandValue = brand.value.trim();
  let productOriginValue = productOrigin.value.trim();

  let priceOriginValue = priceOrder.value.trim();
  let priceOrderValue = priceOrder.value.trim();

  let productDescValue = productDesc.value.trim();

  let categoriesSelectedValue = categoriesSelected.value.trim();

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
  } else {
    setSuccessFor(priceOrder);
  }

  for (var i = 0; i < countType; i++) {
    type = document.getElementById('type-name-' + i);
    quantity = document.getElementById('quantity-' + i);

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
  }

  if (categoriesSelectedValue === '') {
    setErrorFor(categoriesSelected, 'Vui lòng chọn ít nhất một ngành hàng');
  } else {
    setSuccessFor(categoriesSelected);
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

function encodeImgToBase64(element, id) {
  let img = element.files[0];
  let imgReader = new FileReader();
  imgReader.onloadend = function() {
    $('#' + id).attr('class','mb-2 rounded avatar-img');
    $('#' + id).attr('src',imgReader.result);
  }
  imgReader.readAsDataURL(img);
}

$('#product-form').submit(function (e) {
  getTypesValue();
  getCategoriesValue();
  checkInputs();
  if (!isValidate){
    e.preventDefault();
  } else {
    $.ajax({
      url: '/api/product',
      method: 'POST',
      async: false,
      data: {
        'product-name': productName.value.trim(),
        'brand-id': brand.value,
        'product-origin': productOrigin.value.trim(),
        'product-desc': productDesc.value,
        'price-origin': priceOrigin.value,
        'price-order': priceOrder.value,
        'product-types': types.join('@#&'),
        'quantities': quantities.join('@#&'),
        'product-categories': categories.join('@#&'),
        'image-0': $('#img-upload-1').attr('src'),
        'image-1': $('#img-upload-2').attr('src'),
        'image-2': $('#img-upload-3').attr('src')
      },
      success: function (data, textStatus, jqXHR) {
        let result = data.toString().split('\n');
        if (result[0] === 'true') {
          $('#product-form').trigger("reset");
          alert("Thêm sản phẩm mới thành công !");
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
  $('#' + FORM_ID).trigger("reset");
});

$.ajax({
  url: "/api/product-categories",
  method: "GET",
  cache: false,
  success: function (data) {
    let obj = $.parseJSON(data);
    console.log(obj);
    $.each(obj, function (key, value) {
        $('#categories-all').append('<option value="' + value.id + '">' + value.name + '</option>');
    });
  }
});

$('#btn-add-categories').click(function() {
  !$('#categories-all option:selected').remove().appendTo('#categories-selected');

  var options = $('#categories-selected option');
  var arr = options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
  arr.sort(function(o1, o2) { return o1.t < o2.t ? 1 : o1.t > o2.t ? -1 : 0; });
  options.each(function(i, o) {
    o.value = arr[i].v;
    $(o).text(arr[i].t);
  });
});
$('#btn-remove-categories').click(function() {
  !$('#categories-selected option:selected').remove().appendTo('#categories-all');

  var options = $('#categories-all option');
  var arr = options.map(function(_, o) { return { t: $(o).text(), v: o.value }; }).get();
  arr.sort(function(o1, o2) { return o1.t < o2.t ? 1 : o1.t > o2.t ? -1 : 0; });
  options.each(function(i, o) {
    o.value = arr[i].v;
    $(o).text(arr[i].t);
  });
});