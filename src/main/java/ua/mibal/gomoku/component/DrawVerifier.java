package ua.mibal.gomoku.component;

import ua.mibal.gomoku.model.game.Cell;
import ua.mibal.gomoku.model.game.GameTable;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public class DrawVerifier {

    public boolean allCellsFilled(final GameTable gameTable) {
        final int length = gameTable.getLength();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (gameTable.isEmpty(new Cell(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
