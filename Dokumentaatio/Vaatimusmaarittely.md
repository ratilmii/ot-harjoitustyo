# Vaatimusmäärittely #

## Sovelluksen tarkoitus ##

Sovelluksen avulla käyttäjän on mahdollista luoda mosaiikkikuvia. Käyttäjä antaa ohjelmalle kuvan, josta lopullinen mosaiiki tehdään, sekä kokoelman kuvia, joita ohjelma käyttää materiaalina.

## Käyttäjät ##

Sovelluksella on vain yksi käyttäjä kerrallaan, mutta jonkinlainen käyttäjäprofiilitoiminnallisuus, esimerkiksi aiemmin luotujen mosaiikkien selaamiseksi, ei ole poissuljettu ajatus, mikäli aikaa riittää.

## Toiminnallisuus ##

Alustavasti sovelluksen on tarkoitus ainakin voida:

- jakaa kohdekuva ruudukoksi, jossa on käyttäjän valitsema määrä rivejä ja sarakkeita, joka määrittää lopullisen mosaiikin "resoluution", ts. kuinka monta lähdekuvaa tarvitaan
- erottaa jokaisen ruudun, sekä lähdekuvan dominantti väri, jotta ohjelma pystyy valitsemaan parhaiten sopivan kuvan lähdekuvista
- toteuttaa valinta riittävän nopeasti, ettei mosaaikin rakentamisessa kestä liian kauan
- koota lopullinen mosaiikki sopivista lähdekuvista ja tallentaa se tiedostona

Mahdollisia toiminnallisuuksia:

- käyttäjäprofiilin luominen ja aiemmin luotujen mosaiikkien selaus ja muokkaus
- asetusten ja muiden tietojen tallentaminen tietokantaan
- sama toiminnallisuus videotiedostoille (epätodennäköinen)