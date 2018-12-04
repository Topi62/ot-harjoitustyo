/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import budgettool.dao.BudgetDao;
import budgettool.dao.SqlBudgetDao;
import budgettool.domain.BudgetService;
import budgettool.domain.Job;
import budgettool.domain.Row;
import budgettool.domain.User;
import budgettool.ui.TextUi;
import budgettool.ui.Ui;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


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
        List<Job> jobList;
        List<User> userList;
        List<Row> rowList;
        Job job;
        
        ui = new TextUi();
        database = new SqlBudgetDao("jdbc:postgresql://localhost:5432/budgettool");
        budjetti = new BudgetService(database);
         
        //käyttöliittymä tässä vielä, ehtiikö parantaa graafiseksi
        Scanner read = new Scanner(System.in);
        String command;
        int comNr;
        int userId;
        int jobId;
        int getSum;
        boolean getReact;
        comNr = 1;
        while (comNr != 0) {
            System.out.println("\nKomennot: 1 = listaa kaikki työt");
            System.out.println("Komennot: 2 = listaa työnjohtajan työt");
            System.out.println("Komennot: 3 = listaa kaikki budjettirivit");
            System.out.println("Komennot: 4 = ");
            System.out.println("Komennot: 5 = listaa rivit joille pyyntö");
            System.out.println("Komennot: 6 = ");
            System.out.println("Komennot: 7 = lisää työ");
            System.out.println("Komennot: 8 = ");
            System.out.println("Komennot: 0 = lopettaa\n");          
            
            
            comNr = read.nextInt();
            switch (comNr) {
                case 0: break;
                // lopettaa
                case 1 : jobList = budjetti.getJobs();
                         ui.showJobs(jobList);
                         break;
                case 2 : System.out.println("Kenen työt listataan? Anna id numero (int).");
                         userId = read.nextInt(); read.nextLine();
                         jobList = budjetti.getUserJobs(userId);
                         ui.showJobs(jobList);
                         jobId = 1;
                    while (jobId != 0) {
                        System.out.println("Haluatko katsoa jonkun työn rivit 0= ei / työn id -> listaus");
                        jobId = read.nextInt(); read.nextLine();
                        if (jobId == 0) {
                            break;
                        }
                        rowList = budjetti.getRowsOfJob(jobId); 
                        ui.showRows(rowList);
                        }
                         break;
                case 3 : rowList = budjetti.getRows();
                         ui.showRows(rowList);
                         break;
                case 7 : System.out.println("Uusi työ -> Kuka tekee? Anna id numero (int).");
                         userList = budjetti.getUsers();
                         ui.showUsers(userList);
                         userId = read.nextInt(); read.nextLine();
                         System.out.println("Uusi työ -> Anna työlle nimi (12 mrk).");
                         command = read.nextLine();
                         job = budjetti.addJob(command, userId);
                         System.out.println("Uusi työ -> Työ tallennettu.");
                         break; 
       
            }
        }
    }
    
    
        
        
    
    
}