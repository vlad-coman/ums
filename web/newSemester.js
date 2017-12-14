$(document).ready(function () {
    $('#newSemester').on('submit',function (event) {
        event.preventDefault();

        var payload = App.utils.serializeFrom('#newSemester');
        console.log(payload);

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.baseApiPath + 'semester/add',
            success: function(resp) {
                if (resp.id) {
                    App.ss.addMessage("Semester successfully saved!");
                    App.redirect('semesters.html');
                }
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})