package ua.mibal.gomoku;

import ua.mibal.gomoku.model.config.Level;
import ua.mibal.gomoku.model.config.PlayerType;

import static ua.mibal.gomoku.model.config.Level.LEVEL1;
import static ua.mibal.gomoku.model.config.Level.LEVEL2;
import static ua.mibal.gomoku.model.config.PlayerType.COMPUTER;
import static ua.mibal.gomoku.model.config.PlayerType.USER;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua
 */
public class ArgumentParser {

    final String[] args;

    public ArgumentParser(final String[] args) {
        this.args = args;
    }

    public CommandLineArguments parse() {
        Level level = null;
        PlayerType player1Type = null;
        PlayerType player2Type = null;
        for (final String arg : args) {
            if (arg.equalsIgnoreCase(USER.name()) || arg.equalsIgnoreCase(COMPUTER.name())) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());
                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because player types already set: player1type='%s', player2type='%s'.%n",
                            arg, player1Type, player2Type
                    );
                }
            } else if (arg.equalsIgnoreCase(LEVEL1.name()) || arg.equalsIgnoreCase(LEVEL2.name())) {
                if (level == null) {
                    level = Level.valueOf(arg.toUpperCase());
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because level already set: level='%s'.%n",
                            arg, level
                    );
                }
            } else {
                System.err.printf("Unsupported command line argument: '%s'.%n", arg);
            }
        }
        if (level == null) {
            level = LEVEL2;
        }
        if (player1Type == null) {
            return new CommandLineArguments(level, USER, COMPUTER);
        } else if (player2Type == null) {
            return new CommandLineArguments(level, USER, player1Type);
        } else {
            return new CommandLineArguments(level, player1Type, player2Type);
        }
    }


    /**
     * @author Michael Balakhon
     * @link t.me/mibal_ua
     */
    public static class CommandLineArguments {

        final Level level;

        final PlayerType player1Type;

        final PlayerType player2Type;

        public CommandLineArguments(final Level level,
                                    final PlayerType player1Type,
                                    final PlayerType player2Type) {
            this.level = level;
            this.player1Type = player1Type;
            this.player2Type = player2Type;
        }

        public Level getLevel() {
            return level;
        }

        public PlayerType getPlayer1Type() {
            return player1Type;
        }

        public PlayerType getPlayer2Type() {
            return player2Type;
        }
    }
}
