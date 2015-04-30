package persistentie;

import java.sql.*;

/**
 * Static klasse Connectie verzorgt het openen en sluiten van een
 * databankconnectie.
 *
 * @author Yves
 */
public final class Connectie
{
    //verander mysqlUser en mysqlPassword met je persoonlijke login gegevens
    private static final String MYSQL_USER = "root", MYSQL_PASSWORD = "root";
    //database-locatie en driver:
    private static final String JDBC = "jdbc:mysql://localhost:3306/sokobandatabase?user=" + MYSQL_USER + "&password=" + MYSQL_PASSWORD;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static Connection conn = null;

    /**
     * Opent een connectie met de databank.
     */
    public static void start()
    {
        try
        {
            Class.forName(JDBC_DRIVER).newInstance();
            conn = DriverManager.getConnection(JDBC);
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e)
        {
            System.err.println("--- Fout: " + e.getClass() + ": " + e.getMessage());
        }
    }

    /**
     * Geeft de geopende connectie terug.
     *
     * @return De geopende connectie met de databank.
     */
    public static Connection getDatabaseConnectie()
    {
        return conn;
    }

    /**
     * Deze methode sluit de geopende connectie met de databank.
     */
    public static void sluit()
    {
        try
        {
            conn.close();
        }
        catch (Exception e)
        {
            System.out.println("--- Fout: " + e.getClass() + ": " + e.getMessage());
        }
    }
}
