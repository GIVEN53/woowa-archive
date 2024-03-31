package persistence;

import static org.assertj.core.api.Assertions.assertThat;

import database.ConnectionPool;
import domain.room.Room;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import support.TestConnectionPool;

class RoomDaoTest {
    private ConnectionPool connectionPool = new TestConnectionPool();
    private RoomDao roomDao = new RoomDao(connectionPool);

    @AfterEach
    void tearDown() {
        try (var connection = connectionPool.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM ROOM")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void 룸을_저장한다() {
        String name = "초고수만";

        roomDao.save(new Room(name));

        assertThat(roomDao.findAll()).hasSize(1);
    }

    @Test
    void 룸을_삭제한다() {
        Room room = new Room("룸123");
        roomDao.save(room);
        List<Room> rooms = roomDao.findAll();

        roomDao.deleteById(rooms.get(0).getRoomId());

        assertThat(roomDao.findAll()).isEmpty();
    }
}
