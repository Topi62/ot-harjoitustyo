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
public class Job {
    private int id;
    private String name;
    private int owner;
    
    public Job(String name, int owner) {
        // uusi job, jolle haetaan tallentaessa numero tietokannasta
        this.name = name;
        this.owner = owner;
    }
    
    public Job(int id, String name, int owner) {
        //tietokannasta haettu Job
        this.id = id;
        this.name = name;
        this.owner = owner;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getOwner() {
        return owner;
    }
    
    
    @Override
    public String toString() {
        return "Job id: " + id + " Job name: " + name
                + " job owner: " + owner;
    }
}
