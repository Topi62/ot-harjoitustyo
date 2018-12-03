/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import budgettool.dao.BudgetDao;
import budgettool.dao.SqlBudgetDao;
import budgettool.domain.BudgetService;
import budgettool.domain.Job;
import budgettool.ui.TextUi;
import budgettool.ui.Ui;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author tkarkine
 */
public class Main {
    public static void main(String[] args)  {
        //tässä versiossa vielä main hoitaa hommat, jatkossa ui:ssa käyttäjä
        BudgetDao database;
        BudgetService budjetti;
        Ui ui;
        List<Job> list;
        Job job;
        
        ui = new TextUi();
        
       
        database = new SqlBudgetDao("jdbc:postgresql://localhost:5432/budgettool");
        // tällä välitetään jo database, mutta pitää vielä välittää käyttöliittymä
        budjetti = new BudgetService(database);
         
        //täytyy vielä miettiä mihin nää loput laitetaan jatkossa?
              
        //ensimmäinen toiminnallisuus, näytetään työlista
        list = budjetti.getJobs();
        ui.showJobs(list);
        
        //toinen toiminnallisuus lisätään työ
        //lisätään uusi job foreman 1:lle, 
        job = budjetti.addJob("New projekt", 2);
        // tulostetaan uuden jobin tietokannan antama id
        System.out.println(job.getId());
        
         
                
    }
}