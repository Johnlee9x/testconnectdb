import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectDB {
    private static String user = "root";
    private static String password = "123456";
    private static String url = "jdbc:mysql://localhost:3306/cnnexpressdb";
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
