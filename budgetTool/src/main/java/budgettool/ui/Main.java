/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.ui;

import budgettool.dao.SqlBudgetDao;
import budgettool.domain.BudgetService;
import budgettool.domain.Job;
import java.io.IOException;
import java.util.List;


/**
 *
 * @author tkarkine
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SqlBudgetDao database = new SqlBudgetDao("jdbc:sqlite:budjetti.db");
        database.init();
        
        BudgetService budjetti = new BudgetService(database);
        
        //täytyy vielä miettiä mihin nää laitetaan jatkossa, app?
        List<Job> list = budjetti.getJobs();
        
        Ui ui;
        ui= new TextUi();
        ui.showJobs(list);
        
        //lisätään uusi job foreman 1:lle
        Job newJob = budjetti.addJob("New projekt", 2);
        // tulostetaan uuden jobin tietokannan antama id
        System.out.println(newJob.getId());
}
}
