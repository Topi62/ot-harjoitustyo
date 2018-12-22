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

public class AddCostForm extends GridPane {
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
    CheckTextField textFieldNC;
    
    // form for editing row
    public AddCostForm(BudgetService service, Row selectedRow) {
        super();
        this.service = service;
        row = selectedRow;
        init();
        textFieldBS.setText(Integer.toString(row.budgetSumProperty().get()));
        textFieldUS.setText(Integer.toString(row.usedSumProperty().get()));
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
        
        labelUS = new Label("Used Sum");
        textFieldUS = new TextField();        
        textFieldUS.setAlignment(Pos.CENTER_RIGHT);       
        textFieldUS.setEditable(false);
        textFieldUS.setFocusTraversable(false);
        hlayoutUS.getChildren().addAll(textFieldUS, labelUS);
        
        labelNC = new Label("New Cost");
        textFieldNC = new CheckTextField();
        textFieldNC.setAlignment(Pos.CENTER_RIGHT);        
        textFieldNC.setEditable(true);
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
        okButton = new Button("Add Cost");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                row.setUsedSum(row.usedSumProperty().get() + textFieldNC.toInt());
                if (row.usedSumProperty().get() > row.budgetSumProperty().get()) {
                    row.exceededProperty().set(true);            
                }
                service.addCostToRow(row);
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