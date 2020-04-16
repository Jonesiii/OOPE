package harjoitustyo.dokumentit;

/**
 * Abstrakti juuriluokka dokumenteile.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */
 
public abstract class Dokumentti {

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
        return tunniste + EROTIN + teksti;
    }
}