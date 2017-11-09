package com.course.ums;

import com.course.ums.ws.AddStudent;
import com.course.ums.ws.UserAuthenticate;
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


    }
}
