console.log("Hi");


//The code below scales the form to be the appropriate size without being squished.

//On Register Form

jQuery(document).ready(function($) {
    var alterClass = function() {
        var ww = document.body.clientWidth;
        if (ww < 800) {
            $('.form-container-2').removeClass('w-50');
        } else if (ww >= 801) {
            $('.form-container-2').addClass('w-50');
        };
    };
    $(window).resize(function(){
        alterClass();
    });
    //Fire it when the page first loads:
    alterClass();
});



//On User Edit

jQuery(document).ready(function($) {
    var alterClass = function() {
        var ww = document.body.clientWidth;
        if (ww < 800) {
            $('.my-container').removeClass('w-50');
        } else if (ww >= 801) {
            $('.my-container').addClass('w-50');
        };
    };
    $(window).resize(function(){
        alterClass();
    });
    //Fire it when the page first loads:
    alterClass();
});