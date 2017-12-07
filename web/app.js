var App = App || {};

App.constants = {
	baseApiPath: 'http://localhost:8080/',
	baseUrl: '/home/vlad/IdeaProjects/ums/web/'
};

App.utils = App.utils || {};

App.redirect = function(url) {
	if (!url) {
		return false;
	}
	if (url.replace(App.baseUrl) !== url) {
		url = App.baseUrl + url;

}
	window.location.href = url;

}

App.utils.createGrid = function(config) {
	if (!config) {
		throw new error("Cannot initialize the grid", config);
	}
	var dataUrl = config.dataUrl,
		columns = config.columns,
		target = config.target || 'body',
		data,
		generateRow = function(item) {
			var tr = jQuery('<tr>');
			columns.forEach(function(column) {
				var td = jQuery('<td>');
				td.text(item[column]);
				tr.append(td);
			});
			return tr;
		},
		generateHeader = function() {
			var thead = jQuery('<thead>'),
				tr = jQuery('<tr>');
			columns.forEach(function(column) {
				var th = jQuery('<th>');
				th.text(column);
				tr.append(th);
			})
			thead.append(tr);
			return thead;
		},
		generateBody = function() {
			var tbody = $('<tbody>');
			data.forEach(function(item) {
				var row;
				row = generateRow(item);
				tbody.append(row);
			});
			return tbody;
		},
		generateTable = function(){
			var table = $('<table>');
			table.append(generateHeader());
			table.append(generateBody());
			table.addClass('table')
			return table;
		},
		buildGrid = function() {
			var table = generateTable();
			$(target).append(table);
		};

	jQuery.ajax({
		method: "POST",
		data: JSON.stringify({"token": "6"}),
		url: dataUrl,
		success: function(resp) {
			data = resp.items;
			buildGrid();
		},
		error: function(e) {
			console.log(e)
		}
	})

};

App.utils.serializeFrom = function(target){

	var objectify = function (formArray) {
        var returnArray = {token: App.ss.get('auth')['token'].toString()}, i = 0
		;
		for (i = 0; i<formArray.length; i++) {
			returnArray[formArray[i]['name']] = formArray[i]['value'];
		}
		return returnArray;
		
    };

    return objectify($(target).serializeArray());
};

App.ss = {
	get: function(key){
		return JSON.parse(sessionStorage.getItem(key))    ;
	},
	set: function(key, value) {
		value = JSON.stringify(value);
		sessionStorage.setItem(key, value);
	},

};

(function(){
	var auth = App.ss.get('auth');
	if (!auth.token && window.location.href == window.location.href.replace('login.html')) {
		window.location.href = App.constants.baseUrl + 'login.html';
	}
}());

// Managing flash messages

if (App.ss.get('message')!= null) {
	 $(".flashMessage").html(App.ss.get('message'));
	setTimeout(function() {
       $(".flashMessage").fadeOut('slow');
 }, 5000);
}

App.ss.set('message', null);



