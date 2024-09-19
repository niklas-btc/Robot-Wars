package com.main.player;

import com.main.managment.Playfield;

import java.util.Random;

public class Player {

    // Objekt Eigenschaften
    private Playfield playfield;
    private String name;

    private int posX = 1;
    private int posY = 1;


    // Konstruktor
    public Player(String name, Playfield playfield) {
        this.playfield = playfield;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public void moveRight(){
        posX++;
        playfield.SetPlayerPos(posX, posY);
    }

}
