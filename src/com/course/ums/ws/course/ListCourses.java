package com.course.ums.ws.course;

import com.course.ums.auth.AuthManager;
import com.course.ums.ws.ListEntityRoute;

/**
 * Created by vh on 11/16/17.
 */
public class ListCourses extends ListEntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[] {AuthManager.ROLE_ADMIN, AuthManager.ROLE_STUDENT, AuthManager.ROLE_TEACHER};
    }

    @Override
    public String getTableName() {
        return "courses";
    }
}
