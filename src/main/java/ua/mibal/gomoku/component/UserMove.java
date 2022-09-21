package ua.mibal.gomoku.component;

import ua.mibal.gomoku.model.game.Cell;
import ua.mibal.gomoku.model.game.GameTable;
import ua.mibal.gomoku.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public class UserMove implements Move {

    private final GameWindow gameWindow;

    public UserMove(final GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    @Override
    public void make(final GameTable gameTable, final Sign sign) {
        while (true) {
            Cell cell = gameWindow.getUserInput();
            if (gameTable.isEmpty(cell)) {
                gameTable.setSign(cell, sign);
                return;
            } else {
                gameWindow.printErrorMessage("This cell cell is not free. Try again");
            }
        }
    }
}
