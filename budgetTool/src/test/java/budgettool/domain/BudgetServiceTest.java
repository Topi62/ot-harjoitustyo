/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.domain;

import budgettool.dao.BudgetDao;
import budgettool.dao.FakeBudgetDao;
import budgettool.dao.SqlBudgetDao;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tkarkine
 */
public class BudgetServiceTest {
    BudgetDao data;
    BudgetService service;
    
    
    public BudgetServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        data = new FakeBudgetDao();
        service = new BudgetService(data);
       
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void listAllRows() {
      List<Row> rows;
      rows = service.listRowsByBoolean(1);
      // should think about test result
      assertTrue(rows.isEmpty());
    }

    @Test
    public void listRequestRows() {
      List<Row> rows;
      rows = service.listRowsByBoolean(3);
      // should think about test result
      assertTrue(rows.isEmpty());
    }
    
    @Test
    public void listApprovedRows() {
      List<Row> rows;
      rows = service.listRowsByBoolean(1);
      // should think about test result
      assertTrue(rows.isEmpty());
    }
}
