package persistentie;

import domein.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yves SpelerMapper staat in voor het omzetten van een Speler-entiteit
 * uit de databank naar een Speler-object.
 */
public class SpelerMapper
{
    private final Connectie connectie;
    private PreparedStatement sqlStatement;

    public SpelerMapper()
    {
        connectie = new Connectie();
    }

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

        Speler speler = new Speler();

        try
        {
            sqlStatement = connectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next())
            {
                speler.setGebruikersnaam(rs.getString(2)); // 1 voor kolom 1 enz.
                speler.setWachtwoord(rs.getString(3));
                speler.setAdminrechten((rs.getInt(4) == 1 ? "ja" : "nee")); // als gelijk is aan 1, dan ja, anders nee. db heeft 1 voor ja, en 0 voor nee.
                speler.setVoornaam(rs.getString(5));
                speler.setAchternaam(rs.getString(6));
            }
        }
        catch (Exception e)
        {
            System.out.println("--- Fout: " + e.getClass() + ": " + e.getMessage());
        }

        return speler;
    }

    public boolean bestaatGebruikerAl(String gebruikersnaam)
    {
        boolean bestaatGebruikerAl = false;
        String sqlString = "SELECT gebruikernaam FROM Speler WHERE gebruikernaam = '" + gebruikersnaam + "'";

        try
        {
            sqlStatement = connectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();
            String opgehaaldeGebruikersnaam = null;

            while (rs.next())
            {
                opgehaaldeGebruikersnaam = rs.getString(1);
            }

            if (opgehaaldeGebruikersnaam == null) //string leeg, dus speler bestaat nog niet
            {
                bestaatGebruikerAl = false;
            }
            else
            {
                bestaatGebruikerAl = true;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(SpelerMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return bestaatGebruikerAl;
    }

    public void voegToe(Speler speler)
    {
        //int adminrechten = (speler.getAdminrechten().equals("nee") ? 0 : 1);
        try
        {
            String SQL_INSERT = "INSERT INTO Speler(gebruikernaam, wachtwoord, adminrechten, voornaam, achternaam)"
                + " VALUES(?, ?, ?, ?, ?)";

            sqlStatement = connectie.getDatabaseConnectie().prepareStatement(SQL_INSERT);
            sqlStatement.setString(1, speler.getGebruikersnaam());
            sqlStatement.setString(2, speler.getWachtwoord());
            sqlStatement.setInt(3, (speler.getAdminrechten().equals("nee") ? 0 : 1));
            sqlStatement.setString(4, speler.getVoornaam());
            sqlStatement.setString(5, speler.getAchternaam());
            sqlStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.out.println("--- Fout: " + e.getClass() + ": " + e.getMessage());
        }

        //TODO: sqlinstructie schrijven die spelergegevens uit spelerobject haalt en deze in de tabel Speler zet.
    }
}
