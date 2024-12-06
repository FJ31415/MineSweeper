import java.util.Scanner;

public class MineSweeper {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // setup

        // get board width
        System.out.println("board width:");
        int boardWidth = input.nextInt();

        // get board height
        System.out.println("board height:");
        int boardHeight = input.nextInt();

        // get number of mines
        System.out.println("number of mines:");
        int numberOfMines = input.nextInt();

        // start game
        Game game = new Game(boardWidth, boardHeight, numberOfMines);

        // game loop

        // while game hasn't ended:
        do {
           // print board
            game.printBoard();

            // get position
            System.out.println("enter position (x, ENTER, y, ENTER):");
            int x = input.nextInt();
            int y = input.nextInt();

            // uncover position
            game.uncover(x, y);
        } while (!game.isFinished());

        // print last message
        if (game.isWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost! Better luck next time!");
        }

        game.printBoard();

        input.close();
    }
}