public class Tile {
    private boolean uncovered;
    private char content;
    private static final char EMPTY = ' ';
    public static final char MINE_CHAR = 'M';

    public Tile() {
        // set initial values
        uncovered = false;
        content = EMPTY;
    }

    // setter

    public void placeMine() {
        content = MINE_CHAR;
    }

    public void setNumberOfSurroundingMines(int numberOfSurroundingMines) {
        content = (char) ('0' + numberOfSurroundingMines);
    }

    public void setUncovered() {
        if (!uncovered) {
            uncovered = true;
        }

    }

    // getter
    public boolean isMine() {
        return content == MINE_CHAR;
    }

    public char getContent() {
        return content;
    }

    public boolean isUncovered() {
        return uncovered;
    }
}