package persistence;

import static org.assertj.core.api.Assertions.assertThat;

import database.ConnectionPool;
import dto.MovementDto;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import support.TestConnectionPool;

class BoardDaoTest {
    private final ConnectionPool connectionPool = new TestConnectionPool();
    private final BoardDao boardDao = new BoardDao(connectionPool);

    @AfterEach
    void tearDown() {
        try (var connection = connectionPool.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM BOARD")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void 보드를_저장한다() {
        int roomId = 1;
        boardDao.save(new MovementDto("a1", "a2"), roomId);

        assertThat(boardDao.findByRoomId(roomId)).hasSize(1);
    }

    @Test
    void 보드를_삭제한다() {
        int roomId = 1;
        boardDao.save(new MovementDto("a1", "a2"), roomId);
        boardDao.save(new MovementDto("a2", "a3"), roomId);
        boardDao.save(new MovementDto("a3", "a4"), roomId);

        boardDao.deleteByRoomId(roomId);

        assertThat(boardDao.findByRoomId(roomId)).isEmpty();
    }
}
