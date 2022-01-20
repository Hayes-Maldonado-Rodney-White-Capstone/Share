console.log("Hi");

$(function () {
    $(document).scroll(function () {
        var $nav = $(".navbar");
        var $top = $(".img-overlay");
        $nav.toggleClass('scrolled', $(this).scrollTop() > $top.height());
    });
});

const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        const ani1 = entry.target.querySelector('.ani-card-1');
        const aniword1 = entry.target.querySelector('.ani-words-1');

        const ani2 = entry.target.querySelector('.ani-card-2');
        const aniword2 = entry.target.querySelector('.ani-words-2');

        const ani3 = entry.target.querySelector(".ani-card-3")
        const aniword3 = entry.target.querySelector('.ani-words-3');

        const ani4 = entry.target.querySelector(".ani-card-4")
        const aniword4 = entry.target.querySelector(".ani-words-4")

        if (entry.isIntersecting) {
            ani1.classList.add('ani-card-1-animation');
            aniword1.classList.add('ani-card-1-animation');

            ani2.classList.add('ani-card-2-animation');
            aniword2.classList.add('ani-card-2-animation');

            ani3.classList.add('ani-card-3-animation');
            aniword3.classList.add('ani-card-3-animation');

            ani4.classList.add('ani-card-4-animation');
            aniword4.classList.add('ani-card-4-animation');

            return; // if we added the class, exit the function
        }

        // We're not intersecting, so remove the class!
        ani1.classList.remove('ani-card-1-animation');
        aniword1.classList.remove('ani-card-1-animation');

        ani2.classList.remove('ani-card-2-animation');
        aniword2.classList.remove('ani-card-2-animation');

        ani3.classList.remove('ani-card-3-animation');
        aniword3.classList.remove('ani-card-3-animation');

        ani4.classList.remove('ani-card-4-animation');
        aniword4.classList.remove('ani-card-4-animation');
    });
});

observer.observe(document.querySelector('.slogan-section'));
