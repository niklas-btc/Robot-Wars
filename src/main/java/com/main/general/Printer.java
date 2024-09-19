package com.main.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Printer {


    // Map zur Speicherung von Farbcodes
    Map<String, String> colors = new HashMap<String, String>();

    // Konstruktor
    public Printer() {
        colors.put("red", "\033[31m");
        colors.put("green", "\033[32m");
        colors.put("yellow", "\033[33m");
        colors.put("blue", "\033[34m");
        colors.put("magenta", "\033[35m");
        colors.put("cyan", "\033[36m");
        colors.put("reset", "\033[0m");
        colors.put("orange", "\033[38;5;214m");
    }

    // Methode für fetten Text mit Farbcode
    public void print_bold(String message, String color) {
        String colorCode = colors.getOrDefault(color.toLowerCase(), colors.get("reset"));
        System.out.print(colorCode + "\033[1m" + message + colors.get("reset"));
    }

    // Überladung für fetten Text ohne Farbcode
    public void print_bold(String message) {
        print_bold(message, "reset"); // Standardfarbe verwenden
    }

    // Methode für normalen Text mit Farbcode
    public void print(String message, String color) {
        String colorCode = colors.getOrDefault(color.toLowerCase(), colors.get("reset"));
        System.out.print(colorCode + message + colors.get("reset"));
    }

    // Überladung für normalen Text ohne Farbcode
    public void print(String message) {
        print(message, "reset"); // Standardfarbe verwenden
    }

    // Methode für fetten Text mit Zeilenumbruch und Farbcode
    public void println_bold(String message, String color) {
        String colorCode = colors.getOrDefault(color.toLowerCase(), colors.get("reset"));
        System.out.println(colorCode + "\033[1m" + message + colors.get("reset"));
    }

    // Überladung für fetten Text mit Zeilenumbruch ohne Farbcode
    public void println_bold(String message) {
        println_bold(message, "reset"); // Standardfarbe verwenden
    }

    // Methode für normalen Text mit Zeilenumbruch und Farbcode
    public void println(String message, String color) {
        String colorCode = colors.getOrDefault(color.toLowerCase(), colors.get("reset"));
        System.out.println(colorCode + message + colors.get("reset"));
    }

    // Überladung für normalen Text mit Zeilenumbruch ohne Farbcode
    public void println(String message) {
        println(message, "reset"); // Standardfarbe verwenden
    }

}
