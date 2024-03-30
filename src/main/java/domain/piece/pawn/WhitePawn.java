package domain.piece.pawn;

import domain.piece.Color;
import domain.position.Position;
import domain.position.Rank;

public class WhitePawn extends Pawn {
    public WhitePawn() {
        this(0);
    }

    public WhitePawn(int id) {
        super(id);
    }

    @Override
    protected boolean isMovedBack(Position source, Position target) {
        return source.isUpperRankThan(target);
    }

    @Override
    protected Rank initialRank() {
        return Rank.TWO;
    }

    @Override
    public Color color() {
        return Color.WHITE;
    }
}
