package ua.mibal.gomoku.model.game;

import ua.mibal.gomoku.component.Move;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public class Player {

    private final Sign sign;

    private final Move move;

    public Player(final Sign sign, final Move move) {
        this.sign = sign;
        this.move = move;
    }

    public void makeMove(final GameTable gameTable) {
        move.make(gameTable, sign);
    }

    public Sign getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return "'" + sign + "'";
    }
}
