/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.ui;

import budgettool.domain.Job;
import budgettool.domain.Row;
import budgettool.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tkarkine
 */
public class TextUi implements Ui {

    @Override
    public void showJobs(List<Job> jobs) {
        jobs.forEach((job) -> {
            System.out.println(job.toString());
        });
    }

    @Override
    public void showUsers(List<User> users) {
        users.forEach((user) -> {
            System.out.println(user.toString());
        });
    }

    @Override
    public void showRows(List<Row> rowList) {
        rowList.forEach((row) -> {
            System.out.println(row.toString());
        });
    }

    
}


    

