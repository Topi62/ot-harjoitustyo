# Käyttöohje

Lataa tiedosto [budgettool.jar](https://github.com/Topi62/Ot-harjoitustyo/releases/tag/Tag7)

## Konfigurointi

Ohjelma olettaa, että ajoympäristössä on käynnissä postgresql palvelin, sillä käyttäjä postgres ja tämän salasana admin 

Valitettavasti ohjelmaan jäi ominaisuus, että tietokannan taulut alustetaan käynnistyksen yhteydessä, sillä en löytänyt keinoa tarkistaa onko taulussa jo rivejä.

## Ohjelman käynnistäminen

Ohjelma käynnistetään komennolla 

```
java -jar budgettool.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään:

<img src="/dokumentaatio/kuvat/login2.png" width="1000">

Päällikkö kirjautuu luvulla 1  syötekenttään ja painamalla _Log in_.

## Päällikön toiminnot

Olemassa olevat työnjohtajat saa listattua painikkeella _Users_

Uusi käyttäjä  panikkeella _add user_, jolloin avautuu lomake syöttöä varten. Type 1 olisi tarkoitus olla päällikkö, m62 työnjohtaja, mutta tämä ominaisuus ei vielä toimi, nyt vain käyttäjä 1 on päällikkö.

Antamalla käyttäjän id:n syötekenttään tai valitsemalla rivin työnjohtajien listauksesta ja painikkeella _Jobs_ saa listattua kyseisen työnjohtajan työt. Rivin valinta on ristiriita tilanteessa etusijalla.

<img src="https://raw.githubusercontent.com/Topi62/ot-harjoitustyo/master/dokumentaatio/kuvat/jobs.png width="1000">

Antamalla työn id:n syötekenttään tai valitsemalla rivin työlistalta ja painikkeella _Rows_ saa listattua kyseisen työn budjettirivit.

<img src="https://raw.githubusercontent.com/Topi62/ot-harjoitustyo/master/dokumentaatio/kuvat/rows.png with="1000">

Requests painikkeella päällikkö näkee kaikki budjettirivit, joille on tehty lisämäärärahapyyntöjä. Valitsemalla rivin ja painamalla _Handle Request_ päällikkö voi hylätä tai hyväksyä pyynnön avautuvalla lomakkeella. 

_addUser_, _addJob_ ja _addRow_ painikkeet avaavat nimensä mukaisen lomakkeen, joissa kaikki kentät on syötettävä.

## Työnjohtajan toiminnot

Työnjohtaja voi listata työnsä painikkeella _Jobs_ tai lisätä työn painikkeella _addJob_ avautuvalla lomakkeella. Työn voi lisätä myös toiselle käyttäjälle.

Töiden listauksesta voi valita jonkin budjettirivitarkasteluun painikkeella _rows_. 

Budjettirivin valitsemalla sille voi lisätä kustannuksia _addCost->Row_, tai pyytää lisäbudjettia _request_ painikkeella. Myös negatiiviset summat ovat mahdollista ja näin joko poistaa kuluja tai pienentää budjettia.

## Jäljelle jääneet ongelmat

Tietokanta pyyhkiytyy ja alustuu uudelleen aina sammuttaessa sovelluksen. Ei uloskirjautumista, vaan sovellus suljetaan yläkulman ruksista. Ohjelma ei aina muista edellistä valintaa, joten joskus palatessa lomakkeelta näkymä ei päivity, mutta kaatumista ei tapahnut omissa kokeiluissani. 


