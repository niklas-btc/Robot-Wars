package com.main.managment;

import com.main.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameController {

    public Playfield playfield;
    public List<Player> players = new ArrayList<Player>();

    public GameController(Map<String, Integer> settings) {

        int rows = settings.get("rows");
        int columns = settings.get("cols");
        this.playfield = new Playfield(rows, columns);
    }


    public void initializeNewGame(){
        playfield.InitializeNewPlayField();
    }


    public void addNewPlayer(String playerName) {
        Player player = new Player(playerName, this.playfield);
        players.add(player);
    }

    public List<Player> getAllPlayers() {
        return players;
    }
}
