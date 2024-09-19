package com.main.database.user;
import com.main.database.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/// Die Repository-Klasse ist also dafür zuständig, die gesamte Datenzugriffslogik zu kapseln und
/// eine klare Schnittstelle für die Interaktion mit der Datenbank bereitzustellen.
/// Sie trennt die Datenlogik von der Geschäftslogik, was die Wartbarkeit, Testbarkeit
/// und Wiederverwendbarkeit des Codes verbessert.
public class UserRepo {

    Driver driver;

    public UserRepo() {
        this.driver = new Driver();
    }

    public List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";

        try (Connection con = driver.getConnection()) {
            ResultSet rs = driver.executeQuery(con, query);

            while (rs.next()) {
                int UID = rs.getInt("UID");
                String username = rs.getString("username");
                users.add(new User(UID, username));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return users;
    }

    public boolean SaveUser(User user) {
        try (Connection con = driver.getConnection()){
            // Füge Benutzer über ein Prepared Statement in die DB ein
            String query = "INSERT INTO user (username, password) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.execute();
            ps.close();
            // Erfolgreich hinzugefügt
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
