## Yleistä

Tämä on olio-ohjelmoinnin perusteet 2 -kurssin harjoitustyö.

Ohjelma lukee tekstitiedostosta uutisia tai vitsejä, lataa ne kokoelmaksi ohjelmaan
 ja antaa käyttäjän etsiä, muokata ja tulostaa niitä.

## Käynnistys

Ohjelma käynnistetään komentoikkunassa antamalla komentoriviparametreina kaksi
tekstitiedostoa. Ensimmäinen tiedosto sisältää uutiset tai vitsit, jotka halutaan
ladata ohjelman kokoelmaan. Toinen tekstitiedosto pitää sisällään listan sulkusanoista.

```
java Oope2HT jokes_oldies.txt stop_words.txt
```

## Komennot

```print``` -tulostaa kaikki kokoelman dokumentit tai yhden, jos sille antaa parametriksi
 dokumentin tunnisteen.

```
print 12
```

```add``` -lisää käyttäjän määrittelemän dokumentin kokoelmaan. Parametrina komennolle
pitää antaa tunniste///uutisen päivämäärä tai vitsin laji///teksti.

```
add 12///computing///When your hammer is C++, everything begins to look like a thumb.
```

```find``` -etsii dokumentin, joka sisältää parametrina saadut sanat. 

```
find europe music
```

```remove``` -poistaa halutun dokumentin kokoelmasta.

```
remove 12
```

```polish``` -käsittelee kokoelmaa niin, että dokumenttien tekstistä poistetaan toisena
komentoriviparametrina saadun listan sanat ja komennon parametrina saadut merkit.

```
polish :;!
```

```reset``` -kumoaa kaikki muutokset kokoelmaan.

```echo``` -kaiuttaa kaikki käyttäjän komennot. 

```quit``` -lopettaa ohjelman.
