const addEmployeeForm = document.getElementById('add-employee-form');

const lastName = document.getElementById('last-name');
const firstName = document.getElementById('first-name');

const gender = document.getElementById('gender');
const phoneNumber = document.getElementById('phone-number');

const province = document.getElementById('province');
const district = document.getElementById('district');
const commune = document.getElementById('commune');
const addressStreet = document.getElementById('address-street');

const ssn = document.getElementById('ssn');
const email = document.getElementById('email');

addEmployeeForm.addEventListener('submit', e => {
    e.preventDefault();
    checkInputs();
});

function checkInputs() {
    // trim to remove the whitespaces
    const lastNameValue = lastName.value.trim();
    const firstNameValue = firstName.value.trim();

    const phoneNumberValue = phoneNumber.value.trim();

    const provinceValue = province.value;
    const districtValue = district.value;
    const communeValue = commune.value;
    const addressStreetValue = addressStreet.value.trim();

    const ssnValue = ssn.value.trim();
    const emailValue = email.value.trim();

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
    const formGroup = input.parentElement;
    const small = formGroup.querySelector('small');
    formGroup.className = 'form-group error';
    small.innerText = message;
}

function setSuccessFor(input) {
    const formGroup = input.parentElement;
    formGroup.className = 'form-group success';
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