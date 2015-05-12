package gui;

import domein.DomeinController;
import exceptions.GebruikerBestaatException;
import exceptions.GebruikersnaamOngeldigException;
import exceptions.WachtwoordOngeldigException;
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

    /**
     * Initialisatie van het registreren-scherm.
     *
     * @param startScherm parent-component.
     * @param c DomeinController-object.
     */
    RegistrerenSchermController(StartSchermController startScherm, DomeinController c)
    {
        init();

        this.startScherm = startScherm;
        this.c = c;

        btnOK.setOnAction(this::btnOK_gekozen);
        btnAnnuleren.setOnAction(this::btnAnnuleren_gekozen);

        refresh();
    }

    /**
     * Initialisatie van fxml-root.
     */
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

    /**
     * Stelt componenten opnieuw in adhv taal-resources.
     */
    @Override
    public final void refresh()
    {
        lblTitel.setText(c.getString("registreer.titel"));

        txtGebruikersnaam.setPromptText(c.getString("registreer.gebruikersnaam"));
        txtGebruikersnaam.setTooltip(new Tooltip(c.getString("gebruikersnaam.tooltip")));

        pswWachtwoord.setPromptText(c.getString("registreer.wachtwoord"));
        pswWachtwoord.setTooltip(new Tooltip(c.getString("wachtwoord.tooltip")));

        txtVoornaam.setPromptText(c.getString("registreer.voornaam"));
        txtNaam.setPromptText(c.getString("registreer.naam"));

        btnAnnuleren.setText(c.getString("annuleren"));
        btnOK.setText(c.getString("ok"));
    }

    /**
     * Als er op btnOK werd geklikt
     * @param event 
     */
    private void btnOK_gekozen(ActionEvent event)
    {
        try
        {
            c.registreer(txtGebruikersnaam.getText(), pswWachtwoord.getText() /*BCrypt.hashpw(pswWachtwoord.getText(), "$2a$10$RV4IhXXJFyL3EmzvS4sqHu")*/, txtNaam.getText(), txtVoornaam.getText());

            //Wordt enkel uitgevoerd als er geen exception werd gegooid
            startScherm.updateStatusLabel(c.getString("aangemeld.1")
                    + " " + txtGebruikersnaam.getText()
                    + " " + c.getString("aanmelden.zonder")
                    + " " + c.getString("aangemeld.2"));
            startScherm.updateControls(false);
        }
        catch (GebruikerBestaatException gbe)
        {
            System.err.println(gbe);
            startScherm.updateStatusLabel(c.getString("registreer.bestaat"));
        }
        catch (GebruikersnaamOngeldigException goe)
        {
            System.err.println(goe);
            startScherm.updateStatusLabel(c.getString("registreer.ongeldigeGebruikersnaam"));
        }
        catch (WachtwoordOngeldigException woe)
        {
            System.err.println(woe);
            startScherm.updateStatusLabel(c.getString("registreer.ongeldigWachtwoord"));
        }
    }

    /**
     * Als er op btnAnnuleren werd geklikt.
     * @param event 
     */
    private void btnAnnuleren_gekozen(ActionEvent event)
    {
        this.getChildren().clear();
    }
}
