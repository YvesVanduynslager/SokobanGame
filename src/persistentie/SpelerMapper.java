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
     * @return 
     */
    public Speler zoek(String gebruikersnaam, String wachtwoord)
    {
        String sqlString = "SELECT * FROM spelers WHERE gebruikersnaam = '" + gebruikersnaam + "'"
                + "AND wachtwoord = '" + wachtwoord + "'";
        databankConnectie = new Connectie();

        speler = new Speler();
        try
        {
            PreparedStatement sqlStatement = databankConnectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next())
            {
                speler.setID(rs.getString("id"));
                speler.setGebruikersnaam(rs.getString("gebruikersnaam"));
                speler.setWachtwoord(rs.getString("wachtwoord"));
                speler.setAdminrechten(rs.getBoolean("adminrechten"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            databankConnectie.sluit();
        }
        return speler;
    }
}
