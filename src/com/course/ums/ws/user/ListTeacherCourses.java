package com.course.ums.ws.user;

import com.course.ums.auth.AuthManager;
import com.course.ums.ws.ListEntityRoute;

public class ListTeacherCourses extends ListEntityRoute {

    @Override
    public String[] getAuthorizedRoles() {
        return new String[]{AuthManager.ROLE_ADMIN, AuthManager.ROLE_TEACHER};
    }

    @Override
    public String getTableName() {
        return "teachers_courses";
    }
}
