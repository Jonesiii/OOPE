package harjoitustyo.dokumentit;

/**
 * Konkreettinen luokka vitseille.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 1.0
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */
 
public class Vitsi extends Dokumentti {
    /*
     * Attribuutit.
     * 
     */

    /** Vitsin laji */
    private String laji;

    /*
     * Rakentajat
     * 
     */

    public Vitsi(int uusiTunniste, String uusiLaji, String uusiTeksti) throws IllegalArgumentException {
        super(uusiTunniste, uusiTeksti);
        laji(uusiLaji);
    }

    /*
     * Aksessorit
     * 
     */

    public String laji() {
        return laji;
    }

    public void laji(String uusiLaji) throws IllegalArgumentException {
        if (uusiLaji == null || uusiLaji == "") {
            throw new IllegalArgumentException();
        }
        else {
            laji = uusiLaji;
        }
    }

    /*
     * Object-luokan metodien korvaukset.
     * 
     */

    /**
     * Muodostaa dokumentin merkkijonoesityksen, joka koostuu yliluokan toStringistä
     * sekä tämän luokan lajista.
     * 
     * @return dokumentin merkkijonoesitys.
     */
    @Override
    public String toString() {
        String[] osat = super.toString().split(EROTIN);
        return osat[0] + EROTIN + laji() + EROTIN + osat[1];
    }
}