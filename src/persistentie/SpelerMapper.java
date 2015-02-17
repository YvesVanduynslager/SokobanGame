package persistentie;

import domein.*;
import java.sql.*;
/**
 * 
 * @author Yves
 * SpelerMapper staat in voor het omzetten van een Speler-entiteit uit de
 * databank naar een Speler-object.
 */
public class SpelerMapper
{
    private Connectie connectie;
    /**
     *
     * @param gebruikersnaam De gebruikersnaam van de speler
     * @param wachtwoord Het wachtwoord van de speler
     * @return Een spelerobject met gevulde waarden uit de databank.
     */
    public Speler zoek(String gebruikersnaam, String wachtwoord)
    {
        String sqlString = "SELECT * FROM Speler WHERE gebruikernaam = '" + gebruikersnaam + "'"
                + "AND wachtwoord = '" + wachtwoord + "'";
        
        connectie = new Connectie();
        Speler speler = new Speler();
        
        try
        {
            PreparedStatement sqlStatement = connectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next())
            {
                speler.setID(String.format("%s", rs.getInt(1)));
                speler.setGebruikersnaam(rs.getString(1)); // 1 voor kolom 1 enz.
                speler.setWachtwoord(rs.getString(2));
                speler.setAdminrechten((rs.getInt(3) == 1? "ja" : "nee")); // als gelijk is aan een, dan ja, anders nee. db heeft 0 voor ja, en 1 voor nee.
                speler.setVoornaam(rs.getString(4));
                speler.setAchternaam(rs.getString(5));
            }
        }
        catch (Exception e)
        {
            System.out.println("--- Fout: " + e.getMessage());
        }
        finally
        {
            connectie.sluit();
        }
        return speler;
    }
}
