import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteUpdateExam {
    public static void main(String[] args) {
        Connection connection = TestConnectDB.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlInsert = "INSERT INTO user1(username, password)" +
                " VALUES ('user1', '123')";
        String sqlUpdate = "UPDATE user1 SET password = '123456' WHERE id = 2";
        String sqlDelete = "DELETE FROM user1 WHERE id = 1";
        int numberRowsAffected1 = -1;
        int numberRowsAffected2 = -1;
        int numberRowsAffected3 = -1;

        try {
            numberRowsAffected1 = statement.executeUpdate(sqlInsert);
            numberRowsAffected2 = statement.executeUpdate(sqlUpdate);
//            numberRowsAffected3 = statement.executeUpdate(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Affected rows after Inserted: " + numberRowsAffected1);
        System.out.println("Affected rows after Updated: " + numberRowsAffected2);
        System.out.println("Affected rows after Deleted: " + numberRowsAffected3);
    }
}
