'use strict';

$(document).ready(function () {
    $('#update').click(function () {
        let form = $('.needs-validation')[0];

        if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            $.ajax({
                type: 'PUT',
                url: '/api/product/update',
                data: $('form').serialize(),
                cache: false,
                processData: false,
                success: function () {
                    displayMessage('Product details updated successfully.', 'success');
                },
                error: function () {
                    displayMessage('Could not update the details of the product. Please retry later.', 'danger');
                }
            })
        }
    });

    $('#add').click(function () {
        let id = $('#id').val();

        $.ajax({
            type: 'GET',
            url: `/cart/add/product/${id}/`,
            success: function () {
                $('#add').attr('disabled', 'disabled');

                displayMessage('Product added successfully to cart.', 'success');
            },
            error: function () {
                displayMessage('Could not add the product to the cart. Please retry later.', 'danger');
            }
        })
    });

    function displayMessage(message, type) {
        $('#message').text(message);
        $('#alert').removeClass('alert-warning alert-danger alert-success alert-info').addClass(`alert-${type}`).slideDown();

        setTimeout(function () {
            $('#alert').slideUp();
        }, 3000);
    }
});