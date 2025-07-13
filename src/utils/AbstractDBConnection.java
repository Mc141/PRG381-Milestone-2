package utils;

import java.sql.Connection;

public abstract class AbstractDBConnection {
    public abstract void connect();
    public abstract Connection getConnection();
    public abstract void closeConnection();
}
