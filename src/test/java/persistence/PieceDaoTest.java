package persistence;

import static org.assertj.core.api.Assertions.assertThat;

import database.ConnectionManager;
import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.King;
import domain.piece.pawn.BlackPawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import support.TestConnectionManager;

class PieceDaoTest {
    private final ConnectionManager connectionManager = new TestConnectionManager();
    private final PieceDao pieceDao = new PieceDao(connectionManager);

    @AfterEach
    void tearDown() {
        try (var connection = connectionManager.getConnection();
             var preparedStatement = connection.prepareStatement("DELETE FROM PIECE")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void 모든_기물을_저장한다() {
        int gameId = 1;
        Map<Position, Piece> pieces = Map.of(
                new Position(File.F, Rank.TWO), new BlackPawn(),
                new Position(File.F, Rank.SEVEN), new BlackPawn(),
                new Position(File.E, Rank.EIGHT), new BlackPawn(),
                new Position(File.E, Rank.ONE), new BlackPawn()
        );

        pieceDao.saveAll(pieces, gameId);

        Map<Position, Piece> savedPieces = pieceDao.findByGameId(gameId);
        assertThat(savedPieces).hasSize(4);
    }

    @Test
    void 이동한_기물을_업데이트한다() {
        int gameId = 1;
        Position source = new Position(File.F, Rank.TWO);
        Map<Position, Piece> pieces = Map.of(
                source, new King(Color.WHITE),
                new Position(File.F, Rank.SEVEN), new BlackPawn(),
                new Position(File.E, Rank.EIGHT), new BlackPawn(),
                new Position(File.E, Rank.ONE), new BlackPawn()
        );
        pieceDao.saveAll(pieces, gameId);
        Piece piece = pieceDao.findByGameId(gameId).get(source);
        Position target = new Position(File.A, Rank.ONE);

        pieceDao.updatePosition(target, piece.id(), gameId);

        Map<Position, Piece> savedPieces = pieceDao.findByGameId(gameId);
        assertThat(savedPieces.get(source)).isNull();
        assertThat(savedPieces.get(target)).isExactlyInstanceOf(King.class);
    }
}
