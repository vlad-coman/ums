package com.course.ums.ws.semester;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.String.valueOf;

public class AddSemester extends JSONRoute {
    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        String token = request.getString("token");
        if (!DBManager.validateToken(token, AuthManager.ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized!");
        }

        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO semesters(year, index) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, request.getInt("year"));
        ps.setString(2, valueOf(request.getString("index"))); // haven't succeed to insert enum data type with PreparedStatement
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();


        JSONObject result = new JSONObject();
        result.put("id", rs.getInt(1));

        return result;
    }
}
