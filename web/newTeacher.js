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
                    App.ss.addMessage("Teacher successfully saved!");
                    App.redirect('teachers.html');
                }
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})


