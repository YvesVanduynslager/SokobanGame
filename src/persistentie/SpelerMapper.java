package persistentie;

import domein.*;
import java.sql.*;

public class SpelerMapper
{

    private Speler speler;
    private Connectie databankConnectie;

    /**
     *
     * @param gebruikersnaam
     * @param wachtwoord
     * @return 
     */
    public Speler zoek(String gebruikersnaam, String wachtwoord)
    {
        String sqlString = "SELECT * FROM Spelers WHERE gebruikersnaam = '" + gebruikersnaam + "'"
                + "AND wachtwoord = '" + wachtwoord + "'";
        
        databankConnectie = new Connectie();
        speler = new Speler();
        
        try
        {
            PreparedStatement sqlStatement = databankConnectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next())
            {
                speler.setID(rs.getString(1));
                speler.setGebruikersnaam(rs.getString(2));
                speler.setWachtwoord(rs.getString(3));
                speler.setAdminrechten((rs.getInt(4) == 1));
            }
        }
        catch (Exception e)
        {
            System.out.println("Fout: " + e.getMessage());
        }
        finally
        {
            databankConnectie.sluit();
        }
        return speler;
    }
}
