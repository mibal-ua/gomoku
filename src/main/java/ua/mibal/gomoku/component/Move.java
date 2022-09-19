package ua.mibal.gomoku.component;

import ua.mibal.gomoku.model.game.GameTable;
import ua.mibal.gomoku.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public interface Move {

    void make(final GameTable gameTable, final Sign sign);
}
