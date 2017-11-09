package com.course.ums.ws;

import com.course.ums.db.DBManager;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddTeacherGroup extends MyRoute {

    @Override
    public Object myHandle(Request request, Response response) throws Exception {

        JSONObject json = new JSONObject(request.body());
        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  email, password FROM administrators NATURAL JOIN users WHERE `email`= '" + json.get("email") + "' AND `password` = '" + json.get("password") + "' ");
        JSONObject inheritedID = new JSONObject();

        if (resultSet.next()) {
            resultSet = statement.executeQuery("SELECT id FROM groups where id = '" + json.getInt("groups_id") + "'");
            if (resultSet.next()) {
                resultSet = statement.executeQuery("SELECT id FROM teachers_courses where id = '" + json.getInt("teacher_courses_id") + "'");
                if (resultSet.next()) {

                    PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO group_teacher_courses(groups_id, teachers_courses_id) VALUES(?, ?)  ", Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(1, json.getInt("groups_id"));
                    preparedStatement.setInt(2, json.getInt("teacher_courses_id"));
                    preparedStatement.execute();

                    resultSet = preparedStatement.getGeneratedKeys();
                    resultSet.next();
                    inheritedID.put("id", resultSet.getInt(1));
                } else {

                    return " Invalid teacher id! ";
                }
            } else {

                return "Invalid group id! ";
            }
        } else {

            return "Invalid administrator email and/or password";
        }


        return " The teacher group was added to the database with the ID: " + inheritedID.getInt("id");
    }

}
