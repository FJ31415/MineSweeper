public class Board {
    private final Tile[][] tiles;

    public Board(int width, int height, int mineAmount) {
        // generate tiles
        tiles = new Tile[height][width];
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                tiles[y][x] = new Tile();
            }
        }

        generateMines(mineAmount);

        generateNumbers();
    }

    private void generateMines(int mineAmount) {
        // for amount mines:
        for (int i = 0; i < mineAmount; i++) {
            // select random tile without a mine
            int x = (int) (Math.random() * getWidth());
            int y = (int) (Math.random() * getHeight());

            // exception: tile with mine
            if (getTile(x, y).isMine()) {
                // try again
                i--;
                continue;
            }

            // place mine
            getTile(x, y).placeMine();
        }
    }

    private void generateNumbers() {
        // for every tile without a mine:
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                // exception: mine
                if (getTile(x, y).isMine()) {
                    // skip
                    continue;
                }

                // count mines on surrounding tiles
                int numberOfMines = 0;
                // for every surrounding tile:
                for (int sY = y - 1; sY <= y + 1; sY++) {
                    // exception: out of bound y
                    if (isOutOfBoundY(sY)) {
                        continue;
                    }
                    for (int sX = x - 1; sX <= x + 1; sX++) {
                        // exception: out of bound x
                        if (isOutOfBoundX(sX)) {
                            continue;
                        }

                        // count mine
                        if (getTile(sX, sY).isMine()) {
                            numberOfMines++;
                        }
                    }
                }

                // set number of surrounding mines
                getTile(x, y).setNumberOfSurroundingMines(numberOfMines);
            }
        }
    }

    // getter

    public boolean isCleared() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (!getTile(x, y).isMine() && !getTile(x, y).isUncovered()) {
                    // a tile without a mine isn't uncovered yet
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isOutOfBoundY(int y) {
        return y < 0 || y >= getHeight();
    }

    private boolean isOutOfBoundX(int x) {
        return x < 0 || x >= getWidth();

    }

    public boolean isOutOfBounds(int x, int y) {
        return isOutOfBoundX(x) || isOutOfBoundY(y);
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }

    public int getWidth() {
        return tiles[0].length;
    }

    public int getHeight() {
        return tiles.length;
    }
}