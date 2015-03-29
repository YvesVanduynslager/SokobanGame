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
    
    public AanmeldenScherm(DomeinController c)
    {
        //this.startScherm = startScherm;
        this.c = c;
        
        txtGebruikersnaam = new TextField();
        txtGebruikersnaam.setPromptText("Gebruikesnaam");
        
        pswWachtwoord = new PasswordField();
        pswWachtwoord.setPromptText("Wachtwoord");
        
        btnOK = new Button();
        btnOK.setText("OK");
        btnOK.setOnAction(this::ok);
        
        btnAnnuleren = new Button();
        btnAnnuleren.setText("Annuleren");
        btnAnnuleren.setOnAction(this::annuleren);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Aanmelden.fxml"));
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
    }
    
    protected void ok(ActionEvent event)
    {
        c.meldAan(txtGebruikersnaam.getText(), pswWachtwoord.getText());
    }
    
    protected void annuleren(ActionEvent event)
    {
        
    }
}
