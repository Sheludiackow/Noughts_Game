package com.company;

import java.util.Random;
import java.util.Scanner;

public class Array_Noughts {
    static char[][] map;
    static Scanner sc = new Scanner(System.in);
    static char HUMAN_DOT = 'x';
    static char AI_DOT = '0';
    static char EMPTY = '_';
    static Random rnd = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(HUMAN_DOT)){
                System.out.println("Победил человек");
            }
            if (isMapFull()) {
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(AI_DOT)) {
                System.out.println("Победил компьютер");
            }
            if (isMapFull()) {
                break;
            }
        }
        System.out.println("Game over");
    }

    // Проверка победы:
    public static boolean checkWin(char dot) {
        int X;
        int y;
        // Проверка строк на выигрыш
        for (X = 0; X < 2; X++) {
            for (y = 0; y < 2; y++) {
                if (map[y][0 + X] == dot && map[y][1 + X] == dot) {
                    return true;
                }
            }
        }
        // Проверка стобцов на выигрыш
        for (X = 0; X < 2; X++) {
            for (y = 0; y < 2; y++) {
                if (map[0 + X][y] == dot && map[1 + X][y] == dot) {
                    return true;
                }
            }
        }
        // Проверка диагоналей (первые и вторые)
        for (X = 0; X < 2; X++) {
            for (y = 0; y < 2; y++) {
                if (map[0 + X][0 + y] == dot && map[1 + X][1 + y] == dot) {
                    return true;
                }

                if (map[1 - X][0 + y] == dot && map[0 - X][1 + y] == dot) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rnd.nextInt(3);
            y = rnd.nextInt(3);
        } while (!isCellValid(x, y));
        map[y][x] = AI_DOT;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x > 2 || y > 2) {
            return false;
        }
        if (map[y][x] == EMPTY) {
            return true;
        }
        return false;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите ячейку в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[y][x] = HUMAN_DOT;

    }

    public static void initMap() {
        map = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.println();
        System.out.println("  1 2 3");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

