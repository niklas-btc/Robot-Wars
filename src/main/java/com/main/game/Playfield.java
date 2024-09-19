package com.main.game;
import java.util.Random;

public class Playfield {

    private int field[][] = null;
    private int rows = 0;
    private int cols = 0;

    // row = Zeilen, columns = Spalten
    public Playfield(int row, int columns) {
        rows = row;
        cols = columns;
        field = new int[row][columns];
    }


    public void SetPlayerPos(int row, int columns, int iconIndex) {
        // Lösche die alte Spielerposition
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == iconIndex) {
                    field[i][j] = 0; // Alte Position zurücksetzen
                    break;
                }
            }
        }

        // Setze die neue Spielerposition
        field[row][columns] = iconIndex;
    }

    public int[] GetPlayerPos(int iconIndex) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(field[i][j] == iconIndex) {
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

    public void InitializeNewPlayField(){
        Random rand = new Random();
        System.out.println("╔════════════════════════════════════╗");

        for (int i = 0; i < rows; i++) {
            System.out.print("║");
            for (int j = 0; j < cols; j++) {
                // Mit einer Wahrscheinlichkeit von 5% stelle ein hindernisfeld da
                int n = rand.nextInt(100);
                if(n < 5) {
                    field[i][j] = 99; // 99 = Hindernis index, 1,2,3 = Spieler Index, 0 = leer
                    System.out.print(" # ");  // Hindernis
                    continue;
                }
                System.out.print(" . ");  // Platzhalter für leere Felder

            }
            System.out.println("║");
        }
        System.out.println("╚════════════════════════════════════╝");
    }

    public void showPlayField() {
        System.out.println("╔════════════════════════════════════╗");
        for (int i = 0; i < rows; i++) {
            System.out.print("║");
            for (int j = 0; j < cols; j++) {
                // Prüfe auf Spieler Position
                if (field[i][j] == 1) {
                    System.out.print(" Ω ");  // Spieler 1
                } else if (field[i][j] == 2) {
                    System.out.print(" § ");  // Spieler 2
                } else if (field[i][j] == 3) {
                    System.out.print(" € ");  // Spieler 3
                }
                // Prüfe auf Hindernis
                else if (field[i][j] == 99) {
                    System.out.print(" # ");  // Hindernis
                }
                // Leeres Feld
                else {
                    System.out.print(" . ");  // Platzhalter für leere Felder
                }
            }
            System.out.println("║");
        }
        System.out.println("╚════════════════════════════════════╝");
    }

}
