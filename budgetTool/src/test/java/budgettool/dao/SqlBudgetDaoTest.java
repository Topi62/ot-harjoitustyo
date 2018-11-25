/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.dao;
import budgettool.dao.SqlBudgetDao;
import java.sql.*;
import org.junit.After;
import org.junit.AfterClass;
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
     SqlBudgetDao sbd;
     
     public void SqlBudgetDaoTest(){
         
     }
    
   @Before
    public void setUp() throws ClassNotFoundException, SQLException {
        sbd=new SqlBudgetDao("jdbc:postgresql://localhost:5432/budgettool");
    }
    
    @After
    public void tearDown() {
    } 
    
    @Test
    public void connectionNotNull() throws SQLException{
        sbd.getConnection();
        Connection conn = null;
        conn=sbd.getConn();
        assertNotNull(conn);
    }
}
