# BudgetTool

Sovellus auttaa seuraamaan työn budjetoinnin valvontaa. 

Sovelluksessa työlle laaditaan budjetti, jonka päällikkö käyttäjä hyväksyy. Työnjohtaja käyttäjä kirjaa kulut budjettivertailuun 
ja hyväksyttää mahdolliset ylitykset päällikkö käyttäjällä. Päällikkö käyttäjä valvoo työtä raporttinäkymän kautta. Työnjohtajia 
ja töitä voi olla useita ja yhdellä työnjohtajalla voi olla useita töitä.

Sovellus on harjoitustyö Helsingin yliopiston Tietojenkäsittelytieteen kurssille Ohjelmistotekniikan menetelmät.

## Dokumentaatio

[Arkkitehtuurikuvaus](https://github.com/Topi62/ot-harjoitustyo/blob/master/budgetTool/dokumentaatio/arkkitehtuurikuvaus.md)

[Javadoc](https://github.com/Topi62/ot-harjoitustyo/blob/master/budgetTool/target/site/apidocs)

[Käyttöohje](https://github.com/Topi62/ot-harjoitustyo/blob/master/budgetTool/dokumentaatio/kayttoohje.md)

[Tuntikirjapito](https://github.com/Topi62/ot-harjoitustyo/blob/master/budgetTool/dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/Topi62/ot-harjoitustyo/blob/master/budgetTool/dokumentaatio/vaatimusmaarittely.md)

## Sovelluksen tietokanta

Sovellus käyttää PostgreSQL tietokantaa, joka oletuksena täytyy olla käynnissä //localhost:5432 portissa ja sen
käyttäjä postgres ja salasana admin määritelty sekä budgettool database luotu
 
## Komentorivitoiminnot

Ohjelman voi käynnistää /budgetTool kansiossa komennolla
<code>
mvn compile exec:java -Dexec.mainClass=Main
</code>
tai avatun projektin netbeansissa vihreällä napilla

Vähäiset testit suoritetaan komennolla

<pre><code>
mvn test
</code></pre>

## Releaset 

[lopullinen](https://github.com/Topi62/ot-harjoitustyo/blob/master/budgetTool/releases/tag/final)


Testikattavuusraportti luodaan komennolla

<code>
mvn jacoco:report
</code>

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html
, joskin se jäi surkean vähäiseksi.

Komento

<code>
mvn package
</code>

generoi hakemistoon target suoritettavan jar-tiedoston budgetTool-1.0-SNAPSHOT.jar

Checkstyle
Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

<code>
 mvn jxr:jxr checkstyle:checkstyle
</code>

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html, lopulliseen versioo niitä jäi metodien ja tiedostojen pituutta koskien 13 kpl.


