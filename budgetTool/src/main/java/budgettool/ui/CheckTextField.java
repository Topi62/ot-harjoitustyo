/*
 * 
 * 
 * 
 */
package budgettool.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * Class for input checks 
 * @author tkarkine
 */
public class CheckTextField extends TextField {
    
    public CheckTextField() {
        super();
    }
    
    public CheckTextField(String s) {
        super();
    }
    
    public int toInt() {
        int value;
        try {
            value = Integer.parseInt(this.getText());
        } catch (NumberFormatException n) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Anna luku");
            String s = "Valitse rivi / " + "syötä numero ";
            alert.setContentText(s);
            alert.showAndWait();
            return 0; 
        }
        return value;
    }
    
    public boolean notEmpty() {
        String value;
        value = this.getText();
        if (value.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Tieto vaaditaan");
            String s = "Kenttä ei voi olla tyhjä ";
            alert.setContentText(s);
            alert.showAndWait();
            return false; 
        }
        return true;
    }
}
