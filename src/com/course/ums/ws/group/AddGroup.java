package com.course.ums.ws.group;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.AddEntityRoute;
import org.json.JSONObject;


public class AddGroup extends AddEntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN};
    }

    @Override
    public int addEntity(JSONObject request) throws Exception {
        int id = DBManager.addGroup(request);

        return id;
    }
}
