
document.addEventListener('DOMContentLoaded', function() {

    var btn = document.querySelector('#myBtn');
    var overlay = document.querySelector('#overlay-modal');
    var closeBut = document.querySelector('.close');
    var modal =  document.querySelector('.modal');
    const addButton = document.querySelector(".add-button")

    var html = document.querySelector('html');

    btn.addEventListener('click', function(event) {

        event.preventDefault();
        modalElem = document.querySelector('.modal');

        modalElem.classList.add('active');
        overlay.classList.add('active');
        html.classList.add('hystmodal__opened');

    });

    addButton.addEventListener("click", () => {
        document.querySelector(".modal").classList.remove('active');
    })

    closeBut.addEventListener('click', function() {
        // var parentModel = this.closest('.modal');

        modal.classList.remove('active');
        overlay.classList.remove('active');
        html.classList.remove('hystmodal__opened');
    });


    overlay.addEventListener('click', () => {
        overlay.classList.remove('active');
        modal.classList.remove('active');
        html.classList.remove('hystmodal__opened');
    });
});



