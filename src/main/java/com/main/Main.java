package com.main;
import com.main.database.user.UserRepo;
import com.main.game.player.Player;
import com.main.general.Printer;
import com.main.game.GameController;

import java.util.*;

public class Main {

    static UserRepo userRepo = new UserRepo();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();


        // Erstelle eine Hashmap mit Spiel konfigurationseinstellungen
        Map<String, Integer> settings = new HashMap<>();
        settings.put("rows", 10);
        settings.put("cols", 12);
        settings.put("roundLimit", 20);

        // Controller Objekt erzeugen und Spiel initialisieren
        GameController controller = new GameController(settings);
        controller.initializeNewGame();
        printer.println_bold("\n--- Das Spiel wurde erfolgreich Initialisiert ---" ,"green");

        String[] playerIcons = new String[]{"Ω", "§", "€"};

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

        while (controller.getRound() < controller.getRoundLimit()) {

            // Durchlaufe alle Spieler in der aktuellen Runde
            while (controller.getActivePlayerIndex() < controller.players.size()) {

                // erhalte das aktuelle Spieler Objekt
                Player player = controller.players.get(controller.getActivePlayerIndex());

                // Lade das aktuelle Spielfeld
                controller.getPlayfield();

                // Lese die validierte Benutzereingabe
                boolean gultycmd = false;
                while(!gultycmd) {
                    printer.print_bold("(w = up, s = down, a = left, d = right): ", "magenta");
                    String userInput = scanner.next();
                    // Verarbeite den Zug des Spielers
                    if(player.getMoveCommand(userInput.charAt(0))) {
                        gultycmd = true;
                    }else printer.println_bold("Ungültige Eingabe", "red");
                }

                // Spieler hat seinen Zug beendet, springe zum nächsten
                controller.NextPlayer();
            }

            // Beende die aktuelle Runde
            scanner.nextLine();
            controller.nextRound();
        }

        printer.print_bold("\nMaximale Rundenanzahl erreicht, das Spiel wurde beendet!", "orange");
    }
}