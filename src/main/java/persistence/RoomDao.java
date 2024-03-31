package persistence;

import database.ConnectionPool;
import domain.room.Room;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao {
    private final ConnectionPool connectionPool;

    public RoomDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void save(Room room) {
        String query = "INSERT INTO ROOM (NAME) VALUES (?)";
        try (var connection = connectionPool.getConnection();
             var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Room> findAll() {
        String query = "SELECT * FROM ROOM";
        try (var connection = connectionPool.getConnection();
             var preparedStatement = connection.prepareStatement(query);
             var resultSet = preparedStatement.executeQuery()) {
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                int roomId = resultSet.getInt("ROOM_ID");
                String name = resultSet.getString("NAME");
                rooms.add(new Room(roomId, name));
            }
            return rooms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Room> findById(int id) {
        String query = "SELECT * FROM ROOM WHERE ROOM_ID = ?";
        try (var connection = connectionPool.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int roomId = resultSet.getInt("ROOM_ID");
                String name = resultSet.getString("NAME");
                return Optional.of(new Room(roomId, name));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int roomId) {
        String query = "DELETE FROM ROOM WHERE ROOM_ID = ?";
        try (var connection = connectionPool.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
