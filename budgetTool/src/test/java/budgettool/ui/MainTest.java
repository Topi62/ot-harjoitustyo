/*
 * 
 * 
 * 
 */

import budgettool.dao.BudgetDao;
import budgettool.dao.SqlBudgetDao;
import budgettool.domain.BudgetService;
import budgettool.ui.GraphUi;
import java.io.PrintStream;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.loadui.testfx.Assertions.verifyThat;
import static org.loadui.testfx.controls.Commons.hasText;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasChildren;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 *
 * @author tkarkine
 */
public class MainTest extends ApplicationTest {
    BudgetDao database;
    BudgetService service;
    Stage stage;
    GraphUi mainWindow;
    Scene scene;
    FxRobot robot;
    
    public MainTest() {
        database = new SqlBudgetDao("jdbc:postgresql://localhost:5432/budgettool");
        service = new BudgetService(database);
        robot = new FxRobot();
        
       
    }
    
    @Override
        public void start (Stage stage) throws Exception {
        this.stage = stage;
        mainWindow = new GraphUi(service);
        

        scene = new Scene(mainWindow, 1000, 400);
        
        stage.setTitle("Budgettool");
        stage.setScene(scene);
        stage.show(); 
       
       
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
            
     
    }
    
    @After
    public void tearDown() {
    }
    
     @Test
    public void boss_see_users() {
        //Given mainwindow at textField
//      // when:
        write("1");
        robot.clickOn("Log in");
        // then:
        //ensimmäinen button Users
        verifyThat(".button", hasText("Users"));
    }
    
    @Test
    public void user_2_not_see_users() {
        //Given mainwindow at textField
        write("2");
        
        robot.clickOn("Log in");
        // ensimääinen button Jobs
        verifyThat(".button", hasText("Jobs"));
        
    }
    
}
