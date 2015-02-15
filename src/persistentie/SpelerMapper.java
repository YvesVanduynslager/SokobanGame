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
        String sqlString = "SELECT * FROM Spelers WHERE gebruikersnaam = '" + gebruikersnaam + "'"
                + "AND wachtwoord = '" + wachtwoord + "'";
        
        connectie = new Connectie();
        Speler speler = new Speler();
        
        try
        {
            PreparedStatement sqlStatement = connectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next())
            {
                speler.setGebruikersnaam(rs.getString(1)); // 1 voor kolom 1 enz.
                speler.setWachtwoord(rs.getString(2));
                speler.setAdminrechten((rs.getInt(3) == 1)); // als gelijk is aan een, dan true. db heeft 0 voor false, en 1 voor true. wordt hier vlug omgezet naar boolean
                speler.setVoornaam(rs.getString(4));
                speler.setAchternaam(rs.getString(5));
            }
        }
        catch (Exception e)
        {
            System.out.println("Fout: " + e.getMessage());
        }
        finally
        {
            connectie.sluit();
        }
        return speler;
    }
}
