'use strict';

$(document).ready(function () {
    $('#update').click(function () {
        let form = $('.needs-validation')[0];

        if (form.checkValidity() === false){
            form.classList.add('was-validated');
        } else {
            $.ajax({
                type: 'PUT',
                url: '/api/administration/create',
                data: $('form').serialize(),
                cache: false,
                processData: false,
                success: function () {
                    displayMessage('Administration details updated successfully.', 'success');
                },
                error: function () {
                    displayMessage('Could not update the details of the administration. Please retry later.', 'danger');
                }
            })
        }
    });

    function displayMessage(message, type) {
        $('#message').text(message);
        $('#alert').removeClass('alert-warning alert-danger alert-success alert-info').addClass(`alert-${type}`).slideDown();

        setTimeout(function () {
            $('#alert').slideUp();
        }, 3000);
    }
});