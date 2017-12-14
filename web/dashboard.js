

var administrator = 0, student = 0, teacher = 0;

for (var i = 0; i < App.ss.get('auth').roles.length; i++) {


			if (App.ss.get('auth').roles[i] === "administrator") {
			    administrator+=1;
			    continue;
			     
			}

			if (App.ss.get('auth').roles[i] === "student") {
			    student+=1;
			    continue;
			                
			}
			if (App.ss.get('auth').roles[i] === "teacher") {
				teacher+=1;
				

			}

}

if (administrator) {

	$("#administrator").html("<button>Administrator</button>");
}

if(student) {

	$("#student").html("<button >Student</button>");
}

if(teacher) {
	
	$("#teacher").html("<button>Teacher</button>");
}
