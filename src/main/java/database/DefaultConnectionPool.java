package database;

import database.exception.ConnectionFailedException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Stream;

public class DefaultConnectionPool implements ConnectionPool {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "CHESS";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final int MAX_CONNECTIONS = 5;

    private int currentIndex; // todo 동기화 처리 atomicInteger
    private final List<Connection> connections;

    public DefaultConnectionPool() {
        this.currentIndex = -1;
        this.connections = Stream.generate(this::createConnection)
                .limit(MAX_CONNECTIONS)
                .toList();
    }

    @Override
    public Connection getConnection() {
        currentIndex++;
        return connections.get(currentIndex % connections.size());
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new ConnectionFailedException(e.getMessage());
        }
    }
}
