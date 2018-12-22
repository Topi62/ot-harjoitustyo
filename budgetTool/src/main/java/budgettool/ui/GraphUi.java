
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author tkarkine
 */
public class GraphUi extends AnchorPane implements Ui {
    private BudgetService service;
    private Stage formStage;
    private Scene formScene;
    private VBox vLayout;
    private HBox hLayoutFields;
    private TableView<User> userView;
    private TableView<Job> jobView;
    private TableView<Row> rowView;
    private HBox hLayoutButtons;
    private Label label;
    private CheckTextField textField;
    private Button getUsers;
    private Button addUser;
    private Button getJobs;
    private Button addJob;
    private Button getRows;
    private Button addRow;
    private Button addCost;
    private Button getRequests;
    private int requested;
    private Button handleRequest;
 
    private Button logginButton;
    private Row selectedRow;
    
    private int userLoggedNo;
    private User selectedUser;
    private int selectedJobId;
   
    public GraphUi(BudgetService service) {
        super();
        this.service = service;
        this.userLoggedNo = -1;
        this.requested = 0;
        init();
    }
        
    public void init() {
        
        // Alkunäkymä
        textField = new CheckTextField(""); 
        logginButton = new Button("Log in");
        logginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                loggedIn();
            }
        });
       
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
        addRow = new Button("Add a Row");
        addRow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                addRow();
            }
        });
        addCost = new Button("Add Cost->Row");
        addCost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                addCost();
            }
        });
        handleRequest = new Button("Handle Request"); 
        handleRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                selectedRow = rowView.getSelectionModel().getSelectedItem();
                handleRequest();
            }
        });
        addUser = new Button("addUser"); 
        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                addUser();
            }
        });
        addJob = new Button("addJob"); 
        addJob.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                addJob();
            }
        });
        getRequests = new Button("requests");
        getRequests.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                requested = 1;
                showRows();
            }
        });
        
        vLayout = new VBox(10);
        vLayout.setPadding(new Insets(20, 10, 10, 20));
        hLayoutFields = new HBox(10);
        
        
        label = new Label("Anna User id:");
        hLayoutFields.getChildren().addAll(label, textField);
        hLayoutButtons = new HBox(10);
        hLayoutButtons.getChildren().add(logginButton);
        vLayout.getChildren().addAll(hLayoutFields,  hLayoutButtons);   
       
        this.getChildren().add(vLayout);
    
    }
        
    public void loggedIn() {
        if (textField.notEmpty()) {
            if (userLoggedNo == -1) {
                userLoggedNo = textField.toInt();
            }
            hLayoutButtons.getChildren().remove(logginButton);
            if (userLoggedNo == 1) {
                hLayoutButtons.getChildren().addAll(getUsers, addUser, getJobs, addJob, getRequests);
            } else {
                hLayoutButtons.getChildren().addAll(getJobs, addJob);
         
            }
        }
       
    }

    @Override
    public void showUsers() {       
        userView = new TableView<>();
        if (userLoggedNo != 1) {
            return;
        }
         //hae tiedot näytettäväksi
        ObservableList<User> userData = FXCollections.observableArrayList();
        List<User> users = service.getUsers(userLoggedNo);
        users.forEach((u) -> {
            userData.add(u);
        });
        label.setText("Valitse rivi:");
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
        //kokoa taulunäkymä
        userView.getColumns().addAll(idCol, typeCol, nameCol, bossCol);
        userView.setItems(userData);
        userView.setFixedCellSize(25);
        userView.prefHeightProperty().bind(userView.fixedCellSizeProperty().multiply(Bindings.size(userView.getItems()).add(1.01)));
        userView.minHeightProperty().bind(userView.prefHeightProperty());
        userView.maxHeightProperty().bind(userView.prefHeightProperty());
         //tee tilaa taulunäkymän paikalle
        vLayout.getChildren().clear();
        clearButtons();
        if (userLoggedNo == 1) {
            hLayoutButtons.getChildren().addAll(getUsers, addUser, getJobs, addJob, getRequests);
        } else {
            hLayoutButtons.getChildren().addAll(getJobs, addJob);
        }
        
        //näytä taulunäkymä ja edelliset sen alla
        vLayout.getChildren().addAll(hLayoutFields, userView, hLayoutButtons);  
        
        
    }
    
    @Override
    public void showJobs() {
        ObservableList<Job> jobData = FXCollections.observableArrayList();
        List<Job> jobs;
        //työntekijän valittu?
        if (userView == null) {
            jobs = service.getUserJobs(userLoggedNo);
        
        } else {
            selectedUser = userView.getSelectionModel().getSelectedItem();
            if (selectedUser != null) { 
                jobs = service.getUserJobs(selectedUser.getId());
            } else if (textField.toInt() != 0) {            
                jobs = service.getUserJobs(textField.toInt());
            } else {
                return;
            }
        }
        jobs.forEach((j) -> {
            jobData.add(j);
        });
        
        //muotoile taulunäkymä
        //sarakeotsikot
        TableColumn idCol = new TableColumn("Id");   
        TableColumn nameCol = new TableColumn("Name");
        TableColumn ownerCol = new TableColumn("Owner");  
        //data
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ownerCol.setCellValueFactory(new PropertyValueFactory<>("owner"));
              
        label.setText("Valitse job no:");
        
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
        clearButtons();
        //näytä taulunäkymä ja painikkeet sen alla
        if (userLoggedNo == 1) {
            hLayoutButtons.getChildren().addAll(getUsers, addUser, getJobs, addJob, getRows, getRequests);  
        } else {
            hLayoutButtons.getChildren().addAll(getJobs, addJob, getRows);
        }
        vLayout.getChildren().addAll(hLayoutFields, jobView, hLayoutButtons);  
     
    }

    @Override
    public void showRows() { 
        ObservableList<Row> rowData = FXCollections.observableArrayList();
        List<Row> rows;
        if (requested == 1) {
            rows = service.listRowsByBoolean(3);
        } else if (jobView.getSelectionModel().getSelectedItem() == null) {
            
            if (textField.toInt() > 0) {
                selectedJobId = textField.toInt();
                rows = service.getRowsOfJob(selectedJobId);
            } else {
                label.setText("Valitse työ:");        
                return;
            } 
        } else {
            Job selectedJob = jobView.getSelectionModel().getSelectedItem();
            selectedJobId = selectedJob.getId();
            textField.setText(Integer.toString(selectedJobId));
            rows = service.getRowsOfJob(selectedJobId);
            jobView.getSelectionModel().clearSelection();
        }
        
        rows.forEach((r) -> {
            rowData.add(r);
        }); 
        rowView = new TableView<>();
        
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
       
                 
        //kokoa taulunäkymä
        rowView.getColumns().addAll(idCol, jobIdCol, resursCol, budgetSumCol, usedCol, usedProsCol, 
                approvedCol, exceededCol, requestCol, requestSumCol, reasonCol);
        
        
        //muotoile taulu datan mukaan
        rowView.setItems(rowData);
        rowView.setFixedCellSize(32);
        rowView.prefHeightProperty().bind(rowView.fixedCellSizeProperty().multiply(Bindings.size(rowView.getItems()).add(1.02)));
        rowView.minHeightProperty().bind(rowView.prefHeightProperty());
        rowView.maxHeightProperty().bind(rowView.prefHeightProperty());
        
        //tee tilaa taulunäkymän paikalle
        vLayout.getChildren().clear();
        //näytä taulunäkymä ja painikkeet sen alla
        clearButtons();
        if (userLoggedNo == 1) {
            hLayoutButtons.getChildren().addAll(getUsers, addUser, getJobs, addJob, getRows, addRow, getRequests, handleRequest);
            if (requested == 1) {
                //paluu lomakkeelta aiheuttaa virheen, jos on menty requestkäsittelyn kautta
                hLayoutButtons.getChildren().removeAll(addUser, addJob, getRows, addRow);
                requested = 0;
            }
        } else {
            hLayoutButtons.getChildren().addAll(getJobs, addJob, getRows, addRow, addCost);
        }
        vLayout.getChildren().addAll(hLayoutFields, rowView, hLayoutButtons); 
    }

   
    
    public void addRow() {
        AddRowForm rowForm;
        rowForm = new AddRowForm(service, selectedJobId);
        formScene = new Scene(rowForm, 500, 500);
        formStage = new Stage();
        formStage.setScene(formScene);
        formStage.showAndWait();
    }
    
    public void addCost() {
        AddCostForm addCostForm;
        Row selectedRow = rowView.getSelectionModel().getSelectedItem();
        if (selectedRow == null) {
            return;
        }
        addCostForm = new AddCostForm(service, selectedRow);
        formScene = new Scene(addCostForm, 500, 500);
        formStage = new Stage();
        formStage.setScene(formScene);
        formStage.showAndWait();
    }
    
    public void addUser() {
        AddUserForm addUserForm;
        addUserForm = new AddUserForm(service, userLoggedNo);
        formScene = new Scene(addUserForm, 500, 500);
        formStage = new Stage();
        formStage.setScene(formScene);
        formStage.showAndWait();
    }
    
    public void addJob() {
        AddJobForm addJobForm;
        addJobForm = new AddJobForm(service);
        formScene = new Scene(addJobForm, 500, 500);
        formStage = new Stage();
        formStage.setScene(formScene);
        formStage.showAndWait();
        
    }
    
    public void handleRequest() {
        HandleRequestForm handleRequest;
        handleRequest = new HandleRequestForm(service, selectedRow);
        formScene = new Scene(handleRequest, 500, 500);
        formStage = new Stage();
        formStage.setScene(formScene);
        formStage.showAndWait();
    }
    
    public void clearButtons() {
        hLayoutButtons.getChildren().removeAll(getUsers, addUser, getJobs, addJob, getRows, addRow, addCost, getRequests, handleRequest);
    }
}
    
    

   
    

