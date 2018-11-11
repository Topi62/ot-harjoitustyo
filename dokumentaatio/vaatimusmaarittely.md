# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus auttaa seuraamaan työn budjetoinnin valvontaa. Sovelluksessa työlle laaditaan budjetti, jonka päällikkö käyttäjä hyväksyy. Työnjohtaja käyttäjä kirjaa kulut budjettivertailuun ja hyväksyttää mahdolliset ylitykset päällikkö käyttäjällä. Päällikkö käyttäjä valvoo työtä raporttinäkymän kautta. Työnjohtajia ja töitä voi olla useita ja yhdellä työnjohtajalla voi olla useita töitä.

## Käyttäjät

Käyttäjiä ovat
- päällikkö 
- työnjohtajat.

## Käyttöliittymäluonnos



## Version tarjoama toiminnallisuus


Päällikkö kirjautuu:
- päällikkö näkee työnjohtajat ja työt listauksena, josta voi valita jonkun tarkasteluun.
- työnjohtajan valitessaan päällikkö näkee työnjohtajan työt listauksena, josta voi valita jonkun tarkasteluun.
- työn tarkastelussa päällikkö näkee budjettivertailun, sekä mahdolliset ylityksen hyväksymispyynnöt.
- päällikkö voi hyväksyä tai hylätä ylityksen. Hyväksytty pyyntö kirjautuu uudeksi budjettin rivisummaksi.

Työnjohtaja kirjautuu:
- työnjohtaja näkee työnsä listauksena, josta voi valita jonkun tarkasteluun.
- tarkastelussa 
 - työnjohtaja näkee budjettivertailun
 - voi kirjata kuluja 
 - pyytää ylityslupaa tarvittaessa rivi kohtaisesti. 

Budjettivertailussa:
- näkyy budjetin rivisumma
- käytetty summa
- jäljellä oleva summa
- sekä toteutunut prosentti.
- vertailussa erottuu selkeästi rivit, joila budjetti on ylitetty. 

Ylityksen hyväksymispyynnössä on kentät
- nyt toteutuvalle ylitykselle
- tulevalle kokonaisylitykselle 
- perustelulle. 

## Jatkokehitysideoita

- Työlle kiinnitetään resursseja, joille määritellään arvo.
- Summan sijaan resurssikulujen kirjaus tapahtuu kunkin resurssin määrän mukaan.
- Työlle kirjataan valmistumisaste, ja budjettivertailu vertaa paitsi kokonaisbudjettiin, myös valmistumisasteen mukaiseen budjettiin.
