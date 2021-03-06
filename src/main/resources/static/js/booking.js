'use strict';

$(document).ready(function () {
    let ctx = $("meta[name='_ctx']").attr("content") ? $("meta[name='_ctx']").attr("content") : '';
    let getDiff = function (d1, d2) {
        return (Date.parse(d2) - Date.parse(d1)) / 86400000;
    };

    let start = $('#start'),
        end = $('#end');


    $('#rental_duration').val(getDiff(start.val(), end.val()));

    $('#start, #end').change(function () {
        $('#rental_duration').val(getDiff(start.val(), end.val()));
    });

    start.attr('min', moment().format('YYYY-MM-DD'));
    end.attr('min', moment().add(1, 'days').format('YYYY-MM-DD')).attr('max', moment().add(3, 'months').format('YYYY-MM-DD'));

    $('#update').click(function () {
        $.ajax({
            type: 'PUT',
            url: `${ctx}/api/booking/update`,
            data: $('form').serialize(),
            cache: false,
            processData: false,
            success: function () {
                displayMessage('Booking details updated successfully.', 'success');
            },
            error: function () {
                displayMessage('Could not update the details of the booking. Please retry later.', 'danger');
            }
        })
    });

    $('#confirm').click(function () {
        //Not a good practice. Could be modified by anyone.
        let id = $('#bookingID').val();

        $.ajax({
            type: 'GET',
            url: `${ctx}/api/booking/${id}/delete`,
            success: function () {

            },
            error: function () {
                displayMessage('Could not delete the booking. Please retry later.', 'danger');
            }
        });
    });

    $('#confirm_all').click(function () {
        let errors = false;

        $("input:checked").each(function () {
            let id = $(this).attr('id');

            $.ajax({
                type: 'GET',
                url: `${ctx}/api/booking/${id}/delete`,
                success: function () {
                    removeBooking(id);
                },
                error: function () {
                    errors = true;
                }
            });
        }).promise().done(function () {
            if (errors)
                displayMessage('An error occurred during the deletion. Please retry later.', 'danger');
        });
    });

    function removeBooking(id) {
        $('#' + id).closest('tr').fadeOut('slow', function () {
            $(this).remove();
        });
    }

    function displayMessage(message, type) {
        $('#message').text(message);
        $('#alert').removeClass('alert-warning alert-danger alert-success alert-info').addClass(`alert-${type}`).slideDown();

        setTimeout(function () {
            $('#alert').slideUp();
        }, 3000);
    }

});