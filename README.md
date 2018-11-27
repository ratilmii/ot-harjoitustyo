# Mosaic Builder #

Sovelluksen avulla käyttäjän on mahdollista luoda mosaiikkikuvia. Käyttäjä antaa ohjelmalle kuvan, josta lopullinen mosaiiki tehdään, sekä kokoelman kuvia, joita ohjelma käyttää materiaalina. Sovellus etsii kuvien dominantit värit joiden perusteella se asettaa ne sellaiseen järjestykseen, joka vastaa eniten kohdekuvaa.

Sovelluksella on vain yksi käyttäjä kerrallaan, mutta tavoitteena on, että eri käyttäjät voivat luoda itselleen profiilin, joka pitää kirjaa käyttäjän aiemmin luomista mosaiikeista. 

## Dokumentaatio ##

[Vaatimusmäärittely](https://github.com/ratilmii/ot-harjoitustyo/blob/master/Dokumentaatio/Vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/ratilmii/ot-harjoitustyo/blob/master/Dokumentaatio/Tyoaikakirjanpito.md)

## Tämänhetkinen toiminnallisuus ##

Tällä hetkellä sovelluksella on mahdollista avata kuva, josta valmis mosaiikki on tarkoitus muodostaa, esikatseluruutuun, sekä tyhjentää valinta, sekä asettaa kohdehakemisto, josta mosaiikki saa lähdekuvansa ja tyhjentää valinta. 

Ohjelmaan on luotu alustava toiminnallisuus dominantin värin etsimiseen halutusta kuvasta ja tätä demonstroidaan tällä hetkellä kuvalla, joka avataan valmiin mosaiikin malliksi. Ohjelma etsii kuvan dominantin värin ja kys. värin RGB-arvot tulostetaan sille varattuun tekstikenttään. 

Ohjelma jättää huomiotta sellaiset värit, jotka ovat miltei täysin valkoisia, ts. joiden RGB-arvoista jokainen on yli 245. Loput väreistä tallennetaan HashMapiin, josta ohjelma etsii avaimen, jonka arvo on korkein integer. 

(Palautuksista osa on myöhässä, sillä NetBeans lakkasi käynnistymästä juuri ratkaisevalla hetkellä, enkä päässyt lisäämään pom.xml:ään tarpeellisia osioita ja Firefox jäätyi täysin.)
