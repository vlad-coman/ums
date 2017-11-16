package com.course.ums.ws.course;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RemoveTeacherCourse extends JSONRoute {
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {

        String token = request.getString("token");
        if (!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps = DBManager.getConnection().prepareStatement("SELECT * FROM teachers_courses  WHERE teachers_id=? AND courses_id=?");

        ps.setInt(1, request.getInt("teachers_id"));
        ps.setInt(2, request.getInt("courses_id"));
        ResultSet resultSet = ps.executeQuery();

        int id;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
            ps = DBManager.getConnection().prepareStatement("DELETE FROM teachers_courses WHERE teachers_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } else {
            throw new RuntimeException("Invalid inputs");
        }

        JSONObject result = new JSONObject();
        result.put("id", id);

        return result;
    }
}
