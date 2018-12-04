/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.ui;

import budgettool.domain.Job;
import budgettool.domain.Row;
import budgettool.domain.User;
import java.util.List;

/**
 *
 * @author tkarkine
 */
public interface Ui {
    
    public void showJobs(List<Job> jobs);

    
    public void showUsers(List<User> userList);

    public void showRows(List<Row> rowList);

   
}
