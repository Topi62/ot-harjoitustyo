/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package budgettool.dao;

import budgettool.domain.Job;
import budgettool.domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tkarkine
 */
public final class SqlBudgetDao implements BudgetDao {
    private final String databaseAddress;
    private Connection conn;

    public SqlBudgetDao(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }
   

    public void getConnection(String databaseAddress) throws SQLException {
        conn = DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        //mikäli tauluja ei ole luodaan ne
        List<String> lauseet = createTables();
        executeCommands(lauseet);
        
    }
    
    public void executeCommands(List<String> lauseet){
       
        // "try with resources" closing automatically
        try  {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
               st.executeUpdate(lause);
               
            }

        } catch (Throwable t) {
            // error printing
            System.out.println("Error >> " + t.getMessage());
        }
    }
    
     public ResultSet executeQuery(String question){
         ResultSet res = null;
        // "try with resources" closing automatically
        try  {
           
            Statement ps = conn.createStatement();
            res = ps.executeQuery(question);
            return res;

        } catch (Throwable t) {
            // error printing
            System.out.println("Error >> " + t.getMessage());
            return res;
        }
    }

    private List<String> createTables(){
        ArrayList<String> list = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
//        list.add("CREATE TABLE User (id integer PRIMARY KEY, type integer, name varchar(12), boss integer);");
//        list.add("INSERT INTO User  VALUES (1,1,'Boss',1);");
//        list.add("INSERT INTO User  VALUES (2,2,'Foreman1',2);");
  //      list.add("CREATE TABLE Jobs (id integer , name varchar(12),owner integer, PRIMARY KEY (id), FOREIGN KEY (owner) REFERENCES User(id));");
        list.add("INSERT INTO Jobs VALUES (2,'Contract2',2);");

        return list;
    }

    @Override
    public boolean saveNewUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveNewJob(Job job) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Job> getJobs() {
        //ei vielä toteutettu hakua, näytetään uin valmis lista
        List<Job> list=new ArrayList<>();
        return list;
    }
    
    @Override
    public Job addJob(String name, int owner){
        Job job = null;
        
      String question ="Select max(id)+1 from Jobs;";
       
      try {
          ResultSet rs=executeQuery(question);
       job=new Job(rs.getInt(1),name,owner);
        } catch (Throwable t) {
            // error printing
            System.out.println("Error >> " + t.getMessage());
        
         
        }
        return job;
      
       
    }
}

