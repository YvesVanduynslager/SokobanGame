package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class StartSchermController extends GridPane implements Initializable
{
    @FXML
    private Menu menuSpel, menuBewerken, menuGebruiker, menuTaal, menuHelp;
    @FXML
    private MenuItem mItemNieuwSpel, mItemAfsluiten, mItemAanpassenSpelbord,
            mItemMaakSpelbord, mItemAanmelden, mItemRegistreren, mItemInfo,
            mItemNederlands, mItemFrans, mItemEngels;
    @FXML
    private Label lblStatus;

    private DomeinController c;
    private GridPane content;

    public StartSchermController(DomeinController c)
    {
        this.c = c;
        c.setTaalKeuze(1);

        /* Lege gridpane instellen bij start, anders overlappen gridpanes elkaar na 2x selecteren in menu's.
         Bvb. klikken op Gebruiker -> aanmelden, daarna op Gebruiker -> registreren */
        this.content = new GridPane();
        this.add(content, 1, 0);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScherm.fxml"));
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
        
        mItemNieuwSpel.setOnAction(this::nieuwspel_Pressed);

        mItemAanmelden.setOnAction(this::aanmelden);
        mItemRegistreren.setOnAction(this::registreren);
        mItemAfsluiten.setOnAction(this::afsluiten);

        mItemNederlands.setOnAction(this::nederlands);
        mItemFrans.setOnAction(this::frans);
        mItemEngels.setOnAction(this::engels);

        mItemInfo.setOnAction(this::info);
    }
    
    private void nieuwspel_Pressed(ActionEvent event)
    {
        content.getChildren().clear();
        content = new SpeelSpelSchermController(this, c);
        addContent(content);
    }

    private void aanmelden(ActionEvent event)
    {
        content.getChildren().clear();
        this.lblStatus.setText("");
        content = new AanmeldenSchermController(this, c);
        addContent(content);
    }

    private void registreren(ActionEvent event)
    {
        content.getChildren().clear();
        this.lblStatus.setText("");
        content = new RegistrerenSchermController(this, c);
        addContent(content);
    }

    private void afsluiten(ActionEvent event)
    {
        Platform.exit();
    }

    private void nederlands(ActionEvent event)
    {
        c.setTaalKeuze(1);
        this.lblStatus.setText("Taal: Nederlands");
        refreshContent();
    }

    private void frans(ActionEvent event)
    {
        c.setTaalKeuze(3);
        this.lblStatus.setText("Langue: Français");
        refreshContent();
    }

    private void engels(ActionEvent event)
    {
        c.setTaalKeuze(2);
        this.lblStatus.setText("Language: English");
        refreshContent();
    }

    private void refreshContent()
    {
        if (content instanceof AanmeldenSchermController)
        {
            ((AanmeldenSchermController) content).refresh();
        }
        else
        {
            if (content instanceof RegistrerenSchermController)
            {
                ((RegistrerenSchermController) content).refresh();
            }
        }
    }

    private void info(ActionEvent event)
    {

    }

    private void addContent(GridPane content)
    {
        this.add(content, 0, 1);
    }

    public void updateControls(String lblStatus, boolean isAdmin)
    {
        this.lblStatus.setText(lblStatus);
        if (isAdmin)
        {
            this.mItemNieuwSpel.setDisable(false);
            this.mItemAanpassenSpelbord.setDisable(false);
            this.mItemMaakSpelbord.setDisable(false);
        }
        else
        {
            this.mItemNieuwSpel.setDisable(false);
            this.mItemAanpassenSpelbord.setDisable(true);
            this.mItemMaakSpelbord.setDisable(true);
        }
    }

    public void updateControls(String lblStatus)
    {
        this.lblStatus.setText(lblStatus);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}
