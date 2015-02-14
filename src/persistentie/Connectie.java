package persistentie;

import java.sql.*;

public class Connectie
{
    //private final String JDBC_URL = "jdbc:mysql://localhost:3306/sokobandb?user=root&password=yamahar1";

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private final String JDBC_DBNAAM = "sokobandb";
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String JDBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "yamahar1";
    
    private Connection conn = null;

    /**
     * Constructor aanroep maakt connectie met db
     */
    public Connectie()
    {
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(JDBC_URL + JDBC_DBNAAM, JDBC_USERNAME, JDBC_PASSWORD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public Connection getDatabaseConnectie()
    {
        return conn;
    }
    /**
     * sluiten van connectie
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