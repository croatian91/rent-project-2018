'use strict';

$(document).ready(function () {
    $('#search').autocomplete({
        source: function (request, response) {
            $.ajax({
                type: 'GET',
                url: '/search',
                data: {q: request.term},
                success: function (data) {
                    try {
                        response($.map(data,
                            function (user) {
                                return {label: `${user.lastName} ${user.firstName}`, userId: user.id};
                            }));
                    } catch (err) {
                        console.log(err);
                    }
                },
                error: function () {
                }
            })
        },
        select: function (event, ui) {
            window.location.href = `/account/${ui.item.userId}`;
        },
        minLength: 2
    });
});