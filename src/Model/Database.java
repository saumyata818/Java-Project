package Model;

import java.sql.*;

public class Database {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/carrentalsystem"; // include port
    private Connection connection = null; // store connection
    private Statement statement = null;   // store statement

    public Database() {
        try {
            // Initialize connection
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);

            // Initialize statement safely
            if (this.connection != null) {
                this.statement = this.connection.createStatement(
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE
                );
            } else {
                System.err.println("Database connection failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }

    public Statement getStatement() {
        if (this.statement == null) {
            System.err.println("Statement is not initialized!");
        }
        return this.statement;
    }

    public Connection getConnection() {
        if (this.connection == null) {
            System.err.println("Connection is not initialized!");
        }
        return this.connection;
    }

    // Utility method to check if database is connected
    public boolean isConnected() {
        try {
            return this.connection != null && !this.connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
