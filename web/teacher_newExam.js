$(document).ready(function () {
    $('#newExam').on('submit',function (event) {
        event.preventDefault();

        var payload = App.utils.serializeFrom('#newExam');
        console.log(payload);

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.baseApiPath + 'exam/add',
            success: function(resp) {
                if (resp.id) {
                    App.ss.addMessage("Exam successfully saved!");
                    App.redirect('teacher_role.html');
                }
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})