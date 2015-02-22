package persistentie;

import java.sql.*;
/**
 * Maakt een connectie met de sokobandb-databank via mysql-connector.
 * 
 * @author Yves
 */
public class Connectie
{

    //verander mysqlUser en mysqlPassword met je persoonlijke login gegevens!
    private final String mysqlUser = "root", mysqlPassword = "Gent1986";

    //database-locatie en driver:
    private final String JDBC = "jdbc:mysql://localhost:3306/sokobandatabase?user=" + mysqlUser + "&password=" + mysqlPassword;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    private Connection conn = null;

    /**
     * Constructor aanroep maakt connectie met db
     */
    public Connectie()
    {
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(JDBC);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e)
        {
            System.out.println("--- Fout: " + e.getClass() + ": " + e.getMessage());
        }
    }
    
    /**
     * 
     * @return De connectie met de databank.
     */
    public Connection getDatabaseConnectie()
    {
        return conn;
    }
    
    /**
     * Deze methode sluit de geopende connectie met de databank.
     */
    public void sluit()
    {
        try
        {
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println("--- Fout: " + e.getClass() + ": " + e.getMessage());
        }
    }
}
