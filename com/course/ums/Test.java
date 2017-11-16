package com.course.ums;

import com.course.ums.ws.course.AddCourse;
import com.course.ums.ws.course.AddTeacherCourse;
import com.course.ums.ws.course.RemoveTeacherCourse;
import com.course.ums.ws.exam.AddExam;
import com.course.ums.ws.group.*;
import com.course.ums.ws.semester.AddSemester;
import com.course.ums.ws.user.AddUser;
import com.course.ums.ws.user.ListUsers;
import com.course.ums.ws.user.Authenticate;
import com.course.ums.ws.user.AddStudent;
import com.course.ums.ws.user.AddTeacher;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;


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


        Spark.post("user/authenticate", new Authenticate());
        Spark.post("user/student/add", new AddStudent());
        Spark.post("user/teacher/add", new AddTeacher());

        Spark.post("semester/add", new AddSemester());

        Spark.post("course/add", new AddCourse());
        Spark.post("teacher/course/add", new AddTeacherCourse());
        Spark.post("teacher/course/remove", new RemoveTeacherCourse());

        Spark.post("group/add", new AddGroup());
        Spark.post("group/teacher/add", new AddGroupTeacherCourse());
        Spark.post("group/teacher/remove", new RemoveGroupTeacherCourse());
        Spark.post("group/student/add", new AddGroupStudent());
        Spark.post("group/student/remove", new RemoveGroupStudent());

        Spark.post("/exam/add", new AddExam());


    }
}
