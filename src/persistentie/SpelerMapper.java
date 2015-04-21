package persistentie;

import domein.*;
import exceptions.GebruikerBestaatException;
import java.sql.*;

/**
 *
 * @author Yves SpelerMapper staat in voor het omzetten van een Speler-entiteit
 * uit de databank naar een Speler-object en omgekeerd.
 */
public class SpelerMapper
{
    /**
     *
     * @param gebruikersnaam De gebruikersnaam van de speler
     * @param wachtwoord Het wachtwoord van de speler
     * @return Een spelerobject met gevulde waarden uit de databank.
     */
    public Speler zoek(String gebruikersnaam, String wachtwoord)
    {
        Connectie connectie = new Connectie();
        PreparedStatement sqlStatement;
        String sqlString = "SELECT gebruikernaam, wachtwoord, isAdmin, voornaam, achternaam "
                + "FROM Speler "
                + "WHERE gebruikernaam =  '" + gebruikersnaam + "'"
                + "AND wachtwoord = '" + wachtwoord + "'";
        Speler speler = new Speler();

        try
        {
            sqlStatement = connectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next())
            {
                speler.setGebruikersnaam(rs.getString(1)); // 1 voor kolom 1 enz.
                speler.setWachtwoord(rs.getString(2));
                speler.setAdminrechten((rs.getInt(3) == 1 ? "ja" : "nee")); // als gelijk is aan 1, dan ja, anders nee. db heeft 1 voor ja, en 0 voor nee.
                speler.setVoornaam(rs.getString(4));
                speler.setAchternaam(rs.getString(5));
            }

            sqlStatement.close();
        }
        catch (SQLException sqlEx)
        {
            System.err.println("SQL fout" + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
        }
        finally
        {
            connectie.sluit();
        }
        return speler;
    }

    /**
     *
     * @param speler Speler-object met gegevens die moeten toegevoegd worden aan
     * de databank.
     * @throws GebruikerBestaatException throws naar SpelerRepository.
     */
    public void voegToe(Speler speler) throws GebruikerBestaatException
    {
        Connectie connectie = new Connectie();
        PreparedStatement sqlStatement;

        try
        {
            if (bestaatSpeler(speler.getGebruikersnaam()))
            {
                throw new GebruikerBestaatException();
            }

            String SQL_INSERT = "INSERT INTO Speler(gebruikernaam, wachtwoord, isAdmin, achternaam, voornaam) "
                    + "VALUES(?, ?, ?, ?, ?); ";

            sqlStatement = connectie.getDatabaseConnectie().prepareStatement(SQL_INSERT);

            sqlStatement.setString(1, speler.getGebruikersnaam());
            sqlStatement.setString(2, speler.getWachtwoord());
            sqlStatement.setBoolean(3, (speler.getAdminrechten().equals("ja")));
            sqlStatement.setString(4, speler.getAchternaam());
            sqlStatement.setString(5, speler.getVoornaam());

            sqlStatement.executeUpdate();
            sqlStatement.close();
        }
        catch (SQLException sqlEx)
        {
            System.err.println("SQL fout" + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
        }
        finally
        {
            connectie.sluit();
        }
    }

    /**
     * Controleert of de gebruikersnaam al bestaat in de databank.
     *
     * @param gebruikersnaam Te controleren gebruikersnaam
     * @return true als gebruikersnaam al bestaat. false als gebruikersnaam nog
     * niet bestaat
     */
    public boolean bestaatSpeler(String gebruikersnaam)
    {
        Connectie connectie = new Connectie();
        PreparedStatement sqlStatement;
        String sqlString = "SELECT gebruikernaam FROM Speler WHERE gebruikernaam = '" + gebruikersnaam + "'";
        String opgehaaldeGebruikersnaam = null;

        try
        {
            sqlStatement = connectie.getDatabaseConnectie().prepareStatement(sqlString);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next())
            {
                opgehaaldeGebruikersnaam = rs.getString(1);
            }
            sqlStatement.close();
        }
        catch (SQLException sqlEx)
        {
            System.err.println("SQL fout" + sqlEx.getMessage() + "\n" + sqlEx.getSQLState());
        }
        finally
        {
            connectie.sluit();
        }

        return opgehaaldeGebruikersnaam != null; //string moet null zijn om onbestande speler voor te stellen
    }
}