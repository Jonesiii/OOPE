package harjoitustyo.dokumentit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Konkreettinen luokka uutisille.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 1.0
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */
 
public class Uutinen extends Dokumentti {
    /*
     * Attribuutit.
     * 
     */

    /** Uutisen päivämäärä */
    private LocalDate päivämäärä;

    /*
     * Rakentajat
     * 
     */

    public Uutinen(int uusiTunniste, LocalDate uusiPäivämäärä, String uusiTeksti) throws IllegalArgumentException {
        super(uusiTunniste, uusiTeksti);
        päivämäärä(uusiPäivämäärä);
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
        return osat[0] + EROTIN + päivämäärä().format(DateTimeFormatter.ofPattern("d.M.yyyy")) + EROTIN + osat[1];
    }
}