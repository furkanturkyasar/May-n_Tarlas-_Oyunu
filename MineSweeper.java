import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

    public void printArr(String[][] arr) {
        for (String[] row : arr) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
    int rowNumber;
    int win = 0;
    int counter = 0;
    int colNumber;
    int bombCounter;
    boolean session;
    String[][] map;
    String[][] board;

    MineSweeper(int rowNumber, int colNumber) {
        this.rowNumber = rowNumber;
        this.colNumber = colNumber;
        this.bombCounter = rowNumber * colNumber / 4;
        this.session = true;
        this.map = new String[rowNumber][colNumber];// Bombalarin konumunu gosteren map
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                this.map[i][j] = "-";
            }
        }
        this.board = new String[rowNumber][colNumber]; // Oyunun oynandigi board
        for (int i = 0; i < this.rowNumber; i++) {
            for (int j = 0; j < this.colNumber; j++) {
                this.board[i][j] = "-";
            }
        }
    }

    void run() {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Mayinlarin Konumu");
        int k = 0;
        while (k < bombCounter) {
            for (int i = 0; i < this.rowNumber; i++) {
                for (int j = 0; j < this.colNumber; j++) {
                    int random = rand.nextInt(10);

                    if (random > 5 && k < bombCounter) {
                        this.map[i][j] = " * ";
                        k++;
                    } else {
                        this.map[i][j] = " - ";
                    }
                    System.out.print(this.map[i][j]);
                }
                System.out.println();
            }
        }
        System.out.println("==================");
        System.out.println("Mayin tarlasi oyununa hos geldiniz !");
        int row = this.rowNumber;
        int coll = this.colNumber;

        while (session) {
            printArr(this.board);

            if (win==(this.rowNumber*colNumber)-((this.rowNumber*this.colNumber)/4)){
                System.out.println("Tebrikler oyunu kazandiniz!");
                printArr(this.map);
                break;

            }

            System.out.print("Satir giriniz: ");
            row = input.nextInt();
            System.out.print("Sutun giriniz: ");
            coll = input.nextInt();

            if (row >= this.rowNumber || coll >= this.colNumber) {
                System.out.println("Yanlis islem girdiniz");
            } else if (row < 0 || coll < 0) {
                System.out.println("Yanlis islem girdiniz");
            } else {
                if (map[row][coll].equals(" * ")) {
                    System.out.println("Game Over");
                    session = false;
                } else {
                    for (int i = row - 1; i <= row + 1; i++) {
                        for (int j = coll - 1; j <= coll + 1; j++) {
                            try {
                                if (i == row && j == coll) {
                                    continue;
                                } else if (map[i][j].equals(" * ")) {
                                    counter++;
                                }
                            } catch (Exception ignored) {
                            }
                        }
                    }
                    win++;
                    this.board[row][coll] = Integer.toString(counter);
                    counter = 0;
                }
            }
        }
    }
}
