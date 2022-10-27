package ua.mibal.gomoku.model.config;

import ua.mibal.gomoku.component.ComputerMoveStrategy;
import ua.mibal.gomoku.component.strategy.*;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public enum Level {
    
    LEVEL1(new ComputerMoveStrategy[]{
            new RandomComputerMoveStrategy()}),

    LEVEL2(new ComputerMoveStrategy[]{
            new WinNowComputerMoveStrategy(),
            new PreventNowUserWinComputerMoveStrategy(),
            new WinOnTheNextStepComputerMoveStrategy(),
            new PreventBeforeUserWinComputerMoveStrategy(),
            new ThirdStepBothToSecondStepComputerMoveStrategy(),
            new SecondStepBothToFirstStepComputerMoveStrategy(),
            new RandomComputerMoveStrategy()});

    private final ComputerMoveStrategy[] strategies;

    Level(final ComputerMoveStrategy[] strategies) {
        this.strategies = strategies;
    }

    public ComputerMoveStrategy[] getStrategies() {
        return strategies.clone();
    }
}
