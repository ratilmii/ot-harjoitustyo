# Arkkitehtuurikuvaus # 

## Käyttöliittymä ##

Ohjelma sisältää Java FXML:llä toteutetun käyttöliittymän. Kaikki toiminnallisuus tapahtuu yhden ja saman näkymän sisällä, poislukien kuvien ja hakemiston avaamiseen liittyvät tiedostoselaimet. Käyttöliittymän keskeiset osa-alueet ovat vasemman laidan "asetus"-osio, jossa annetaan mosaiikin tarvitsemat tiedot ja oikean laidan "toteutus"-osio, jossa varsinainen mosaiikki luodaan.

## Sovelluslogiikka ##

Sovelluksen pääasiallienen toiminnallisuus tapahtuu luokassa FXMLController, joka puolestaan hyödyntää luokkia ImageCompare ja ImageColor ja ImageBuild vertaillessaan kuvia toisiinsa ja rakentaessaan lopullisen mosaiikin. Ohjelma käy läpi jokaisen mosaiikin tiilen pikseli pikseliltä ja vertaa niitä kulloinkin vuorossa olevan vertailtavan kuvan samaan pikseliin. Pikselien rgb-arvoja ajatellaan vektoreina ja näiden vektorien erotus lisätään yhteissummaan. Jokaisen tiilen lopussa katsotaan, mikä kansion kuvista antoi pienimmän summan vektorien erotukselle ja pienimmän summan antanut kuva valitaan lopulliseen mosaiikkiin.

## Tietojen tallennus ##

Käyttäjä voi halutessaan tallentaa saadun mosaiikin png-kuvana tiedostoon.

## Nykyisen version heikkoudet ##

Ohjelma on äärimmäisen hidas, mikä johtuu siitä, että jokaisen tiilen kohdalla käydään läpi jokaisen lähdekuvan jokainen pikseli aina uudelleen.
