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
import java.util.LinkedList;

public class Oope2HT {
    public static void main(String[] args) {
        System.out.println("Welcome to L.O.T.");

        /* annetaan error, jos komentoriviparametreja on väärä määrä
        if (args.length == 0 || args.length > 2) {
            System.out.println("Invalid command-line argument!\nProgram terminated.");
        } */

        Vitsi vitsi = new Vitsi(1, "misc", "A SQL query goes into a bar, walks up to two tables and asks, \"Can I join you?\"");

        System.out.println(vitsi);
        LocalDate date = LocalDate.of(2018, 11, 11);
        Uutinen news = new Uutinen(2, date, "Nothing sucks like a Hoover.");

        System.out.println(news);

        System.out.println(vitsi.equals(news)); 
        System.out.println(vitsi.equals(vitsi));

        System.out.println();
        LinkedList<String> lista1 = new LinkedList<String>();
        lista1.add("nothing");
        lista1.add("a");
        String poistettavat1 = ".";
        news.siivoa(lista1, poistettavat1);
        System.out.println(news + "!");

        LinkedList<String> lista2 = new LinkedList<String>();
        lista2.add("a");
        lista2.add("into");
        lista2.add("up");
        lista2.add("to");
        lista2.add("two");
        lista2.add("can");
        lista2.add("i");
        lista2.add("you");
        lista2.add("and");
        String poistettavat2 = ",.:?\"\'";
        vitsi.siivoa(lista2, poistettavat2);
        System.out.println(vitsi + "!");

    }
}