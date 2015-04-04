package gui;

import domein.DomeinController;
import exceptions.GebruikerBestaatException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class RegistrerenSchermController extends GridPane implements Initializable, SpelerMenuInterface
{
    @FXML
    private TextField txtGebruikersnaam, txtVoornaam, txtNaam;
    @FXML
    private Button btnAnnuleren, btnOK;
    @FXML
    private PasswordField pswWachtwoord;
    @FXML
    private Label lblTitel;

    private StartSchermController startScherm;
    private DomeinController c;
    private boolean geldig;

    RegistrerenSchermController(StartSchermController startScherm, DomeinController c)
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

        lblTitel.setText(c.getString("registreer.main"));
    }

    @Override
    public void refresh()
    {
        lblTitel.setText(c.getString("registreer.main"));
    }

    private void ok_Pressed(ActionEvent event)
    {
        try
        {
            startScherm.updateControls("Aangemeld als " + txtGebruikersnaam.getText() + " ZONDER adminrechten.", false);
            //lblStatus = new Label("Aangemeld als " + txtGebruikersnaam.getText() + " ZONDER adminrechten.");
            c.registreer(txtGebruikersnaam.getText(), pswWachtwoord.getText(), txtNaam.getText(), txtVoornaam.getText());
            //startScherm.setLblStatus();
            //startScherm.setLblStatus(lblStatus);
//            startScherm.setMenuItemNieuwSpel(false);
//            startScherm.setMenuAanpassenSpelbord(true);
//            startScherm.setMenuItemMaakSpelbord(true);
            geldig = true;
        }
        catch (GebruikerBestaatException gbe)
        {
//            lblStatus = new Label("Gebruikersnaam bestaat al");
//            lblStatus.setStyle("-fx-text-fill: #c4d8de;");
            System.err.println(gbe);
            startScherm.updateControls("Gebruikersnaam bestaat al");
            //startScherm.setLblStatus("Gebruikersnaam bestaat al");
            geldig = false;
        }
        catch (IllegalArgumentException iae)
        {
            //lblStatus = new Label("Ongeldige gebruikersnaam of wachtwoord ingegeven. Probeer opnieuw");
            System.err.println(iae);

            //lblStatus.setStyle("-fx-text-fill: #c4d8de;");
            //startScherm.setLblStatus(lblStatus);
            //startScherm.setLblStatus("Ongeldige gebruikersnaam of wachtwoord ingegeven. Probeer opnieuw");
            startScherm.updateControls("Ongeldige gebruikersnaam of wachtwoord ingegeven.Probeer opnieuw");
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}
