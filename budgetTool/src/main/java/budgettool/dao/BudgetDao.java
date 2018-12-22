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
    public List<User> getUsers(int user);
     
    public List<Row> getRows();
    public List<Row> getRowsByBoolean(String column);
    public List<Row> getRowsOfJob(int jobId);
    public void addRow(int jobid, String resurs, int requestsum, String reason);
    public boolean addCostToRow(int id, int sum, boolean exceeded);

    public void rejectRequest(Integer value);

    public void acceptRequest(int id, boolean exceeded, int budgetSum);

    public void addRequest(int id, boolean exceeded, int requestSum, String reason);

   

   

    

    
    
    
}
