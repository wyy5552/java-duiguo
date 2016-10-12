package test.upfile.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {
    private static final String user = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://127.0.0.1:3306/imooc";
    private static Connection conn;
    
    
	public static void main(String[] args) throws Exception {
		// 1,加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        // 2, 获取数据库链接
        conn = DriverManager.getConnection(url, user, password);
        //3，通过数据库的链接操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select name,age from god");
        while(rs.next())
        {
        	System.out.println(rs.getString("name")+ "," + rs.getInt("age"));
        }
	}

}
