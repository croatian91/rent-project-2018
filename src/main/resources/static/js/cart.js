'use strict';

$(document).ready(function () {
    let ctx = $("meta[name='_ctx']").attr("content") ? $("meta[name='_ctx']").attr("content") : '';

    $('.delete').click(function () {
        let id = $(this).closest('tr').attr('id');

        removeProduct(id);
    });

    $('#create').click(function () {
        let bookings = [];

        $('tr.product').each(function (i, product) {
            bookings.push({
                'product': {
                    'id': $(product).attr('id')
                },
                'start': $(product).find('[name=\'start\']').val(),
                'end': $(product).find('[name=\'end\']').val()
            })
        });

        $.ajax({
            url: `${ctx}/booking/create`,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(bookings),
            success: function (data) {
                $('[data-toggle="popover"]').popover('dispose');

                bookings.forEach(function (booking) {
                    let productId = booking.product.id;

                    if (Object.keys(data).indexOf(productId.toString()) === -1) {
                        removeProduct(productId);
                        $('#' + productId).popover('dispose');
                    }
                });

                setTimeout(function () {
                    Object.keys(data).forEach(function (productId) {
                        $('#' + productId).popover({
                            content: data[productId]
                        }).popover('show');
                    });
                }, 1000);
            }
        });
    });

    function removeProduct(id) {
        $.ajax({
            type: 'GET',
            url: `${ctx}/cart/remove/product/${id}/`,
            success: function () {
                $(`#${id}`).fadeOut('slow', function () {
                    $(this).remove();
                });
            },
            error: function () {
                displayMessage('Could not remove the product from the cart. Please retry later.', 'danger');
            }
        });
    }

    function displayMessage(message, type) {
        $('#message').text(message);
        $('#alert').removeClass('alert-warning alert-danger alert-success alert-info').addClass(`alert-${type}`).slideDown();

        setTimeout(function () {
            $('#alert').slideUp();
        }, 3000);
    }

    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
    });
});