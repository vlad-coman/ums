package com.course.ums.ws;

import com.course.ums.db.DBManager;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddCourse extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {

        JSONObject json = new JSONObject(request.body());
        JSONObject inheritedID = new JSONObject();
        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  email, password FROM administrators NATURAL JOIN users WHERE `email`= '" + json.get("email") + "' AND `password` = '" + json.get("password") + "' ");

        if (resultSet.next()) {

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO courses(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, json.getString("name"));
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            inheritedID.put("id", resultSet.getInt(1));


        } else {

            return "Invalid administrator email and/or password";
        }


        return "The course was successfully added to the database with the ID: " + inheritedID.getInt("id");
    }
}
