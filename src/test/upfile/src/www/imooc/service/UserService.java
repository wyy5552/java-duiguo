package test.upfile.src.www.imooc.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import test.upfile.src.www.imooc.entity.User;
import test.upfile.src.www.imooc.util.DBUtil;


/**
 * Created by iShu on 16/5/23.
 */
public class UserService {

    private Statement statement;
    private ResultSet resultSet;
    private Connection conn;

    public boolean login(User user) {
        String sql = "SELECT username, password FROM tb_user WHERE username = '"
                + user.getName() + "' AND password = '" + user.getPassword() + "'";
        conn = DBUtil.getConnection();
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(User user) {
        String sql = "insert into tb_user(username, password) values" +
                " ('" + user.getName() + "', '" + user.getPassword() + "') ";

        statement = DBUtil.getStatment();
        try {
            int r = statement.executeUpdate(sql);
            System.out.println(r);
        }catch(SQLIntegrityConstraintViolationException e) {
            // Duplicate entry
            return false;
        }catch (SQLException e) {
            return false;
        }
        return true;
    }

    public void updateUser(User user) {

    }

    public void deleteUser() {

    }

    public static void main(String[] args) {
        User user = new User("1", "jowishu", "jowishu");
        UserService userService = new UserService();
        System.out.println(userService.login(user));
    }

}
