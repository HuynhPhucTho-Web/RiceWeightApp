package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public Connection getConnection() throws Exception {
        String user = "sa"; // đổi theo user DB của bạn
        String pass = "your_password"; // đổi theo mật khẩu của bạn
        String url = "jdbc:sqlserver://localhost:1433;databaseName=RiceWeightApp";

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, pass);
    }
}
