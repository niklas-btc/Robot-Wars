package com.main.game;
import com.main.general.Printer;

import java.util.Random;

public class Playfield {

    private int field[][] = null;
    private int rows = 0;
    private int cols = 0;
    private Printer printer = new Printer();

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // row = Zeilen, columns = Spalten
    public Playfield(int row, int columns) {
        rows = row;
        cols = columns;
        field = new int[row][columns];
    }


    public boolean SetPlayerPos(int row, int columns, int iconIndex) {
        // Prüfe, on die neue Postion sich innerhalb des Spielfeldes befindet
        if(row < 0 || row >= rows || columns < 0 || columns >= cols) {
            return false;
        }

        // Prüfe, ob das Feld welches der Spieler betreten möchte ein Hindernis ist
        if(field[row][columns] == 99) {
            return false;
        }

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
        return true;
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


    public void LauchBullet(int startRow, int startCol, String direction) {

        boolean hitSomething = false;
        while (!hitSomething) {
            switch (direction) {
                case "up":
                    startRow--;
                    break;
                case "down":
                    startRow++;
                    break;
                case "left":
                    startCol--;
                    break;
                case "right":
                    startCol++;
                    break;
            }
            // Wenn die Position gesetzt werden konnte (return true) hat die Bullet noch nichts getroffen
            hitSomething = !setBulletPos(startRow, startCol);
            try {
                Thread.sleep(200);
            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    private boolean setBulletPos(int row, int columns) {
        // Lösche die alten Kanonenkugeln
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (field[i][j] == 100) {
                    field[i][j] = 0; // Alte Position zurücksetzen
                    break;
                }
            }
        }

        // Prüfe, on die neue Postion sich innerhalb des Spielfeldes befindet, falls nicht hat die Bullet etwas getroffen
        if(row < 0 || row >= rows || columns < 0 || columns >= cols) {
            return false;
        }
        else if(field[row][columns] == 1) {
            field[row][columns] = 4; // Spieler 1 wurde getroffen, Setze Spieler 1 auf sein Schadensmodel
            return false;
        }
        else if(field[row][columns] == 2) {
            field[row][columns] = 5; // Spieler 2 wurde getroffen, Setze Spieler 2 auf sein Schadensmodel
            return false;
        }
        else if(field[row][columns] == 3) {
            field[row][columns] = 6; // Spieler 3 wurde getroffen, Setze Spieler 3 auf sein Schadensmodel
            return false;
        }
        else if(field[row][columns] == 99) {
            field[row][columns] = 991;// Treffe auf das Hindernis
            return false;
        }

        // Die Bullet hat nichts getroffen, zeige sie auf dem Feld an
        field[row][columns] = 100;
        System.out.println("\n\n\n");
        showPlayField();
        return true;

    }


    public void InitializeNewPlayField(){
        Random rand = new Random();
        System.out.println("╔════════════════════════════════════╗");

        for (int i = 0; i < rows; i++) {
            System.out.print("║");
            for (int j = 0; j < cols; j++) {
                // Mit einer Wahrscheinlichkeit von 5% stelle ein hindernisfeld da
                int n = rand.nextInt(100);
                if(n < 8) {
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
                    printer.print(" Ω ", "yellow");  // Spieler 1
                } else if (field[i][j] == 2) {
                    printer.print(" § ", "cyan"); // Spieler 2
                } else if (field[i][j] == 3) {
                    printer.print(" € ", "magenta"); // Spieler 3
                }
                // Prüfe, ob Spieler in ihrem Schadesmodel angezeigt werden sollen
                else if (field[i][j] == 4) {
                    printer.print(" Ω ", "red");  // Spieler 1
                    field[i][j] = 1; // Setze von SchadensModell wieder auf normal
                } else if (field[i][j] == 5) {
                    printer.print(" § ", "red"); // Spieler 2
                    field[i][j] = 2; // Setze von SchadensModell wieder auf normal
                } else if (field[i][j] == 6) {
                    printer.print(" € ", "red"); // Spieler 3
                    field[i][j] = 3; // Setze von SchadensModell wieder auf normal
                }
                // Prüfe auf Hindernis
                else if (field[i][j] == 99) {
                    printer.print(" # ");  // Hindernis
                }
                else if (field[i][j] == 991) {
                    printer.print(" # ", "red");  // Hindernis als Schadensmodell
                    field[i][j] = 99;
                }
                // Prüfe auf Kanonenkugel
                else if (field[i][j] == 100) {
                    printer.print(" * ", "red");  // Kanonenkugel
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
