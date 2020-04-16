/**
 * Ohjelman ajoluokka.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */

import java.util.Scanner;
import harjoitustyo.dokumentit.*;
import java.time.LocalDate;

public class Oope2HT {
    public static void main(String[] args) {
        System.out.println("Welcome to L.O.T.");

        /* annetaan error, jos komentoriviparametreja on väärä määrä
        if (args.length == 0 || args.length > 2) {
            System.out.println("Invalid command-line argument!\nProgram terminated.");
        } */

        Vitsi vitsi = new Vitsi(1, "misc", "hehee");

        System.out.println(vitsi);
        LocalDate date = LocalDate.of(2018, 11, 11);
        Uutinen news = new Uutinen(2, date, "Nämä 7 tapaa tuhoavat parisuhteesi");

        System.out.println(news);

        System.out.println(vitsi.equals(news)); 
        System.out.println(vitsi.equals(vitsi));
    }
}