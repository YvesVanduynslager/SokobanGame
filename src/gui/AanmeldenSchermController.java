package gui;

import domein.DomeinController;
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
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class AanmeldenSchermController extends GridPane implements Initializable, Refreshable
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
    private StartSchermController startScherm;
    private boolean geldig;

    public AanmeldenSchermController(StartSchermController startScherm, DomeinController c)
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

        refresh();
    }

    private void ok_Pressed(ActionEvent event)
    {
        String[] speler;
        String gebruikersnaam, wachtwoord;

        gebruikersnaam = txtGebruikersnaam.getText();
        wachtwoord = pswWachtwoord.getText();

        c.meldAan(gebruikersnaam, wachtwoord);
        speler = c.geefSpeler();

        if (speler[0] == null)
        {
            geldig = false;
            startScherm.updateStatusLabel(c.getString("aanmelden.nietgevonden"));
        }
        else
        {
            geldig = true;
            String adminrechtenHulp = (speler[1].equals("ja") ? c.getString("aanmelden.met") : c.getString("aanmelden.zonder"));

            if (speler[1].equals("ja"))
            {
                startScherm.updateControls(true);
            }
            else
            {
                startScherm.updateControls(false);
            }
            
            startScherm.updateStatusLabel(c.getString("aangemeld.1") + speler[0] + " " + adminrechtenHulp + c.getString("aangemeld.2"));
        }
    }

    @Override
    public final void refresh()
    {
        lblTitel.setText(c.getString("aanmelden.titel"));
        txtGebruikersnaam.setPromptText(c.getString("aanmelden.gebruikersnaam"));
        txtGebruikersnaam.setTooltip(new Tooltip(c.getString("aanmelden.gebruikersnaam.tooltip")));
        pswWachtwoord.setPromptText(c.getString("aanmelden.wachtwoord"));
        pswWachtwoord.setTooltip(new Tooltip(c.getString("aanmelden.wachtwoord.tooltip")));
        btnAnnuleren.setText(c.getString("annuleren"));
    }

    private void annuleren_Pressed(ActionEvent event)
    {
        this.getChildren().clear();
    }

    public boolean isSuccess()
    {
        return geldig;
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}
