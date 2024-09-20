package com.main.game.player;

import com.main.game.Playfield;

public class Player {

    // Objekt Eigenschaften
    private Playfield playfield;
    private String name;
    private int iconIndex = 0;

    private int posX = 1;
    private int posY = 1;


    // Konstruktor
    public Player(String name, int iconIndex, Playfield playfield) {
        this.playfield = playfield;
        this.posY = iconIndex+1;
        this.posX = iconIndex+1;
        playfield.SetPlayerPos(posY, posX, iconIndex); // Setzte die Start Position des Spielers
        this.name = name;
        this.iconIndex = iconIndex;
    }

    // Getter/Setter Zugriffsmethoden
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public String checkAndSetValidMoveCommand(char cmd){

        switch (cmd) {
            case 'w':
                return moveUp();
            case 's':

                return moveDown();
            case 'a':

                return moveLeft();
            case 'd':
                return moveRight();
        }
        return "Ungültige Eingabe";
    }


    // Aktualisiere die neue Spieler-Koordinate nur, wenn der Zug des Spielers gültig war,
    // also er sich zu einem leeren Feld bewegt hat
    public String moveLeft(){
        int temp_posX = posX-1;
        if(playfield.SetPlayerPos(posY, temp_posX, this.iconIndex)) {
            posX = temp_posX;
            return null;
        }
        return "Ungültiger Zug";
    }
    public String moveRight(){
        int temp_posX = posX+1;
        if(playfield.SetPlayerPos(posY, temp_posX, this.iconIndex)) {
            posX = temp_posX;
            return null;
        }
        return "Ungültiger Zug";
    }
    public String moveUp(){
        int temp_posY = posY-1;
        if(playfield.SetPlayerPos(temp_posY, posX, this.iconIndex)) {
            posY = temp_posY;
            return null;
        }
        return "Ungültiger Zug";
    }
    public String moveDown(){
        int temp_posY = posY+1;
        if(playfield.SetPlayerPos(temp_posY, posX, this.iconIndex)) {
            posY = temp_posY;
            return null;
        }
        return "Ungültiger Zug";
    }

}
