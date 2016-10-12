package  test.upfile.src.www.imooc.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import test.upfile.src.www.imooc.entity.File;
import test.upfile.src.www.imooc.entity.User;
import test.upfile.src.www.imooc.util.DBUtil;

/**
 * Created by iShu on 16/5/23.
 */
public class FileService {

    private Connection connection = null;
    private Statement statement = null;



    public FileService() {
        connection = DBUtil.getConnection();
        statement = DBUtil.getStatment();
    }

    /**
     * 保存用户文件
     * @param file
     * @param user
     * @return
     */
    public boolean save(File file, User user) {

        String uid = queryUid(user);
        String sql2 = "INSERT INTO tb_file(uid, fname, fcontent) " +
                "VALUES ('" + uid + "', '" + file.getName() + "', '" + file.getContent() + "')";
        try {
            statement.executeUpdate(sql2);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    public ResultSet query(User user) {
        ResultSet resultSet = null;
        String username = user.getName();
        String uid = queryUid(user);
        String sql = "SELECT fid, fname FROM tb_file " +
                "WHERE uid = (SELECT uid FROM tb_user WHERE username = '" + username + "')";
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 查询用户的id
     * @param user
     * @return
     */
    public String queryUid(User user) {

        String sql1 = "SELECT uid FROM tb_user WHERE username = '" + user.getName() + "'";
        String uid = null;
        try {
            ResultSet resultSet = statement.executeQuery(sql1);
            if (resultSet.next()) {
                uid = resultSet.getString("uid");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uid;

    }
}
