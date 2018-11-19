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
public class SqlBudgetDao implements BudgetDao {
    private String databaseAddress;

    public SqlBudgetDao(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        //mikäli tauluja ei ole luodaan ne
        List<String> lauseet = sqlLauseet();

        // "try with resources" closing automatically
        try (Connection conn = getConnection()) {
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

    private List<String> sqlLauseet() {
        ArrayList<String> list = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        list.add("CREATE TABLE User (id integer PRIMARY KEY, type integer, name varchar(12), boss integer);");
        list.add("INSERT INTO User  VALUES (1,1,'Boss',1);");
        list.add("INSERT INTO User  VALUES (2,2,'Foreman1',2);");
        list.add("CREATE TABLE Jobs (id integer PRIMARY KEY, name varchar(12),owner integer, FOREIGN KEY owner REFERENCES User(id);");
        list.add("INSERT INTO Jobs VALUES (1,'Contract1',2);");

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
}

