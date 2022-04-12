
/* code by webdevtrick ( https://webdevtrick.com ) */
function enableEditMode() {
  descN.document.designMode = "on";
}
function Edit(command) {
  descN.document.execCommand(command, false, null);
}
function execVal(command, value) {
  descN.document.execCommand(command, false, value);
}