/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.dao;
import budgettool.dao.SqlBudgetDao;
import budgettool.domain.Job;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tkarkine
 * 
 */

 public class SqlBudgetDaoTest {
    SqlBudgetDao data;
     
    public void SqlBudgetDaoTest(){
       
    }
    
    @Before
    public void setUp() {
        data=new SqlBudgetDao("jdbc:postgresql://localhost:5432/budgettool");
    }
    
    @After
    public void tearDown() {
        
    } 
   
    @Test
    public void addJobReturnsJobid(){
        Job job;
        job =data.addJob("testin luoma", 1);
        assertTrue(job.getId()>1);
    }       
    
    @Test
    public void getJobsReturnsRows() {
    List<Job> jobs;
    jobs =data.getJobs();
    assertNotNull(jobs);
    }

}
    
    

