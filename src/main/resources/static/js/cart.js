'use strict';

$(document).ready(function () {
    $('.delete').click(function () {
        let id = $(this).closest('tr').attr('id');

        $.ajax({
            type: 'GET',
            url: `/cart/remove/product/${id}/`,
            success: function () {
                $(`#${id}`).fadeOut('slow', function () {
                    $(this).remove();
                });
            },
            error: function () {
                displayMessage('Could not remove the product from the cart. Please retry later.', 'danger');
            }
        });
    });

    $('#create').click(function () {

    });

    function displayMessage(message, type) {
        $('#message').text(message);
        $('#alert').removeClass('alert-warning alert-danger alert-success alert-info').addClass(`alert-${type}`).slideDown();

        setTimeout(function () {
            $('#alert').slideUp();
        }, 3000);
    }
});