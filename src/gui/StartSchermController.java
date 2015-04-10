package gui;

import domein.DomeinController;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class StartSchermController extends GridPane implements Initializable
{
    @FXML
    private Menu menuSpel, menuBewerken, menuGebruiker, menuTaal, menuHelp, menuKiesSpel;
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
        this.c.setTaalKeuze(1);

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

        mItemAanmelden.setOnAction(this::aanmelden);
        mItemRegistreren.setOnAction(this::registreren);
        mItemAfsluiten.setOnAction(this::afsluiten);

        mItemNederlands.setOnAction(this::nederlands);
        mItemFrans.setOnAction(this::frans);
        mItemEngels.setOnAction(this::engels);

        mItemInfo.setOnAction(this::info);

        MenuItem keuzeSpel;
        for (String spelNaam : this.c.geefSpelNamen())
        {
            String naam = spelNaam.substring(0, 1).toUpperCase() + spelNaam.substring(1); //Eerste letter omzetten naar hoofdletter
            keuzeSpel = new MenuItem(naam);
            keuzeSpel.setOnAction(this::spel_gekozen);
            menuKiesSpel.getItems().add(keuzeSpel);
        }
    }

    private void spel_gekozen(ActionEvent event)
    {
        for (int spelIndex = 0; spelIndex < c.geefSpelNamen().size(); spelIndex++)
        {
            if (event.getSource() == menuKiesSpel.getItems().get(spelIndex))
            {
                c.selecteerSpel(c.geefSpelNamen().get(spelIndex));
                c.startVolgendSpelbord();
                content.getChildren().clear();
                this.lblStatus.setText("");
                content = new SpelbordController(this, c);

                addContent(content);
                content.requestFocus(); //BELANGRIJK!!! NIET IN SpelbordController oproepen!
            }
        }
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
            else
            {
                if (content instanceof SpelbordController)
                {
                    ((SpelbordController) content).refresh();
                }
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
            this.menuKiesSpel.setDisable(false);
            //this.mItemNieuwSpel.setDisable(false);
            this.mItemAanpassenSpelbord.setDisable(false);
            this.mItemMaakSpelbord.setDisable(false);
        }
        else
        {
            this.menuKiesSpel.setDisable(false);
            //this.mItemNieuwSpel.setDisable(false);
            this.mItemAanpassenSpelbord.setDisable(true);
            this.mItemMaakSpelbord.setDisable(true);
        }
    }

    public void updateStatusLabel(String lblStatus)
    {
        this.lblStatus.setText(lblStatus);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}
