$(document).ready(function () {
    $('#newCourse').on('submit',function (event) {
        event.preventDefault();

        var payload = App.utils.serializeFrom('#newCourse');
        console.log(payload);

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.baseApiPath + 'course/add',
            success: function(resp) {
                if (resp.id) {
                    App.ss.addMessage("Course successfully saved!");
                    App.redirect('courses.html');
                }
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})