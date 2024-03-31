package domain.board;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Queen;
import domain.piece.pawn.BlackPawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessBoardTest {
    @Test
    void 기물을_움직일_때_중간에_다른_기물이_있으면_예외가_발생한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        Map<Position, Piece> positionPieceMap = Map.of(
                source, new Queen(Color.WHITE),
                new Position(File.F, Rank.FIVE), new Queen(Color.BLACK));
        ChessBoard board = new ChessBoard(positionPieceMap, Color.WHITE);

        assertThatThrownBy(() -> board.movePiece(source, target))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이동 경로에 다른 기물이 존재합니다.");
    }

    @Test
    void 기물을_움직일_때_중간에_다른_기물이_없으면_이동한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.EIGHT);
        Piece piece = new Queen(Color.WHITE);
        ChessBoard board = new ChessBoard(Map.of(source, piece), Color.WHITE);

        board.movePiece(source, target);

        assertThat(board.getPositionAndPieces()).containsEntry(target, piece)
                .doesNotContainKey(source);
    }

    @Test
    void 기물을_잡는다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.G, Rank.FIVE);
        Piece sourcePiece = new Queen(Color.WHITE);
        BlackPawn targetPiece = new BlackPawn();
        Map<Position, Piece> positionPieceMap = Map.of(
                source, sourcePiece,
                target, targetPiece);
        ChessBoard board = new ChessBoard(positionPieceMap, Color.WHITE);

        board.movePiece(source, target);

        assertThat(board.getPositionAndPieces()).containsEntry(target, sourcePiece)
                .doesNotContainValue(targetPiece);
    }

    @Test
    void 기물을_움직일_때_자신의_말이_아니면_예외가_발생한다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.FIVE);
        Map<Position, Piece> positionPieceMap = Map.of(source, new Queen(Color.BLACK));
        ChessBoard board = new ChessBoard(positionPieceMap, Color.WHITE);

        assertThatThrownBy(() -> board.movePiece(source, target))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자신의 말을 움직여야 합니다.");
    }

    @Test
    void 기물을_움직이면_턴이_바뀐다() {
        Position source = new Position(File.F, Rank.FOUR);
        Position target = new Position(File.F, Rank.FIVE);
        Map<Position, Piece> positionPieceMap = Map.of(source, new Queen(Color.WHITE));
        ChessBoard board = new ChessBoard(positionPieceMap, Color.WHITE);

        board.movePiece(source, target);
    }
}
