package com.course.ums.ws.course;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AddCourse extends JSONRoute {
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if (!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO courses(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, request.getString("name"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();


        JSONObject result = new JSONObject();
        result.put("id", rs.getInt(1));

        return result;
    }
}
