/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import exceptions.GebruikerBestaatException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class RegistrerenScherm extends GridPane
{
    @FXML
    private TextField txtGebruikersnaam;
    @FXML
    private Button btnAnnuleren;
    @FXML
    private Button btnOK;
    @FXML
    private PasswordField pswWachtwoord;
    @FXML
    private TextField txtVoornaam;
    @FXML
    private TextField txtNaam;

    private StartScherm startScherm;
    private DomeinController c;
    private boolean geldig;

    RegistrerenScherm(StartScherm startScherm, DomeinController c)
    {
        this.startScherm = startScherm;
        this.c = c;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrerenScherm.fxml"));
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

        btnOK.setOnAction(this::ok_Pressed);
        btnAnnuleren.setOnAction(this::annuleren_Pressed);
    }

    private void ok_Pressed(ActionEvent event)
    {
        Label lblStatus;
        try
        {
            //lblStatus = new Label("Aangemeld als " + txtGebruikersnaam.getText() + " ZONDER adminrechten.");
            c.registreer(txtGebruikersnaam.getText(), pswWachtwoord.getText(), txtNaam.getText(), txtVoornaam.getText());
            startScherm.setLblStatus("Aangemeld als " + txtGebruikersnaam.getText() + " ZONDER adminrechten.");
            //startScherm.setLblStatus(lblStatus);
            geldig = true;
        }
        catch (GebruikerBestaatException gbe)
        {
//            lblStatus = new Label("Gebruikersnaam bestaat al");
//            lblStatus.setStyle("-fx-text-fill: #c4d8de;");
            System.err.println(gbe);
            startScherm.setLblStatus("Gebruikersnaam bestaat al");
            //startScherm.setLblStatus(lblStatus);
            geldig = false;
        }
        catch (IllegalArgumentException iae)
        {
            //lblStatus = new Label("Ongeldige gebruikersnaam of wachtwoord ingegeven. Probeer opnieuw");
            System.err.println(iae);
            
            //lblStatus.setStyle("-fx-text-fill: #c4d8de;");
            //startScherm.setLblStatus(lblStatus);
            startScherm.setLblStatus("Ongeldige gebruikersnaam of wachtwoord ingegeven. Probeer opnieuw");
            geldig = false;
        }
    }

    private void annuleren_Pressed(ActionEvent event)
    {
        this.getChildren().clear();
    }

    public boolean isGeldig()
    {
        return geldig;
    }
}
