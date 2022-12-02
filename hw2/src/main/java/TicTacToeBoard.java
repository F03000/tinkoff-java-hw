import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TicTacToeBoard {
    private final int boardSize;
    private final List<List<Cell>> board;
    private Cell turn;
    private int leftEmptyCells;

    public TicTacToeBoard(int boardSize) {
        this.boardSize = boardSize;
        leftEmptyCells = boardSize * boardSize;
        board = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            board.add(
                    new ArrayList<>(
                            Collections.nCopies(boardSize, Cell.EMPTY)
                    ));
        }
        turn = Cell.X;
    }

    private TicTacToeBoard(int boardSize, List<List<Cell>> board, Cell turn, int leftEmptyCells) {
        this.boardSize = boardSize;
        this.board = board;
        this.turn = turn;
        this.leftEmptyCells = leftEmptyCells;
    }


    public MoveResult makeMove(Move move) {
        if (isNotValidMove(move)) {
            throw new IllegalArgumentException("Move cell is not empty");
        }
        Cell currentTurn = turn;
        board.get(move.getCoordX()).set(move.getCoordY(), currentTurn);
        leftEmptyCells--;
        changeTurn();
        return checkForWin(move, currentTurn);
    }

    private boolean isNotValidMove(Move move) {
        return move.getCoordX() < 0
                || move.getCoordX() >= boardSize
                || move.getCoordY() < 0
                || move.getCoordY() >= boardSize
                || !isCellEmpty(move.getCoordX(), move.getCoordY());
    }

    /**
     * Checks if after last move game resulted.
     *
     * @param move        - last move
     * @param currentTurn - last move's type
     * @return result of the game after last move
     */
    private MoveResult checkForWin(Move move, Cell currentTurn) {
        if (gameResultedAfterMove(move)) {
            return convertToResult(currentTurn);
        }
        if (leftEmptyCells == 0) {
            return MoveResult.DRAW;
        }
        return MoveResult.UNKNOWN;
    }

    /**
     * Methods checks if game resulted
     * @param move - last move
     * @return True if some line contains only from identical cells
     */
    private boolean gameResultedAfterMove(Move move) {
        return checkLine(move, 1, 0) + checkLine(move, -1, 0) >= boardSize + 1
                || checkLine(move, 0, 1) + checkLine(move, 0, -1) >= boardSize + 1
                || checkLine(move, 1, 1) + checkLine(move, -1, -1) >= boardSize + 1
                || checkLine(move, 1, -1) + checkLine(move, -1, 1) >= boardSize + 1;
    }

    /**
     * Returns the number of identical cells from move.x, move.y to move.x + dx * n, move.y + dy * n, n -> +inf
     *
     * @param initialPoint - move.x, move.y
     * @param dx           - x-coordinate magnification step
     * @param dy           - y-coordinate magnification step
     * @return number of identical cells
     */
    private int checkLine(Move initialPoint, int dx, int dy) {
        int row = initialPoint.getCoordX(), column = initialPoint.getCoordY();
        Cell currentCell = board.get(row).get(column);
        int counter = 0;
        while (isCellIdenticalToFixedCell(row, column, currentCell)) {
            row += dx;
            column += dy;
            counter++;
        }
        return counter;
    }

    private boolean isCellIdenticalToFixedCell(int row, int column, Cell currentCell) {
        return row >= 0
                && row < boardSize
                && column >= 0
                && column < boardSize
                && board.get(row).get(column) == currentCell;
    }

    private MoveResult convertToResult(Cell currentTurn) {
        return currentTurn == Cell.X ? MoveResult.X_PLAYER_WINS : MoveResult.O_PLAYER_WINS;
    }

    public boolean isCellEmpty(int coordX, int coordY) {
        return board.get(coordX).get(coordY) == Cell.EMPTY;
    }

    private void changeTurn() {
        turn = turn == Cell.X ? Cell.O : Cell.X;
    }

    public int getBoardSize() {
        return boardSize;
    }

    /**
     * CLI board print
     */
    public void printBoard() {
        for (int i = boardSize - 1; i >= 0; i--) {
            System.out.print(i + 1);
            System.out.print('|');
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board.get(i).get(j));
                if (j != boardSize - 1) {
                    System.out.print('|');
                }
            }
            System.out.println();
            for (int j = 0; j < 2 * boardSize + 1; j++) {
                if (j != 1) {
                    System.out.print('-');
                } else {
                    System.out.print('|');
                }
            }
            System.out.println();
        }
        System.out.print(" |");
        for (int i = 0; i < boardSize; i++) {
            System.out.print(i + 1);
            if (i != boardSize - 1) {
                System.out.print('|');
            }
        }
        System.out.println();
    }


    public TicTacToeBoard getCopy() {
        List<List<Cell>> newBoard = new ArrayList<>();
        for (int i = 0; i < boardSize; i++) {
            newBoard.add(new ArrayList<>());
            for (int j = 0; j < boardSize; j++) {
                newBoard.get(i).add(board.get(i).get(j));
            }
        }
        return new TicTacToeBoard(boardSize, newBoard, turn, leftEmptyCells);
    }

    public Cell getCurrentTurn() {
        return turn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicTacToeBoard that = (TicTacToeBoard) o;
        return boardSize == that.boardSize && leftEmptyCells == that.leftEmptyCells && board.equals(that.board) && turn == that.turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardSize, board, turn, leftEmptyCells);
    }
}
