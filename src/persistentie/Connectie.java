package persistentie;

import java.sql.*;
/**
 * 
 * @author Yves
 * Maakt een connectie met de sokobandb-databank via mysql-connector.
 */
public class Connectie
{
    private final String JDBC = "jdbc:mysql://localhost:3306/sokobandb?user=root&password=yamahar1";
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
            System.out.println(e.getMessage());
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
            e.printStackTrace();
        }
    }
}