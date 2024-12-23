const navItems = document.querySelectorAll('.nav-item');

const openDropdown = (dropdown) => {
    dropdown.style.opacity = '1';
    dropdown.style.visibility = 'visible';
}

const closeDropdown = (dropdown) => {
    dropdown.style.opacity = '0';
    dropdown.style.visibility = 'hidden';
}

navItems.forEach(item => {
    const dropdown = item.querySelector('.dropdown');

    item.addEventListener('mouseenter', () => {
        openDropdown(dropdown)
    })

    item.addEventListener('mouseleave', () => {
        closeDropdown(dropdown)
    })
})

// 캐러셀
let currentSlide = 0;

function showSlide(index) {
    const slides = document.querySelectorAll('.menu-item');
    if (index >= slides.length) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = slides.length - 1;
    } else {
        currentSlide = index;
    }
    const offset = -currentSlide * 100;
    slides.forEach(slide => {
        slide.style.transform = `translateX(${offset}%)`;
    });
}

function nextSlide() {
    showSlide(currentSlide + 1);
}

function prevSlide() {
    showSlide(currentSlide - 1);
}

// 초기 슬라이드 설정
showSlide(currentSlide);

let currentRecommendationSlide = 0;

function showRecommendationSlide(index) {
    const slides = document.querySelectorAll('#carousel2 .menu-item2');
    const container = document.querySelector('#carousel2 .recommendation-items');
    if (index >= slides.length) {
        currentRecommendationSlide = 0;
    } else if (index < 0) {
        currentRecommendationSlide = slides.length - 1;
    } else {
        currentRecommendationSlide = index;
    }
    const offset = -currentRecommendationSlide * 100;
    container.style.transform = `translateX(${offset}%)`;
}

function nextRecommendationSlide() {
    showRecommendationSlide(currentRecommendationSlide + 1);
}

function prevRecommendationSlide() {
    showRecommendationSlide(currentRecommendationSlide - 1);
}

// 초기 슬라이드 설정
showRecommendationSlide(currentRecommendationSlide);

