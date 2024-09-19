package com.main.database.user;



/// Die User-Klasse dient als Modell für die Benutzer-Entität in der Anwendung.
/// Sie definiert die Struktur der Benutzerdaten und ermöglicht die einfache Erstellung, Bearbeitung und Verwaltung
/// von Benutzerobjekten in Kombination mit der Repository- und Service-Schicht.
public class User {

    public User(int id, String username){
        SetID(id);
        SetUsername(username);
    }

    // Objekt Eigenschaften
    private int UID; // primär Schlüssel
    private String username;

    // Zugriffsmethoden auf Eigenschaften via Getter und Setter
    public int GetID() {
        return UID;
    }
    public void SetID(int id) {
        this.UID = id;
    }

    public String GetUsername() {
        return username;
    }
    public void SetUsername(String name) {
        this.username = name;
    }
}
