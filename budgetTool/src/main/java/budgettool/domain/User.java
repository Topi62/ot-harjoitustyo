package budgettool.domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a User
 * 
 * @author tkarkine
 */ 

public class User {
    private final IntegerProperty id;
    private final IntegerProperty type;
    private final StringProperty name;
    private final IntegerProperty boss;
    
    public User(int type, String name, int boss) {
        //uusi käyttäjä jolle haetaan tietokannasta id ja boss
        this.id = new SimpleIntegerProperty(0);
        this.type = new SimpleIntegerProperty(type);
        this.name = new SimpleStringProperty(name);       
        this.boss = new SimpleIntegerProperty(boss);
    }
    
    public User(int id, int type, String name, int boss) {
        //tietokannasta haettu käyttäjä
        this.id = new SimpleIntegerProperty(id);
        this.type = new SimpleIntegerProperty(type);
        this.name = new SimpleStringProperty(name);       
        this.boss = new SimpleIntegerProperty(boss);
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public void setBoss(int boss) {
        this.boss.set(boss);
    }
    
    public int getId() {
        return id.get();
    }
    
    public IntegerProperty idProperty() {
        return id;
    }
    
    public int getType() {
        return type.get();
    }
    
    public IntegerProperty typeProperty() {
        return type;
    }
    
    public String getName() {
        return name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    public int getBoss() {
        return boss.get();
    }
    
    public IntegerProperty bossProperty() {
        return boss;
    }
    
    @Override
    public String toString() {
        return "id->" + getId() + " " + getName() + " boss ->" + getBoss();
    }
}
