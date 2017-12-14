App.utils.createGrid({
    dataUrl: "http://localhost:8080/exam/list_view",
    columns: ['id', 'date' , 'teacher_first_name', 'teacher_last_name' , 'course_name'],
    target: '#grid'
});