$(document).ready(function () {
    $('#newTeacher').on('submit',function (event) {
        event.preventDefault();

        var payload = App.utils.serializeFrom('#newTeacher');
        console.log(payload);

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.baseApiPath + 'user/teacher/add',
            success: function(resp) {
                if (resp.id) {
                    App.redirect('teachers.html');
                    App.ss.set('message', "Saved!!!!")
                }
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})