package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DefaultConnectionManager implements ConnectionManager {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "CHESS";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new IllegalStateException("DB 연결 오류:" + e.getMessage(), e);  // todo 예외 처리 변경
        }
    }
}
