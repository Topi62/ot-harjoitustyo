/*
 * Class for console ui
 */
package budgettool.ui;

import budgettool.dao.BudgetDao;
import budgettool.dao.SqlBudgetDao;
import budgettool.domain.BudgetService;
import budgettool.domain.Job;
import budgettool.domain.Row;
import budgettool.domain.User;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author tkarkine
 */
public class TextUi implements Ui {
    //tässä versiossa vielä main hoitaa hommat, jatkossa ui:ssa käyttäjä
    BudgetDao database;
    BudgetService budjetti;
    Ui ui;
    List<Job> jobList;
    List<User> userList;
    List<Row> rowList;
    Job job;
        
    public TextUi() {
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
                         showJobs(jobList);
                         break;
                case 2 : System.out.println("Kenen työt listataan? Anna id numero (int).");
                         userId = read.nextInt(); 
                         read.nextLine();
                        // jobList = budjetti.getUserJobs(userId);
                         ui.showJobs();
                         jobId = 1;
                    while (jobId != 0) {
                        System.out.println("Haluatko katsoa jonkun työn rivit 0= ei / työn id -> listaus");
                        jobId = read.nextInt(); 
                        read.nextLine();
                        if (jobId == 0) {
                            break;
                        }
                        rowList = budjetti.getRowsOfJob(jobId); 
                        showRows();
                    }
                         break;
                case 3 : rowList = budjetti.getRows();
                         showRows();
                         break;
                case 7 : System.out.println("Uusi työ -> Kuka tekee? Anna id numero (int).");
                         userList = budjetti.getUsers();
                         ui.showUsers();
                         userId = read.nextInt(); 
                         read.nextLine();
                         System.out.println("Uusi työ -> Anna työlle nimi (12 mrk).");
                         command = read.nextLine();
                         job = budjetti.addJob(command, userId);
                         System.out.println("Uusi työ -> Työ tallennettu.");
                         break;       
            }
        }   
    }

    public void showJobs(List<Job> jobs) {
        jobs.forEach((job) -> {
            System.out.println(job.toString());
        });
    }

    public void showUsers(List<User> users) {
        users.forEach((user) -> {
            System.out.println(user.toString());
        });
    }

    public void showRows(List<Row> rowList) {
        rowList.forEach((row) -> {
            System.out.println(row.toString());
        });
    }

    @Override
    public void showUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showJobs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showRows() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}


    

