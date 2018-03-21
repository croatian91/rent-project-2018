'use strict';

$(document).ready(function () {
    let getDiff = function (d1, d2) {
        return Math.floor((Date.parse(d2) - Date.parse(d1)) / 86400000);
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
            url: '/api/booking/update',
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
            url: `/api/booking/${id}/delete`,
            success: function () {
                window.location.replace("/");
            },
            error: function () {
                displayMessage('Could not delete the booking. Please retry later.', 'danger');
            }
        });
    });

    function displayMessage(message, type) {
        $('#message').text(message);
        $('#alert').removeClass('alert-warning alert-danger alert-success alert-info').addClass(`alert-${type}`).slideDown();

        setTimeout(function () {
            $('#alert').slideUp();
        }, 3000);
    }

});