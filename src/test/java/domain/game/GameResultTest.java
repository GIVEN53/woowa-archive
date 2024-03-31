package domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void 화이트_또는_블랙의_킹_중_한_쪽이_잡혔으면_true를_반환한다() {
        Map<Position, Piece> board = Map.of(
                new Position(File.F, Rank.FIVE), new King(Color.BLACK)
        );
        GameResult gameResult = new GameResult(board);

        boolean isCapturedKing = gameResult.isCapturedKing();

        assertThat(isCapturedKing).isTrue();
    }

    @Test
    void 화이트_또는_블랙의_킹_모두_잡히지_않았으면_false를_반환한다() {
        Map<Position, Piece> board = Map.of(
                new Position(File.E, Rank.FOUR), new King(Color.WHITE),
                new Position(File.F, Rank.FIVE), new King(Color.BLACK)
        );
        GameResult gameResult = new GameResult(board);

        boolean isCapturedKing = gameResult.isCapturedKing();

        assertThat(isCapturedKing).isFalse();
    }

    @Test
    void 화이트_킹이_잡혔으면_블랙이_승리한다() {
        Map<Position, Piece> board = Map.of(
                new Position(File.F, Rank.FIVE), new King(Color.BLACK)
        );
        GameResult gameResult = new GameResult(board);

        Color winner = gameResult.getWinner();

        assertThat(winner).isEqualTo(Color.BLACK);
    }

    @Test
    void 블랙_킹이_잡혔으면_화이트가_승리한다() {
        Map<Position, Piece> board = Map.of(
                new Position(File.F, Rank.FIVE), new King(Color.WHITE)
        );
        GameResult gameResult = new GameResult(board);

        Color winner = gameResult.getWinner();

        assertThat(winner).isEqualTo(Color.WHITE);
    }

    @Test
    void 화이트_킹과_블랙_킹이_모두_잡히지_않았으면_승자를_구할_수_없다() {
        Map<Position, Piece> board = Map.of(
                new Position(File.E, Rank.FOUR), new King(Color.WHITE),
                new Position(File.F, Rank.FIVE), new King(Color.BLACK)
        );
        GameResult gameResult = new GameResult(board);

        assertThatThrownBy(gameResult::getWinner)
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("게임이 종료되지 않았습니다.");
    }
}
