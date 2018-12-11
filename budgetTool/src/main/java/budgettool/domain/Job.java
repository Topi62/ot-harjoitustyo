package budgettool.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Job
*
 * @author tkarkine
 */
public class Job {
    private final IntegerProperty id;
    private final StringProperty name;
    private final IntegerProperty owner;
    
    public Job(String name, int owner) {
        // uusi job, jolle haetaan tallentaessa numero tietokannasta
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.owner = new SimpleIntegerProperty(owner);
    }
    
    public Job(int id, String name, int owner) {
        //tietokannasta haettu Job
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.owner = new SimpleIntegerProperty(owner);
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public int getId() {
        return id.get();
    }
    
    public IntegerProperty idProperty() {
        return id;
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public void setOwner(int id) {
        this.owner.set(id);
    }
    
    public int getOwner() {
        return owner.get();
    }
    
    public IntegerProperty ownerProperty() {
        return owner;
    }   
    
    @Override
    public String toString() {
        return "Job id: " + id + " Job name: " + name
                + " job owner: " + owner;
    }
}
