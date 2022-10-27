package ua.mibal.gomoku.component;

import ua.mibal.gomoku.model.game.Cell;
import ua.mibal.gomoku.model.game.GameTable;
import ua.mibal.gomoku.model.game.Player;
import ua.mibal.gomoku.model.game.Sign;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public class WinnerVerifier {

    private int length;

    public boolean isWinner(final GameTable gameTable, final Player player) {
        length = gameTable.getLength();
        return isWinnerByRows(gameTable, player.getSign()) ||
               isWinnerByCols(gameTable, player.getSign()) ||
               isWinnerByMainDiagonal(gameTable, player.getSign()) ||
               isWinnerBySecondDiagonal(gameTable, player.getSign());
    }

    private boolean isWinnerByRows(final GameTable gameTable, final Sign sign) {
        return isWinnerByLambdaConversion(gameTable, sign, Cell::new);
    }

    private boolean isWinnerByCols(final GameTable gameTable, final Sign sign) {
        return isWinnerByLambdaConversion(gameTable, sign, (i, j) -> new Cell(j, i));
    }

    private boolean isWinnerByLambdaConversion(final GameTable gameTable,
                                               final Sign sign,
                                               final Lambda lambda) {
        int count = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (gameTable.getSign(lambda.getCell(i, j)) == sign) {
                    count++;
                    if (count == 5) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean isWinnerByMainDiagonal(final GameTable gameTable, final Sign sign) {
        int countLeft = 0;
        int countRight = 0;
        for (int k = 0; k < length - 4; k++) {
            for (int x = 0; x < length; x++) {
                if (x + k >= length || length - 1 - (x + k) < 0) {
                    break;
                }
                if (k != 0) {
                    if (gameTable.getSign(new Cell(x + k, x)) == sign) {
                        countLeft++;
                    } else {
                        countLeft = 0;
                    }
                }
                if (gameTable.getSign(new Cell(x, x + k)) == sign) {
                    countRight++;
                } else {
                    countRight = 0;
                }
                if (countLeft == 5 || countRight == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isWinnerBySecondDiagonal(final GameTable gameTable, final Sign sign) {
        int countLeft = 0;
        int countRight = 0;
        for (int k = 0; k < length - 4; k++) {
            for (int x = 0; x < length; x++) {
                if (x + k >= length || length - 1 - (x + k) < 0) {
                    break;
                }
                if (k != 0) {
                    if (gameTable.getSign(new Cell((x + k), length - 1 - x)) == sign) {
                        countRight++;
                    } else {
                        countRight = 0;
                    }
                }
                if (gameTable.getSign(new Cell(x, (length - 1 - (x + k)))) == sign) {
                    countLeft++;
                } else {
                    countLeft = 0;
                }
                if (countLeft == 5 || countRight == 5) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @author Michael Balakhon
     * @link t.me/mibal_ua.
     */
    @FunctionalInterface
    private interface Lambda {

        Cell getCell(int i, int j);
    }
}
