
package budgettool.ui;

/*
 * Class for Grafics
 *
 * @author T. Kärkinen
 */

import budgettool.domain.BudgetService;
import budgettool.domain.Job;
import budgettool.domain.Row;
import budgettool.domain.User;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author tkarkine
 */
public class GraphUi extends AnchorPane implements Ui {
    private BudgetService service;
    private VBox vLayout;
    private HBox hLayoutFields;
    private TableView<User> userView;
    private TableView<Job> jobView;
    private TableView<Row> rowView;
    private HBox hLayoutButtons;
    private Label label;
    private TextField textField;
    private Button getUsers;
    private Button getJobs;
    private Button getRows;
    private Button acceptRequest;
    
    public GraphUi(BudgetService service) {
        super();
        this.service = service;
        
        // Alkunäkymä
        textField = new TextField(""); 
       
        getUsers = new Button("Users");
        getUsers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                showUsers();
            }
        });
        getJobs = new Button("Jobs");
        getJobs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                showJobs();
            }
        });
        getRows = new Button("Rows");
        getRows.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                showRows();
            }
        });
        acceptRequest = new Button("Accept Row"); 
        acceptRequest.disableProperty().set(true);
        
        hLayoutButtons = new HBox(10);
        hLayoutButtons.getChildren().addAll(getUsers, getJobs, getRows, acceptRequest);
       
        
        vLayout = new VBox(10);
        vLayout.setPadding(new Insets(20, 10, 10, 20));
        hLayoutFields = new HBox(10);
        
        
        label = new Label("User id:");
        hLayoutFields.getChildren().addAll(label, textField);
        vLayout.getChildren().addAll(hLayoutFields,  hLayoutButtons);   
       
        this.getChildren().add(vLayout);
    
    }

   

    @Override
    public void showUsers() {
        //muotoile taulunäkymä ja sarakeotsikot
        TableColumn idCol = new TableColumn("Id");   
        TableColumn typeCol = new TableColumn("Type");
        TableColumn nameCol = new TableColumn("Name");
        TableColumn bossCol = new TableColumn("Boss");  
        //data
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bossCol.setCellValueFactory(new PropertyValueFactory<>("boss"));
              
        //hae tiedot näytettäväksi
        ObservableList<User> userData = FXCollections.observableArrayList();
        List<User> users = service.getUsers();
        users.forEach((u) -> {
            userData.add(u);
        });
                 
        //kokoa taulunäkymä
        userView = new TableView<>();
        userView.getColumns().addAll(idCol, typeCol, nameCol, bossCol);
        userView.setItems(userData);
        userView.setFixedCellSize(25);
        userView.prefHeightProperty().bind(userView.fixedCellSizeProperty().multiply(Bindings.size(userView.getItems()).add(1.01)));
        userView.minHeightProperty().bind(userView.prefHeightProperty());
        userView.maxHeightProperty().bind(userView.prefHeightProperty());
        
        //tee tilaa taulunäkymän paikalle
        vLayout.getChildren().clear();
        //näytä taulunäkymä ja edelliset sen alla
        vLayout.getChildren().addAll(hLayoutFields, userView, hLayoutButtons);      
    }
    
    @Override
    public void showJobs() {
        //muotoile taulunäkymä
        //sarakeotsikot
        TableColumn idCol = new TableColumn("Id");   
        TableColumn nameCol = new TableColumn("Name");
        TableColumn ownerCol = new TableColumn("Owner");  
        //data
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ownerCol.setCellValueFactory(new PropertyValueFactory<>("owner"));
              
        //hae tiedot näytettäväksi
        ObservableList<Job> jobData = FXCollections.observableArrayList();
        List<Job> jobs = service.getJobs();
        jobs.forEach((j) -> {
            jobData.add(j);
        });
                 
        //kokoa taulunäkymä
        jobView = new TableView<>();
        jobView.getColumns().addAll(idCol,  nameCol, ownerCol);
        
        
        //muotoile taulu datan mukaan
        jobView.setItems(jobData);
        jobView.setFixedCellSize(25);
        jobView.prefHeightProperty().bind(jobView.fixedCellSizeProperty().multiply(Bindings.size(jobView.getItems()).add(1.01)));
        jobView.minHeightProperty().bind(jobView.prefHeightProperty());
        jobView.maxHeightProperty().bind(jobView.prefHeightProperty());
        
        //tee tilaa taulunäkymän paikalle
        vLayout.getChildren().clear();
        //näytä taulunäkymä ja painikkeet sen alla
        vLayout.getChildren().addAll(hLayoutFields, jobView, hLayoutButtons);  
     
    }

    @Override
    public void showRows() { 
        //muotoile taulunäkymä
        //sarakeotsikot
        TableColumn idCol = new TableColumn("Id");   
        TableColumn jobIdCol = new TableColumn("Jobid");
        TableColumn resursCol = new TableColumn("Resurs");
        TableColumn budgetSumCol = new TableColumn("BudgetSum");   
        TableColumn usedCol = new TableColumn("Used");
        TableColumn usedProsCol = new TableColumn("Used%");  
        TableColumn approvedCol = new TableColumn("Approved");   
        TableColumn exceededCol = new TableColumn("Exceeded");
        TableColumn requestCol = new TableColumn("Request");
        TableColumn requestSumCol = new TableColumn("RequestSum");
        TableColumn reasonCol = new TableColumn("Reason");  
        
        //data
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        jobIdCol.setCellValueFactory(new PropertyValueFactory<>("jobId"));
        resursCol.setCellValueFactory(new PropertyValueFactory<>("resurs"));
        budgetSumCol.setCellValueFactory(new PropertyValueFactory<>("budgetSum"));
        usedCol.setCellValueFactory(new PropertyValueFactory<>("usedSum"));
        usedProsCol.setCellValueFactory(new PropertyValueFactory<>("usedPros"));
        approvedCol.setCellValueFactory(new PropertyValueFactory<>("approved"));
        exceededCol.setCellValueFactory(new PropertyValueFactory<>("exceeded"));
        requestCol.setCellValueFactory(new PropertyValueFactory<>("request"));
        requestSumCol.setCellValueFactory(new PropertyValueFactory<>("requestSum"));
        reasonCol.setCellValueFactory(new PropertyValueFactory<>("reason"));
              
        //hae tiedot näytettäväksi
        ObservableList<Row> rowData = FXCollections.observableArrayList();
        List<Row> rows = service.getRows();
        rows.forEach((r) -> {
            rowData.add(r);
        });
                 
        //kokoa taulunäkymä
        rowView = new TableView<>();
        rowView.getColumns().addAll(idCol, jobIdCol, resursCol, budgetSumCol, usedCol, usedProsCol, 
                approvedCol, exceededCol, requestCol, requestSumCol, reasonCol);
        
        
        //muotoile taulu datan mukaan
        rowView.setItems(rowData);
        rowView.setFixedCellSize(25);
        rowView.prefHeightProperty().bind(rowView.fixedCellSizeProperty().multiply(Bindings.size(rowView.getItems()).add(1.01)));
        rowView.minHeightProperty().bind(rowView.prefHeightProperty());
        rowView.maxHeightProperty().bind(rowView.prefHeightProperty());
        
        //tee tilaa taulunäkymän paikalle
        vLayout.getChildren().clear();
        //näytä taulunäkymä ja painikkeet sen alla
        vLayout.getChildren().addAll(hLayoutFields, rowView, hLayoutButtons); 
    }
    
    

   
    
}
