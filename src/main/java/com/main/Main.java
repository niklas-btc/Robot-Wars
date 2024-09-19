package com.main;
import com.main.database.user.UserRepo;
import com.main.general.Printer;
import com.main.managment.GameController;
import com.main.managment.Playfield;

import java.util.*;

public class Main {

    static UserRepo userRepo = new UserRepo();

    public static void main(String[] args) {
        //List<User> users = userRepo.readAllUsers();
        //for (User user : users) {
            // int UID = user.GetID();
            // String username = user.GetUsername();

            //  Formatierte Ausgabe über String Interpolation
            //System.out.printf("ID: %s | Benutzername: %s %n", UID, username); // %n = Zeilenumbruch, %s = stellt String dar, %S stellt String in UpperCase dar
        //}
        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        // Erstelle eine Hashmap mit Spiel konfigurationseinstellungen
        Map<String, Integer> settings = new HashMap<>();
        settings.put("rows", 10);
        settings.put("cols", 12);

        GameController controller = new GameController(settings);

        controller.initializeNewGame();
        printer.println_bold("--- Das Spiel wurde erfolgreich Initialisiert ---" ,"green");

        // Spieler konfigurationen treffen
        boolean morePlayers = true;
        while(morePlayers) {
            printer.println("");
            printer.print("Trage den Namen des " + (controller.players.size()+1) + ". Spielers ein: ");
            String playerName = scanner.nextLine();
            controller.addNewPlayer(playerName);
            printer.println_bold( "--- " + playerName + " wurde als Spieler mit der Figur Ω hinzugefügt ---", "green");

            // Maximal 3 Spieler erlaubt
            if(controller.players.size() < 3) {
                printer.print("Möchtest du einen weiteren Spieler hinzufügen? Ja/Nein: ", "yellow");
                if(Objects.equals(scanner.nextLine(), "Ja")) {
                    morePlayers = true;
                }else morePlayers = false;
            }else {
                printer.println("");
                printer.println_bold("--- Maximale Spieler Anzahl erreicht ---", "orange");
                morePlayers = false;
            }
        }
        // Spieler konfiguration abgeschlossen
        printer.println("");
        System.out.print("\033[0;1m" + "Spiel konfigurationen abgeschlossen. Starte das Spiel mit Enter");
        scanner.nextLine();

        controller.playfield.showPlayField();

        scanner.nextLine();
    }
}