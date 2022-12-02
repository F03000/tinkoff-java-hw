import java.util.HashMap;
import java.util.Map;

public class MinimaxPlayer implements Player {
    private final TicTacToeBoard board;
    private final Map<TicTacToeBoard, Move> minimaxBoardToBestMove;
    private final Map<TicTacToeBoard, Integer> minimaxBoardToScore;

    public MinimaxPlayer(TicTacToeBoard board) {
        this.board = board;
        minimaxBoardToBestMove = new HashMap<>();
        minimaxBoardToScore = new HashMap<>();
        calculateBestMoves(new TicTacToeBoard(board.getBoardSize()));
    }

    /**
     * Preprocessing for Best Player. Realisation of MiniMax algorithm.
     * @param currentBoard
     * @return
     */
    private int calculateBestMoves(TicTacToeBoard currentBoard) {
        if (minimaxBoardToScore.containsKey(currentBoard)) {
            return minimaxBoardToScore.get(currentBoard);
        }
        boolean isMaxStage = currentBoard.getCurrentTurn() == Cell.X; // True if Bot needs to find move for 'X'
        int bestScore = isMaxStage ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Move bestMove = null;

        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                try {
                    TicTacToeBoard newBoard = currentBoard.getCopy();
                    MoveResult result = newBoard.makeMove(new Move(i, j));
                    int currentScore = switch (result) {
                        case UNKNOWN -> calculateBestMoves(newBoard);
                        case DRAW -> 0;
                        case X_PLAYER_WINS -> 10;
                        case O_PLAYER_WINS -> -10;
                    };
                    if ((isMaxStage && currentScore > bestScore) || (!isMaxStage && currentScore < bestScore)) {
                        bestScore = currentScore;
                        bestMove = new Move(i, j);
                    }
                } catch (IllegalArgumentException e) {
                    // No operations
                }
            }
        }
        minimaxBoardToBestMove.put(currentBoard, bestMove);
        minimaxBoardToScore.put(currentBoard, bestScore);
        return bestScore;
    }

    @Override
    public Move makeMove() {
        return minimaxBoardToBestMove.get(board);
    }
}
