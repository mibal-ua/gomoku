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

import static ua.mibal.gomoku.component.strategy.AbstractComputerMoveStrategy.COLS_AND_ROWS;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua
 */
public class RandomComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        final Cell[] emptyCells = new Cell[(int) Math.pow(COLS_AND_ROWS, 2)];
        int count = 0;
        for (int i = 0; i < COLS_AND_ROWS; i++) {
            for (int j = 0; j < COLS_AND_ROWS; j++) {
                Cell cell = new Cell(i, j);
                if (gameTable.isEmpty(cell)) {
                    emptyCells[count++] = cell;
                }
            }
        }
        if (count > 0) {
            final Cell randomCell = emptyCells[new Random().nextInt(count)];
            gameTable.setSign(randomCell, sign);
            return true;
        } else {
            return false;
        }
    }
}
