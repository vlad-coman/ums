App.utils.createGrid({
    dataUrl: "http://localhost:8080/exam/grades/list_view_sem1",
    columns: ['id' , 'grade' , 'course_name' , 'student_first_name', 'student_last_name' , 'teacher_last_name', 'teacher_first_name'],
    target: '#grid'
});
