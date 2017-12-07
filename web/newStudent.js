$(document).ready(function () {
    $('#newStudent').on('submit',function (event) {
        event.preventDefault();

        var payload = App.utils.serializeFrom('#newStudent');
        console.log(payload);

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.baseApiPath + 'user/student/add',
            success: function(resp) {
                if (resp.id) {
                    App.redirect('students.html');
                    App.ss.set('message', "Saved!!!!")
                }
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})