package com.course.ums.ws.group;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RemoveGroupTeacherCourse extends JSONRoute {
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if (!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps = DBManager.getConnection().prepareStatement("SELECT * FROM group_teacher_courses  WHERE groups_id=? AND teachers_courses_id=?");

        ps.setInt(1, request.getInt("groups_id"));
        ps.setInt(2, request.getInt("teachers_courses_id"));
        ResultSet resultSet = ps.executeQuery();

        int id;
        if (resultSet.next()) {
            id = resultSet.getInt("id");
            ps = DBManager.getConnection().prepareStatement("DELETE FROM group_teacher_courses WHERE id =?");
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
