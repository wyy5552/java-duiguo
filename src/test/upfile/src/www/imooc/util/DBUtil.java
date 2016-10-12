package test.upfile.src.www.imooc.util;

import java.sql.*;

/**
 * Created by iShu on 16/5/23.
 */
public class DBUtil {

    private static final String user = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static Connection conn;
    private static Statement stat;

    /**
     * 数据库加载
     */
    public static Connection getConnection() {
        try {
            // 1,加载驱动程序
            Class.forName("com.mysql.jdbc.Driver");
            // 2, 获取数据库链接
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭链接
     */
    public void close() {
            try {
                if (conn != null)
                    conn.close();
                if (stat != null)
                    stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static Statement getStatment() {
        conn = getConnection();
        try {
            stat = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stat;
    }
}
