package softeer2nd;

import softeer2nd.chess.ChessView;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.pieces.Position;

import java.util.Scanner;

public class Main {
    private static final String START_COMMAND = "start";
    private static final String FINISH_COMMAND = "end";
    private static final String MOVE_COMMAND = "move";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        ChessView chessView = new ChessView(board);

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if(input.equals(START_COMMAND)) board.initialize();

            if(input.startsWith(MOVE_COMMAND)) {
                String[] strings = input.split(" ");
                chessGame.movePiece(new Position(strings[1]), new Position(strings[2]));
            }

            if(input.equals(FINISH_COMMAND)) break;
            System.out.println(chessView.showBoard());
        }
        scanner.close();
    }
}