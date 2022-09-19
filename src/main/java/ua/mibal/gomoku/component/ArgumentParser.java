package ua.mibal.gomoku.component;

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
        int gameTableSize = 0;
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
            } else if (isNumber(arg)) {
                int size = Integer.parseInt(arg);
                if (7 <= size && size <= 15) {
                    if (gameTableSize == 0) {
                        gameTableSize = size;
                    } else {
                        System.err.printf(
                                "Invalid command line argument: '%s', because game table size already set: size='%s'.%n",
                                arg, gameTableSize
                        );
                    }
                } else {
                    System.err.printf(
                            "Invalid command line argument: '%s', because game table size should be between 7 and 15.%n",
                            arg
                    );
                }
            } else {
                System.err.printf("Unsupported command line argument: '%s'.%n", arg);
            }
        }
        if (level == null) {
            level = LEVEL2;
        }
        if (gameTableSize == 0) {
            gameTableSize = 15;
        }
        if (player1Type == null) {
            return new CommandLineArguments(level, USER, COMPUTER, gameTableSize);
        } else if (player2Type == null) {
            return new CommandLineArguments(level, USER, player1Type, gameTableSize);
        } else {
            return new CommandLineArguments(level, player1Type, player2Type, gameTableSize);
        }
    }

    private boolean isNumber(final String arg) {
        try {
            Integer.parseInt(arg);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    /**
     * @author Michael Balakhon
     * @link t.me/mibal_ua
     */
    public static class CommandLineArguments {

        private final Level level;

        private final PlayerType player1Type;

        private final PlayerType player2Type;

        private final int gameTableSize;

        public CommandLineArguments(final Level level,
                                    final PlayerType player1Type,
                                    final PlayerType player2Type,
                                    final int gameTableSize) {
            this.level = level;
            this.player1Type = player1Type;
            this.player2Type = player2Type;
            this.gameTableSize = gameTableSize;
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

        public int getGameTableSize() {
            return gameTableSize;
        }
    }
}
