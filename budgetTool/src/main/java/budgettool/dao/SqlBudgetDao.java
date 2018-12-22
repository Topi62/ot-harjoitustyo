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
 * 
 * 
 * @author tkarkine
 */
public final class SqlBudgetDao implements BudgetDao {
    private String databaseAddress;
    private Connection conn;
    private PreparedStatement st;
    private ResultSet res;
    
    /**
    * Tietokannan luonti
    *
    * @param databaseAddress mahdollistaisi tietokannan sijoituksen paremmin
    */

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
    
    /**
    * lyhentää virheiden käsittleyn kirjoitusta
    *
    * 
    */
    
    private void errorMessage(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
     
    /**
    * yhdistää tietokantaan
    *
    * 
    */
    
    public void getConnection()  {
        try {
            conn = DriverManager.getConnection(databaseAddress, "postgres", "admin");
        } catch (SQLException e) {
           // error printing
            errorMessage(e);
        }
    }
    
    /**
    * tekstikäyttöjärjestelmä sql käskyjen toteuttamista
    * hylätty myöhemmin
    * @param lauseet lista suoritettavista sql lauseista
    */
        
    public void executeCommands(List<String> lauseet) {
        getConnection();
        try {
            for (String lause : lauseet) {
                st = conn.prepareStatement(lause);            
                st.executeUpdate();
            }    
            conn.close();
        } catch (SQLException ex) {
            errorMessage(ex);
        } 
    }
    
    /**
    * yksittäisen sql käskyn suoritus
    *
    * @param lause joka suoritetaan SQL
    */
    
    public void executeCommand(String lause) {
        try {
            Statement st2 = conn.createStatement();
            // suoritetaan komento poistettava?
            st2.executeUpdate(lause);
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
            
        }
    }
    
    /**
    * @return  List of Job eli listan haetuista töistä
    *
    * 
    */

    @Override
    public List<Job> getJobs() {
        List<Job> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.prepareStatement("SELECT * FROM \"jobs\";");
            res = st.executeQuery();
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
    
    /**
    * Uuden työn luonti
    * @param name uuden työn nimi
    * @param owner uuden työn työnjohtaaj
    * 
    * @return Job uuden työn tietokantaan tallennettu olio
    */
    
    @Override
    public Job addJob(String name, int owner) {
        Job job = null;
        try {
        //haetaan tietokannasta seuraava numero
            getConnection();
            st = conn.prepareStatement("Select max(id)+1 from \"jobs\";");
            res = st.executeQuery();
            if (res.next()) {
                job = new Job(res.getInt(1), name, owner);
                //lisätään job tietokantaan
                PreparedStatement ps = conn.prepareStatement("INSERT INTO \"jobs\" VALUES (?, ?, ?);");
                ps.setInt(1, job.getId());
                ps.setString(2, name);
                ps.setInt(3, owner);
                ps.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
        return job;
    }
    
    /**
    * Uuden työnjohtajan lisäys
    * @param type 1 päällikkö, 2 työnjohtaja
    * @param name uuden käyttäjän nimi
    * @param boss esimiehen käyttäjäid
    * 
    * @return User käyttäjän tietokantaan tallennettu olio
    */

    @Override
    public User addUser(int type, String name, int boss) {
        User user = null;
        try {        
            getConnection();
            st = conn.prepareStatement("Select max(id)+1 from \"user\";");
            res = st.executeQuery();
            if (res.next()) {
                user = new User(res.getInt(1), type, name, boss);
                st = conn.prepareStatement("INSERT INTO \"user\" VALUES (?, ?, ?, ?);");
                st.setInt(1, user.getId());
                st.setInt(2, user.getType());
                st.setString(3, name);
                st.setInt(4, user.getBoss());
                st.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
        return user; 
    }
    
    /**
    * Budjettirivin lisäys
    * 
    * @param jobid rivi liitetään aina työhön
    * @param resurs rivin resurssia kuvaava nimi 15 char
    * @param requestsum ehdotus budjettisummaksi
    * @param reason syy kustannuksille 50 mrk
    * 
    */

    @Override
    public void addRow(int jobid, String resurs, int requestsum, String reason) {
        try {
        //haetaan tietokannasta seuraava numero
            getConnection();
            st = conn.prepareStatement("Select max(id)+1 from \"rows\";");
            res = st.executeQuery();
            if (res.next()) {
                // add row to budget
                st = conn.prepareStatement("INSERT INTO \"rows\" (id, jobid, resurs, budgetsum, approved, exceeded, request, "
                       + "requestsum, reason) VALUES (?, ?, ?, 0, FALSE, FALSE, TRUE, ?, ?);");
                st.setInt(1, res.getInt(1));
                st.setInt(2, jobid);
                st.setString(3, resurs);
                st.setInt(4, requestsum);
                st.setString(5, reason);
                st.executeUpdate();
            }
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
    }
    
    /**
    * Kulun lisäys budjettiriviin
    * 
    * @param id rivin id, saatu valitsemalal tai syötteenä
    * @param sum kulu, voi olla myös negatiivinen, joka vähentää esim hyvityksen
    * @param exceeded boolean onko budjetti ylitetty
    * 
    * @return true, jos tallennus onnistui. ei käytössä
    */

    @Override
    public boolean addCostToRow(int id, int sum, boolean exceeded) {
        try {
        //haetaan tietokannasta seuraava numero
            getConnection();
            st = conn.prepareStatement("UPDATE \"rows\" set usedsum = ?, exceeded = ? where id = ?;");
            st.setInt(1, sum);
            st.setBoolean(2, exceeded);
            st.setInt(3, id);
            st.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // error printing
            errorMessage(e);
        }
        return true;
    }
    
    /**
    * Ei toteutettu vielä
    * @param id  käyttäjän tunnus
    * @param name käyttäjän nimi 12 merkkiä
    * 
    * @return User kirjautunut käyttäjä
    */

    @Override
    public User loginUser(int id, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
    * Budjettirivien haku eri tekijöillä
    * 
    * @param condition voitaisiin luoda eri hakuehtoja
    * 
    * @return List of Rows ehdon täyttäviä budjettirivejä
    */
    
    @Override
    public List<Row> getRowsByBoolean(String condition) {
        //usage with approved, exceeded or request
        List<Row> rows = new ArrayList<>();
        try {
            getConnection();
            System.out.println(condition);
            st = conn.prepareStatement("SELECT * FROM rows WHERE" + condition + ";");
            // jos käyttää prepare + ? tulee ' merkit ympärille, ja kysely ei toimi
            res = st.executeQuery();
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
    
    /**
    * Työnjohtajien listaus
    * 
    * @param user esimiehen tunnus
    * 
    * @return List of User siis esimiehen alaiset
    */

    @Override
    public List<User> getUsers(int user) {
        List<User> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.prepareStatement("SELECT * FROM \"user\" WHERE (boss)=?;");
            st.setInt(1, user);
            res = st.executeQuery();
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
    
    /**
    * Tietyn työnjohtajan työt
    * 
    * @param userId työnjohtajan tunnus
    * 
    * @return List of Jobs työnjohtajan työt
    */

    @Override
    public List<Job> getUserJobs(int userId) {
        List<Job> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.prepareStatement("SELECT * FROM \"jobs\" WHERE (owner)=?;");
            st.setInt(1, userId);
            res = st.executeQuery();
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
    
    /**
    * Listaa kaikki budjettirivit
    * 
    * @return List of Row jossa kaikki budjettirivit
    */

    @Override
    public List<Row> getRows() {
        List<Row> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.prepareStatement("SELECT * FROM \"rows\";");
            res = st.executeQuery();
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
    
    /**
    * Tietyn työn budjettirivit
    * 
    * @param jobId työn id
    * 
    * @return List of Row työn budjettirivit
    */

    @Override
    public List<Row> getRowsOfJob(int jobId) {
        List<Row> list = new ArrayList<>();
        try {
            getConnection();
            st = conn.prepareStatement("SELECT * FROM \"rows\" WHERE (jobid)=? ORDER BY (id);");
            st.setInt(1, jobId);
            res = st.executeQuery();
            while (res.next()) {
                list.add(new Row(res.getInt("id"), res.getInt("jobid"), res.getString("resurs"),
                    res.getInt("budgetsum"), res.getInt("usedsum"), res.getBoolean("approved"), res.getBoolean("exceeded"),
                    res.getBoolean("request"), res.getInt("requestsum"), res.getString("reason")));
                conn.close();
            }
        } catch (SQLException e) {
            errorMessage(e); 
        }
        return list;
    }
    
    /**
    * Ylityspyynnön hylkääminen
    * 
    * @param id hylättävän pyynnön budjettirivin tunnus
    * 
    */

    @Override
    public void rejectRequest(Integer id) {
        try {
            getConnection();
            st = conn.prepareStatement("UPDATE \"rows\" SET (requested) = 'false', (requestSum) = 0, (reason) = \"\" WHERE (id)= ?;");
            st.setInt(1, id);
            st.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            errorMessage(e);
        }
    }
    
    /**
    * Budjettiehdotuksen hyväksyntä
    * 
    * @param id  budjettirivin tunnus
    * @param exceeded true jos budjettisumma on ylitetty
    * @param budqetSum uusi budjettisumma
    * 
    */

    @Override
    public void acceptRequest(int id, boolean exceeded, int budqetSum) { 
        try {
            getConnection();
            if (exceeded) {
                st = conn.prepareStatement("UPDATE \"rows\" SET approved = 'true', request = 'false', exceeded = 'true', requestSum = 0, budgetSum = ?, reason = null WHERE id= ?;");
            
            } else {
                st = conn.prepareStatement("UPDATE \"rows\" SET approved = 'true', request = 'false', exceeded = 'false', requestSum = 0, budgetSum = ?, reason = null WHERE id = ?;");
            }
            st.setInt(1, budqetSum);
            st.setInt(2, id);
            st.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            errorMessage(e);
        }
    }
    
    /**
    * Uuden budjettiylityksen pyyntö
    * 
    * @param id rivi jota pyyntö koskee
    * @param exceeded onko budjetti jo ylitetty
    * @param requestSum paljonko lisää budjettiin ehdotetaan
    * @param reason syy budjetin kasvattamiselle
    * 
    */

    @Override
    public void addRequest(int id, boolean exceeded, int requestSum, String reason) {
        try {
            getConnection();
            if (exceeded) {
                st = conn.prepareStatement("UPDATE \"rows\" SET approved = 'false', request = 'true', exceeded = 'true', requestSum = ?, reason = ? WHERE id= ?;");
            
            } else {
                st = conn.prepareStatement("UPDATE \"rows\" SET approved = 'false', request = 'true', exceeded = 'false', requestSum = ?, reason = ? WHERE id = ?;");
            }
            st.setInt(1, requestSum);
            st.setString(2, reason);
            st.setInt(3, id);
            st.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            errorMessage(e);
        }
    }
}

