package harjoitustyo.dokumentit;
import harjoitustyo.apulaiset.*;
import java.util.*;
import java.util.stream.*;

/**
 * Abstrakti juuriluokka dokumenteile.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 1.0
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */

public abstract class Dokumentti implements Comparable <Dokumentti>, Tietoinen <Dokumentti> {

    /* 
     * Julkiset luokkavakiot.
     *
     */
    
    /** Tietojen erotin kaikille saatavilla olevana vakiona. */
    public static final String EROTIN = "///";

    /*
     * Attribuutit.
     * 
     */

    /** Dokumentin yksilöivä kokonaisluku. */
    private int tunniste; 

    /** Dokumentin teksti */
    private String teksti;

    /*
     * Rakentaja
     * 
     */

    public Dokumentti(int uusiTunniste, String uusiTeksti) throws IllegalArgumentException {
        tunniste(uusiTunniste);
        teksti(uusiTeksti);
    }

    /*
     * Aksessorit.
     * 
     */

    public int tunniste() {
        return tunniste;
    }

    public void tunniste(int uusiTunniste) throws IllegalArgumentException {
        if (uusiTunniste < 1) {
            throw new IllegalArgumentException();
        }
        else {
            tunniste = uusiTunniste;
        }
    }

    public String teksti() {
        return teksti;
    }

    public void teksti(String uusiTeksti) throws IllegalArgumentException {
        if (uusiTeksti == null || uusiTeksti == "") {
            throw new IllegalArgumentException();
        }
        else {
            teksti = uusiTeksti;
        }
    }

    /*
     * Object-luokan metodien korvaukset.
     * 
     */

    /**
     * Muodostaa dokumentin merkkijonoesityksen, joka koostuu tunnisteesta
     * erottimesta ja tekstistä.
     * 
     * @return dokumentin merkkijonoesitys.
     */
    @Override
    public String toString() {
        // Hyodynnetään vakiota, jotta ohjelma olisi joustavampi.
        return tunniste() + EROTIN + teksti();
    }

    /**
     * Vertaa dokumenttien tunnistetta.
     * 
     * @return True jos tunnisteet ovat samat. False, jos eivät.
     */
    @Override
   public boolean equals(Object obj) {
      try {
         // liitetään olioon dokumentti-luokan viite
         Dokumentti toinen = (Dokumentti)obj;

         // dokumentit ovat samat jos niiden tunnisteet ovat samat
         return tunniste == toinen.tunniste();
      }
      catch (Exception e) {
         return false;
      }
    }

    /**
     * Vertaa dokumenttien tunnistetta.
     * 
     * @return 0 jos samat. -1, jos pienempi. 1, jos suurempi.
     */
   @Override
   public int compareTo(Dokumentti d) {
      if (tunniste == d.tunniste) {
         return 0;
      }
      else if (tunniste < d.tunniste) {
         return -1;
      }
      else {
         return 1;
      }
   }

   /*
     * Tietoinen-rajapinnan metodien korvaukset.
     * 
     */

   /**
    * Tutkii sisältääkö dokumentin teksti kaikki siitä haettavat sanat. Kunkin
    * listan sanan Li on vastattava yhtä tai useampaa dokumentin sanan Dj
    * esiintymää täysin (Li.equals(Dj) == true), jotta sanat täsmäävät. Jos parametrin
    * arvo on esimerkiksi ["cat, "dog"], niin hakusanat ja dokumentti täsmäävät
    * vain ja ainostaan, jos dokumentissa esiintyy vähintään kerran sanat "cat"
    * ja "dog". Dokumentin sanan osajonon ei katsota vastaavan hakusanaa.
    * Esimerkiksi hakusana "cat" ja dokumentin sana "catnip" eivät täsmää.
    *
    * @param hakusanat lista dokumentin tekstistä haettavia sanoja.
    * @return true jos kaikki listan sanat löytyvät dokumentista. Muuten palautetaan
    * false.
    * @throws IllegalArgumentException jos sanalista on tyhjä tai se on null-arvoinen.
    */

    @Override
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat)
    throws IllegalArgumentException {
        try {
            if (hakusanat.isEmpty()) {
                throw new IllegalArgumentException();
            }
            else {
                int määrä = 0;
                String[] sanat = teksti.split(" ");
                for (int i = 0; i < hakusanat.size(); i++) {
                    for (int j = 0; j < sanat.length; j++) {
                        if (hakusanat.get(i).equals(sanat[j])) {
                            määrä++;
                            break; // jottei lasketa ylimääräisiä osumia
                        }
                    }
                }
                // Koska aiemmin laskettiin vain yksi osuma per hakusana, kaikki sanat ovat
                // löytyneet, mikäli osumien määrä täsmää hakusanalistan koon kanssa.
                if (määrä == hakusanat.size()) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
    * Muokkaa dokumenttia siten, että tiedonhaku on helpompaa ja tehokkaampaa.
    * <p>
    * Metodi poistaa ensin dokumentin tekstistä kaikki annetut välimerkit ja
    * muuntaa sitten kaikki kirjainmerkit pieniksi ja poistaa lopuksi kaikki
    * sulkusanojen esiintymät.
    * <p>
    * Välimerkit annetaan merkkijonona. Jos testistä haluttaisiin poistaa esimerkiksi
    * pilkut ja pisteet, olisi jälkimmäisen parametrin arvo ",.". Sulkusanan Si ja
    * dokumentin sanan Dj on vastattava täysin toisiaan (Si.equals(Dj) == true),
    * jotta Dj poistetaan. Sulkusanoja poistettaessa on huolehdittava siitä,
    * että tekstin alkuun, tekstin sanojen väliin tai tekstin loppuun ei jää ylimääräisiä
    * väliliyöntejä. Näin ollen tekstin alussa ja lopussa ei ole välejä ja sanojen
    * välissä on yksi välilyönti myös siivouksen jälkeen.
    *
    * @param sulkusanat lista dokumentin tekstistä poistettavia sanoja.
    * @param välimerkit dokumentin tekstistä poistettavat välimerkit merkkijonona.
    * @throws IllegalArgumentException jos jompikumpi parametri on tyhjä tai null-
    * arvoinen.
    */
    @Override
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit)
    throws IllegalArgumentException {
        try {
            if (sulkusanat.isEmpty() || välimerkit == "") {
                throw new IllegalArgumentException();
            }
            else {
                // poistetaan välimerkit
                for (int i = 0; i < välimerkit.length(); i++) {
                    for (int j = 0; j < teksti.length(); j++) {
                        if (välimerkit.charAt(i) == teksti.charAt(j)) {
                            teksti = teksti.replace(teksti.substring(j, j+1), "");
                        }
                    }
                }
                // muunnetaan isot kirjaimet pieniksi
                teksti = teksti.toLowerCase();
                // luodaan virta tekstin sanoista
                teksti = Stream.of(teksti.split(" "))
                // filtteröidään pois sulkusanat
                .filter(s -> !sulkusanat.contains(s))
                // kasataan takaisin merkkijonoksi
                .collect(Collectors.joining(" "));

                // poistetaan ylimääräiset välilyönnit 
                teksti = teksti.trim().replaceAll("[ ]{2,}", " ");
            }
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }
}