package budgettool.ui;

/*
 * Budgettool Application
 * 
 * @author Toivo Kärkinen
 */

import budgettool.dao.SqlBudgetDao;
import budgettool.domain.BudgetService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private SqlBudgetDao database;
    private BudgetService service;
    

    @Override
    public void start(Stage stage) throws Exception {
        database = new SqlBudgetDao("jdbc:postgresql://localhost:5432/budgettool");
        service = new BudgetService(database);
       
           
        GraphUi mainWindow = new GraphUi(service);
        

        Scene scene = new Scene(mainWindow, 1000, 400);
        
        stage.setTitle("Budgettool");
        stage.setScene(scene);
        stage.show(); 
    }
    
    public static void main(String[] args)  {
        launch(args);
    }
}
    
    
        
        
    
    
