# Luokka/pakkauskaavio

## BudgetTool

![Pakkauskaavio](budgettool.jpg)

## Rakenne

Pakkaus budgettool.ui sisältää käynnistysluokan Main ja graafisen käyttöliittymän, josta keskeinen on pääikkunan luokka GraphUi. Aluksi oli suunnitelmissa tekstikäyttöliittymä, mutta vihjeen saatuani keskityin graafiseen liittymään. GraphUi kutsuu tarvittaessa lomakkeita toteuttavia luokkia.

Pakkaus budgettool.domain sisältää BudgetService luokan, johon oli tarkoitus määrittää sovelluslogiikkaa ja tarkistuksia, mutta tämä osuus jäi vajaaksi, nyt se lähinnä välittää kutsut eteenpäin. Pakkauksessa on myös tietomallien modelluokat User, Job ja Row.

Pakkaus budgettool.dao huolehtii tietokannan käytöstä. Valitettavasti tietokannan olemassa ololle en ehtinyt löytää kunnollista ratkaisua, joten nyt tietokanta pyyhitään, jottei tuplaid:t kaada ohjelmaa.

Test hakemistossa on vain MainTest, joka tarkistaa kirjautumisen eri käyttäjätasoilla. Aika ei riittänyt tehdä testejä lisää, kun alun vaikeuksien jälkeen vihdoin sain grafiikan toimimaan, mutta tuosta se alkaisi.

