package com.course.ums.ws.exam;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddExam extends JSONRoute {
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {

        String token = request.getString("token");
        if (!DBManager.validateToken(token, AuthManager.ROLE_TEACHER)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO exams(date, semesters_id, teachers_courses_id, groups_id) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, request.getString("date"));
        ps.setString(2, request.getString("semesters_id"));
        ps.setString(3, request.getString("teachers_courses_id"));
        ps.setString(4, request.getString("groups_id"));
        ResultSet rs;

        PreparedStatement ps2 = DBManager.getConnection().prepareStatement("SELECT * FROM teachers_courses WHERE teachers_id=? ");
        ps2.setString(1, request.getString("token"));
        ResultSet resultSet = ps2.executeQuery();

        if (resultSet.next()) {
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();

        } else {
            throw new RuntimeException("You must be the teacher for the exam you want to enter!");


        }


        JSONObject result = new JSONObject();
        result.put("id", rs.getInt(1));

        return result;


    }
}
