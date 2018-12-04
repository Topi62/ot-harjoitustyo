/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.dao;

import budgettool.domain.Job;
import budgettool.domain.Row;
import budgettool.domain.User;
import java.util.List;

/**
 *
 * @author tkarkine
 */
public interface BudgetDao {
    public List<Job> getJobs();   
    public List<Job> getUserJobs(int userId);
    public Job addJob(String name, int owner);
    
    public User addUser(int type, String name, int boss);
    public User loginUser(int id, String name);
    public List<User> getUsers();
     
    public List<Row> getRows();
    public List<Row> getRowsByBoolean(String column);
    public void addRow(int jobid, String resurs, int budgetsum);
    public boolean addCostToRow(int id, int sum);

    public List<Row> getRows(int jobId);

   

    

    
    
    
}
