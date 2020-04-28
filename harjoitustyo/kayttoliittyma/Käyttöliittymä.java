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
public class Käyttöliittymä {
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

    public Käyttöliittymä(Kokoelma uusiKorpus, LinkedList<String> uusiSulkulista) {
        korpus(uusiKorpus);
        sulkulista(uusiSulkulista);
    }

    /*
     * Aksessorit.
     * 
     */

    /** Lukeva aksessori - korpus. */
    public Kokoelma korpus() {
        return korpus;
    }
    /** Asettava aksessori - korpus. */
    public void korpus(Kokoelma uusiKorpus) {
        if (uusiKorpus != null) {
            korpus = uusiKorpus;
        }
    }

    /** Lukeva aksessori - sulkulista. */
    public LinkedList<String> sulkulista() {
        return sulkulista;
    }
    /** Asettava aksessori - sulkulista. */
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
        // kopioidaan kokoelman omalista, jotta reset-komennon yhteydessä muokattua
        // ja alkuperäistä kokoelmaa voidaan verrata keskenään
        boolean pyörii = true;
        boolean kaiutus = false;
        Scanner lukija = new Scanner(System.in);
        while (pyörii) {
            System.out.println("Please, enter a command:");
            String komento1 = lukija.nextLine();
            if (kaiutus == true) {
                System.out.println(komento1);
            }
            // jaetaan komento kahteen osaan; itse komentoon ja sen parametriin
            String[] komento = komento1.split(" ", 2);

            // quit-komento
            if (komento[0].equals(LOPETA)) {
                System.out.println("Program terminated.");
                lukija.close();
                pyörii = false;
            }
            // tulosta-komento
            else if (komento[0].equals(TULOSTA)) {
                try {
                    // print-komennolla saa olla vain 1 parametri, joka voi olla vain kokonaisluku
                    if (komento.length == 2 && komento[1].split(" ").length == 1){
                        Dokumentti tuloste = korpus.hae(Integer.parseInt(komento[1]));
                        if (tuloste == null) {
                            System.out.println("Error!");
                        }
                        else {
                            System.out.println(tuloste);
                        }
                    }
                    else if (komento.length == 1) {
                        System.out.println(korpus.luoTuloste());
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
            // lisää-komento
            else if (komento[0].equals(LISAA)) {
                // jaetaan komennon loppuosa erottimella osiin
                String[] osat = komento[1].split(Dokumentti.EROTIN);

                // haetaan ensimmäinen kokoelmassa oleva alkio, jotta voidaan myöhemmin 
                // päätellä onko kokoelmassa uutisia vai vitsejä
                Dokumentti eka = korpus.dokumentit().getFirst();
                
                // huomioidaan, että vain yhden vitsin tai uutisen voi lisätä kerrallaan, eikä
                // tunniste saa olla sama kuin jo kokoelmassa olevalla dokumentilla
                if (osat.length != 3 || korpus.hae(Integer.parseInt(osat[0])) != null) {
                    System.out.println("Error!");
                }
                else if (eka.getClass() == Vitsi.class && Character.isLetter(osat[1].charAt(0))) {
                    Vitsi vitsi = new Vitsi(Integer.parseInt(osat[0]), osat[1], osat[2]);
                    korpus.lisää(vitsi);
                }
                else if (eka.getClass() == Uutinen.class && Character.isDigit(osat[1].charAt(0))) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
                    Uutinen uutinen = new Uutinen(Integer.parseInt(osat[0]), LocalDate.parse(osat[1], formatter), osat[2]);
                    korpus.lisää(uutinen);
                }
                else {
                    System.out.println("Error!");
                }
            }
            // hae-komento
            else if (komento[0].equals(HAE)) {
                // luodaan lista komennon parametreista, jotta se voidaan 
                // antaa parametrina dokumentti-luokan sanatTäsmäävät metodille
                LinkedList<String> hakusanat = new LinkedList<String>(Arrays.asList(komento[1].split(" ")));
                for (int i = 0; i < korpus.dokumentit().size(); i++) {
                    if (korpus.dokumentit().get(i).sanatTäsmäävät(hakusanat) == true) {
                        System.out.println(korpus.dokumentit().get(i).tunniste());
                    }
                }
            }
            // poista-komento
            else if (komento[0].equals(POISTA)) {
                // Haetaan komennossa annetulla parametrilla kokoelmasta.
                // Jos parametri on virheellinen, metodi palauttaa null-arvon.
                Dokumentti poistettava = korpus.hae(Integer.parseInt(komento[1]));
                if (poistettava == null) {
                    System.out.println("Error!");
                }
                else {
                    korpus.dokumentit().remove(Integer.parseInt(komento[1]) - 1);
                }
            }
            // siivoa-komento
            else if (komento[0].equals(SIIVOA)) {
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
            // reset-komento
            else if (komento[0].equals(PERU)) {
                // Lukee kokoelman luomisen yhteydessä tallennetun tiedostonimen kautta
                // tiedoston uudelleen ja korvaa vanhan dokumentit-attribuutin uudella
                korpus.dokumentit(korpus.lisääKokoelmaan(korpus.tiedostoPolku()).dokumentit());
            }
            // kaiuta-komento
            else if (komento[0].equals(KAIUTA)) {
                if (kaiutus == false) {
                    System.out.println(komento1);
                    kaiutus = true;
                }
                else {
                    kaiutus = false;
                }
            }
            // jos komento kirjoitetaan väärin
            else {
                System.out.println("Error!");
            }
        }
    }
}