package dao;

import model.Counselor;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CounselorDAO {
    private static final Logger LOGGER = Logger.getLogger(CounselorDAO.class.getName());

    private final Connection connection;
    private ArrayList<Counselor> counselors;

    public CounselorDAO(Connection connection) {
        this.connection = connection;
        this.counselors = new ArrayList<>();
    }

    // Returns the in-memory list of counselors
    public ArrayList<Counselor> getCounselorList() {
        return counselors;
    }

    // Reads all counselors from the database and populates the internal list
    public ArrayList<Counselor> readCounselors() {
        counselors.clear();  // Clear the list before reloading from DB

        String query = "SELECT * FROM Counselors";

        try (Statement statement = connection.createStatement();
             ResultSet results = statement.executeQuery(query)) {

            while (results.next()) {
                Counselor counselor = new Counselor(
                        results.getInt("counselor_id"),
                        results.getString("first_name"),
                        results.getString("last_name"),
                        results.getString("specialization"),
                        results.getBoolean("available")
                );
                counselors.add(counselor);  // Add each counselor to the list
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error reading counselors from database", e);
        }

        return counselors;
    }

    // Refreshes the in-memory counselor list from the database
    private void updateCounselorList() {
        counselors = readCounselors();
    }

    // Inserts a new counselor record into the database and refreshes the list
    public void addCounselor(Counselor counselor) {
        String query = "INSERT INTO Counselors (first_name, last_name, specialization, available) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, counselor.getFirstName());
            stmt.setString(2, counselor.getLastName());
            stmt.setString(3, counselor.getSpecialisation());
            stmt.setBoolean(4, counselor.isAvailable());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding counselor", e);
        }

        updateCounselorList();  // Keep the in-memory list in sync
    }

    // Updates an existing counselor's data in the database and refreshes the list
    public void updateCounselor(Counselor counselor) {
        String query = "UPDATE Counselors SET first_name = ?, last_name = ?, specialization = ?, available = ? WHERE counselor_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, counselor.getFirstName());
            stmt.setString(2, counselor.getLastName());
            stmt.setString(3, counselor.getSpecialisation());
            stmt.setBoolean(4, counselor.isAvailable());
            stmt.setInt(5, counselor.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating counselor", e);
        }

        updateCounselorList();  // Refresh local state after update
    }

    // Deletes a counselor by ID from the database and refreshes the list
    public boolean deleteCounselor(Counselor counselor) {
        String query = "DELETE FROM Counselors WHERE counselor_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, counselor.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                LOGGER.warning("No counselor deleted. Possibly due to foreign key constraint.");
                return false;
            }

            updateCounselorList();
            return true;

        } catch (SQLException e) {
            if (e.getSQLState().startsWith("23")) { // 23xxx = constraint violation
                LOGGER.warning("Constraint violation: Counselor is in use elsewhere.");
            } else {
                LOGGER.log(Level.SEVERE, "Error deleting counselor", e);
            }
            return false;
        }
    }
}
