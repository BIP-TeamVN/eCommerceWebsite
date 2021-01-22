let isValidatee = false;
function checkNumber(){
  $.ajax({
    url: "/api/check-number-bill",
    method: "GET",
    async: false,   // wait until done this scope
    success: function (data) {
      let result = data.toString();
      if(result == "true"){
        isValidatee = true;
      }
    },
    cache: false
  });
  console.log("check");
}
  function GetBill(billId){
  checkNumber();
    let paras = JSON.stringify({
      'id': billId.toString(),
      'status': 4
    })
    if(isValidatee){
      $.ajax({
        url: "/api/bill/view/detail",
        method: 'PUT',
        async: false,
        cache: false,
        data: paras,
        success: function (){
          $("#hay" + billId).remove();
          reloadPage();
        }
      })
      console.log("hello");
      alert("Nhận đơn thành công!");
    }else {
      alert("Bạn đã nhận tối đa số đơn hàng cho phép");
    }
  }
