/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.domain;

/**
 *
 * @author tkarkine
 */
public class User {
    private int id;
    private int type;
    private String name;
    private int boss;
    
    public User(int type, String name) {
        //uusi käyttäjä jolle haetaan tietokannasta id ja boss
        this.type = type;
        this.name = name;
    }
    
    public User(int id, int type, String name, int boss) {
        //tietokannasta haettu käyttäjä
        this.id = id;
        this.type = type;
        this.name = name;
        this.boss = boss;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setBoss(int boss) {
        this.boss = boss;
    }
    
    public int getId() {
        return id;
    }
    
    public int getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
    public int getBoss() {
        return boss;
    }
    
    @Override
    public String toString() {
        return "id->" + id + " " + name + " boss ->" + boss;
    }
}
