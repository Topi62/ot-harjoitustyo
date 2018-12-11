package budgettool.dao;

import budgettool.domain.Job;
import budgettool.domain.Row;
import budgettool.domain.User;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.h2.tools.RunScript;

/**
 * Class for Database connection
 * 
 * @author tkarkine
 */
public final class SqlBudgetDao implements BudgetDao {
    private String databaseAddress;
    private Connection conn;
    private Statement st;
    private ResultSet res;

    public SqlBudgetDao(String databaseAddress) {
        this.databaseAddress = databaseAddress;
        try {
            conn = DriverManager.getConnection(databaseAddress, "postgres", "admin");
            if (conn != null) {
                //testing connection and create tables if not exists
//              ResultSet rs = conn.getMetaData().getTables(null, null, "rows", null);
//               while (!rs.next()) { 
                try {
                    RunScript.execute(conn, new FileReader("sql/database-tables.sql"));
                    RunScript.execute(conn, new FileReader("sql/database-data.sql"));
                    conn.close();
                } catch (FileNotFoundException | SQLException e) {
                    errorMessage(e);
                } 
//              }      
            } 
        } catch (SQLException e) {
            errorMessage(e);
        }
       
    }
    
    private void errorMessage(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
     
    public void getConnection()  {
        try {
            conn = DriverManager.getConnection(databaseAddress, "postgres", "admin");
        } catch (SQLException e) {
           // error printing
            errorMessage(e);
        }
    }
        
    public void executeCommands(List<String> lauseet) {
        getConnection();
        try {
            st = conn.createStatement();
            for (String lause : lauseet) {
                st.executeUpdate(lause);
            }    
            conn.close();
        } catch (SQLException ex) {
            errorMessage(ex);
        } 
    }
    
    public void executeCommand(String lause) {
        try {
            Statement st2 = conn.createStatement();
            // suoritetaan komento
            st2.executeUpdate(lause);
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
            
        }
    }
    
    public ResultSet executeQuery(String question) {
        // poistettava, conn sulkeminen sulkee resultSetin
        res = null;
        // "try with resources" closing automatically
        try  {
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery(question);
            
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
        return res;
    }

    @Override
    public List<Job> getJobs() {
        List<Job> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("SELECT * FROM \"jobs\";");
            while (res.next()) {
                list.add(new Job(res.getInt("id"),
                    res.getString("name"),
                    res.getInt("owner")
                ));
                conn.close();
            }
        } catch (SQLException e) {
            errorMessage(e);
        }
        return list;
    }
    
    @Override
    public Job addJob(String name, int owner) {
        Job job = null;
        try {
        //haetaan tietokannasta seuraava numero
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("Select max(id)+1 from \"jobs\";");
            if (res.next()) {
                job = new Job(res.getInt(1), name, owner);
                //lisätään job tietokantaan
                executeCommand("INSERT INTO \"jobs\" VALUES (" + job.getId() +
                    ", '" + name + "', " + owner + ");");
            }
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
        return job;
    }

    @Override
    public User addUser(int type, String name, int boss) {
        User user = null;
        try {
        //haetaan tietokannasta seuraava numero
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("Select max(id)+1 from \"user\";");
            if (res.next()) {
                user = new User(res.getInt(1), type, name, boss);
                //lisätään job tietokantaan
                executeCommand("INSERT INTO \"user\" VALUES (" + user.getId() +
                    ", " + type + ", '" + name + "', " + boss + ");");
            }
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
        return user; 
    }

    @Override
    public void addRow(int jobid, String resurs, int budgetsum) {
        try {
        //haetaan tietokannasta seuraava numero
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("Select max(id)+1 from \"rows\";");
            if (res.next()) {
               // add row to budget
                executeCommand("INSERT INTO \"rows\" (id, jobid, resurs, budgetsum) VALUES (" + res.getInt(1) +
                    ", " + jobid + ", '" + resurs + "', " + budgetsum + ");");
            }
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
    }

    @Override
    public boolean addCostToRow(int id, int sum) {
        try {
        //haetaan tietokannasta seuraava numero
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("Select usedsum from \"rows\" where id = " + id + ";");
            if (res.next()) {
               // add row to budget
                executeCommand("UPDATE \"rows\" set (usedsum)= " + (res.getInt(1) +
                    sum) + " WHERE (id)= " + id + ";");
            }
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
        return true;
    }

    @Override
    public User loginUser(int id, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Row> getRowsByBoolean(String condition) {
        //usage with approved, exceeded or request
        List<Row> rows = new ArrayList<>();
        try {
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("SELECT * FROM \"rows\" WHERE " + condition + ";");
            while (res.next()) {
                rows.add(new Row(res.getInt("id"), res.getInt("jobid"),
                    res.getString("resurs"), res.getInt("budgetSum"), res.getInt("usedSum"),
                    res.getBoolean("approved"), res.getBoolean("exceeded"), res.getBoolean("request"),
                    res.getInt("requestSum"), res.getString("reason")
                ));
                conn.close();
            }
        } catch (SQLException e) {
            errorMessage(e);
        }
        return rows; 
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("SELECT * FROM \"user\";");
            while (res.next()) {
                list.add(new User(res.getInt("id"),
                    res.getInt("type"),    
                    res.getString("name"),
                    res.getInt("boss")
                ));
                conn.close();
            }
        } catch (SQLException e) {
            errorMessage(e);
        }
        return list;
    }

    @Override
    public List<Job> getUserJobs(int userId) {
        List<Job> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("SELECT * FROM \"jobs\" WHERE (owner)=" + userId + ";");
            while (res.next()) {
                list.add(new Job(res.getInt("id"),
                    res.getString("name"),
                    res.getInt("owner")
                ));
                conn.close();
            }
        } catch (SQLException e) {
            errorMessage(e);
        }
        return list;
    }

    @Override
    public List<Row> getRows() {
        List<Row> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("SELECT * FROM \"rows\";");
            while (res.next()) {
                list.add(new Row(res.getInt("id"),
                    res.getInt("jobid"), res.getString("resurs"),
                    res.getInt("budgetsum"), res.getInt("usedsum"),
                    res.getBoolean("approved"), res.getBoolean("exceeded"),
                    res.getBoolean("request"), res.getInt("requestsum"),    
                    res.getString("reason")));
                conn.close();
            }
        } catch (SQLException e) {
            errorMessage(e);
        }
        return list;
    }

    @Override
    public List<Row> getRowsOfJob(int jobId) {
        List<Row> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.createStatement();
            res = st.executeQuery("SELECT * FROM \"rows\" WHERE (jobid)=" + jobId + ";");
            while (res.next()) {
                list.add(new Row(res.getInt("id"),
                    res.getInt("jobid"), res.getString("resurs"),
                    res.getInt("budgetsum"), res.getInt("usedsum"),
                    res.getBoolean("approved"), res.getBoolean("exceeded"),
                    res.getBoolean("request"), res.getInt("requestsum"),    
                    res.getString("reason")));
                conn.close();
            }
        } catch (SQLException e) {
            errorMessage(e);
        }
        return list;
    }
}

