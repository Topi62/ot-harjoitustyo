/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.domain;

import budgettool.dao.BudgetDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tkarkine
 */
public class BudgetService {
    BudgetDao database;
    
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
            case 1 : column = "(approved) = TRUE";
                break;
            case 2 : column = "(exceeded) = TRUE";
                break;
            case 3 : column = "(request) = TRUE";
                break;
            default : column = "1";
            // list all
        }
        rows = database.getRowsByBoolean(column);
        return rows;
    }
}
