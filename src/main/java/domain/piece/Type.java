package domain.piece;

import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import java.util.function.BiFunction;

public enum Type {
    KING(0, King::new),
    QUEEN(9, Queen::new),
    ROOK(5, Rook::new),
    BISHOP(3, Bishop::new),
    KNIGHT(2.5, Knight::new),
    PAWN(1, (color, id) -> {
        if (color.isWhite()) {
            return new WhitePawn(id);
        }
        return new BlackPawn(id);
    });

    private final double score;
    private final BiFunction<Color, Integer, Piece> pieceGenerator;

    Type(double score, BiFunction<Color, Integer, Piece> pieceGenerator) {
        this.score = score;
        this.pieceGenerator = pieceGenerator;
    }

    public double score() {
        return score;
    }

    public Piece createPiece(Color color, int id) {
        return pieceGenerator.apply(color, id);
    }

    public boolean isSameType(Type other) {
        return this == other;
    }
}
