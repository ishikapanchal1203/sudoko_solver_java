
import java.util.Scanner;

public class sudoko_solver {
    static int steps = 0; // step counter

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            int[][] board = new int[9][9];

            System.out.println("Enter Sudoku (use 0 for empty cells):");

            // taking input
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    board[i][j] = sc.nextInt();
                }
            }

            long startTime = System.currentTimeMillis();

            if (solve(board)) {
                long endTime = System.currentTimeMillis();

                System.out.println("\nSolved Sudoku:\n");
                printBoard(board);

                System.out.println("\nSteps taken: " + steps);
                System.out.println("Time taken: " + (endTime - startTime) + " ms");
            }
            else {
                System.out.println("No solution exists!");
            }

            sc.close();
        }

        // check if number is valid
        static boolean isValid(int[][] board, int row, int col, int num) {

            // row & column check
            for (int i = 0; i < 9; i++) {
                if (board[row][i] == num) return false;
                if (board[i][col] == num) return false;
            }

            // 3x3 box check
            int startRow = row - row % 3;
            int startCol = col - col % 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[startRow + i][startCol + j] == num)
                        return false;
                }
            }

            return true;
        }

        // solve using backtracking
        static boolean solve(int[][] board) {

            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {

                    if (board[row][col] == 0) {

                        for (int num = 1; num <= 9; num++) {

                            steps++; // count steps

                            if (isValid(board, row, col, num)) {
                                board[row][col] = num;

                                if (solve(board)) return true;

                                board[row][col] = 0; // backtrack
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }

        // print formatted board
        static void printBoard(int[][] board) {

            for (int i = 0; i < 9; i++) {

                if (i % 3 == 0 && i != 0) {
                    System.out.println("------+-------+------");
                }

                for (int j = 0; j < 9; j++) {

                    if (j % 3 == 0 && j != 0) {
                        System.out.print("| ");
                    }

                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }