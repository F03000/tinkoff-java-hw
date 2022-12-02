import java.util.Scanner;

public class HumanPlayer implements Player {
    private final TicTacToeBoard board;
    private final Scanner scanner;

    public HumanPlayer(TicTacToeBoard board, Scanner scanner) {
        this.board = board;
        this.scanner = scanner;
    }

    @Override
    public Move makeMove() {
        while (true) {
            System.out.println("Enter your move:");
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            scanner.nextLine();
            if (isValidMove(x, y)) {
                return new Move(x, y);
            }
            System.out.println("Invalid move");
        }
    }

    private boolean isValidMove(int x, int y) {
        return x > 0
                || x <= board.getBoardSize()
                || y > 0
                || y <= board.getBoardSize()
                || board.isCellEmpty(x, y);
    }
}
