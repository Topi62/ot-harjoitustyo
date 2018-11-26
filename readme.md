# BudgetTool

Sovellus auttaa seuraamaan työn budjetoinnin valvontaa. 

Sovelluksessa työlle laaditaan budjetti, jonka päällikkö käyttäjä hyväksyy. Työnjohtaja käyttäjä kirjaa kulut budjettivertailuun 
ja hyväksyttää mahdolliset ylitykset päällikkö käyttäjällä. Päällikkö käyttäjä valvoo työtä raporttinäkymän kautta. Työnjohtajia 
ja töitä voi olla useita ja yhdellä työnjohtajalla voi olla useita töitä.

Sovellus on harjoitustyö Helsingin yliopiston Tietojenkäsittelytieteen kurssille Ohjelmistotekniikan menetelmät.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Topi62/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/Topi62/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus.md)

[Tuntikirjapito](https://github.com/Topi62/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Sovelluksen tietokanta

Sovellus käyttää PostgreSQL tietokantaa, joka oletuksena täytyy olla käynnissä //localhost:5432 portissa ja sen
käyttäjä postgres ja salasana admin määritelty
 
## Komentorivitoiminnot

Testit suoritetaan komennolla

<pre><code>
mvn test
</code></pre>

## Releaset

[viikko4](https://github.com/Topi62/ot-harjoitustyo/blob/master/budgetTool/tag/ver0.2)

