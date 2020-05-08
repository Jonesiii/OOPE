package harjoitustyo.kayttoliittyma;
/**
 * Konkreettinen luokka käyttöliittymälle.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */
import harjoitustyo.kokoelma.*;
import harjoitustyo.dokumentit.*;
import harjoitustyo.omalista.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Kayttoliittyma {
    /* 
     * Julkiset luokkavakiot.
     *
     */
    
    /** Tulostuskomento. */
    private static final String TULOSTA = "print";
    /** Lisäyskomento */
    private static final String LISAA = "add";
    /** Hakukomento. */
    private static final String HAE = "find";
    /** Poistamiskomento. */
    private static final String POISTA = "remove";
    /** Esikäsittelykomento */
    private static final String SIIVOA = "polish";
    /** Perumiskomento. */
    private static final String PERU = "reset";
    /** Kaiutuskomento */
    private static final String KAIUTA = "echo";
    /** Lopetuskomento. */
    private static final String LOPETA = "quit";

    /*
     * Attribuutit.
     * 
     */

    /** Vitseistä tai uutisista koostuva kokoelma. */
    private Kokoelma korpus;

    /** Lista sanoista, jotka halutaan sulkea hakutilanteessa pois. */
    private LinkedList<String> sulkulista;

    /*
     * Rakentajat.
     * 
     */

    public Kayttoliittyma(Kokoelma uusiKorpus, LinkedList<String> uusiSulkulista) {
        korpus(uusiKorpus);
        sulkulista(uusiSulkulista);
    }

    /*
     * Aksessorit.
     * 
     */

    public Kokoelma korpus() {
        return korpus;
    }

    public void korpus(Kokoelma uusiKorpus) {
        if (uusiKorpus != null) {
            korpus = uusiKorpus;
        }
    }

    public LinkedList<String> sulkulista() {
        return sulkulista;
    }
    
    public void sulkulista(LinkedList<String> uusiSulkulista) {
        if (uusiSulkulista != null) {
            sulkulista = uusiSulkulista;
        }
    }

    /*
     * Luokan omat metodit.
     * 
     */

    /**
    * Pyörittää käyttöliittymän silmukkaa.
    * <p>
    * Metodi pyörittää silmukkaa, jossa käyttäjä antaa komentoja ohjelmalle. Metodin
    * silmukka pyörii kunnes käyttäjä antaa sille quit-komennon.
    */
    public void pääsilmukka() {
        boolean pyorii = true;
        boolean kaiutus = false;
        // esitellään silmukan ulkopuolella, jottei luoda koko ajan uutta scanneria.
        Scanner lukija = new Scanner(System.in);
        while (pyorii) {
            System.out.println("Please, enter a command:");
            String komento1 = lukija.nextLine();
            if (kaiutus == true) {
                System.out.println(komento1);
            }
            // jaetaan komento kahteen osaan; itse komentoon ja sen parametriin
            String[] komento = komento1.split(" ", 2);

            // LOPETA-KOMENTO
            if (komento[0].equals(LOPETA) && komento.length == 1) {
                System.out.println("Program terminated.");
                lukija.close();
                pyorii = false;
            }
            // TULOSTA-KOMENTO
            else if (komento[0].equals(TULOSTA) && komento.length <= 2) {
                try {
                    // print-komennolla saa olla vain 1 parametri, jota ei saa jakaa osiin
                    if (komento.length == 2){
                        Dokumentti tuloste = korpus.hae(Integer.parseInt(komento[1]));
                        if (tuloste == null) {
                            System.out.println("Error!");
                        }
                        else {
                            System.out.println(tuloste);
                        }
                    }
                    else if (komento.length == 1) {
                        String tuloste = korpus.luoTuloste();
                        // tulostetaan vain, jos kokoelmassa on tulostettavaa
                        if (tuloste != "") {
                            System.out.println(tuloste);
                        }
                    }
                    else {
                        System.out.println("Error!");
                    }
                }
                // jos käyttäjä yrittää syöttää numeron tilalla muuta merkkiä print-komennon
                // parametriksi
                catch (NumberFormatException e) {
                    System.out.println("Error!");
                }
            }
            // LISÄÄ-KOMENTO
            else if (komento[0].equals(LISAA) && komento.length == 2) {
                if (korpus.luoDokumentti(komento[1]) != null) {
                    korpus.lisää(korpus.luoDokumentti(komento[1]));
                }
                else {
                    System.out.println("Error!");
                }
            }
            // HAE-KOMENTO
            else if (komento[0].equals(HAE) && komento.length >= 2) {
                // luodaan lista komennon parametreista, jotta se voidaan 
                // antaa parametrina dokumentti-luokan sanatTäsmäävät metodille
                LinkedList<String> hakusanat = new LinkedList<String>(Arrays.asList(komento[1].split(" ")));
                for (int i = 0; i < korpus.dokumentit().size(); i++) {
                    if (korpus.dokumentit().get(i).sanatTäsmäävät(hakusanat) == true) {
                        System.out.println(korpus.dokumentit().get(i).tunniste());
                    }
                }
            }
            // POISTA-KOMENTO
            else if (komento[0].equals(POISTA) && komento.length == 2) {
                // huomioidaan, että parametreja voi olla vain yksi
                LinkedList<String> parametrit = new LinkedList<String>(Arrays.asList(komento[1].split(" ")));
                if (parametrit.size() != 1) {
                    System.out.println("Error!");
                }
                else {
                    // Haetaan komennossa annetulla parametrilla kokoelmasta.
                    // Jos parametri on virheellinen, metodi palauttaa null-arvon.
                    try {
                        Dokumentti poistettava = korpus.hae(Integer.parseInt(komento[1]));
                        if (poistettava == null) {
                            System.out.println("Error!");
                        }
                        else {
                            // etsitään poistettava
                            for (int i = 0; i < korpus.dokumentit().size(); i++) {
                                if (korpus.dokumentit().get(i).equals(poistettava)) {
                                    korpus.dokumentit().remove(i);
                                }
                            }
                        }
                    }
                    catch (NumberFormatException e) {
                        System.out.println("Error!");
                    }
                }
            }
            // SIIVOA-KOMENTO
            else if (komento[0].equals(SIIVOA) && komento.length == 2) {
                // huomioidaan, että parametreja voi olla vain yksi
                LinkedList<String> parametrit = new LinkedList<String>(Arrays.asList(komento[1].split(" ")));
                if (parametrit.size() != 1) {
                    System.out.println("Error!");
                }
                else {
                    // Iteroidaan kokoelman läpi hyödyntäen jokaisen dokumentin kohdalla
                    // Dokumentti-luokan siivoa-metodia.
                    for (int i = 0; i < korpus.dokumentit().size(); i++) {
                        // Koska siivoa metodi muokkaa saamaansa sulkusanalistaa,
                        // luodaan listasta kopio, jotta metodi saa aina parametrina
                        // alkuperäisen listan
                        LinkedList<String> iterointilista = new LinkedList<String>();
                        iterointilista = (LinkedList<String>) sulkulista.clone();
                        korpus.dokumentit().get(i).siivoa(iterointilista, komento[1]);
                    }
                }
            }
            // RESET-KOMENTO
            else if (komento[0].equals(PERU) && komento.length == 1) {
                // Lukee kokoelman luomisen yhteydessä tallennetun tiedostonimen kautta
                // tiedoston uudelleen ja korvaa vanhan dokumentit-attribuutin uudella
                korpus.dokumentit(korpus.lisääKokoelmaan(korpus.tiedostoPolku()).dokumentit());
            }
            // KAIUTA-KOMENTO
            else if (komento[0].equals(KAIUTA) && komento.length == 1) {
                if (kaiutus == false) {
                    System.out.println(komento1);
                    kaiutus = true;
                }
                else {
                    kaiutus = false;
                }
            }
            // jos komento kirjoitetaan väärin tai annetaan väärä määrä parametreja
            else {
                System.out.println("Error!");
            }
        }
    }
}