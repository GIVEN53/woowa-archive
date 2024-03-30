package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Type;
import domain.position.Position;

public class Rook extends NonPawn {
    public Rook(Color color) {
        this(color, 0);
    }

    public Rook(Color color, int id) {
        super(color, id);
    }

    @Override
    protected void validateDirection(Position source, Position target) {
        if (!source.isStraightDirectionTo(target)) {
            throw new IllegalArgumentException("Rook은 직선 방향으로 이동해야 합니다.");
        }
    }

    @Override
    protected void validateMoveCount(Position source, Position target) {
    }

    @Override
    public Type type() {
        return Type.ROOK;
    }
}
