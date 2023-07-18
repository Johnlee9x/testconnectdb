import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetExam {
    public static void main(String[] args) throws SQLException {
        String sqlSelect = "SElECT * FROM user1";
        Connection connection = TestConnectDB.getConnection();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(sqlSelect);
        while (resultSet.next()){
            showUserInfo(resultSet);
            if(!resultSet.next()){
                break;
            }
        }
        System.out.println("MOve to previous row");
        while (resultSet.previous()){
            showUserInfo(resultSet);
            if(!resultSet.previous()) break;
        }
        System.out.println("Move to the last row");
        resultSet.last();
        showUserInfo(resultSet);

        System.out.println("Move to the first row");
        resultSet.first();
        showUserInfo(resultSet);

    }
    public static void showUserInfo(ResultSet resultSet) throws SQLException {
        System.out.println("Id: " + resultSet.getInt("id"));
        System.out.println("Name: " + resultSet.getString("username"));
        System.out.println("Password: " + resultSet.getString("password"));
        System.out.println("CreatedDate: " + resultSet.getTimestamp("createdDate"));
    }
}
