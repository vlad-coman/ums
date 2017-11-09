package com.course.ums.ws;

import com.course.ums.db.DBManager;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RemoveTeacherCourse extends MyRoute{

    @Override
    public Object myHandle(Request request, Response response) throws Exception {

        JSONObject json = new JSONObject(request.body());

        Statement statement = DBManager.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT  email, password FROM administrators NATURAL JOIN users WHERE `email`= '" + json.get("email") + "' AND `password` = '" + json.get("password") + "' ");

        if (resultSet.next()) {
            resultSet = statement.executeQuery("SELECT id FROM teachers where id = '" + json.getInt("teachers_id") + "'");
            if (resultSet.next()) {
                resultSet = statement.executeQuery("SELECT id FROM courses where id = '" + json.getInt("courses_id") + "'");

                if (resultSet.next()) {

                    PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("DELETE FROM teachers_courses WHERE teachers_id=?");
                    preparedStatement.setInt(1, json.getInt("teachers_id"));
                    preparedStatement.executeUpdate();



                } else {

                    return " Invalid course_id! ";
                }
            } else {

                return "Invalid teacher_id! ";
            }
        } else {

            return "Invalid administrator email and/or password";
        }


        return " The course was succesfully removed from the database!";
//

    }

}
