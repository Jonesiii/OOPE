package harjoitustyo.kokoelma;
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

public class Kokoelma implements Kokoava<Dokumentti>{
    /*
     * Attribuutit.
     * 
     */

    /** Dokumentit listana. */
    private OmaLista<Dokumentti> dokumentit;

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

    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
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