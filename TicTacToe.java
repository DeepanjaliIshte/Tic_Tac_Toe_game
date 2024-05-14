import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char[][] board;

    public TicTacToe() {
        board = new char[3][3];
        initializeBoard();
    }

    private static void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("-------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static void placeMark(int row, int col, char mark) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
            board[row][col] = mark;
        } else {
            System.out.println("Invalid Position");
        }
    }

    private static boolean checkWin(char mark) {
        return (board[0][0] == mark && board[0][1] == mark && board[0][2] == mark) ||
               (board[1][0] == mark && board[1][1] == mark && board[1][2] == mark) ||
               (board[2][0] == mark && board[2][1] == mark && board[2][2] == mark) ||
               (board[0][0] == mark && board[1][0] == mark && board[2][0] == mark) ||
               (board[0][1] == mark && board[1][1] == mark && board[2][1] == mark) ||
               (board[0][2] == mark && board[1][2] == mark && board[2][2] == mark) ||
               (board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
               (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark);
    }

    private static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        displayBoard();

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        char currentPlayer = 'X';

        while (true) {
            int row, col;
            if (currentPlayer == 'X') {
                System.out.println("Player X's turn:");
                System.out.print("Enter row and column (e.g., 1 2): ");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
            } else {
                System.out.println("Player O's turn (AI):");
                row = random.nextInt(3);
                col = random.nextInt(3);
            }

            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                placeMark(row, col, currentPlayer);
                displayBoard();

                if (checkWin(currentPlayer)) {
                    System.out.println("Player " + currentPlayer + " wins!");
                    break;
                } else if (checkDraw()) {
                    System.out.println("It's a draw!");
                    break;
                }

                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            } else {
                System.out.println("Invalid move! Try again.");
            }
        }
        scanner.close();
    }
}
