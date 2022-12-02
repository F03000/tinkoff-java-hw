import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int boardSize;
        while (true) {
            System.out.println("Enter board's size:");
            try {
                boardSize = Integer.parseInt(scanner.nextLine());
                if (boardSize > 0) {
                    break;
                } else {
                    System.out.println("Number should be positive. Try again.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Couldn't parse number. Try again.");
            }
        }
        Game game = new Game(boardSize, scanner);
        game.start();
    }
}
