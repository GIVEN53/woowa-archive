package persistence;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PositionDao {
    private final ConnectionManager connectionManager;

    public PositionDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void save(Position position, int gameId, int pieceId) {
        String query = "INSERT INTO POSITION VALUES (?, ?, ?, ?)";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, pieceId);
            preparedStatement.setInt(3, position.file().order());
            preparedStatement.setInt(4, position.rank().number());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Position findByGameIdAndPieceId(int gameId, int pieceId) {
        String query = "SELECT * FROM POSITION WHERE GAME_ID = ? AND PIECE_ID = ?";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, pieceId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                File file = File.fromOrder(resultSet.getInt("FILE"));
                Rank rank = Rank.fromNumber(resultSet.getInt("RANK"));
                return new Position(file, rank);
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void delete(int gameId, int pieceId) {
        String query = "DELETE FROM POSITION WHERE GAME_ID = ? AND PIECE_ID = ?";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.setInt(2, pieceId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
