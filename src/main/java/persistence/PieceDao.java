package persistence;

import database.ConnectionManager;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class PieceDao {
    private final ConnectionManager connectionManager;

    public PieceDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void saveAll(Map<Position, Piece> pieces, int gameId) {
        String query = "INSERT INTO PIECE (GAME_ID, TYPE, COLOR, FILE, `RANK`) VALUES (?, ?, ?, ?, ?)";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            for (Map.Entry<Position, Piece> entry : pieces.entrySet()) {
                Position position = entry.getKey();
                Piece piece = entry.getValue();
                preparedStatement.setInt(1, gameId);
                preparedStatement.setString(2, piece.type().name());
                preparedStatement.setString(3, piece.color().name());
                preparedStatement.setInt(4, position.file().order());
                preparedStatement.setInt(5, position.rank().number());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePosition(Position target, int pieceId, int gameId) { // todo dto
        String query = "UPDATE PIECE SET FILE = ?, `RANK` = ? WHERE PIECE_ID = ? AND GAME_ID = ?";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, target.file().order());
            preparedStatement.setInt(2, target.rank().number());
            preparedStatement.setInt(3, pieceId);
            preparedStatement.setInt(4, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<Position, Piece> findByGameId(int gameId) {
        String query = "SELECT * FROM PIECE WHERE GAME_ID = ?";
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<Position, Piece> pieces = new HashMap<>();
            while (resultSet.next()) {
                File file = File.fromOrder(resultSet.getInt("FILE"));
                Rank rank = Rank.fromNumber(resultSet.getInt("RANK"));
                Position position = new Position(file, rank);

                Type type = Type.valueOf(resultSet.getString("TYPE"));
                Color color = Color.valueOf(resultSet.getString("COLOR"));
                int pieceId = resultSet.getInt("PIECE_ID");
                Piece piece = type.createPiece(color, pieceId);
                pieces.put(position, piece);
            }
            return pieces;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
