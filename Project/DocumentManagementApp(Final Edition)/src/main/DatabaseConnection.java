package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the connection to the SQL Server database.
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=DocumentManagementDB;encrypt=true;trustServerCertificate=true";
    private static final String USER = "Yassen";
    private static final String PASSWORD = "Yassen@487";

    /**
     * Establishes and returns a connection to the database.
     * @return Connection object for SQL Server.
     * @throws SQLException If the connection fails.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
