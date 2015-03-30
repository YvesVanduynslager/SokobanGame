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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class AanmeldenScherm extends GridPane
{
    @FXML
    private TextField txtGebruikersnaam;
    @FXML
    private Button btnAnnuleren;
    @FXML
    private Button btnOK;
    @FXML
    private PasswordField pswWachtwoord;

    private DomeinController c;
    private StartScherm startScherm;
    private boolean geldig;
    
    public AanmeldenScherm(StartScherm startScherm, DomeinController c)
    {
        this.startScherm = startScherm;
        this.c = c;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AanmeldenScherm.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try
        {
            loader.load();
        }
        catch(IOException ex)
        {
            throw new RuntimeException(ex);
        }
        
        //txtGebruikersnaam.setPromptText("Gebruikersnaam");
        
        //pswWachtwoord = new PasswordField();
        //pswWachtwoord.setPromptText("Wachtwoord");
        
        btnOK.setOnAction(this::ok_Pressed);
        
//        btnAnnuleren = new Button();
//        btnAnnuleren.setText("Annuleren");
        btnAnnuleren.setOnAction(this::annuleren);
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
            startScherm.lblStatus.setText("Speler niet gevonden!");
        }
        else
        {
            geldig = true;
            String adminrechtenHulp = (speler[1].equals("ja") ? " MET " : " ZONDER ");
            startScherm.lblStatus.setText("Aangemeld als: " + speler[0] + " " + adminrechtenHulp + " adminrechten.");
            if(speler[1].equals("ja"))
            {
                startScherm.mItemNieuwSpel.setDisable(false);
                startScherm.mItemMaakSpelbord.setDisable(false);
                startScherm.mItemAanpassenSpelbord.setDisable(false);
            }
            else
            {
                startScherm.mItemNieuwSpel.setDisable(false);
            }
        }
    }
    
    protected void annuleren(ActionEvent event)
    {
        
    }
    
    public boolean isSuccess()
    {
        return geldig;
    }
}
