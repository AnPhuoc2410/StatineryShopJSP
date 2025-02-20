/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.addEventListener('DOMContentLoaded', (event) => {
    const avatar = document.getElementById('avatar');
    const dropdownMenu = document.getElementById('dropdownMenu');

    avatar.addEventListener('click', () => {
        if (dropdownMenu.style.display === 'block') {
            dropdownMenu.style.display = 'none';
        } else {
            dropdownMenu.style.display = 'block';
        }
    });

    // Hide dropdown menu when clicking outside of it
    window.addEventListener('click', (event) => {
        if (!event.target.matches('.avatar')) {
            if (dropdownMenu.style.display === 'block') {
                dropdownMenu.style.display = 'none';
            }
        }
    });
});
