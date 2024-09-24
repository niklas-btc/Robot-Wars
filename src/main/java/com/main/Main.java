package com.main;
import com.main.game.player.Player;
import com.main.general.Printer;
import com.main.game.GameController;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();


        printer.println_bold("\n ---    Herzlich Willkommen bei Robot-Wars    ---\n" ,"green");

        // Erstelle eine Hashmap mit Spiel konfigurationseinstellungen
        Map<String, Integer> settings = new HashMap<>();
        settings.put("rows", 10);
        settings.put("cols", 16);
        settings.put("roundLimit", 20);

        // Controller Objekt erzeugen und Spiel initialisieren
        GameController controller = new GameController(settings);
        controller.initializeNewGame();
        printer.println_bold("\n ---    Spielfeld erfolgreich Initialisiert   ---" ,"green");

        String[] playerIcons = new String[]{"✈\uFE0F", "\uD83D\uDEE9\uFE0F", "\uD83D\uDE81"};

        // Spieler konfigurationen treffen
        boolean morePlayers = true;
        while(morePlayers) {
            printer.print("\nTrage den Namen des " + (controller.players.size()+1) + ". Spielers ein: ");
            String playerName = scanner.nextLine();
            controller.addNewPlayer(playerName);
            printer.println_bold( "--- " + playerName + " wurde als Spieler mit der Figur " + playerIcons[controller.players.size()-1] + " hinzugefügt ---", "green");

            // Maximal 3 Spieler erlaubt
            if(controller.players.size() < 3) {
                printer.print("Möchtest du einen weiteren Spieler hinzufügen? Ja/Nein: ", "yellow");
                if(Objects.equals(scanner.nextLine().toLowerCase(), "ja")) {
                    morePlayers = true;
                }else morePlayers = false;
            }else {
                printer.println("");
                printer.println_bold("--- Maximale Spieler Anzahl erreicht ---", "orange");
                morePlayers = false;
            }
        }

        // Spieler konfiguration abgeschlossen
        printer.print_bold("\nSpiel konfigurationen abgeschlossen. Starte das Spiel mit Enter", "green");
        scanner.nextLine();


        // Starte ein neues Spiel
        controller.startNewGame();
        boolean gameOver = false;

        while (controller.getRound() < controller.getRoundLimit()+1 && !gameOver) {

            // Durchlaufe alle Spieler in der aktuellen Runde
            while (controller.getActivePlayerIndex() < controller.players.size()) {

                // erhalte das aktuelle Spieler Objekt
                Player player = controller.players.get(controller.getActivePlayerIndex());

                // Lade das aktuelle Spielfeld
                controller.getPlayfield();

                // Lese die validierte Benutzereingabe
                boolean gultycmd = false;
                while(!gultycmd) {
                    printer.println_bold("Move : W = Up │ S = Down │ A = Left │ D = Right", "blue");
                    printer.println_bold("Shoot: 1 = Up │ 2 = Down │ 3 = Left │ 4 = Right", "magenta");
                    printer.print("Eingabe: ");
                    String userInput = scanner.next();
                    // Verarbeite den Zug des Spielers und stelle ihn, wenn gültig da
                    String errorMsg = player.checkAndSetValidMoveCommand(userInput.charAt(0));
                    if(errorMsg == null) {
                        gultycmd = true;
                    }else {
                        controller.getPlayfield();
                        printer.println_bold("--- Fehlermeldung: " + errorMsg + " ---", "red");
                    };
                }

                // Prüfe, ob ein Spieler getroffen wurde (-1 = Kein Spieler getroffen)
                int playerThatIsHit = controller.playfield.getPlayerThatIsHit();
                if(playerThatIsHit != -1) {
                    controller.players.get(playerThatIsHit).getDmg(10);
                    controller.playfield.setPlayerThatIsHit(-1);
                    if(controller.players.get(playerThatIsHit).getHealth() <= 0) {
                        controller.playfield.SetBlankField(controller.players.get(playerThatIsHit).getPosY(), controller.players.get(playerThatIsHit).getPosX());
                        controller.players.remove(playerThatIsHit);
                    }
                    // prüfen, ob nur noch ein Spieler existiert, falls ja, hat dieser gewonnen
                    if(controller.players.size() <= 1) {
                        gameOver = true;
                        break;
                    }
                }

                // Spieler hat seinen Zug beendet, springe zum nächsten
                controller.NextPlayer();
            }

            // Beende die aktuelle Runde
            scanner.nextLine();
            controller.nextRound();
        }

        // Wenn gameOver true ist, dann wurde das Spiel beendet, indem einer der Spieler gewonnen hat, falls nicht, ist es ein RoundOut Ende
        if(gameOver) {
            String activePlayerName = controller.players.get(controller.getActivePlayerIndex()).getName();
            printer.print_bold("\nSpiel beendet: " + activePlayerName + " hat gewonnen!", "green");
        }else{
            printer.print_bold("\nMaximale Rundenanzahl erreicht, das Spiel wurde beendet!", "red");
        }
    }
}