package com.nibado.nrow;

import java.util.Scanner;

public class NrowMain {

    public void start() {
        final Scanner scan = new Scanner(System.in);
        final AI ai = new RandomAI();

        System.out.print("Board width:  ");
        final int width = scan.nextInt();
        System.out.print("Board height: ");
        final int height = scan.nextInt();

        final Board board = new Board(width, height);

        final boolean cont = true;
        while (cont) {
            if (board.getEmptyFields() == 0) {
                System.out.println("Stalemate!");
                break;
            }
            printBoard(board);
            System.out.print("X:");
            final int x = scan.nextInt();
            System.out.print("Y:");
            final int y = scan.nextInt();
            if (board.get(x, y) == 0) {
                board.set(x, y, 1);
                if (board.getEmptyFields() == 0) {
                    System.out.println("Stalemate!");
                    break;
                }
                final int[] pick = ai.pick(board);
                board.set(pick[0], pick[1], 2);
            }
            else {
                System.out.println("Can't pick that one!");
            }
        }
        scan.close();
    }

    public static int solution(final Board board) {
        return -1;
    }

    public static void printBoard(final Board board) {
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                char c;
                switch (board.get(x, y)) {
                    case 0:
                        c = '.';
                        break;
                    case 1:
                        c = 'O';
                        break;
                    case 2:
                        c = 'X';
                        break;
                    default:
                        c = '?';
                        break;
                }

                System.out.print(c);
                System.out.print(' ');
            }
            System.out.println();
            printLine(' ', board.getWidth() * 2);
        }
    }

    public static void main(final String... argv) {
        final NrowMain main = new NrowMain();
        main.start();
    }

    public static void printLine(final char c, final int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(c);
        }
        System.out.println();
    }
}
