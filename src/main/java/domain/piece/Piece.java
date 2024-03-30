package domain.piece;

import domain.position.Position;

public interface Piece {
    void validateMovement(Position source, Position target, Piece other);

    Color color();

    Type type();

    int id();

    default double score() {
        return type().score();
    }

    default boolean isSameColor(Color color) {
        return color().isSameColor(color);
    }

    default boolean isSameType(Type type) {
        return type().isSameType(type);
    }
}
