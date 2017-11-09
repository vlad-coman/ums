package com.course.ums.ws;


import com.course.ums.db.DBManager;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.ResultSet;
import java.sql.Statement;

public class UserAuthenticate extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {

        JSONObject json = new JSONObject(request.body());
        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, email, password FROM users WHERE `email`= '" + json.get("email") + "' AND `password` = '" + json.get("password") + "' ");

        JSONObject user = new JSONObject();

        if (resultSet.next()) {

            user.put("id", resultSet.getInt("id"));
            user.put("email", resultSet.getString("email"));
            user.put("password", resultSet.getString("password"));

        } else {

            return "Invalid username and/or password!";
        }

        JSONArray roles = new JSONArray();

        resultSet = statement.executeQuery("SELECT * FROM students NATURAL JOIN users where students.id = users.id ");
        while (resultSet.next()) {
            if (resultSet.getInt("id") == user.getInt("id")) {
                JSONObject role = new JSONObject();
                role.put("role", "student");
                roles.put(role);
            }
        }

        resultSet = statement.executeQuery("SELECT * FROM teachers NATURAL JOIN users where teachers.id = users.id ");
        while (resultSet.next()) {
            if (resultSet.getInt("id") == user.getInt("id")) {
                JSONObject role = new JSONObject();
                role.put("role", "teacher");
                roles.put(role);
            }
        }

        resultSet = statement.executeQuery("SELECT * FROM administrators NATURAL JOIN users where administrators.id = users.id ");
        while (resultSet.next()) {
            if (resultSet.getInt("id") == user.getInt("id")) {
                JSONObject role = new JSONObject();
                role.put("role", "administrator");
                roles.put(role);
            }
        }

        if (roles.length() == 0) {
            return "Error! The user with id: " + user.get("id") + " has no assigned roles";
        }

        return "The user you have requested has id: " + user.get("id") + ". " + " The user has following roles in the system: " + roles;
    }

}
