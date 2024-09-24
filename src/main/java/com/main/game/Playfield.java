package com.main.game;
import com.main.general.Printer;

import java.util.Random;

public class Playfield {

    private int field[][] = null;
    private int rows = 0;
    private int cols = 0;
    private Printer printer = new Printer();

    private int playerThatIsHit = -1; // Wenn ein Spieler getroffen wurde, wird hier sein index eingetragen.

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getPlayerThatIsHit() {
        return playerThatIsHit;
    }
    public void setPlayerThatIsHit(int playerThatIsHit) {
        this.playerThatIsHit = playerThatIsHit;
    }

    public void SetBlankField(int row, int column){
        field[row][column] = 0;
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
        else if(field[row][columns] == 1 || field[row][columns] == 2 || field[row][columns] == 3) {
            int Player_index = field[row][columns];
            setPlayerThatIsHit(Player_index-1);
            hitAnimation(row, columns); // Stelle Treffer-Animation da 💥
            field[row][columns] = Player_index; // zeige wieder drn ursprünglichen Charakter da
            return false;
        }
        else if(field[row][columns] == 99) {
            hitAnimation(row, columns);  // Stelle Treffer-Animation da 💥
            field[row][columns] = 991;// Treffe auf das Hindernis, stelle getroffenes Icon da 🌋
            return false;
        }
        else if(field[row][columns] == 991) {
            hitAnimation(row, columns);
            field[row][columns] = 0;// Zerstöre das hindernis
            return false;
        }

        // Die Bullet hat nichts getroffen, zeige sie auf dem Feld an
        field[row][columns] = 100;
        System.out.println("\n\n\n");
        showPlayField();
        return true;

    }

    // Stelle für 0.5s 💥 auf dem Spieler da
    public void hitAnimation(int row, int column){
        field[row][column] = 992;
        showPlayField();
        try {
            Thread.sleep(300);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void InitializeNewPlayField(){
        Random rand = new Random();
        System.out.println("╔════════════════════════════════════════════════╗");

        for (int i = 0; i < rows; i++) {
            System.out.print("║");
            for (int j = 0; j < cols; j++) {
                // Mit einer Wahrscheinlichkeit von 8 % stelle ein Hindernisfeld (🗻) da
                int n = rand.nextInt(100);
                if(n < 8) {
                    field[i][j] = 99; // 99 = Hindernis index, 1,2,3 = Spieler Index, 0 = leer
                    System.out.print(" \uD83D\uDDFB ");  // Hindernis
                    continue;
                }
                System.out.print("   ");  // Platzhalter für leere Felder

            }
            System.out.println("║");
        }
        System.out.println("╚════════════════════════════════════════════════╝");
    }

    public void showPlayField() {
        System.out.println("╔════════════════════════════════════════════════╗");
        for (int i = 0; i < rows; i++) {
            System.out.print("║");
            for (int j = 0; j < cols; j++) {
                // Prüfe auf Spieler Position
                if (field[i][j] == 1) {
                    printer.print(" ✈\uFE0F ", "yellow");  // Spieler 1
                } else if (field[i][j] == 2) {
                    printer.print(" \uD83D\uDEE9\uFE0F ", "cyan"); // Spieler 2
                } else if (field[i][j] == 3) {
                    printer.print(" \uD83D\uDE81 ", "magenta"); // Spieler 3
                }
                // Prüfe auf Hindernis
                else if (field[i][j] == 99) {
                    printer.print(" \uD83D\uDDFB ");  // Hindernis
                }
                else if (field[i][j] == 991) {
                    printer.print(" \uD83C\uDF0B ", "red");  // Hindernis als Schadensmodell
                }
                else if (field[i][j] == 992) {
                    printer.print(" \uD83D\uDCA5 ", "red");  // Explosions Animationsicon 💥
                }
                // Prüfe auf Kanonenkugel
                else if (field[i][j] == 100) {
                    printer.print(" \uD83D\uDE80 ", "red");  // Kanonenkugel
                }
                // Leeres Feld
                else {
                    System.out.print("   ");  // Platzhalter für leere Felder
                }
            }
            System.out.println("║");
        }
        System.out.println("╚════════════════════════════════════════════════╝");
    }


}
