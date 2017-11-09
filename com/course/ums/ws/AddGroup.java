package com.course.ums.ws;

import com.course.ums.db.DBManager;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddGroup extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {

        JSONObject json = new JSONObject(request.body());
        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  email, password FROM administrators NATURAL JOIN users WHERE `email`= '" + json.get("email") + "' AND `password` = '" + json.get("password") + "' ");
        JSONObject inheritedID = new JSONObject();

        if (resultSet.next()) {
            resultSet = statement.executeQuery("SELECT id FROM semesters where id = '" + json.getInt("semesters_id") + "'");
            if (resultSet.next()) {

                PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO groups(semesters_id) VALUES(?)  ", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, json.getInt("semesters_id"));
                preparedStatement.execute();

                resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                inheritedID.put("id", resultSet.getInt(1));

            } else {

                return "Invalid or no semester in the database";
            }

        } else {

            return "Invalid administrator email and/or password";
        }

        return " The group was succesfully added to the database with the ID: " + inheritedID.getInt("id");
    }


}
