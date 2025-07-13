package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseInitializer {

    private static final Logger logger = Logger.getLogger(DatabaseInitializer.class.getName());

    public static void initialise(AbstractDBConnection db) {
        db.connect();

        try (Connection conn = db.getConnection(); Statement stmt = conn.createStatement()) {
            try {
                stmt.executeUpdate("DROP TABLE Feedback");
                stmt.executeUpdate("DROP TABLE Appointments");
                stmt.executeUpdate("DROP TABLE Students");
                stmt.executeUpdate("DROP TABLE Counselors");
            } catch (SQLException ignore) {
                // Ignore not found exceptions
            }

            // Create Students table
            stmt.executeUpdate("""
                CREATE TABLE Students (
                    student_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    first_name VARCHAR(50),
                    last_name VARCHAR(50)
                )
            """);

            // Create Counselors table
            stmt.executeUpdate("""
                CREATE TABLE Counselors (
                    counselor_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    first_name VARCHAR(50),
                    last_name VARCHAR(50),
                    specialization VARCHAR(100),
                    available BOOLEAN
                )
            """);

            // Create Appointments table
            stmt.executeUpdate("""
                CREATE TABLE Appointments (
                    appointment_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    student_id INT,
                    counselor_id INT,
                    appointment_date DATE,
                    appointment_time TIME,
                    status VARCHAR(50),
                    FOREIGN KEY (student_id) REFERENCES Students(student_id),
                    FOREIGN KEY (counselor_id) REFERENCES Counselors(counselor_id)
                )
            """);

            // Create Feedback table
            stmt.executeUpdate("""
                CREATE TABLE Feedback (
                    feedback_id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    student_id INT,
                    counselor_id INT,
                    appointment_id INT,
                    rating INT CHECK (rating BETWEEN 1 AND 5),
                    comments VARCHAR(255),
                    FOREIGN KEY (student_id) REFERENCES Students(student_id),
                    FOREIGN KEY (counselor_id) REFERENCES Counselors(counselor_id),
                    FOREIGN KEY (appointment_id) REFERENCES Appointments(appointment_id)
                )
            """);

            logger.info("Tables created successfully.");

            // Insert dummy students
            stmt.executeUpdate("""
                INSERT INTO Students (first_name, last_name)
                VALUES ('John', 'Doe'),
                       ('Alice', 'Brown')
            """);

            // Insert dummy counselors
            stmt.executeUpdate("""
                INSERT INTO Counselors (first_name, last_name, specialization, available)
                VALUES ('Emily', 'Smith', 'Mental Health', TRUE),
                       ('Mark', 'Taylor', 'Career Guidance', TRUE)
            """);

            // Insert dummy appointments
            stmt.executeUpdate("""
                INSERT INTO Appointments (student_id, counselor_id, appointment_date, appointment_time, status)
                VALUES (1, 1, CURRENT_DATE, CURRENT_TIME, 'Scheduled'),
                       (2, 2, CURRENT_DATE, CURRENT_TIME, 'Scheduled')
            """);

            // Insert dummy feedback
            stmt.executeUpdate("""
                INSERT INTO Feedback (student_id, counselor_id, appointment_id, rating, comments)
                VALUES (1, 1, 1, 5, 'Excellent session!'),
                       (2, 2, 2, 4, 'Very informative.')
            """);

            logger.info("Dummy data inserted.");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database initialisation failed", e);
        } finally {
            db.closeConnection();
        }
    }
}
