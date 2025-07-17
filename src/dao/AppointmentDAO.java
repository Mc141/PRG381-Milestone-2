package dao;

import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentDAO {

    private final Connection connection;
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    // Retrieves all students from the database and returns them as a map of "Full Name" -> ID
    public Map<String, Integer> getAllStudents() throws SQLException {
        Map<String, Integer> students = new LinkedHashMap<>();
        String sql = "SELECT student_id, first_name, last_name FROM Students";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                students.put(fullName, id);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch all students", e);
            throw e;
        }

        return students;
    }

    // Retrieves all counselors from the database and returns them as a map of "Full Name" -> ID
    public Map<String, Integer> getAllCounselors() throws SQLException {
        Map<String, Integer> counselors = new LinkedHashMap<>();
        String sql = "SELECT counselor_id, first_name, last_name FROM Counselors";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("counselor_id");
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                counselors.put(fullName, id);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch all counselors", e);
            throw e;
        }

        return counselors;
    }

    // Retrieves all appointment records and maps their data to Appointment model objects
    public ArrayList<Appointment> getAllAppointments() throws SQLException {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = """
            SELECT a.appointment_id, a.appointment_date, a.appointment_time, a.status,
                   s.student_id, s.first_name AS student_first, s.last_name AS student_last,
                   c.counselor_id, c.first_name AS counselor_first, c.last_name AS counselor_last
            FROM Appointments a
            JOIN Students s ON a.student_id = s.student_id
            JOIN Counselors c ON a.counselor_id = c.counselor_id
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getInt("appointment_id"));
                appointment.setDate(rs.getDate("appointment_date").toLocalDate());
                appointment.setTime(rs.getTime("appointment_time").toLocalTime());
                appointment.setStatus(rs.getString("status"));

                appointment.setStudentId(rs.getInt("student_id"));
                appointment.setStudentName(rs.getString("student_first") + " " + rs.getString("student_last"));

                appointment.setCounselorId(rs.getInt("counselor_id"));
                appointment.setCounselorName(rs.getString("counselor_first") + " " + rs.getString("counselor_last"));

                appointments.add(appointment);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to fetch all appointments", e);
            throw e;
        }

        return appointments;
    }

    // Updates an existing appointment record in the database
    public void updateAppointment(Appointment appt) throws SQLException {
        String sql = "UPDATE Appointments SET appointment_date = ?, appointment_time = ?, student_id = ?, counselor_id = ?, status = ? WHERE appointment_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(appt.getDate()));
            stmt.setTime(2, java.sql.Time.valueOf(appt.getTime()));
            stmt.setInt(3, appt.getStudentId());
            stmt.setInt(4, appt.getCounselorId());
            stmt.setString(5, appt.getStatus());
            stmt.setInt(6, appt.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to update appointment: " + appt.getId(), e);
            throw e;
        }
    }

    // Deletes an appointment from the database by ID
    public void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM Appointments WHERE appointment_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to delete appointment: " + appointmentId, e);
            throw e;
        }
    }

    // Inserts a new appointment record into the database
    public void createAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO Appointments (student_id, counselor_id, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getStudentId());
            stmt.setInt(2, appointment.getCounselorId());
            stmt.setDate(3, java.sql.Date.valueOf(appointment.getDate()));
            stmt.setTime(4, java.sql.Time.valueOf(appointment.getTime()));
            stmt.setString(5, "Scheduled");

            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to create appointment", e);
            throw e;
        }
    }
}
