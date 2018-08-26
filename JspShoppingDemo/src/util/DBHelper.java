package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by wangshuyang on 2017/3/15.
 */
public class DBHelper {
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/shopping?useUnicode=true&characterEncoding=UTF-8";
    private static final String username = "root";
    private static final String password = "19940209";
    private static Connection conn = null;

    // 静态代码块负责加载驱动
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 单例模式返回数据库连接对象
    public static Connection getConnection() throws SQLException {
        if (conn == null){
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBHelper.getConnection();
            if (conn != null) {
                System.out.println("数据库连接成功!");
            } else {
                System.out.println("数据库连接失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
