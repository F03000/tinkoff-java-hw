import java.util.Scanner;

public class Game {
    private final TicTacToeBoard board;
    private final Player firstPlayer;
    private final Player secondPlayer;

    /**
     * Allows two players to play the game. Currently, available Player/Bot vs Player/Bot, you can customize this in code
     * @param boardSize - size of the game board
     * @param scanner - input scanner
     */
    public Game(int boardSize, Scanner scanner) {
        board = new TicTacToeBoard(boardSize);
        firstPlayer = new HumanPlayer(board, scanner);
        secondPlayer = new MinimaxPlayer(board);
    }

    public void start() {
        while (true) {
            if (makeMove(firstPlayer) != MoveResult.UNKNOWN) {
                return;
            }
            if (makeMove(secondPlayer) != MoveResult.UNKNOWN) {
                return;
            }
        }
    }

    private MoveResult makeMove(Player player) {
        board.printBoard();
        MoveResult result = board.makeMove(player.makeMove());
        if (result != MoveResult.UNKNOWN) {
            System.out.println("Game is over! The winner is: " + result);
            System.out.println("Final board:");
            board.printBoard();
        }
        return result;
    }
}
