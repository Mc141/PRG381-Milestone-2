package dao;

import model.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeedbackDAO {

    private final Connection connection;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public FeedbackDAO(Connection connection) {
        this.connection = connection;
    }

    // Retrieves all students with their full names and IDs
    public Map<String, Integer> getAllStudents() throws SQLException {
        Map<String, Integer> students = new LinkedHashMap<>();
        String sql = "SELECT student_id, first_name, last_name FROM Students";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                students.put(fullName, id); // Map full name to student ID
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch all students", e);
            throw e;
        }

        return students;
    }

    // Retrieves all counselors with their full names and IDs
    public Map<String, Integer> getAllCounselors() throws SQLException {
        Map<String, Integer> counselors = new LinkedHashMap<>();
        String sql = "SELECT counselor_id, first_name, last_name FROM Counselors";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("counselor_id");
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                counselors.put(fullName, id); // Map full name to counselor ID
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch all counselors", e);
            throw e;
        }

        return counselors;
    }

    // Retrieves all appointment IDs for selection or lookup
    public Map<String, Integer> getAllAppointments() throws SQLException {
        Map<String, Integer> appointments = new LinkedHashMap<>();
        String sql = "SELECT appointment_id FROM Appointments";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("appointment_id");
                appointments.put(String.valueOf(id), id); // Use ID as both key and value
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch all appointments", e);
            throw e;
        }

        return appointments;
    }

    // Fetches all feedback entries from the database
    public ArrayList<Feedback> getAllFeedbacks() throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        String sql = """
            SELECT f.feedback_id, f.appointment_id, f.student_id, f.counselor_id, f.comments, f.rating
            FROM Feedback f
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("feedback_id"));
                feedback.setAppointmentId(rs.getInt("appointment_id"));
                feedback.setStudentId(rs.getInt("student_id"));
                feedback.setCounselorId(rs.getInt("counselor_id"));
                feedback.setComments(rs.getString("comments"));
                feedback.setRating(rs.getInt("rating"));

                feedbacks.add(feedback); // Add to result list
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch all feedback", e);
            throw e;
        }
        return feedbacks;
    }

    // Inserts a new feedback entry into the database
    public void createFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO Feedback (appointment_id, student_id, counselor_id, comments, rating) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getAppointmentId());
            stmt.setInt(2, feedback.getStudentId());
            stmt.setInt(3, feedback.getCounselorId());
            stmt.setString(4, feedback.getComments());
            stmt.setInt(5, feedback.getRating());

            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to create feedback", e);
            throw e;
        }
    }

    // Updates an existing feedback entry by ID
    public void updateFeedback(Feedback feedback) throws SQLException {
        String sql = "UPDATE Feedback SET appointment_id = ?, student_id = ?, counselor_id = ?, comments = ?, rating = ? WHERE feedback_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, feedback.getAppointmentId());
            stmt.setInt(2, feedback.getStudentId());
            stmt.setInt(3, feedback.getCounselorId());
            stmt.setString(4, feedback.getComments());
            stmt.setInt(5, feedback.getRating());
            stmt.setInt(6, feedback.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to update feedback: " + feedback.getId(), e);
            throw e;
        }
    }

    // Deletes a feedback entry by its ID
    public void deleteFeedback(int feedbackId) throws SQLException {
        String sql = "DELETE FROM Feedback WHERE feedback_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, feedbackId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete feedback: " + feedbackId, e);
            throw e;
        }
    }

    // Retrieves a single feedback entry by ID
    public Feedback getFeedbackById(int feedbackId) throws SQLException {
        String sql = "SELECT * FROM Feedback WHERE feedback_id = ?";
        Feedback feedback = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, feedbackId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    feedback = new Feedback();
                    feedback.setId(rs.getInt("feedback_id"));
                    feedback.setAppointmentId(rs.getInt("appointment_id"));
                    feedback.setStudentId(rs.getInt("student_id"));
                    feedback.setCounselorId(rs.getInt("counselor_id"));
                    feedback.setComments(rs.getString("comments"));
                    feedback.setRating(rs.getInt("rating"));
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch feedback by ID: " + feedbackId, e);
            throw e;
        }

        return feedback;
    }
}
