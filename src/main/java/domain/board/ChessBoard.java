package domain.board;

import domain.piece.Color;
import domain.piece.Empty;
import domain.piece.Piece;
import domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<Position, Piece> board;
    private Color turn;

    public ChessBoard(Map<Position, Piece> board, Color turn) {
        this.board = new HashMap<>(board);
        this.turn = turn;
    }

    public void movePiece(Position source, Position target) {
        Piece sourcePiece = findByPosition(source);
        validateTurnAndChange(sourcePiece);
        sourcePiece.validateMovement(source, target, findByPosition(target));
        validatePathClear(source, target);
        moveToTargetPosition(source, target);
    }

    private void validateTurnAndChange(Piece sourcePiece) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException("자신의 말을 움직여야 합니다.");
        }
        turn = turn.opposite();
    }

    private Piece findByPosition(Position position) {
        return board.getOrDefault(position, Empty.create());
    }

    private void validatePathClear(Position source, Position target) {
        List<Position> path = source.findPathTo(target);
        boolean existPiece = path.stream().anyMatch(board::containsKey);
        if (existPiece) {
            throw new IllegalArgumentException("이동 경로에 다른 기물이 존재합니다.");
        }
    }

    private void moveToTargetPosition(Position source, Position target) {
        board.put(target, board.remove(source));
    }

    public Map<Position, Piece> getPositionAndPieces() {
        return Collections.unmodifiableMap(board);
    }

    public Color getPieceColor(Position position) {
        return findByPosition(position).color();
    }
}
