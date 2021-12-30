package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*Представление игрового поля в виде матрицы*/
        char[][] grid = new char[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = '_';

        /*Вывод пустого игрового поля*/
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");

            for (int j = 0; j < 3; j++)
                System.out.print(grid[i][j] + " ");

            System.out.print("|\n");
        }
        System.out.println("---------");

        /*Начало ввода данных от игроков*/
        boolean isFinished = false;
        /*Если true - ход Х, если false - ход O*/
        boolean queue = true;

        do {
            /*Ввод и проверка координат от игрока*/
            boolean isValidInput = false;
            int xCoordinate = 0;
            int yCoordinate = 0;

            do {
                System.out.print("Enter the coordinates: ");

                try {
                    Scanner in = new Scanner(System.in);
                    xCoordinate = in.nextInt();
                    yCoordinate = in.nextInt();
                    isValidInput = true;
                } catch (InputMismatchException ex) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if (!(xCoordinate >= 1 && xCoordinate <= 3 && yCoordinate >= 1 && yCoordinate <= 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    isValidInput = false;
                } else if (!(grid[xCoordinate - 1][yCoordinate - 1] == '_')) {
                    System.out.println("This cell is occupied! Choose another one!");
                    isValidInput = false;
                }
            } while (!isValidInput);

            if (queue) {
                grid[xCoordinate - 1][yCoordinate - 1] = 'X';
                queue = false;
            } else {
                grid[xCoordinate - 1][yCoordinate - 1] = 'O';
                queue = true;
            }

            /*Вывод игрового поля*/
            System.out.println("---------");
            for (int i = 0; i < 3; i++) {
                System.out.print("| ");

                for (int j = 0; j < 3; j++)
                    System.out.print(grid[i][j] + " ");

                System.out.print("|\n");
            }
            System.out.println("---------");

            /*проверяем кто выиграл*/
            boolean xWins = false;
            boolean oWins = false;

            /*Для X*/
            for (int i = 0; i < 3; i++)   // Пробегаем по строкам
                if (grid[0][i] == 'X' && grid[1][i] == 'X' && grid[2][i] == 'X') {
                    isFinished = true;
                    xWins = true;
                    break;
                }

            for (int i = 0; i < 3; i++)   // Пробегаем по столбцам
                if (grid[i][0] == 'X' && grid[i][1] == 'X' && grid[i][2] == 'X') {
                    isFinished = true;
                    xWins = true;
                    break;
                }

            if (grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X') {  // Проверка диагонали 1
                isFinished = true;
                xWins = true;
            }

            if (grid[2][0] == 'X' && grid[1][1] == 'X' && grid[0][2] == 'X') {  // Проверка диагонали 2
                isFinished = true;
                xWins = true;
            }

            /*Для O*/
            for (int i = 0; i < 3; i++)   // Пробегаем по строкам
                if (grid[0][i] == 'O' && grid[1][i] == 'O' && grid[2][i] == 'O') {
                    isFinished = true;
                    oWins = true;
                    break;
                }

            for (int i = 0; i < 3; i++)   // Пробегаем по столбцам
                if (grid[i][0] == 'O' && grid[i][1] == 'O' && grid[i][2] == 'O') {
                    isFinished = true;
                    oWins = true;
                    break;
                }

            if (grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O') {  // Проверка диагонали 1
                isFinished = true;
                oWins = true;
            }

            if (grid[2][0] == 'O' && grid[1][1] == 'O' && grid[0][2] == 'O') {  // Проверка диагонали 2
                isFinished = true;
                oWins = true;
            }

            /*Ничья*/
            int counterX = 0;
            int counterO = 0;

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (grid[i][j] == 'X') counterX++;
                    else if (grid[i][j] == 'O') counterO++;

            if (counterO + counterX == 9 && !isFinished) {
                System.out.println("Draw");
                isFinished = true;
            }

            /*Проверка кто выиграл*/
            if (Math.abs(counterX - counterO) > 1 || xWins && oWins)
                System.out.println("Impossible");
            else if (xWins)
                System.out.println("X wins");
            else if (oWins)
                System.out.println("O wins");
        } while (!isFinished);
    }
}
