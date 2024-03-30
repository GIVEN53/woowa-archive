package domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.Map;
import org.junit.jupiter.api.Test;

class GameResultTest {
    @Test
    void 남아있는_기물의_점수를_계산한다() {
        Color color = Color.BLACK;
        Map<Position, Piece> board = Map.of(
                new Position(File.B, Rank.ONE), new Queen(color),
                new Position(File.C, Rank.TWO), new Rook(color),
                new Position(File.D, Rank.THREE), new Bishop(color),
                new Position(File.E, Rank.FOUR), new Knight(color),
                new Position(File.F, Rank.FIVE), new King(color),
                new Position(File.G, Rank.SIX), new BlackPawn()
        );
        GameResult gameResult = new GameResult(board);

        double score = gameResult.calculateScore(color);

        assertThat(score).isEqualTo(20.5);
    }

    @Test
    void 같은_file의_pawn이_있을_경우_각각의_pawn_점수를_절반으로_계산한다() {
        Color color = Color.BLACK;
        Map<Position, Piece> board = Map.of(
                new Position(File.B, Rank.ONE), new BlackPawn(),
                new Position(File.B, Rank.TWO), new BlackPawn(),
                new Position(File.B, Rank.THREE), new BlackPawn(),
                new Position(File.D, Rank.FOUR), new BlackPawn(),
                new Position(File.E, Rank.FIVE), new BlackPawn(),
                new Position(File.F, Rank.SIX), new BlackPawn()
        );
        GameResult gameResult = new GameResult(board);

        double score = gameResult.calculateScore(color);

        assertThat(score).isEqualTo(4.5);
    }
}
