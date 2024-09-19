package com.main.game.player;

import com.main.game.Playfield;

public class Player {

    // Objekt Eigenschaften
    private Playfield playfield;
    private String name;

    private int posX = 1;
    private int posY = 1;


    // Konstruktor
    public Player(String name, Playfield playfield) {
        this.playfield = playfield;
        playfield.SetPlayerPos(posY, posX); // Setzte die Start Position des Spielers
        this.name = name;
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
        playfield.SetPlayerPos(posY, posX);
    }
    public void moveRight(){
        posX++;
        playfield.SetPlayerPos(posY, posX);
    }
    public void moveUp(){
        posY--;
        playfield.SetPlayerPos(posY, posX);
    }
    public void moveDown(){
        posY++;
        playfield.SetPlayerPos(posY, posX);
    }

}
