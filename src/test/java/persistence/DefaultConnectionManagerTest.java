package persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import org.junit.jupiter.api.Test;

class DefaultConnectionManagerTest {
    private final ConnectionManager connectionManager = new DefaultConnectionManager();

    @Test
    void 데이터베이스와_커넥션을_맺는다() {
        Connection connection = connectionManager.getConnection();

        assertThat(connection).isNotNull();
    }
}
