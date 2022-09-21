package ua.mibal.gomoku.model.config;

import ua.mibal.gomoku.component.ComputerMoveStrategy;
import ua.mibal.gomoku.component.strategy.PreventUserWinComputerMoveStrategy;
import ua.mibal.gomoku.component.strategy.RandomComputerMoveStrategy;
import ua.mibal.gomoku.component.strategy.WinNowComputerMoveStrategy;
import ua.mibal.gomoku.component.strategy.WinOnTheNextStepComputerMoveStrategy;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public enum Level {
    
    LEVEL1(new ComputerMoveStrategy[]{
            new RandomComputerMoveStrategy()}),

    LEVEL2(new ComputerMoveStrategy[]{
            new WinNowComputerMoveStrategy(),
            new PreventUserWinComputerMoveStrategy(),
            new WinOnTheNextStepComputerMoveStrategy(),
            new RandomComputerMoveStrategy()});

    private final ComputerMoveStrategy[] strategies;

    Level(final ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }

    public ComputerMoveStrategy[] getStrategies() {
        return strategies.clone();
    }
}
