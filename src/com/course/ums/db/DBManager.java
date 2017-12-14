package com.course.ums.db;

import org.json.JSONObject;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vh on 11/2/17.
 */
public class DBManager {

    private static final String DB_URL = "jdbc:mysql://learner:student@localhost/ums?useLegacyDatetimeCode=false";
    public static DateFormat DF = new SimpleDateFormat("yyyy-MM-dd");

    private static final String EXAM_TABLE = "exams";
    private static final Map<String, Type> EXAM_META_DATA = new HashMap<>();

    static {
        EXAM_META_DATA.put("date", Type.DATE);
        EXAM_META_DATA.put("semesters_id", Type.INTEGER);
        EXAM_META_DATA.put("teachers_courses_id", Type.INTEGER);
        EXAM_META_DATA.put("groups_id", Type.INTEGER);


    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static boolean validateToken(String token, String role) {
        String table = role + "s";

        PreparedStatement ps = null;
        try {
            ps = DBManager.getConnection().prepareStatement("SELECT id FROM " + table + " WHERE id=?");
            ps.setInt(1, Integer.parseInt(token));
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int addUser(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO users(first_name, last_name, email, password) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, request.getString("first_name"));
        ps.setString(2, request.getString("last_name"));
        ps.setString(3, request.getString("email"));
        ps.setString(4, request.getString("password"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addCourse(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO courses(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, request.getString("name"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addGroup(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO groups(semesters_id, name) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, request.getInt("semesters_id"));
        ps.setString(2, request.getString("name"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addSemester(JSONObject request) throws SQLException {
        PreparedStatement ps = DBManager.getConnection().prepareStatement("INSERT INTO semesters(year, `index`) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, request.getInt("year"));
        ps.setString(2, request.getString("index"));
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    public static int addExam(JSONObject request) throws SQLException, ParseException {
        return addEntity(request, EXAM_TABLE, EXAM_META_DATA);
    }

    public static int addEntity(JSONObject request, String table, Map<String, Type> metaData) throws SQLException, ParseException {
        String query = "INSERT INTO %s(%s) VALUES(%s)";
        String columns = "";
        String values = "";
        for (String column : metaData.keySet()) {
            if (!columns.isEmpty()) {
                columns += ", ";
                values += ", ";
            }

            columns += "`" + column + "`";
            values += "?";
        }

        query = String.format(query, "`" + table + "`", columns, values);
        PreparedStatement ps = DBManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int index = 1;
        for (String column : metaData.keySet()) {
            Type type = metaData.get(column);
            switch (type) {
                case DATE:
                    ps.setDate(index, new Date(DF.parse(request.getString(column)).getTime()));
                    break;

                case STRING:
                    ps.setString(index, request.getString(column));
                    break;

                case INTEGER:
                    ps.setInt(index, request.getInt(column));
                    break;
            }

            index++;
        }

        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1);
    }

    enum Type {
        INTEGER,
        STRING,
        DATE
    }
}
