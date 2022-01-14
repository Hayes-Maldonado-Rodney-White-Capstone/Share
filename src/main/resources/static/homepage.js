console.log("Hi");

$(function () {
    $(document).scroll(function () {
        var $nav = $(".navbar");
        var $top = $(".img-overlay");
        $nav.toggleClass('scrolled', $(this).scrollTop() > $top.height());
    });
});