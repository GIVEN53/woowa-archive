package domain.board;

public enum Winner {
    WHITE,
    BLACK,
    DRAW;

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isDraw() {
        return this == DRAW;
    }
}
