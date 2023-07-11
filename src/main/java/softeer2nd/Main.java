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
    private static boolean started = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        ChessView chessView = new ChessView(board);
        boolean isFinish = false;

        while (!isFinish) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            String[] command = getCommand(input);

            switch (command[0]) {
                case START_COMMAND:
                    doStartCommand(board);
                    break;
                case MOVE_COMMAND:
                    doMoveCommand(chessGame, command);
                    break;
                case FINISH_COMMAND:
                    isFinish = true;
                    break;
                default:
                    invalidCommand();
            }
            if (started) {
                System.out.println(chessView.showBoard());
            }
        }
        scanner.close();
    }


    private static String[] getCommand(String input) {
        return input.split(" ");
    }

    private static void doStartCommand(Board board) {
        board.initialize();
        started = true;
    }

    private static void doMoveCommand(ChessGame chessGame, String[] command) {
        if (!checkCanMoveCommand(command)) {
            return;
        }
        try {
            chessGame.movePiece(new Position(command[1]), new Position(command[2]));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean checkCanMoveCommand(String[] command) {
        if (!started) {
            System.out.println("체스가 시작되지 않았습니다");
            return false;
        }

        if (command.length != 3) {
            System.out.println("move 명령어의 인수가 너무 많습니다");
            return false;
        }
        return true;
    }

    private static void invalidCommand() {
        System.out.println("해석할 수 없는 명령입니다.");
    }
}
