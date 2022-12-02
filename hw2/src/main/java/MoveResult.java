public enum MoveResult {
    X_PLAYER_WINS, O_PLAYER_WINS, DRAW, UNKNOWN;

    @Override
    public String toString() {
        return switch (this) {
            case X_PLAYER_WINS -> "First player";
            case O_PLAYER_WINS -> "Second player";
            case DRAW -> "No winner, tie game";
            default -> throw new IllegalStateException("Game resulted in invalid state");
        };
    }
}
