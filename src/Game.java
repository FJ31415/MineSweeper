import logic.Board;

public class Game {
    private enum GameState {
        ONGOING,
        LOST,
        WON
    }

    private GameState state;
    private final Board board;

    public Game(int boardWidth, int boardHeight, int numberOfMines) {
        state = GameState.ONGOING;
        board = new Board(boardWidth, boardHeight, numberOfMines);
    }

    public void uncover(int x, int y) {
        // exception: out of bounds
        if (board.isOutOfBounds(x, y)) {
            return;
        }

        // uncover
        board.getTile(x, y).setUncovered();

        if (board.getTile(x, y).isMine()) {
            // mine uncovered
            state = GameState.LOST;
        } else if (board.isCleared()) {
            // board cleared
            state = GameState.WON;
        }

    }

    public void printBoard() {
        // print x coordinates
        System.out.print(" ");
        for (int x = 0; x < board.getWidth(); x++) {
            System.out.print(" " + x);
        }
        System.out.println();

        // print board
        for (int y = 0; y < board.getHeight(); y++) {
            // print y coordinate
            System.out.print("" + y);

            // print board line
            for (int x = 0; x < board.getWidth(); x++) {
                System.out.print(' ');

                if (board.getTile(x, y).isUncovered()) {
                    // uncovered
                    System.out.print(board.getTile(x, y).getContent());
                } else {
                    // not yet uncovered
                    System.out.print('-');
                }
            }

            System.out.println();
        }
    }

    public boolean isFinished() {
        return state != GameState.ONGOING;
    }

    public boolean isWon() {
        return state == GameState.WON;
    }
}