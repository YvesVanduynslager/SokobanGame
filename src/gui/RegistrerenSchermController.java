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
import wachtwoordbeveiliging.BCrypt;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class RegistrerenSchermController extends GridPane implements Refreshable
{
    @FXML
    private TextField txtGebruikersnaam, txtVoornaam, txtNaam;
    @FXML
    private Button btnAnnuleren, btnOK;
    @FXML
    private PasswordField pswWachtwoord;
    @FXML
    private Label lblTitel;

    private final StartSchermController startScherm;
    private final DomeinController c;
    private boolean geldig;

    RegistrerenSchermController(StartSchermController startScherm, DomeinController c)
    {
        this.startScherm = startScherm;
        this.c = c;

        btnOK.setOnAction(this::btnOK_gekozen);
        btnAnnuleren.setOnAction(this::btnAnnuleren_gekozen);

        refresh();
    }
    
    @Override
    public final void init()
    {
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
    }

    @Override
    public final void refresh()
    {
        lblTitel.setText(c.getString("registreer.titel"));
        txtGebruikersnaam.setPromptText(c.getString("registreer.gebruikersnaam"));
        txtVoornaam.setPromptText(c.getString("registreer.voornaam"));
        txtNaam.setPromptText(c.getString("registreer.naam"));
        btnAnnuleren.setText(c.getString("annuleren"));
        btnOK.setText(c.getString("ok"));
        pswWachtwoord.setPromptText(c.getString("registreer.wachtwoord"));
    }

    private void btnOK_gekozen(ActionEvent event)
    {
        try
        {
            c.registreer(txtGebruikersnaam.getText(), BCrypt.hashpw(pswWachtwoord.getText(), "$2a$10$RV4IhXXJFyL3EmzvS4sqHu"), txtNaam.getText(), txtVoornaam.getText());
            
            //Wordt uitgevoerd als er geen exception werd gegooid
            startScherm.updateStatusLabel(c.getString("aangemeld.1") + txtGebruikersnaam.getText() + c.getString("aanmelden.zonder") + c.getString("aangemeld.2"));
            startScherm.updateControls(false);
            //geldig = true;
        }
        catch (GebruikerBestaatException gbe)
        {
            System.err.println(gbe);
            startScherm.updateStatusLabel(c.getString("registreer.bestaat"));
        }
        catch (IllegalArgumentException iae)
        {
            System.err.println(iae);
            startScherm.updateStatusLabel(c.getString("registreer.ongeldig"));
        }
    }

    private void btnAnnuleren_gekozen(ActionEvent event)
    {
        this.getChildren().clear();
    }
}