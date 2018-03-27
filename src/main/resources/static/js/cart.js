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

    $('#create').click(function (e) {
        let products = [];

        $('tr.product').each(function (i, product) {
            products.push({
                'product': {
                    'id': $(product).attr('id')
                },
                'start': $(product).find('[name=\'start\']').val(),
                'end': $(product).find('[name=\'end\']').val()
            })
        });


        $.ajax({
            url: '/booking/create',
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(products)
        });
    });

    function displayMessage(message, type) {
        $('#message').text(message);
        $('#alert').removeClass('alert-warning alert-danger alert-success alert-info').addClass(`alert-${type}`).slideDown();

        setTimeout(function () {
            $('#alert').slideUp();
        }, 3000);
    }

    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
    });
});