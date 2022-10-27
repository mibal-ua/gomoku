/*
 * Copyright (c) 2022. http://t.me/mibal_ua
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package ua.mibal.gomoku.component.strategy;

import ua.mibal.gomoku.component.ComputerMoveStrategy;
import ua.mibal.gomoku.model.game.Cell;
import ua.mibal.gomoku.model.game.GameTable;
import ua.mibal.gomoku.model.game.Sign;

import java.util.Random;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {
    
    protected static int lengthOfTable;

    private final int expectedSignCells;

    public AbstractComputerMoveStrategy(final int countExpectedSignCells) {
        this.expectedSignCells = countExpectedSignCells;
    }

    @Override
    public final boolean tryToMakeMove(final GameTable gameTable, final Sign moveSign) {
        lengthOfTable = gameTable.getLength();
        final Sign findSign = getFindSign(moveSign);
        BestCells bestCells = new BestCells();
        findBestCellForMoveByRows(gameTable, findSign, bestCells);
        findBestCellForMoveByCols(gameTable, findSign, bestCells);
        findBestCellForMoveByMainDiagonal(gameTable, findSign, bestCells);
        findBestCellForMoveBySecondaryDiagonal(gameTable, findSign, bestCells);
        if (bestCells.count > 0) {
            final Cell randomCell = bestCells.emptyCells[new Random().nextInt(bestCells.count)];
            gameTable.setSign(randomCell, moveSign);
            return true;
        } else {
            return false;
        }
    }

    protected abstract Sign getFindSign(Sign moveSign);

    private void findBestCellForMoveByRows(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, Cell::new);
    }

    private void findBestCellForMoveByCols(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, (i, j) -> new Cell(j, i));
    }

    private void findBestCellForMoveByMainDiagonal(final GameTable gameTable,
                                                   final Sign findSign,
                                                   final BestCells bestCells) {
        // right
        findBestCellForMoveByMainDiagonalUsingSecondaryLambdaConversion(gameTable, findSign, bestCells,
                (i, j) -> new int[]{i, j});
        // right
        findBestCellForMoveByMainDiagonalUsingSecondaryLambdaConversion(gameTable, findSign, bestCells,
                (i, j) -> new int[]{j, i});
    }

    private void findBestCellForMoveBySecondaryDiagonal(final GameTable gameTable,
                                                        final Sign findSign,
                                                        final BestCells bestCells) {
        //right
        findBestCellForMoveBySecondaryDiagonalUsingSecondaryLambdaConversion(gameTable, findSign, bestCells,
                (temp, j) -> new int[]{temp, j});
        //left
        findBestCellForMoveBySecondaryDiagonalUsingSecondaryLambdaConversion(gameTable, findSign, bestCells,
                (temp, j) -> new int[]{j, temp});
    }

    private void findBestCellForMoveUsingLambdaConversion(final GameTable gameTable,
                                                          final Sign findSign,
                                                          final BestCells bestCells,
                                                          final Lambda lambda) {
        int countByDirection = 0;
        for (int i = 0; i < lengthOfTable; i++) {
            for (int j = 0; j < lengthOfTable; j++) {
                if (gameTable.getSign(lambda.conversion(i, j)) == findSign) {
                    countByDirection++;
                } else {
                    countByDirection = 0;
                }
                if (countByDirection == expectedSignCells) {
                    Cell newCell = lambda.conversion(i, j + 1);
                    if ((j + 1 < lengthOfTable) && gameTable.isEmpty(newCell)) {
                        bestCells.add(newCell);
                    }
                    newCell = lambda.conversion(i, j - expectedSignCells);
                    if ((j - expectedSignCells > -1) && gameTable.isEmpty(newCell)) {
                        bestCells.add(newCell);
                    }
                }
            }
        }
    }

    private void findBestCellForMoveByMainDiagonalUsingSecondaryLambdaConversion(final GameTable gameTable,
                                                                                 final Sign findSign,
                                                                                 final BestCells bestCells,
                                                                                 final SecondaryLambda lambda) {
        int countByDirection = 0;
        for (int i = 0; i < lengthOfTable - 4; i++) {
            for (int j = 0; j < lengthOfTable; j++) {
                int[] coordinates = lambda.conversion(j, j + i);
                int x = coordinates[0];
                int y = coordinates[1];
                if (j + i >= lengthOfTable) {
                    break;
                }
                if (gameTable.getSign(new Cell(x, y)) == findSign) {
                    countByDirection++;
                } else {
                    countByDirection = 0;
                }
                if (countByDirection == expectedSignCells) {
                    Cell newCell = new Cell(x - expectedSignCells, y - expectedSignCells);
                    if ((y - expectedSignCells > -1) && (x - expectedSignCells > -1) &&
                        gameTable.isEmpty(newCell)) {
                        bestCells.add(newCell);
                    }
                    newCell = new Cell(x + 1, y + 1);
                    if ((y + 1 < lengthOfTable) && (x + 1 < lengthOfTable) &&
                        gameTable.isEmpty(newCell)) {
                        bestCells.add(newCell);
                    }
                }
            }
        }
    }

    private void findBestCellForMoveBySecondaryDiagonalUsingSecondaryLambdaConversion(final GameTable gameTable,
                                                                                      final Sign findSign,
                                                                                      final BestCells bestCells,
                                                                                      final SecondaryLambda lambda) {
        int countByDirection = 0;
        for (int i = 0; i < lengthOfTable - 4; i++) {
            for (int j = 0; j < lengthOfTable; j++) {
                int[] coordinates = lambda.conversion(j + i, j);
                int x = coordinates[0];
                int y = coordinates[1];
                if (lengthOfTable - 1 - j - i < 0) {
                    break;
                }
                if (gameTable.getSign(new Cell(x, lengthOfTable - 1 - y)) == findSign) {
                    countByDirection++;
                } else {
                    countByDirection = 0;
                }
                if (countByDirection == expectedSignCells) {
                    Cell newCell = new Cell(x - expectedSignCells, lengthOfTable - 1 - y + expectedSignCells);
                    if ((x - expectedSignCells > -1) && (lengthOfTable - 1 - y + expectedSignCells < lengthOfTable) &&
                        gameTable.isEmpty(newCell)) {
                        bestCells.add(newCell);
                    }
                    newCell = new Cell(x + 1, lengthOfTable - y - 2);
                    if ((x + 1 < lengthOfTable) && (lengthOfTable - y - 2 > -1) &&
                        gameTable.isEmpty(newCell)) {
                        bestCells.add(newCell);
                    }
                }
            }
        }
    }

    /**
     * @author Michael Balakhon
     * @link t.me/mibal_ua
     */
    @FunctionalInterface
    private interface Lambda {

        Cell conversion(int i, int j);
    }

    @FunctionalInterface
    private interface SecondaryLambda {

        int[] conversion(int i, int j);
    }

    /**
     * @author Michael Balakhon
     * @link t.me/mibal_ua
     */
    private static class BestCells {

        private Cell[] emptyCells = new Cell[30];

        private int count;

        private void add(final Cell cell) {
            if (count >= emptyCells.length) {
                Cell[] newData = new Cell[emptyCells.length * 2];
                System.arraycopy(emptyCells, 0, newData, 0, emptyCells.length);
                emptyCells = newData;
            }
            emptyCells[count++] = cell;
        }
    }
}
