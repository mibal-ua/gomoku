package ua.mibal.gomoku.model.game;

/**
 * @author Michael Balakhon
 * @link t.me/mibal_ua.
 */
public class Cell {

    private final int row;

    private final int col;

    public Cell(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "Cell{" +
               "row=" + row +
               ", col=" + col +
               '}';
    }
}
