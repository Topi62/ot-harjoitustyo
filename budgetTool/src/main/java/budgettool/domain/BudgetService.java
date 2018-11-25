/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.domain;

import budgettool.dao.SqlBudgetDao;
import java.util.List;

/**
 *
 * @author tkarkine
 */
public class BudgetService {
    SqlBudgetDao database;
    
    public BudgetService(SqlBudgetDao database){
        this.database = database;
    }
    
    public List<Job> getJobs(){
        return database.getJobs();
    }
    
    public Job addJob(String name, int owner){
        return database.addJob(name,owner);
    }
}
