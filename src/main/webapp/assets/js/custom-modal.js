const MESSAGE_MODAL_ID = 'message-modal';

function showMessageModal(iconClass, modalHeader, modalContent, buttonText = 'OK', callbackFunction = null) {
  let modalHtml =
    '<div class="modal fade" id="' + MESSAGE_MODAL_ID + '" tabindex="-1" role="dialog" ' +
    '     aria-labelledby="' + MESSAGE_MODAL_ID + '-lb" aria-hidden="true">' +
    '  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable" role="document">' +
    '    <div class="modal-content">' +
    '      <div class="modal-header">' +
    '        <h5 id="' + MESSAGE_MODAL_ID + '-lb" class="modal-title">' +
    '           <em class="' + iconClass + ' mr-2"></em>' + modalHeader +
    '        </h5>' +
    '        <button type="button" class="close" data-dismiss="modal" aria-label="Close">' +
    '          <span aria-hidden="true">&times;</span>' +
    '        </button>' +
    '      </div>' +
    '      <div class="modal-body py-0">' +
    '        <p class="text-justify m-0">' + modalContent + '</p>' +
    '      </div>' +
    '      <div class="modal-footer">' +
    '        <button type="button" class="btn btn-primary px-5" data-dismiss="modal">' + buttonText + '</button>' +
    '      </div>' +
    '    </div>' +
    '  </div>' +
    '</div>';

  $('#' + MESSAGE_MODAL_ID).remove();
  $('body').append(modalHtml);
  $('#' + MESSAGE_MODAL_ID).modal('show');
  $('#' + MESSAGE_MODAL_ID).on('hidden.bs.modal', function () {
    if(callbackFunction != null && typeof callbackFunction === "function") {
      callbackFunction();
    }
  });
}