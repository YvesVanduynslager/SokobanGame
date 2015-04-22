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
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 * Deze klasse implementeert het StartScherm (betere naam mss HoofdScherm).
 * Dit scherm bevat een menu en een statusbalk (lblSatus), ZONDER content.
 * De content wordt later aangepast naargelang de gebruiker controls aanklikt.
 *
 * @author Yves
 */
public class StartSchermController extends GridPane implements Refreshable, Initializable
{
    @FXML
    private Menu menuSpel, menuBewerken, menuGebruiker, menuTaal, menuHelp, menuKiesSpel;
    @FXML
    private MenuItem mItemAfsluiten, mItemAanpassenSpelbord,
            mItemMaakSpelbord, mItemAanmelden, mItemRegistreren, mItemInfo, mItemControls,
            mItemNederlands, mItemFrans, mItemEngels;
    @FXML
    private Label lblStatus;

    private DomeinController c;
    private GridPane content;

    public StartSchermController(DomeinController c)
    {
        this.c = c;
        this.c.setTaalKeuze(1);

        this.content = new GridPane();
        
        addContent(content);
        
        BackgroundImage achtergrond = new BackgroundImage(new Image("/images/achtergrond.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        this.setBackground(new Background(achtergrond));

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

        mItemAanmelden.setOnAction(this::mItemAanmelden_gekozen);
        mItemRegistreren.setOnAction(this::mItemRegistreren_gekozen);
        mItemAfsluiten.setOnAction(this::mItemAfsluiten_gekozen);

        mItemNederlands.setOnAction(this::mItemNederlands_gekozen);
        mItemFrans.setOnAction(this::mItemFrans_gekozen);
        mItemEngels.setOnAction(this::mItemEngels_gekozen);

        mItemInfo.setOnAction(this::mItemInfo_gekozen);
        mItemControls.setOnAction(this::mItemControls_gekozen);

        /*
         * Dit (en spel_gekozen methode) zorgen ervoor dat alle spelnamen uit de databank aan het menu worden toegevoegd
         * en een ActionHandler toegewezen krijgen.
         */
        MenuItem keuzeSpel; //MenuItem declareren om later toe te voegen aan menuKiesSpel.
        for (String spelNaam : this.c.geefSpelNamen()) //Voor elke spelnaam die in de db zit
        {
            String naam = spelNaam.substring(0, 1).toUpperCase() + spelNaam.substring(1); //Eerste letter van spel omzetten naar hoofdletter
            keuzeSpel = new MenuItem(naam); //spelnaam toewijzen aan een nieuw MenuItem
            keuzeSpel.setOnAction(this::spel_gekozen); //ActionHandler toevoegen aan het MenuItem
            menuKiesSpel.getItems().add(keuzeSpel); //MenuItem toevoegen aan menuKiesSpel
        }
        
        refreshMenuLabels();
    }

    /**
     * Deze methode loopt door het aantal spelnamen en controleert via event.getSource() welk MenuItem aangeklikt werd.
     * @param event 
     */
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
                break;
            }
        }
    }

    private void mItemAanmelden_gekozen(ActionEvent event)
    {
        content.getChildren().clear();
        this.lblStatus.setText("");
        content = new AanmeldenSchermController(this, c);
        addContent(content);
    }

    private void mItemRegistreren_gekozen(ActionEvent event)
    {
        content.getChildren().clear();
        this.lblStatus.setText("");
        content = new RegistrerenSchermController(this, c);
        addContent(content);
    }

    private void mItemAfsluiten_gekozen(ActionEvent event)
    {
        Platform.exit();
    }

    private void mItemNederlands_gekozen(ActionEvent event)
    {
        c.setTaalKeuze(1);
        this.lblStatus.setText("Taal: Nederlands");
        refresh();
    }

    private void mItemFrans_gekozen(ActionEvent event)
    {
        c.setTaalKeuze(3);
        this.lblStatus.setText("Langue: Français");
        refresh();
    }

    private void mItemEngels_gekozen(ActionEvent event)
    {
        c.setTaalKeuze(2);
        this.lblStatus.setText("Language: English");
        refresh();
    }
    
    private void mItemInfo_gekozen(ActionEvent event)
    {
        content.getChildren().clear();
        this.lblStatus.setText("");
        content = new InfoSchermController(c);
        addContent(content);
    }
    
    private void mItemControls_gekozen(ActionEvent event)
    {
        
    }
    
    private void refreshMenuLabels()
    {
        this.menuSpel.setText(c.getString("menu.spel"));
        this.menuKiesSpel.setText(c.getString("menu.spel.kies"));
        this.mItemAfsluiten.setText(c.getString("menu.spel.afsluiten"));

        this.menuBewerken.setText(c.getString("menu.bewerken"));
        this.mItemAanpassenSpelbord.setText(c.getString("menu.bewerken.aanpassen"));
        this.mItemMaakSpelbord.setText(c.getString("menu.bewerken.maak"));

        this.menuGebruiker.setText(c.getString("menu.gebruiker"));
        this.mItemAanmelden.setText(c.getString("menu.gebruiker.aanmelden"));
        this.mItemRegistreren.setText(c.getString("menu.gebruiker.registreren"));

        this.menuTaal.setText(c.getString("menu.taal"));
        this.mItemNederlands.setText(c.getString("menu.taal.nederlands"));
        this.mItemFrans.setText(c.getString("menu.taal.frans"));
        this.mItemEngels.setText(c.getString("menu.taal.engels"));

        this.menuHelp.setText(c.getString("menu.help"));
        this.mItemInfo.setText(c.getString("menu.help.info"));
    }

    @Override
    public void refresh()
    {
        refreshMenuLabels();
        
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
                else
                {
                    if(content instanceof InfoSchermController)
                    {
                        ((InfoSchermController) content).refresh();
                    }
                }
            }
        }
    }

    private void addContent(GridPane content)
    {
        this.add(content, 0, 1);
    }

    public void updateControls(boolean isAdmin)
    {
        if (isAdmin)
        {
            this.menuKiesSpel.setDisable(false);
            this.mItemAanpassenSpelbord.setDisable(false);
            this.mItemMaakSpelbord.setDisable(false);
        }
        else
        {
            this.menuKiesSpel.setDisable(false);
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
