package com.course.ums.ws.group;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddGroupTeacherCourse extends JSONRoute {

    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if (!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO group_teacher_courses(groups_id, teachers_courses_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, request.getString("groups_id"));
        ps.setString(2, request.getString("teachers_courses_id"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        JSONObject result = new JSONObject();
        result.put("id", rs.getInt(1));

        return result;
    }
}
