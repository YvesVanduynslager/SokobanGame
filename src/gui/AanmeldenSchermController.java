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
import wachtwoordbeveiliging.BCrypt;

/**
 * GUI-scherm voor UC1: Meld aan.
 *
 * @author Yves
 */
public class AanmeldenSchermController extends GridPane implements Refreshable
{
    @FXML
    private Label lblTitel;
    @FXML
    private TextField txtGebruikersnaam;
    @FXML
    private Button btnAnnuleren, btnOK;
    @FXML
    private PasswordField pswWachtwoord;

    private final DomeinController c;
    private final StartSchermController startScherm;

    /**
     * Initialisatie van het aanmelden-scherm.
     * 
     * @param startScherm parent-component.
     * @param c DomeinController-object.
     */
    public AanmeldenSchermController(StartSchermController startScherm, DomeinController c)
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
    }

    /**
     * Als er op btnOK geklikt werd
     * @param event 
     */
    private void btnOK_gekozen(ActionEvent event)
    {
        String[] speler;
        String gebruikersnaam, wachtwoord;

        gebruikersnaam = txtGebruikersnaam.getText();
        wachtwoord = BCrypt.hashpw(pswWachtwoord.getText(), "$2a$10$RV4IhXXJFyL3EmzvS4sqHu");

        c.meldAan(gebruikersnaam, wachtwoord);
        speler = c.geefSpeler();

        if (speler[0] == null)
        {
            startScherm.updateStatusLabel(c.getString("aanmelden.nietgevonden"));
        }
        else
        {
            String adminrechtenHulp = (speler[1].equals("ja") ? c.getString("aanmelden.met") : c.getString("aanmelden.zonder"));

            if (speler[1].equals("ja"))
            {
                startScherm.updateControls(true);
            }
            else
            {
                startScherm.updateControls(false);
            }

            startScherm.updateStatusLabel(c.getString("aangemeld.1") + " " + speler[0] + " " + adminrechtenHulp + " " + c.getString("aangemeld.2"));
        }
    }

    /**
     * Stelt componenten opnieuw in adhv taal-resources.
     */
    @Override
    public final void refresh()
    {
        lblTitel.setText(c.getString("aanmelden.titel"));

        txtGebruikersnaam.setPromptText(c.getString("aanmelden.gebruikersnaam"));
        txtGebruikersnaam.setTooltip(new Tooltip(c.getString("gebruikersnaam.tooltip")));

        pswWachtwoord.setPromptText(c.getString("aanmelden.wachtwoord"));
        pswWachtwoord.setTooltip(new Tooltip(c.getString("wachtwoord.tooltip")));

        btnAnnuleren.setText(c.getString("annuleren"));
    }

    /**
     * Code die uitgevoerd wordt als er op btnAnnuleren werd geklikt.
     * @param event 
     */
    private void btnAnnuleren_gekozen(ActionEvent event)
    {
        this.getChildren().clear();
    }
}
