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
    private String databaseAddress;
    private Connection conn;
    private ResultSet res;

    public SqlBudgetDao(String databaseAddress) throws ClassNotFoundException, SQLException {
       this.databaseAddress = databaseAddress;
       
        if (databaseNotExist()){
            createDatabase("budgettool");
        }
        //jos tauluja ei ole luotu, tee nämä
//        List<String> lauseet = createTables();
//        executeCommands(lauseet);
        
    }
     
    public Connection getConn(){
        return conn;
    }

    public void getConnection()  {
        try {
            conn = DriverManager.getConnection(databaseAddress, "postgres","admin");
        } catch (SQLException e) {
           // error printing
            System.out.println("Error get connection >> " + e.getMessage());
        }
    }
    
    public void closeConnection() throws SQLException{
        conn.close();
    }
    
    public boolean databaseNotExist(){
        //not know yet how to test if exsist
        
        return false;
    }
    
    public void createDatabase(String databasename) throws SQLException{
        getConnection();
        Statement statement = conn.createStatement();
        statement.executeUpdate("CREATE DATABASE budgettool");
        closeConnection();
        databaseAddress+="budgettool";
        List<String> lauseet = createTables();
        executeCommands(lauseet);
    }
    
    public void executeCommands(List<String> lauseet){
        try  {
            getConnection();
            Statement st = conn.createStatement();
            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("suoritetaan: "+lause);
               st.executeUpdate(lause);
               
            }
            closeConnection();
        } catch (SQLException e) {
            // error printing
            System.out.println("Error >> " + e.getMessage());
        }
    }
    
     public void executeCommand(String lause){
       try  {
            getConnection();
            Statement st = conn.createStatement();
            // suoritetaan komento
             st.executeUpdate(lause);
             closeConnection();
        } catch (SQLException e) {
            // error printing
            System.out.println("Error >> " + e.getMessage());
        }
    }
    
     public ResultSet executeQuery(String question) {
        
         res = null;
        // "try with resources" closing automatically
        try  {
            getConnection();
            Statement ps = conn.createStatement();
            res = ps.executeQuery(question);
          } catch (SQLException e) {
            // error printing
            System.out.println("Error on query >> " + e.getMessage());
        }
        return res;
    }

    private List<String> createTables(){
        ArrayList<String> list = new ArrayList<>();

         //tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
       // list.add("CREATE TABLE \"user\" IF NOT EXIST (id INT PRIMARY KEY NOT NULL, type INT, name varchar(12), boss INT);");
       // list.add("INSERT INTO \"user\"  VALUES (1,1,'Boss',1);");
        //list.add("INSERT INTO \"user\"  VALUES (2,2,'Foreman1',2);");
        //list.add("CREATE TABLE \"jobs\"  (id INT PRIMARY KEY NOT NULL , name varchar(12),owner INT REFERENCES \"user\" (id));");
        //list.add("INSERT INTO \"jobs\" VALUES (2,'Contract2',2);");

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
        res = executeQuery("SELECT * FROM \"jobs\";");
          try {
            while (res.next()){
               list.add(new Job (res.getInt("id"),
                res.getString("name"),
                res.getInt("owner")
                ));
               closeConnection();
            }
        } catch (SQLException e) {
            System.out.println("Error >> " + e.getMessage());
        }
        return list;
    }
    
    @Override
    public Job addJob(String name, int owner){
        Job job = null;
        //haetaan tietokannasta seuraava numero
        String question ="Select max(id)+1 from \"jobs\";";
        ResultSet rs=executeQuery(question);
        
          try {
              if(rs.next()){
            job=new Job(rs.getInt(1),name,owner);
            //lisätään job tietokantaan
            executeCommand("INSERT INTO \"jobs\" VALUES ("+ job.getId() +
                    ", '" + name + "', " + owner +");");
            
              }
          } catch (SQLException e) {
            // error printing
            System.out.println("Error >> " + e.getMessage());
           
        }
          return job;
    }
}

