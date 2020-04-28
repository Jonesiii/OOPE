package harjoitustyo.kokoelma;
import java.io.FileNotFoundException;

/**
 * Konkreettinen luokka dokumenttine hallinnointiin.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */
import harjoitustyo.apulaiset.*;
import harjoitustyo.omalista.*;
import harjoitustyo.dokumentit.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;

public class Kokoelma implements Kokoava<Dokumentti>{
    /*
     * Attribuutit.
     * 
     */

    /** Dokumentit listana. */
    private OmaLista<Dokumentti> dokumentit;

    /** Tiedoston polku, joka luetaan ohjelman käynnistyessä */
    private String tiedostoPolku;
    /*
     * Rakentaja
     * 
     */

    public Kokoelma() {
        dokumentit = new OmaLista<Dokumentti>();
    }
    /*
     * Aksessorit.
     * 
     */

    public String tiedostoPolku() {
        return tiedostoPolku;
    }
    
    public void tiedostoPolku(String uusiTiedosto) {
        if (uusiTiedosto != null) {
            tiedostoPolku = uusiTiedosto;
        }
    }

    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }
    public void dokumentit(OmaLista<Dokumentti> uusiLista) {
        if (uusiLista != null) {
            dokumentit = uusiLista;
        }
    }


    /*
     * Luokan omat metodit.
     * 
     */

    /**
    * Lukee saamansa tekstitiedoston ja lisää sen sisällön kokoelmaan joko
    * vitsiksi tai uutiseksi.
    * <p>
    * Tekstistä päätellään onko se vitsi vai uutinen EROTTIMIEN välissä olevaa
    * merkkijonoa tarkastelemalla.
    *
    * @param tiedosto komentoriviparametrina saatu tekstitiedosto.
    */
    public Kokoelma lisääKokoelmaan(String tiedostonNimi) {
        // Esitellään viite try-lohkon ulkopuolella, jotta voidaan sulkea lukija
        // tarvittaessa virheen tapahtuessa.
        Scanner tiedostonLukija = null;

        // luodaan kokoelma
        Kokoelma korpus = new Kokoelma();
        // tallennetaan tiedostopolku reset-komentoa varten
        korpus.tiedostoPolku(tiedostonNimi);

        // Varaudutaan poikeukseen.
        try {
            // Avataan tiedosto.
            File tiedosto = new File(tiedostonNimi);

            // Liitetään lukija tiedostoon.
            tiedostonLukija = new Scanner(tiedosto);

            // Luetaan tiedostosta
            while (tiedostonLukija.hasNextLine()) {
                // Luetaan rivi tiedostosta.
                String rivi = tiedostonLukija.nextLine();

                //tarkastetaan onko kyseessä uutinen vai vitsi
                String[] osat = rivi.split(Dokumentti.EROTIN);
                // lisätään vitsi
                if (Character.isLetter(osat[1].charAt(0))) {
                    Vitsi vitsi = new Vitsi(Integer.parseInt(osat[0]), osat[1], osat[2]);
                    korpus.lisää(vitsi);
                }
                // lisätään uutinen
                else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
                    Uutinen uutinen = new Uutinen(Integer.parseInt(osat[0]), LocalDate.parse(osat[1], formatter), osat[2]);
                    korpus.lisää(uutinen);
                }
            }
        }
        // otetaan file not found kiinni
        catch (FileNotFoundException e) {
            System.out.println("Missing file!\nProgram terminated.");
            return null;
        }
        tiedostonLukija.close();
        return korpus;
    }

    /**
    * Lukee saamansa tekstitiedoston ja lisää sen sisällön sulkulistaan.
    * <p>
    * Tekstistä päätellään onko se vitsi vai uutinen EROTTIMIEN välissä olevaa
    * merkkijonoa tarka
    *
    * @param tiedosto komentoriviparametrina saatu tekstitiedosto.
    */
    public LinkedList<String> luoSulkulista(String tiedostonNimi) {
        // Esitellään viite try-lohkon ulkopuolella, jotta voidaan sulkea lukija
        // tarvittaessa virheen tapahtuessa.
        Scanner tiedostonLukija = null;

        // luodaan linkitetty lista
        LinkedList<String> lista = new LinkedList<String>();

        // Varaudutaan poikeukseen.
        try {
            // Avataan tiedosto.
            File tiedosto = new File(tiedostonNimi);

            // Liitetään lukija tiedostoon.
            tiedostonLukija = new Scanner(tiedosto);

            // Luetaan tiedostosta
            while (tiedostonLukija.hasNextLine()) {
                // Luetaan rivi tiedostosta.
                String rivi = tiedostonLukija.nextLine();
                lista.addLast(rivi);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Missing file!\nProgram terminated.");
            return null;
        }
        return lista;
    }
    /**
    * Luo vitsin tai uutisen riippuen onko kokoelmassa vitsejä vai uutisia.
    * <p>
    * Metodi selvittää ensin, onko syöte oikeanlainen ja tarkistaa samalla,
    * ettei annetulla numerolla ole jo lisätty dokumenttia. Seuraavaksi se
    * päättelee onko kokoelmassa uutisia vai vitsejä ja luo päättelyn
    * perusteella uuden vitsin tai uutisen.
    *
    * @param dokkari käyttäjän antama merkkijono
    * @return vitsi tai uutinen
    */
    public Dokumentti luoDokumentti(String dokkari) {
        // jaetaan merkkijono osiin
        String[] osat = dokkari.split(Dokumentti.EROTIN);
        if (osat.length != 3 || hae(Integer.parseInt(osat[0])) != null) {
            return null;
        }
        // tarkastetaan onko kokoelmassa vitsejä vai uutisia ja luodaan sen perusteella 
        // uusi vitsi/uutinen
        else { 
            if (dokumentit().getFirst().getClass() == Vitsi.class
            && Character.isLetter(osat[1].charAt(0))) {
                Vitsi vitsi = new Vitsi(Integer.parseInt(osat[0]), osat[1], osat[2]);
                return vitsi;
            }
            else if (dokumentit().getFirst().getClass() == Uutinen.class
            && Character.isDigit(osat[1].charAt(0))) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
                Uutinen uutinen = new Uutinen(Integer.parseInt(osat[0]), LocalDate.parse(osat[1], formatter), osat[2]);
                return uutinen;
            }
            else {
                return null;
            }
        }
    }

    /**
    * Luo merkkijonon kaikista kokoelman dokumenteista.
    * <p>
    * Merkkijonossa dokumentit erotellaan toisistaan rivinvaihdolla.
    *
    * @return kaikkien dokumenttien merkkijonoesitys
    */
    public String luoTuloste() {
        String tuloste = "";
        for (int i = 0; i < dokumentit.size(); i++) {
            // vika rivi ilman rivinvaihtoa
            if (i == dokumentit.size() - 1) {
                tuloste = tuloste.concat(dokumentit.get(i).toString());
            }
            else {
                tuloste = tuloste.concat(dokumentit.get(i).toString()+"\n");
            }
        }
        return tuloste;
    }

    /*
     * Kokoava-rajapinnan metodien korvaukset.
     * 
     */

    /**
    * Lisää kokoelmaan käyttöliittymän kautta annetun dokumentin.
    * <p>
    * Metodi kutsuu oman listan lisää-metodia kokoelman dokumentit-attribuutin
    * kautta, jotta uusi dokumentti saadaan lisätyksi oikeaan paikkaan listalla.
    * <p>
    * Lisäys onnistuu, jos parametri liittyy dokumenttiin, jota voidaan vertailla 
    * Comparable-rajapinnan compareTo-metodilla ja jos kokoelmassa ei ole vielä
    * Dokumentti-luokan equals-metodilla mitaten samanlaista dokumenttia.
    * Null-arvon lisäys epäonnistuu aina.
    *
    * @param uusi viite lisättävään dokumenttiin.
    * @throws IllegalArgumentException jos dokumentin vertailu Comparable-rajapintaa
    * käyttäen ei ole mahdollista, listalla on jo equals-metodin mukaan sama
    * dokumentti eli dokumentti, jonka tunniste on sama kuin uuden dokumentin
    * tai parametri on null.
    * @see Ooperoiva
    */
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        // tarkastetaan ettei dokumenttia ole jo lisätty
        for (int i = 0; i < dokumentit.size(); i++) {
            if (dokumentit.get(i).equals(uusi)) {
                throw new IllegalArgumentException();
            }
        }
        // muut poikkeukset tarkastetaan OmaListan lisää-metodin kautta, joten voidaan kutsua sitä
        dokumentit.lisää(uusi);
    }

   /**
    * Hakee kokoelmasta dokumenttia, jonka tunniste on sama kuin parametrin
    * arvo.
    * <p>
    * Tästä metodista on paljon hyötyä Kokoelma-luokassa, koska moni ohjelman
    * komennoista yksilöi dokumentin sen tunnisteen perusteella.
    *
    * @param tunniste haettavan dokumentin tunniste.
    * @return viite löydettyyn dokumenttiin. Paluuarvo on null, jos haettavaa
    * dokumenttia ei löydetty.
    */
    public Dokumentti hae(int tunniste) {
        for (int i = 0; i < dokumentit.size(); i++) {
            if (dokumentit.get(i).tunniste() == tunniste) {
                return dokumentit.get(i);
            }
        } 
        return null;
    }
}