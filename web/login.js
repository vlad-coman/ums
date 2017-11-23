
$(document).ready(function () {
    $('#loginForm').on('submit',function (event) {
        event.preventDefault();

        var payload = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.basePath + 'user/authenticate',
            success: function(resp) {
                data = JSON.stringify(resp);
                sessionStorage.setItem('user', data);
            },
            
            error: function(e) {
                console.log(e)
            }
        })
    })
})