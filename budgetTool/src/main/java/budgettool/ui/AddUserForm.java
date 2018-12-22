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
import budgettool.domain.User;
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

public class AddUserForm extends GridPane {
    BudgetService service;
    User user;
    int boss;
    VBox vlayout;
    HBox hlayoutBS;
    HBox hlayoutUS;
    HBox hlayoutNC;
    HBox hlayoutButtons;
    Button cancelButton;
    Button okButton;
    Label labelBS;
    TextField textFieldBoss;
    Label labelUS;
    CheckTextField textFieldUS;
    Label labelNC;
    CheckTextField textFieldNC;
    
    public AddUserForm(BudgetService service, int boss) {
        super();
        this.service = service;
        this.boss = boss;
        init();
        textFieldBoss.setText(Integer.toString(boss));
    }

    private void init() {
        vlayout = new VBox(20);
        hlayoutBS = new HBox(10);
        hlayoutUS = new HBox(10);
        hlayoutNC = new HBox(10);
        
        vlayout.setPadding(new Insets(20, 10, 10, 20));
        
        labelBS = new Label("Boss");
        textFieldBoss = new TextField();
        textFieldBoss.setAlignment(Pos.CENTER_RIGHT);
        textFieldBoss.setEditable(false);
        textFieldBoss.setFocusTraversable(false);
        hlayoutBS.getChildren().addAll(textFieldBoss, labelBS);
        
        labelUS = new Label("Type");
        textFieldUS = new CheckTextField();        
        textFieldUS.setAlignment(Pos.CENTER_RIGHT);       
        textFieldUS.setEditable(true);
        textFieldUS.setFocusTraversable(true);
        hlayoutUS.getChildren().addAll(textFieldUS, labelUS);
        
        labelNC = new Label("Name");
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
        okButton = new Button("Add User");
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (textFieldUS.toInt() != 0 && textFieldNC.notEmpty()) {
                    user = new User(textFieldUS.toInt(), textFieldNC.getText(), boss);                
                    service.addUser(user);
                    Node  source = (Node) t.getSource(); 
                    Stage stage  = (Stage) source.getScene().getWindow();
                    stage.close();
                }
            }
        });
        hlayoutButtons.getChildren().addAll(cancelButton, okButton);
        
        vlayout.getChildren().addAll(hlayoutBS, hlayoutUS, hlayoutNC, hlayoutButtons);
        this.getChildren().addAll(vlayout);
    }
}

