package ua.mibal.gomoku.model.game;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public enum Sign {

    EMPTY,

    X,

    O;

    public Sign oppositeSign() {
        if (this == X) {
            return O;
        } else if (this == O) {
            return X;
        } else {
            throw new IllegalStateException("Empty value does not have an opposite one.");
        }
    }

    @Override
    public String toString() {
        if (this == EMPTY) {
            return " ";
        } else {
            return name();
        }
    }
}
