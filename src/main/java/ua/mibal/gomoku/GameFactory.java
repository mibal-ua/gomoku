package ua.mibal.gomoku;

import ua.mibal.gomoku.component.*;
import ua.mibal.gomoku.model.config.Level;
import ua.mibal.gomoku.model.config.PlayerType;
import ua.mibal.gomoku.model.game.Player;

import static ua.mibal.gomoku.model.config.PlayerType.COMPUTER;
import static ua.mibal.gomoku.model.game.Sign.O;
import static ua.mibal.gomoku.model.game.Sign.X;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua
 */
public class GameFactory {

    final PlayerType player1Type;

    final PlayerType player2Type;

    final Level level;

    final int gameTableSize;

    public GameFactory(final String[] args) {
        final ArgumentParser.CommandLineArguments commandLineArguments =
                new ArgumentParser(args).parse();
        player1Type = commandLineArguments.getPlayer1Type();
        player2Type = commandLineArguments.getPlayer2Type();
        level = commandLineArguments.getLevel();
        gameTableSize = commandLineArguments.getGameTableSize();
    }

    public Game create() {
        final GameWindow gameWindow = new GameWindow(gameTableSize);
        final Player player1;
        if (player1Type == COMPUTER) {
            player1 = new Player(X, new ComputerMove(level.getStrategies()));
        } else {
            player1 = new Player(X, new UserMove(gameWindow));
        }
        final Player player2;
        if (player2Type == COMPUTER) {
            player2 = new Player(O, new ComputerMove(level.getStrategies()));
        } else {
            player2 = new Player(O, new UserMove(gameWindow));
        }
        final boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;
        return new Game(player1,
                player2,
                new WinnerVerifier(),
                new DrawVerifier(),
                gameWindow,
                canSecondPlayerMakeFirstMove,
                gameTableSize);
    }
}
