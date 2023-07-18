import data.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student = new Student(7, "duy An", 11);
        addNewStudent(student);
        List<Student> list = getAllStudent();
        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println("truoc khi delete");
        for(Student x : list){
            System.out.println(x);
        }
        deleteById(1);
        List<Student> list1 = getAllStudent();
        System.out.println("sau khi xoa id = 1");
        for(Student y : list1){
            System.out.println(y);
        }
        updateInfoStudent(4, "duy tam", 22);
        List<Student> list2 = getAllStudent();
        for(Student x : list2){
            System.out.println(x);
        }
    }
    public static List<Student> getAllStudent(){
        List<Student> list = new ArrayList<>();
        Connection connection = TestConnectDB.getConnection();
        String sqlQuery = "SELECT * FROM STUDENT";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if(connection != null){
            try {
                preparedStatement = connection.prepareStatement(sqlQuery);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Student student = new Student();
                    student.setId(resultSet.getInt("ID"));
                    student.setName(resultSet.getString("ten"));
                    student.setAge(resultSet.getInt("tuoi"));
                    list.add(student);
                }
                return list;

            } catch (SQLException e) {
                return null;
            }
            finally {
                try {
                    connection.close();
                    assert preparedStatement != null;
                    preparedStatement.close();
                    assert resultSet != null;
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }
    public static Student findAStudentById(int id){
        Student student;
        Connection connection = TestConnectDB.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sqlQuery = "SELECT * FROM student WHERE id = ?";
        if(connection != null){
            try {
                preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    student = new Student();
                    student.setId(resultSet.getInt("id"));
                    student.setAge(resultSet.getInt("tuoi"));
                    student.setName(resultSet.getString("ten"));
                    return student;
                }
            } catch (SQLException e) {
                return null;
            }
            finally {
                try {
                    connection.close();
                    assert preparedStatement != null;
                            preparedStatement.close();
                    assert resultSet != null;
                            resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static void addNewStudent(Student student){
        String sqlQuery = "INSERT INTO student VALUES(?, ?, ?)";
        Connection connection = TestConnectDB.getConnection();
        PreparedStatement preparedStatement = null;

        if(connection != null){
            try {
                preparedStatement = connection.prepareStatement(sqlQuery);
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, student.getId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setInt(3, student.getAge());
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
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
    public static void deleteById(int id){
        String sqlQuery = "DELETE FROM student WHERE id = ?";
        Connection connection = TestConnectDB.getConnection();
        PreparedStatement preparedStatement = null;
        if(connection != null){
            if(findAStudentById(id) != null){
                try {
                    preparedStatement = connection.prepareStatement(sqlQuery);
                    connection.setAutoCommit(false);
                    preparedStatement.setInt(1,id);
                    preparedStatement.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
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
            else{
                System.out.println("Id khong hop le");
            }
        }
    }
    public static void updateInfoStudent(int id, String name, int age){
        String sqlQuery = "UPDATE student SET ten = ?, tuoi = ? WHERE id = ?";
        Connection connection = TestConnectDB.getConnection();
        PreparedStatement preparedStatement = null;
        if(connection != null){
            if(findAStudentById(id) != null){
                try {
                    preparedStatement = connection.prepareStatement(sqlQuery);
                    connection.setAutoCommit(false);
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setInt(3, id);
                    preparedStatement.executeUpdate();
                    connection.commit();
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
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
            else{
                System.out.println("Id khong hop le");
            }
        }
    }

    
    
}