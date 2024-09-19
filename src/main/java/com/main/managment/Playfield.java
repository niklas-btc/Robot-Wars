package com.main.managment;

import java.io.IOException;

public class Playfield {

    private int field[][] = null;
    private int rows = 0;
    private int cols = 0;


    public void SetPlayerPos(int row, int columns) {
        // Lösche die alte Spielerposition
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == 1) {
                    field[i][j] = 0; // Alte Position zurücksetzen
                    break;
                }
            }
        }

        // Setze die neue Spielerposition
        field[row][columns] = 1;
    }

    public int[] GetPlayerPos() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(field[i][j] == 1) {
                    int[] user_position = new int[2];
                    user_position[0] = i;
                    user_position[1] = j;
                    return user_position; // Rückgabe der spieler Position [x-koordinate, y-koordinate]
                }
            }
        }

        // Kein Spieler gefunden
        return null;
    }


    // row = Zeilen, columns = Spalten
    public Playfield(int row, int columns) {
        rows = row;
        cols = columns;
        field = new int[row][columns];
        field[0][0] = 1; // Startposition des Spielers
    }

    public void InitializeNewPlayField(){
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                field[i][j] = 0;
                System.out.printf("|\t");
            }
            System.out.printf("|%n");
        }
    }

    public void showPlayField() {
        ClearConsole();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                // Prüfe, auf Spieler Position
                if(field[i][j] == 1) {
                    System.out.printf("| Ω ");
                }else {
                    System.out.printf("|\t");
                }
            }
            System.out.printf("|%n");
        }
    }

    public void ClearConsole(){
        int lines = 20;
        for(int i = 0; i < lines; i++) {
            System.out.println();
        }
    }
}
