# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus auttaa seuraamaan työn budjetoinnin valvontaa. Sovelluksessa työlle laaditaan budjetti, jonka päällikkö käyttäjä hyväksyy. Työnjohtaja käyttäjä kirjaa kulut budjettivertailuun ja hyväksyttää mahdolliset ylitykset päällikkö käyttäjällä. Päällikkö käyttäjä valvoo työtä raporttinäkymän kautta. Työnjohtajia ja töitä voi olla useita ja yhdellä työnjohtajalla voi olla useita töitä.

## Käyttäjät

Käyttäjiä ovat päällikkö ja työnjohtajat.

## Käyttöliittymäluonnos



## Version tarjoama toiminnallisuus


Päällikkö kirjautuu, jolloin päällikkö näkee työnjohtajat ja työt listauksena, josta voi valita jonkun tarkasteluun.
Työnjohtajan valitessaan päällikkö näkee työnjohtajan työt listauksena, josta voi valita jonkun tarkasteluun.

Työn tarkastelussa päällikkö näkee budjettivertailun, sekä mahdolliset ylityksen hyväksymispyynnöt.

Päällikkö voi hyväksyä tai hylätä ylityksen. Hyväksytty pyyntö kirjautuu uudeksi budjettin rivisummaksi.

Työnjohtaja kirjautuu, jolloin näkee työnsä listauksena, josta voi valita jonkun tarkasteluun.
Tarkastelussa työnjohtaja näkee budjettivertailun, voi kirjata kuluja ja pyytää ylityslupaa tarvittaessa rivi kohtaisesti. 

Budjettivertailussa näkyy budjetin rivisumma, käytetty summa, jäljellä oleva summa, sekä toteutunut prosentti.
Vertailussa erottuu selkeästi rivit, joila budjetti on ylitetty. 

Ylityksen hyväksymispyynnössä on kentät nyt toteutuvalle ylitykselle, tulevalle kokonaisylitykselle sekä perustelulle. 

## Jatkokehitysideoita

Työlle kiinnitetään resursseja, joille määritellään arvo. Summan sijaan resurssikulujen kirjaus tapahtuu kunkin resurssin määrän mukaan. Työlle kirjataan valmistumisaste, ja budjettivertailu vertaa paitsi kokonaisbudjettiin, myös valmistumisasteen mukaiseen budjettiin.
