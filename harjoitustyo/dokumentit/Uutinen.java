package harjoitustyo.dokumentit;
import java.time.LocalDate;

/**
 * Konkreettinen luokka uutisille.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */
 
public class Uutinen extends Dokumentti {
    /*
     * Attribuutit.
     * 
     */

    /** Vitsin laji */
    private LocalDate päivämäärä;

    /*
     * Rakentajat
     * 
     */

    public Uutinen(int uusiTunniste, LocalDate uusiPäivämäärä, String uusiTeksti) throws IllegalArgumentException {
        super(uusiTunniste, uusiTeksti);

    }

    /*
     * Aksessorit
     * 
     */

    public LocalDate päivämäärä() {
        return päivämäärä;
    }

    public void päivämäärä(LocalDate uusiPäivämäärä) throws IllegalArgumentException {
        if (uusiPäivämäärä == null) {
            throw new IllegalArgumentException();
        }
        else {
            päivämäärä = uusiPäivämäärä;
        }
    }
    /*
     * Object-luokan metodien korvaukset.
     * 
     */

    /**
     * Muodostaa dokumentin merkkijonoesityksen, joka koostuu yliluokan toStringistä
     * sekä tämän luokan päivämäärästä.
     * 
     * @return dokumentin merkkijonoesitys.
     */
    @Override
    public String toString() {
        String[] osat = super.toString().split(EROTIN);
        return osat[0] + EROTIN + päivämäärä() + EROTIN + osat[1];
    }
}