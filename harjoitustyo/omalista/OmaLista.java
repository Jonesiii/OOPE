package harjoitustyo.omalista;
/**
 * LinkedLististä peritty geneerinen oma lista -luokka.
 * <p>
 * Olio-ohjelmoinnin perusteet II, kevät 2020.
 * 
 * @version 0.1
 * @author Joonas Arola, joonas.arola@tuni.fi.
 */

import java.util.LinkedList;
import java.util.Comparator;
import harjoitustyo.apulaiset.*;
import java.util.Iterator;
import java.util.stream.*;

public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
    /**
    * Lisää listalle uuden alkion sen suuruusjärjestyksen mukaiseen paikkaan.
    * <p>
    * Listan alkioiden välille muodostuu kasvava suuruusjärjestys, jos lisäys
    * tehdään vain tällä metodilla, koska uusi alkio lisätään listalle siten,
    * että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
    * jälkeen ja ennen kaikkia itseään suurempia alkioita. Alkioiden suuruusjärjestys
    * päätellään Comparable-rajapinnan compareTo-metodilla.
    * <p>
    * Jos parametri viittaa esimerkiksi Integer-olioon, jonka arvo on 2 ja listan
    * muut tietoalkiot ovat arvoltaan 0 ja 3, on listan sisältö metodin suorittamisen
    * jälkeen [0, 2, 3], koska {@literal 0 < 2 < 3}.
    * <p>
    * Käytä toteutuksessa SuppressWarnings-annotaatiota, jotta kääntäjä ei valita
    * vaarallisesta tyyppimuunnoksesta.
    *
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @throws IllegalArgumentException jos lisäys epäonnistui, koska uutta alkiota
    * ei voitu vertailla. Vertailu epäonnistuu, kun parametri on null-arvoinen
    * tai siihen liittyvän olion luokka tai luokan esivanhempi ei vastoin oletuksia
    * toteuta Comparable-rajapintaa.
    */
    @SuppressWarnings({"unchecked"})
    public void lisää(E uusi) throws IllegalArgumentException {
        try {
            Comparable uusi1 = (Comparable)uusi;
            if (uusi == null) {
                throw new IllegalArgumentException();
            }
            else {
                // Käydään lista läpi hyödyntämällä streamia, niin että lasketaan pienempien
                // ja samankokoisten määrä ja hyödynnetään tätä määrää uuden alkion lisäysindeksinä 
                int pienemmät = (int)super
                    .stream()
                    .map(Comparable.class::cast)
                    .filter(s->s.compareTo(uusi) <= 0)
                    .count();

                super.add(pienemmät, uusi);
            }
        }
        catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}