package persistentie;

import domein.*;
import exceptions.GebruikerBestaatException;
import java.sql.*;

/**
 *
 * @author Yves
 * SpelerMapper staat in voor het omzetten van een Speler-entiteit
 * uit de databank naar een Speler-object en omgekeerd.
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

    public void voegToe(Speler speler) throws GebruikerBestaatException//Exception
    {
        try
        {
            if(bestaatSpeler(speler.getGebruikersnaam()))
            {
                throw new GebruikerBestaatException();//Exception();
            }
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
        catch(SQLException SQLe)
        {
            System.out.println("SQL fout");
        }
        catch (GebruikerBestaatException gbe)//Exception e)
        {
            throw new GebruikerBestaatException("Gebruiker bestaat al!");//Exception("Fout");
            //System.out.println("--- Fout: " + e.getClass() + ": " + e.getMessage());
        }
    }
    
    public boolean bestaatSpeler(String gebruikersnaam)
    {
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
        }
        catch (SQLException ex)
        {
            System.out.println("--- Fout: " + ex.getClass() + ": " + ex.getMessage());
        }
        
        return opgehaaldeGebruikersnaam != null; //string moet null zijn om onbestande speler voor te stellen
//        if (opgehaaldeGebruikersnaam != null) //string moet null zijn om onbestande speler voor te stellen
//        {
//            return true;
//            //bestaatSpeler = true;
//        }
//        else
//        {
//            return false;
//        }
    }
}
