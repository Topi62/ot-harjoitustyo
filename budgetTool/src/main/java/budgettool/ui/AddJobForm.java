/*
 * 
 * 
 * 
 */
package budgettool.ui;

/**
 *
 * @author tkarkine
 */
import budgettool.domain.BudgetService;
import budgettool.domain.Job;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddJobForm extends GridPane {
    BudgetService service;
    Job job;
    VBox vlayout;
    HBox hlayoutBS;
    HBox hlayoutUS;
    HBox hlayoutNC;
    HBox hlayoutButtons;
    Button cancelButton;
    Button okButton;
    Label labelBS;
    CheckTextField textFieldOwner;
    Label labelNC;
    CheckTextField textFieldNC;
    
    public AddJobForm(BudgetService service) {
        super();
        this.service = service;
        init();
    }

    private void init() {
        vlayout = new VBox(20);
        hlayoutBS = new HBox(10);
        hlayoutUS = new HBox(10);
        hlayoutNC = new HBox(10);
        
        vlayout.setPadding(new Insets(20, 10, 10, 20));
        
        labelBS = new Label("Owner");
        textFieldOwner = new CheckTextField();
        textFieldOwner.setAlignment(Pos.CENTER_LEFT);
        hlayoutBS.getChildren().addAll(textFieldOwner, labelBS);        
        
        labelNC = new Label("Name");
        textFieldNC = new CheckTextField();
        textFieldNC.setAlignment(Pos.CENTER_LEFT);        
        hlayoutNC.getChildren().addAll(textFieldNC, labelNC);
        
        
        
        hlayoutButtons = new HBox(20);
        cancelButton = new Button("Cancel");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                Node  source = (Node) t.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });    
        okButton = new Button("Add Job");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (!textFieldNC.notEmpty() || textFieldOwner.toInt() == 0) {
                    return;
                }                
                job = new Job(textFieldNC.getText(), textFieldOwner.toInt());
                
                service.addJob(job);
                Node  source = (Node) t.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
        hlayoutButtons.getChildren().addAll(cancelButton, okButton);
        
        vlayout.getChildren().addAll(hlayoutBS, hlayoutNC, hlayoutButtons);
        this.getChildren().addAll(vlayout);
    }
    
    
}
    

