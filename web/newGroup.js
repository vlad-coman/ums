$(document).ready(function () {
    $('#newGroup').on('submit',function (event) {
        event.preventDefault();

        var payload = App.utils.serializeFrom('#newGroup');
        console.log(payload);

        jQuery.ajax({
            method: "POST",
            data: JSON.stringify(payload),
            url: App.constants.baseApiPath + 'group/add',
            success: function(resp) {
                if (resp.id) {
                    App.redirect('groups.html');
                    App.ss.set('message', "Saved!!!!")
                    
                    
                  }
                    
                
                   
            },
            error: function(e) {
                console.log(e)
            }
        })
    })
})