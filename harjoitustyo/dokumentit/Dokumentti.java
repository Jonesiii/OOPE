package harjoitustyo.dokumentit;

/**
 * Abstrakti juuriluokka dokumenteile.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */
 
import harjoitustyo.apulaiset.*;
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

    /** Parametrillinen rakentaja */
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
     * @return True, jos tunnisteet ovat samat. False, jos eivät.
     */
    @Override
   public boolean equals(Object obj) {
      try {
         // liitetään olioon akku-luokan viite
         Dokumentti toinen = (Dokumentti)obj;

         // dokumentit ovat samat jos niiden varaustasot ovat samat
         return tunniste == toinen.tunniste();
      }
      catch (Exception e) {
         return false;
      }
    }

    /**
     * Vertaa dokumenttien tunnistetta.
     * 
     * @return 0, jos samat. -1, jos pienempi. 1, jos suurempi.
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
}