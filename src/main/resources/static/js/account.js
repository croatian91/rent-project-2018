'use strict';

$(document).ready(function () {
    let ctx = $("meta[name='_ctx']").attr("content") ? $("meta[name='_ctx']").attr("content") : '';

    $('#update').click(function () {
        let form = $('.needs-validation')[0];

        if ($('#password').val() !== $('#passwordConfirm').val()) {
            displayMessage('The passwords are different.', 'warning');
        } else if (form.checkValidity() === false) {
            form.classList.add('was-validated');
        } else {
            $.ajax({
                type: 'PUT',
                url: `${ctx}/api/account/update`,
                data: $('form').serialize(),
                cache: false,
                processData: false,
                success: function () {
                    displayMessage('Account details updated successfully.', 'success');
                },
                error: function () {
                    displayMessage('Could not update your account. Please retry later.', 'danger');
                }
            })
        }
    });

    $('#confirm').click(function () {
        $.ajax({
            type: 'GET',
            url: `${ctx}/api/account/delete`,
            success: function () {
                window.location.replace("/login");
            },
            error: function () {
                displayMessage('Could not delete your account. Please retry later.', 'danger');
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