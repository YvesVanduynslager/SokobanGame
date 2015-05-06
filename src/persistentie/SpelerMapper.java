package persistentie;

import domein.*;
import exceptions.GebruikerBestaatException;
import java.sql.*;
import wachtwoordbeveiliging.BCrypt;

/**
 *
 * SpelerMapper staat in voor het omzetten van een Speler-entiteit
 * uit de databank naar een Speler-object en omgekeerd.
 * 
 * @author Yves
 */
public final class SpelerMapper
{
    /**
     * Doorzoekt de spelertabel op het voorkomen van de twee meegegeven parameters.
     *
     * @param gebruikersnaam De gebruikersnaam van de speler
     * @param wachtwoord Het wachtwoord van de speler
     * @return Een spelerobject met gevulde waarden uit de databank.
     */
    public Speler zoek(String gebruikersnaam, String wachtwoord)
    {        
        String sqlString = "SELECT gebruikernaam, wachtwoord, isAdmin, voornaam, achternaam "
                + "FROM speler "
                + "WHERE gebruikernaam =  '" + gebruikersnaam + "'"
                + "AND wachtwoord = '" + wachtwoord + "'";
        Speler speler = new Speler();

        Connectie.start();
        PreparedStatement sqlStatement;
        
        try
        {
            sqlStatement = Connectie.getDatabaseConnectie().prepareStatement(sqlString);
            
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
            Connectie.sluit();
        }
        return speler;
    }

    /**
     * Slaat de gegevens meegegeven in het paramterobject op in de databank.
     *
     * @param speler Speler-object met gegevens die moeten toegevoegd worden aan
     * de databank.
     * @throws GebruikerBestaatException throws naar SpelerRepository.
     */
    public void voegToe(Speler speler) throws GebruikerBestaatException
    {
        String SQL_INSERT = "INSERT INTO speler(gebruikernaam, wachtwoord, isAdmin, achternaam, voornaam) "
                + "VALUES(?, ?, ?, ?, ?); ";
        Connectie.start();
        PreparedStatement sqlStatement;

        try
        {
            if (bestaatSpeler(speler.getGebruikersnaam()))
            {
                throw new GebruikerBestaatException();
            }

            sqlStatement = Connectie.getDatabaseConnectie().prepareStatement(SQL_INSERT);
            
            sqlStatement.setString(1, speler.getGebruikersnaam());
            sqlStatement.setString(2, BCrypt.hashpw(speler.getWachtwoord(), "$2a$10$RV4IhXXJFyL3EmzvS4sqHu")/*speler.getWachtwoord()*/);
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
            Connectie.sluit();
        }
    }

    /**
     * Hulpmethode die controleert of de gebruikersnaam al bestaat in de databank. Oproepende
     * methode moet zorgen voor het openen en sluiten van de connectie.
     *
     * @param gebruikersnaam Te controleren gebruikersnaam
     * @return true als gebruikersnaam al bestaat. false als gebruikersnaam nog
     * niet bestaat
     */
    private boolean bestaatSpeler(String gebruikersnaam)
    {
        PreparedStatement sqlStatement;
        String sqlString = "SELECT gebruikernaam FROM speler WHERE gebruikernaam = '" + gebruikersnaam + "'";
        String opgehaaldeGebruikersnaam = null;

        try
        {
            sqlStatement = Connectie.getDatabaseConnectie().prepareStatement(sqlString);
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

        return opgehaaldeGebruikersnaam != null; //string moet null zijn om onbestande speler voor te stellen
    }
}
