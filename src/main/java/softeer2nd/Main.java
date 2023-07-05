package softeer2nd;

import softeer2nd.chess.Board;

import java.util.Scanner;

public class Main {
    private static final String START_COMMAND = "start";
    private static final String FINISH_COMMAND = "end";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        while (true) {
            String input = scanner.nextLine().trim();
            if(input.equals(START_COMMAND)) {
                board.initialize();
                System.out.println(board.print());
            }
            if(input.equals(FINISH_COMMAND)) break;
        }
        scanner.close();

    }
}