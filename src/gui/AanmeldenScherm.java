/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class AanmeldenScherm extends GridPane implements SpelerMenuInterface
{
    @FXML
    private Label lblTitel;
    @FXML
    private TextField txtGebruikersnaam;
    @FXML
    private Button btnAnnuleren, btnOK;
    @FXML
    private PasswordField pswWachtwoord;

    private DomeinController c;
    private StartScherm startScherm;
    private boolean geldig;

    public AanmeldenScherm(StartScherm startScherm, DomeinController c)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
        
        this.startScherm = startScherm;
        this.c = c;

        btnOK.setOnAction(this::ok_Pressed);
        btnAnnuleren.setOnAction(this::annuleren_Pressed);

        //refresh();
        lblTitel.setText(c.getString("aanmelden.titel"));
        txtGebruikersnaam.setPromptText(c.getString("aanmelden.gebruikersnaam"));
        txtGebruikersnaam.setTooltip(new Tooltip(c.getString("aanmelden.gebruikersnaam.tooltip")));
        pswWachtwoord.setPromptText(c.getString("aanmelden.wachtwoord"));
        pswWachtwoord.setTooltip(new Tooltip(c.getString("aanmelden.wachtwoord.tooltip")));
        btnAnnuleren.setText(c.getString("aanmelden.annuleren"));
    }

    private void ok_Pressed(ActionEvent event)
    {
        String[] speler;
        String gebruikersnaam, wachtwoord;

        gebruikersnaam = txtGebruikersnaam.getText();
        System.out.println(gebruikersnaam);
        wachtwoord = pswWachtwoord.getText();
        System.out.println(wachtwoord);

        c.meldAan(gebruikersnaam, wachtwoord);
        speler = c.geefSpeler();

        if (speler[0] == null)
        {
            geldig = false;
            spelerNietGevonden("Speler niet gevonden of fout wachtwoord ingevuld!");
        }
        else
        {
            geldig = true;
            String adminrechtenHulp = (speler[1].equals("ja") ? " MET " : " ZONDER ");

            if (speler[1].equals("ja"))
            {
                spelerGevonden("Aangemeld als: " + speler[0] + " " + adminrechtenHulp + " adminrechten.", true);
                startScherm.setMenuItemNieuwSpel(false);
                startScherm.setMenuAanpassenSpelbord(false);
                startScherm.setMenuItemMaakSpelbord(false);
            }
            else
            {
                spelerGevonden("Aangemeld als: " + speler[0] + " " + adminrechtenHulp + " adminrechten.", false);
                startScherm.setMenuItemNieuwSpel(false);
                startScherm.setMenuAanpassenSpelbord(true);
                startScherm.setMenuItemMaakSpelbord(true);
            }
        }
    }

    @Override
    public void refresh()
    {
        lblTitel.setText(c.getString("aanmelden.titel"));
        txtGebruikersnaam.setPromptText(c.getString("aanmelden.gebruikersnaam"));
        txtGebruikersnaam.setTooltip(new Tooltip(c.getString("aanmelden.gebruikersnaam.tooltip")));
        pswWachtwoord.setPromptText(c.getString("aanmelden.wachtwoord"));
        pswWachtwoord.setTooltip(new Tooltip(c.getString("aanmelden.wachtwoord.tooltip")));
        btnAnnuleren.setText(c.getString("aanmelden.annuleren"));
    }

    private void annuleren_Pressed(ActionEvent event)
    {
        this.getChildren().clear();
    }

    public boolean isSuccess()
    {
        return geldig;
    }

    private void spelerGevonden(String bericht, boolean adminRechten)
    {
        startScherm.setLblStatus(bericht);

        if (adminRechten)
        {
            startScherm.setMenuItemNieuwSpel(false);
            startScherm.setMenuItemMaakSpelbord(false);
            startScherm.setMenuAanpassenSpelbord(false);
        }
        else
        {
            startScherm.setMenuItemNieuwSpel(false);
            startScherm.setMenuItemMaakSpelbord(true);
            startScherm.setMenuAanpassenSpelbord(true);
        }
    }

    private void spelerNietGevonden(String bericht)
    {
        startScherm.setLblStatus(bericht);
    }
}
