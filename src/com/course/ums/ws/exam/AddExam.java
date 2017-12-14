package com.course.ums.ws.exam;

import com.course.ums.auth.AuthManager;
import com.course.ums.db.DBManager;
import com.course.ums.ws.AddEntityRoute;
import org.json.JSONObject;

/**
 * Created by vh on 12/7/17.
 */
public class AddExam extends AddEntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN, AuthManager.ROLE_TEACHER};
    }

    @Override
    public int addEntity(JSONObject request) throws Exception {
        return DBManager.addExam(request);
    }
}
