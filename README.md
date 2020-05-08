## Yleistä

Tämä on olio-ohjelmoinnin perusteet 2 -kurssin harjoitustyö.

Ohjelma lukee tekstitiedostosta uutisia tai vitsejä, lataa ne kokoelmaksi ohjel-
maan ja antaa käyttäjän etsiä, muokata ja tulostaa niitä.

## Käynnistys

Ohjelma käynnistetään komentoikkunassa antamalla komentoriviparametreina kaksi
tekstitiedostoa. Ensimmäinen tiedosto sisältää uutiset tai vitsit, jotka halutaan
ladata ohjelman kokoelmaan. Toinen tekstitiedosto pitää sisällään listan sulkusa-
noista.

```
java Oope2HT vitsit.txt sulkusanat.txt
```

## Komennot

```print``` -tulostaa kaikki kokoelman dokumentit tai yhden, jos sille antaa paramet-
riksi dokumentin tunnisteen. Esim: "print 12".

```add``` -lisää käyttäjän määrittelemän dokumentin kokoelmaan. Parametrina komennolle
pitää antaa tunniste///uutisen päivämäärä tai vitsin laji///teksti. Esim:
"add 12///computing///When your hammer is C++, everything begins to look like a thumb."

```find``` -etsii dokumentin, joka sisältää parametrina saadut sanat. Esim: "find europe
music".

```remove``` -poistaa halutun dokumentin kokoelmasta. Esim. "remove 12".

```polish``` -käsittelee kokoelmaa niin, että dokumenttien tekstistä poistetaan toisena
komentoriviparametrina saadun listan sanat ja komennon paramterina saadut merkit.
Esim: "polish :;!". Tämä poistaa siis sulkusanojen lisäksi merkit :, ; ja !.

```reset``` -kumoaa kaikki muutokset kokoelmaan.

```echo``` -kaiuttaa kaikki käyttäjän komennot. 

```quit``` -lopettaa ohjelman.
