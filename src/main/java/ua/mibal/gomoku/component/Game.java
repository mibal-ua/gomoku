package ua.mibal.gomoku.component;

import ua.mibal.gomoku.model.game.GameTable;
import ua.mibal.gomoku.model.game.Player;

import java.util.Random;

/**
 * @author Michael Balakhon
 * @link mibal_ua.t.me
 */
public class Game {

    private final Player player1;

    private final Player player2;

    private final WinnerVerifier winnerVerifier;

    private final DrawVerifier drawVerifier;

    private final GameWindow gameWindow;

    private final boolean canSecondPlayerMakeFirstMove;

    private final int gameTableSize;

    public Game(final Player player1,
                final Player player2,
                final WinnerVerifier winnerVerifier,
                final DrawVerifier drawVerifier,
                final GameWindow gameWindow,
                final boolean canSecondPlayerMakeFirstMove,
                final int gameTableSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.winnerVerifier = winnerVerifier;
        this.drawVerifier = drawVerifier;
        this.gameWindow = gameWindow;
        this.canSecondPlayerMakeFirstMove = canSecondPlayerMakeFirstMove;
        this.gameTableSize = gameTableSize;
    }

    public void start() {
        boolean restart = false;
        while (true) {
            final GameTable gameTable = new GameTable(gameTableSize);
            if (restart) gameWindow.printGameTable(gameTable);
            restart = false;
            if (canSecondPlayerMakeFirstMove && new Random().nextBoolean()) {
                player2.makeMove(gameTable);
                gameWindow.printGameTable(gameTable);
            }
            final Player[] players = {player1, player2};
            while (!restart) {
                for (final Player player : players) {
                    player.makeMove(gameTable);
                    gameWindow.printGameTable(gameTable);
                    if (winnerVerifier.isWinner(gameTable, player)) {
                        gameWindow.printInfoMessage(player + " WIN!");
                        restart = true;
                        break;
                    }
                    if (drawVerifier.allCellsFilled(gameTable)) {
                        gameWindow.printInfoMessage("DRAW!");
                        restart = true;
                        break;
                    }
                }
            }
        }
    }
}
