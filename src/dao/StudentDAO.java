package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

public class StudentDAO {
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    private final Connection connection;
    private ArrayList<Student> students;

    public StudentDAO(Connection connection) {
        this.connection = connection;
        this.students = new ArrayList<>();
    }

    public ArrayList<Student> getStudentList() {
        return students;
    }

    public ArrayList<Student> readStudents() {
        students.clear();

        String query = "SELECT * FROM Students";

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(query)) {

            while (results.next()) {
                Student student = new Student();
                student.setId(results.getInt("student_id"));
                student.setFirstName(results.getString("first_name"));
                student.setLastName(results.getString("last_name"));
                students.add(student);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading students from database", e);
        }

        return students;
    }

    private void updateStudentList() {
        students = readStudents();
    }

    public void addStudent(Student student) {
        String query = "INSERT INTO Students (first_name, last_name) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding student", e);
        }

        updateStudentList();
    }

    public void updateStudent(Student student) {
        String query = "UPDATE Students SET first_name = ?, last_name = ? WHERE student_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student", e);
        }

        updateStudentList();
    }

    public void deleteStudent(Student student) {
        String query = "DELETE FROM Students WHERE student_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, student.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting student", e);
        }

        updateStudentList();
    }
}
