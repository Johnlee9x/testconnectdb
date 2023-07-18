import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionExam {
    public static void main(String[] args) {
        String sqlInsr = "INSERT INTO user1 ( username, password) VALUES (?,? )";
        Connection connection = TestConnectDB.getConnection();
        PreparedStatement preparedStatement = null;
        {
            try {
                preparedStatement = connection.prepareStatement(sqlInsr);
                connection.setAutoCommit(false);
                preparedStatement.setString(1, "KIMINAWA");
                preparedStatement.setString(2, "KIM");
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
            finally {
                try {
                    connection.close();
                    assert preparedStatement != null;
                        preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
