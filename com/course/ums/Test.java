package com.course.ums;

import com.course.ums.ws.*;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;


public class Test {
    public static void main(String[] args) throws Exception {

        Spark.port(8080);
        Spark.get("/hello", new Route() {
            @Override
            public Object handle(Request request, Response response) {
                return "world";
            }
        });

        Spark.post("/user/authenticate", new UserAuthenticate());
        Spark.post("/user/student/add", new AddStudent());
        Spark.post("/user/teacher/add", new AddTeacher());
        Spark.post("/user/course/add", new AddCourse());
        Spark.post("/user/semester/add", new AddSemester());
        Spark.post("/user/group/add", new AddGroup());
        Spark.post("/teacher/course/add", new AddTeacherCourse());
        Spark.post("/teacher/course/remove", new RemoveTeacherCourse());
        Spark.post("/group/teacher/add", new AddTeacherGroup());


    }
}

