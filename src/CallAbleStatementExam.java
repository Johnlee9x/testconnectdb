import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallAbleStatementExam {
    public static void main(String[] args) {
        String sqlQuery = "CALL pro_find_user_by_id(4)";
        Connection connection = TestConnectDB.getConnection();
        CallableStatement callableStatement = null;

        {
            try {
                callableStatement = connection.prepareCall(sqlQuery);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                showInfor(resultSet);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void showInfor(ResultSet resultSet) throws SQLException {
        System.out.println("Id: " + resultSet.getInt("id"));
        System.out.println("Name: " + resultSet.getString("username"));
        System.out.println("Password: " + resultSet.getString("password"));
        System.out.println("CreatedDate: " + resultSet.getTimestamp("createdDate"));
    }

}
