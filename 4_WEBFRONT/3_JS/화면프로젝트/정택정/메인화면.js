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