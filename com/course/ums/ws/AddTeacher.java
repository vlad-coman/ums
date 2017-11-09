package com.course.ums.ws;

import com.course.ums.db.DBManager;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddTeacher extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {

        JSONObject json = new JSONObject(request.body());
        JSONObject inheritedID = new JSONObject();
        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  email, password FROM administrators NATURAL JOIN users WHERE `email`= '" + json.get("email") + "' AND `password` = '" + json.get("password") + "' ");

        if (resultSet.next()) {

            PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO users(first_name, last_name, email, password) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, json.getString("first_name"));
            preparedStatement.setString(2, json.getString("last_name"));
            preparedStatement.setString(3, json.getString("username"));
            preparedStatement.setString(4, json.getString("user_password"));
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            inheritedID.put("id", resultSet.getInt(1));

            preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO teachers(id, level) VALUES(?, ?)");
            preparedStatement.setInt(1, inheritedID.getInt("id"));
            preparedStatement.setInt(2, json.getInt("level"));

            preparedStatement.execute();

        } else {

            return "Invalid administrator email and/or password";
        }


        return "The teacher was successfully added to the database with the ID: " + inheritedID.getInt("id");
    }
}

// JSON sample for testing  {"email":"f@" , "password":"ff", "first_name":"ggg", "last_name":"lll", "username":"poc", "user_password":"thm", "level":"33"}

