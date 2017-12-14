package com.course.ums;

import com.course.ums.ws.AddUser;
import com.course.ums.ws.ListUsers;
import com.course.ums.ws.course.AddCourse;
import com.course.ums.ws.course.ListCourses;
import com.course.ums.ws.exam.*;
import com.course.ums.ws.group.AddGroup;
import com.course.ums.ws.group.ListGroups;
import com.course.ums.ws.semester.AddSemester;
import com.course.ums.ws.semester.ListSemesters;
import com.course.ums.ws.user.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by vh on 11/2/17.
 */
public class Test {
    public static void main(String[] args) throws Exception {

        Spark.port(8080);
        Spark.post("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "world";
            }
        });
        Spark.post("/user/add", new AddUser());
        Spark.post("/user/list", new ListUsers());
        Spark.get("/user/list", new ListUsers());


        Spark.post("user/authenticate", new Authenticate());
        Spark.post("user/student/add", new AddStudent());
        Spark.post("user/teacher/add", new AddTeacher());

        Spark.post("course/add", new AddCourse());
        Spark.post("semester/add", new AddSemester());
        Spark.post("group/add", new AddGroup());
        Spark.post("exam/add", new AddExam());


        Spark.post("user/student/list", new ListStudents());
        Spark.post("user/teacher/list", new ListTeachers());
        Spark.post("course/list", new ListCourses());
        Spark.post("semester/list", new ListSemesters());
        Spark.post("group/list", new ListGroups());
        Spark.post("exam/list", new ListExams());
        Spark.post("/user/list/teachers_courses", new ListTeacherCourses());
        Spark.post("user/list/grades", new ListGrades());
        Spark.post("exam/list_view", new ListViewExams());
        Spark.post("exam/list_view_group1", new ListViewExamsGroup1());
        Spark.post("exam/list_view_group2", new ListViewExamsGroup2());
        Spark.post("exam/grades/list_view_sem1", new ListViewGradesSem1());
        Spark.post("exam/grades/list_view_sem2", new ListViewGradesSem2());


    }
}
