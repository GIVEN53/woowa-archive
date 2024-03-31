package persistence;

import database.ConnectionManager;
import dto.MovementDto;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    private final ConnectionManager connectionManager;

    public BoardDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void save(MovementDto movementDto, int roomId) {
        String query = "INSERT INTO BOARD (SOURCE, TARGET, ROOM_ID) VALUES (?, ?, ?)";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, movementDto.source());
            preparedStatement.setString(2, movementDto.target());
            preparedStatement.setInt(3, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MovementDto> findByRoomId(int roomId) {
        String query = "SELECT * FROM BOARD WHERE ROOM_ID = ?";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roomId);
            var resultSet = preparedStatement.executeQuery();
            List<MovementDto> movements = new ArrayList<>();
            while (resultSet.next()) {
                String source = resultSet.getString("SOURCE");
                String target = resultSet.getString("TARGET");
                movements.add(new MovementDto(source, target));
            }
            return movements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteByRoomId(int roomId) {
        String query = "DELETE FROM BOARD WHERE ROOM_ID = ?";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
