package com.course.ums.ws.exam;

import com.course.ums.auth.AuthManager;
import com.course.ums.ws.ListEntityRoute;

public class ListViewExamsGroup2 extends ListEntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN, AuthManager.ROLE_STUDENT, AuthManager.ROLE_TEACHER};
    }

    @Override
    public String getTableName() {
        return "view_exams_group2";
    }
}
