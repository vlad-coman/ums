App.utils.createGrid({
    dataUrl: "http://localhost:8080/exam/list_view_group2",
    columns: ['id', 'date','teacher_first_name', 'teacher_last_name', 'course_name' , 'semester_year', 'semester_index'],
    target: '#grid'
});
