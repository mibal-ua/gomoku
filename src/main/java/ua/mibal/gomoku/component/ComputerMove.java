package ua.mibal.gomoku.component;

import ua.mibal.gomoku.model.game.GameTable;
import ua.mibal.gomoku.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public class ComputerMove implements Move {

    private final ComputerMoveStrategy[] strategies;

    public ComputerMove(final ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }

    @Override
    public void make(final GameTable gameTable, final Sign sign) {
        for (final ComputerMoveStrategy strategy : strategies) {
            if (strategy.tryToMakeMove(gameTable, sign)) {
                return;
            }
        }
        throw new IllegalArgumentException(
                "Game table does not contain empty cells or invalid configuration for computer strategies."
        );
    }
}

