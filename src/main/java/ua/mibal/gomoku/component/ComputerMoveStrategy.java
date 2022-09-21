package ua.mibal.gomoku.component;

import ua.mibal.gomoku.model.game.GameTable;
import ua.mibal.gomoku.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public interface ComputerMoveStrategy {
    boolean tryToMakeMove(GameTable gameTable, Sign sign);
}
