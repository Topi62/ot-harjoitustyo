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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HandleRequestForm extends GridPane {
    BudgetService service;
    Row row;
    VBox vlayout;
    HBox hlayoutBS;
    HBox hlayoutUS;
    HBox hlayoutNC;
    HBox hlayoutButtons;
    Button cancelButton;
    Button okButton;
    Label labelBS;
    TextField textFieldBS;
    Label labelUS;
    TextField textFieldUS;
    Label labelNC;
    TextField textFieldNC;
    
    public HandleRequestForm(BudgetService service, Row selectedRow) {
        super();
        this.service = service;
        row = selectedRow;
        init();
        textFieldBS.setText(Integer.toString(row.budgetSumProperty().get()));
        textFieldUS.setText(Integer.toString(row.usedSumProperty().get()));
        textFieldNC.setText(row.reasonProperty().get());
        cancelButton.requestFocus();
    }

    private void init() {
        vlayout = new VBox(20);
        hlayoutBS = new HBox(10);
        hlayoutUS = new HBox(10);
        hlayoutNC = new HBox(10);
        
        vlayout.setPadding(new Insets(20, 10, 10, 20));
        
        labelBS = new Label("Budget Sum");
        textFieldBS = new TextField();
        textFieldBS.setAlignment(Pos.CENTER_RIGHT);
        textFieldBS.setEditable(false);
        textFieldBS.setFocusTraversable(false);
        hlayoutBS.getChildren().addAll(textFieldBS, labelBS);
        
        labelUS = new Label("Request Sum");
        textFieldUS = new TextField();        
        textFieldUS.setAlignment(Pos.CENTER_RIGHT);       
        textFieldUS.setEditable(false);
        textFieldUS.setFocusTraversable(false);
        hlayoutUS.getChildren().addAll(textFieldUS, labelUS);
        
        labelNC = new Label("Reason");
        textFieldNC = new TextField();
        textFieldNC.setAlignment(Pos.CENTER_LEFT);        
        textFieldNC.setEditable(false);
        hlayoutNC.getChildren().addAll(textFieldNC, labelNC);
        
        
        
        hlayoutButtons = new HBox(20);
        cancelButton = new Button("Reject");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (row.usedSumProperty().get() > row.budgetSumProperty().get()) {
                    row.exceededProperty().set(true);            
                }
                row.approvedProperty().set(false);
                row.reasonProperty().set("");
                row.requestSumProperty().set(0);
                service.rejectRequest(row);
                Node  source = (Node) t.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });    
        okButton = new Button("Approve request");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                row.budgetSumProperty().set(row.budgetSumProperty().get() + row.requestSumProperty().get());
                row.approvedProperty().set(true);
                row.requestProperty().set(false);
                row.reasonProperty().set("");
                row.requestSumProperty().set(0);
                
                if (row.usedSumProperty().get() > row.budgetSumProperty().get()) {
                    row.exceededProperty().set(true);            
                } else {
                    row.exceededProperty().set(false); 
                }
                service.acceptRequest(row);
                Node  source = (Node) t.getSource(); 
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
            }
        });
        hlayoutButtons.getChildren().addAll(cancelButton, okButton);
        
        vlayout.getChildren().addAll(hlayoutBS, hlayoutUS, hlayoutNC, hlayoutButtons);
        this.getChildren().addAll(vlayout);
    }
    
    
}

    

