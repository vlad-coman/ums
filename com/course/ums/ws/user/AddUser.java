package com.course.ums.ws.user;

import com.course.ums.db.DBManager;
import com.course.ums.ws.JSONRoute;
import org.json.JSONObject;

import java.sql.PreparedStatement;


public class AddUser extends JSONRoute {

    @Override
    public JSONObject handleJSONRequest(JSONObject request) throws Exception {
        PreparedStatement preparedStatement = DBManager.getConnection().prepareStatement("INSERT INTO users(first_name, last_name, email, password) VALUES(?, ?, ?, ?)");
        preparedStatement.setString(1, request.getString("first_name"));
        preparedStatement.setString(2, request.getString("last_name"));
        preparedStatement.setString(3, request.getString("email"));
        preparedStatement.setString(4, request.getString("password"));
        preparedStatement.execute();

        JSONObject result = new JSONObject();
        result.put("result", "ok");

        return result;
    }
}
