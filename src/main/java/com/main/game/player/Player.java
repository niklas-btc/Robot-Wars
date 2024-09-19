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

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public boolean getMoveCommand(char cmd){

        switch (cmd) {
            case 'w':
                moveUp();
                return true;
            case 's':
                moveDown();
                return true;
            case 'a':
                moveLeft();
                return true;
            case 'd':
                moveRight();
                return true;
        }
        return false;
    }


    public void moveLeft(){
        posX--;
        playfield.SetPlayerPos(posY, posX, this.iconIndex);
    }
    public void moveRight(){
        posX++;
        playfield.SetPlayerPos(posY, posX, this.iconIndex);
    }
    public void moveUp(){
        posY--;
        playfield.SetPlayerPos(posY, posX, this.iconIndex);
    }
    public void moveDown(){
        posY++;
        playfield.SetPlayerPos(posY, posX, this.iconIndex);
    }

}
