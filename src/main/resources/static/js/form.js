console.log("Hi");


//The code below scales the form to be the appropriate size without being squished.

if ($(window).width() < 800) {
    $('#my-form').addClass('w-50');
} else {
    $('#my-form').removeClass('w-50');
}