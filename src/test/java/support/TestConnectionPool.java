package support;

import database.ConnectionPool;
import database.exception.ConnectionFailedException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnectionPool implements ConnectionPool {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "TEST";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionFailedException(e.getMessage());
        }
    }
}
