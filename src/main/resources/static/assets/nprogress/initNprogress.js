$(document).ajaxStart(function () {
    NProgress.start();
}).ajaxStop(function () {
    NProgress.done();
})