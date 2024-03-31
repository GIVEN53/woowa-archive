package domain.game;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.Type;
import domain.position.File;
import domain.position.Position;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameResult {
    private static final int SAME_FILE_PAWN_COUNT = 2;
    private static final double SAME_FILE_PAWN_SCORE = 0.5;

    private final Map<Position, Piece> board;

    public GameResult(Map<Position, Piece> board) {
        this.board = board;
    }

    public double calculateScore(Color color) {
        return calculatePieceScore(color) - calculateSameFilePawnScore(color);
    }

    private double calculatePieceScore(Color color) {
        return board.values().stream()
                .filter(piece -> piece.color().isSameColor(color))
                .mapToDouble(Piece::score)
                .sum();
    }

    private double calculateSameFilePawnScore(Color color) {
        Map<File, Long> sameFilePawnCount = board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color) && entry.getValue().isSameType(Type.PAWN))
                .map(entry -> entry.getKey().file())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return sameFilePawnCount.values().stream()
                .filter(count -> count >= SAME_FILE_PAWN_COUNT)
                .mapToDouble(count -> count * SAME_FILE_PAWN_SCORE)
                .sum();
    }
}
