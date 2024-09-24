package com.main.game.player;

import com.main.game.Playfield;

import java.util.Random;

public class Player {

    // Objekt Eigenschaften
    private Playfield playfield;
    private String name;
    private int iconIndex;

    private int health;

    private int posX;
    private int posY;


    // Konstruktor
    public Player(String name, int iconIndex, Playfield playfield) {
        this.playfield = playfield;

        // Setzte eine zufällige Startposition des Spielers, prüfe vorher auf ein freies Feld
        Random rand = new Random();
        boolean valid = false;
        while(!valid) {
            this.posY = rand.nextInt(playfield.getRows());
            this.posX = rand.nextInt(playfield.getCols());
            valid = playfield.SetPlayerPos(this.posY, this.posX, iconIndex);
        }

        this.health = 20;
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void getDmg(int dmg){
        this.health = health - dmg;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


    public String checkAndSetValidMoveCommand(char cmd){

        return switch (cmd) {
            case 'w' -> moveUp();
            case 's' -> moveDown();
            case 'a' -> moveLeft();
            case 'd' -> moveRight();
            case '1' -> Shoot("up");
            case '2' -> Shoot("down");
            case '3' -> Shoot("left");
            case '4' -> Shoot("right");
            default -> "Ungültige Eingabe";
        };
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

    public String Shoot(String direction){

        playfield.LauchBullet(posY, posX, direction);
        return null;
    }

}
