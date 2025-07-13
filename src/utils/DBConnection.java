package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection extends AbstractDBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String URL = "jdbc:derby:StudentCareDB;create=true";

    private Connection connection;

    public DBConnection() {
        loadDriver();
    }

    private void loadDriver() {
        try {
            Class.forName(DRIVER);
            LOGGER.info("JDBC Driver loaded successfully.");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "JDBC Driver not found.", e);
        }
    }

    @Override
    public void connect() {
        try {
            connection = DriverManager.getConnection(URL);
            LOGGER.info("Connected to database successfully.");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to connect to database.", e);
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Database connection closed.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Failed to close database connection.", e);
        }
    }
}
