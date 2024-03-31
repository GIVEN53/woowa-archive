package persistence;

import database.ConnectionManager;
import dto.MovementDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import support.TestConnectionManager;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class BoardDaoTest {
    private final ConnectionManager connectionManager = new TestConnectionManager();
    private final BoardDao boardDao = new BoardDao(connectionManager);

    @AfterEach
    void tearDown() {
        try (var connection = connectionManager.getConnection();
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
