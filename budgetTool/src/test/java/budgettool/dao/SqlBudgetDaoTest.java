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
     SqlBudgetDao wrongData;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
     
     public void SqlBudgetDaoTest(){
         
     }
    
   @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        data=new SqlBudgetDao("jdbc:postgresql://localhost:5432/budgettool");
        wrongData= new SqlBudgetDao("jdbc:posgresql://nowhere");
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @After
    public void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    } 
    
    @Test
    public void connectionCanBeMade() throws SQLException{
        data.getConnection();
        assertNotNull(data);
        data.closeConnection();
       
    }
    
    @Test
    public void addJobReturnsJobid(){
        Job job;
        job =data.addJob("testin luoma", 1);
        assertTrue(job.getId()>1);
    }       
    
    
    @Test
    public void getJobsReturnsRows() throws SQLException{
    data.getConnection();
    List<Job> jobs;
    jobs =data.getJobs();
    assertNotNull(jobs);
    data.closeConnection();
    }

}
    
    

