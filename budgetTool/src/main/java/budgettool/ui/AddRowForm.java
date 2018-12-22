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
import budgettool.domain.Row;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddRowForm extends GridPane {
    BudgetService service;
    Row row;
    int jobId;
    VBox vlayout;
    HBox hlayoutJI;
    HBox hlayoutRS;
    HBox hlayoutRR;
    HBox hlayoutRN;
    HBox hlayoutButtons;
    Button cancelButton;
    Button okButton;
    Label labelJI;
    TextField textFieldJI;    
    Label labelRN;
    CheckTextField textFieldRN;
    Label labelRR;
    CheckTextField textFieldRR;
    Label labelRS;
    CheckTextField textFieldRS;
    
    // form for editing row
    public AddRowForm(BudgetService service, int jobId) {
        super();
        this.service = service;
        this.jobId = jobId;
        init();
        textFieldJI.setText(Integer.toString(jobId));
    }

    private void init() {
        vlayout = new VBox(20);
        hlayoutJI = new HBox(10);
        hlayoutRS = new HBox(10);
        hlayoutRR = new HBox(10);
        hlayoutRN = new HBox(10);
        
        vlayout.setPadding(new Insets(20, 10, 10, 20));
        
        labelJI = new Label("Job Id");
        textFieldJI = new TextField();        
        textFieldJI.setAlignment(Pos.CENTER_RIGHT);       
        textFieldJI.setEditable(false);
        textFieldJI.setFocusTraversable(false);
        hlayoutJI.getChildren().addAll(textFieldJI, labelJI);
        
        
        labelRN = new Label("Resurs Name");
        textFieldRN = new CheckTextField();
        hlayoutRN.getChildren().addAll(textFieldRN, labelRN);
        
        labelRS = new Label("Reques Sum");
        textFieldRS = new CheckTextField();
        textFieldRS.setAlignment(Pos.CENTER_RIGHT);
        hlayoutRS.getChildren().addAll(textFieldRS, labelRS);
        
        
        labelRR = new Label("Reason:");
        textFieldRR = new CheckTextField();
        hlayoutRR.getChildren().addAll(labelRR, textFieldRR);
        
        
        
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
        okButton = new Button("Add Row");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (textFieldRS.toInt() == 0) {
                    return;
                }
                if (!textFieldRN.notEmpty()) {
                    return;
                }               
                if (!textFieldRR.notEmpty()) {
                    return;
                } 
                row = new Row(jobId, textFieldRN.getText(), textFieldRS.toInt(), textFieldRR.getText());
                service.addRow(row);
                Node  source = (Node) t.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();                
            }
        });
        hlayoutButtons.getChildren().addAll(cancelButton, okButton);
        vlayout.getChildren().addAll(hlayoutJI, hlayoutRN, hlayoutRS, hlayoutRR, hlayoutButtons);
        this.getChildren().addAll(vlayout);
    } 
}