// enable tooltips
var tooltipTriggerList = [].slice.call(
  document.querySelectorAll('[data-bs-toggle="tooltip"]')
);
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl);
});

// click nav&tab go there & click go to form
var form = document.getElementById("form");
function heightToTop(ele) {
  let bridge = ele;
  let root = document.body;
  let height = 0;
  do {
    height += bridge.offsetTop;
    bridge = bridge.offsetParent;
  } while (bridge !== root);

  return height;
}
function goToTab() {
  window.scrollTo({
    top: heightToTop(document.getElementsByClassName("tab-content")[0]) - 70,
    behavior: "smooth"
  });
}
function goForm() {
  window.scrollTo({
    top: heightToTop(form) - 170,
    behavior: "smooth"
  });
}

// mobile btn toggle
var bottomBtn = document.getElementById("bottomBtn");
bottomBtn.style.display = "none";
window.onscroll = function () {
  const t = document.documentElement.scrollTop || document.body.scrollTop;
  if (bottomBtn !== null) {
    if (
      t >
        heightToTop(document.getElementsByClassName("tab-content")[0]) - 100 &&
      t < heightToTop(form) - 170
    ) {
      bottomBtn.style.display = "block";
    } else {
      bottomBtn.style.display = "none";
    }
  }
};

// form validation
(function () {
  "use strict";
  var forms = document.querySelectorAll(".needs-validation");
  Array.prototype.slice.call(forms).forEach(function (form) {
    form.addEventListener(
      "submit",
      function (event) {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }

        form.classList.add("was-validated");
      },
      false
    );
  });
})();