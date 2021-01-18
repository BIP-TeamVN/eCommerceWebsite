$(document).ready(function () {
  $(".input-numeric").inputSpinner({
    decrementButton: "<em class=\"fa fa-minus\"></em>", // button text
    incrementButton: "<em class=\"fa fa-plus\"></em>", // ..
    groupClass: "", // css class of the resulting input-group
    buttonsClass: "btn-outline-primary p-2",
    buttonsWidth: "1rem",
    // textAlign: "center", // alignment of the entered number
    // autoDelay: 500, // ms threshold before auto value change
    // autoInterval: 50, // speed of auto value change
    // buttonsOnly: false, // set this `true` to disable the possibility to enter or paste the number via keyboard
    // locale: navigator.language, // the locale, per default detected automatically from the browser
    template: // the template of the input
      '<div class="input-group ${groupClass}">' +
      '<div class="input-group-prepend">' +
      '<button style="min-width: ${buttonsWidth}" class="btn btn-decrement ${buttonsClass} btn-minus" type="button">${decrementButton}</button>' +
      '</div>' +
      '<input type="text" inputmode="decimal" style="text-align: ${textAlign}; width: 3rem; height: 2.5rem; line-height: 2.5rem;" class="form-control form-control-text-input"/>' +
      '<div class="input-group-append">' +
      '<button style="min-width: ${buttonsWidth}" class="btn btn-increment ${buttonsClass} btn-plus" type="button">${incrementButton}</button>' +
      '</div>' +
      '</div>'
  })
});