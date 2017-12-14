package com.course.ums.ws.user;

import com.course.ums.auth.AuthManager;
import com.course.ums.ws.ListEntityRoute;

public class ListGrades extends ListEntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN, AuthManager.ROLE_STUDENT,AuthManager.ROLE_TEACHER};
    }

    @Override
    public String getTableName() {
        return "grades";
    }
}
