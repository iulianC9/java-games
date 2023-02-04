import java.util.*;

public class Minesweeper {
    static int size = 10;
    static char[][] board = new char[size][size];
    static boolean[][] mines = new boolean[size][size];
    static Scanner scan = new Scanner(System.in);
    static int totalMines = 10;

    public static void main(String[] args) {
        setupBoard();
        placeMines();
        playGame();
    }

    static void setupBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
    }

    static void placeMines() {
        int placed = 0;
        Random rand = new Random();
        while (placed < totalMines) {
            int x = rand.nextInt(size);
            int y = rand.nextInt(size);
            if (!mines[x][y]) {
                mines[x][y] = true;
                placed++;
            }
        }
    }

    static void playGame() {
        while (true) {
            System.out.println("Enter the row and column to reveal (e.g. 3 5)");
            int row = scan.nextInt();
            int col = scan.nextInt();
            if (mines[row][col]) {
                System.out.println("You hit a mine! Game over.");
                break;
            } else {
                revealCell(row, col);
                if (checkWin()) {
                    System.out.println("You won!");
                    break;
                }
            }
        }
    }

    static void revealCell(int row, int col) {
        int mines = countMines(row, col);
        board[row][col] = (char) (mines + '0');
        System.out.println("Mines around this cell: " + mines);
    }

    static int countMines(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < size && j >= 0 && j < size && Minesweeper.mines[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    static boolean checkWin() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == '-' && !mines[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
