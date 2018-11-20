/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.dao;

import budgettool.domain.Job;
import budgettool.domain.User;
import java.util.List;

/**
 *
 * @author tkarkine
 */
public interface BudgetDao {
    public boolean saveNewUser(User user);
    public boolean saveNewJob(Job job);
    public List<Job> getJobs();   
    public Job addJob(String name, int owner);
    
}
