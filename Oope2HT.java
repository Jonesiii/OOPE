/**
 * Ohjelman ajoluokka.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */



import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.*;
import harjoitustyo.kokoelma.*;
import harjoitustyo.kayttoliittyma.*;
import java.util.LinkedList;


public class Oope2HT {
    public static void main(String[] args) {
        System.out.println("Welcome to L.O.T.");
        
        // luodaan errorilmoitus, jos annetaan väärä määrä parametreja komentorivillä
        if (args.length != 2) {
            System.out.println("Wrong number of command-line arguments!\nProgram terminated.");
        }
        // luodaan kometoriviparametreista dokumentit ja sulkusanalista ja käynnistetään
        // käyttöliittymän silmukka
        else {
            String lisättävät = args[0];
            String sulkusanat = args[1];
            Kokoelma korpus = new Kokoelma();
            LinkedList<String> sulkulista = korpus.luoSulkulista(sulkusanat);

            // Kutsutaan käyttöliittymää pyörittävää metodia
            if (korpus != null && sulkulista != null) {
                Kayttoliittyma käyttis = new Kayttoliittyma(korpus.lisääKokoelmaan(lisättävät), sulkulista);
                käyttis.pääsilmukka();
            }
        }
    }
}