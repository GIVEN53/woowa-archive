package persistence;

import database.ConnectionManager;
import domain.room.Room;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDao {
    private final ConnectionManager connectionManager;

    public RoomDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void save(Room room) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement("INSERT INTO ROOM (NAME) VALUES (?)")) {
            preparedStatement.setString(1, room.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Room> findAll() {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM ROOM");
             var resultSet = preparedStatement.executeQuery()) {
            List<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                rooms.add(new Room(resultSet.getInt("ROOM_ID"), resultSet.getString("NAME")));
            }
            return rooms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Room> findById(int roomId) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement("SELECT * FROM ROOM WHERE ROOM_ID = ?")) {
            preparedStatement.setInt(1, roomId);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Room(resultSet.getInt("ROOM_ID"), resultSet.getString("NAME")));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int roomId) {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM ROOM WHERE ROOM_ID = ?")) {
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
