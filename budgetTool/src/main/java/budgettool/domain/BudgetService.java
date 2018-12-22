/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.domain;

import budgettool.dao.BudgetDao;
import java.util.List;

/**
* Po sovelluslogiikasta huolehtiva luokka
*
* tässä muodossa lähinnä välittää pyynnöt 
*/

public class BudgetService  {
    BudgetDao database;
    
    
       
    /**
    * @param    database    tietokannan käytöstä huolehtiva luokka 
    */
    public BudgetService(BudgetDao database) {
        this.database = database;
    }
    
    public List<Job> getJobs() {
        return database.getJobs();
    }
    
    public Job addJob(String name, int owner) {
        return database.addJob(name, owner);
    }
    
    public User addUser(int type, String name, int boss) {
        return database.addUser(type, name, boss);
    }
    
    public List<Row> listRowsByBoolean(int choice) {
        List<Row> rows;
        String column;
        switch (choice) {
            case 1 : column = " approved = 't'";
                break;
            case 2 : column = " exceeded = 't'";
                break;
            case 3 : column = " request = 't'";
                break;
            default : column = "1";
            // list all
        }
        rows = database.getRowsByBoolean(column);
        return rows;
    }

    public List<User> getUsers(int user) {
        return database.getUsers(user);
    }

    public List<Job> getUserJobs(int userId) {
        return database.getUserJobs(userId);
    }

    public List<Row> getRows() {
        return database.getRows();
    }

    public List<Row> getRowsOfJob(int jobId) {
        return database.getRowsOfJob(jobId);
    }

    public void addCostToRow(Row row) {
        
        database.addCostToRow(row.idProperty().get(), row.usedSumProperty().get(), row.exceededProperty().get());
        
    }

    public void addRow(Row row) {
        database.addRow(row.jobIdProperty().get(), row.resursProperty().get(), row.requestSumProperty().get(), row.reasonProperty().get());
    }

    public void addJob(Job job) {
        database.addJob(job.getName(), job.getOwner());
    }

    public void addUser(User user) {
        database.addUser(user.getType(), user.getName(), user.getBoss());
    }

    public void rejectRequest(Row row) {
        database.rejectRequest(row.idProperty().get());
    }

    public void acceptRequest(Row row) {
        database.acceptRequest(row.idProperty().get(), row.exceededProperty().get(), row.budgetSumProperty().get());
    }

    public void addRequest(Row row) {
        database.addRequest(row.idProperty().get(), row.exceededProperty().get(), row.requestSumProperty().get(), row.reasonProperty().get());
    }

   
    
}
