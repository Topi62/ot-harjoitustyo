/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.ui;

import budgettool.domain.Job;
import java.util.List;

/**
 *
 * @author tkarkine
 */
public class TextUi implements Ui{

    @Override
    public void showJobs(List<Job> jobs) {
      // pomon työlistaus
    System.out.println("boss");
    //toistetaan per työnjohtaja
    for (int f=0;f<2;f++){
    System.out.println("foreman"+f);
    // toistetaan per työ
    for (int j=0;j<4;j++){
    System.out.println("Job"+j + " name of the job");
    }
    }
    }
    
}
