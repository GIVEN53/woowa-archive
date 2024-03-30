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
    KING(King::new),
    QUEEN(Queen::new),
    ROOK(Rook::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    PAWN((color, id) -> {
        if (color.isWhite()) {
            return new WhitePawn(id);
        }
        return new BlackPawn(id);
    });

    private final BiFunction<Color, Integer, Piece> pieceGenerator;

    Type(BiFunction<Color, Integer, Piece> pieceGenerator) {
        this.pieceGenerator = pieceGenerator;
    }

    public Piece createPiece(Color color, int id) {
        return pieceGenerator.apply(color, id);
    }
}
