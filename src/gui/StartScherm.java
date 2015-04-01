/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class StartScherm extends GridPane
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

    public StartScherm(DomeinController c)
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

        mItemAanmelden.setOnAction(this::aanmelden);
        mItemRegistreren.setOnAction(this::registreren);
        mItemAfsluiten.setOnAction(this::afsluiten);

        mItemNederlands.setOnAction(this::nederlands);
        mItemFrans.setOnAction(this::frans);
        mItemEngels.setOnAction(this::engels);

        mItemInfo.setOnAction(this::info);
        
        //lblStatus.setStyle("-fx-text-fill: #00d8d8;");
    }

    private void aanmelden(ActionEvent event)
    {
        content.getChildren().clear();
        this.setLblStatus("");
        content = new AanmeldenScherm(this, c);
        addContent(content);
    }

    private void registreren(ActionEvent event)
    {
        content.getChildren().clear();
        this.setLblStatus("");
        content = new RegistrerenScherm(this, c);
        addContent(content);
    }

    private void afsluiten(ActionEvent event)
    {
        Platform.exit();
    }

    private void nederlands(ActionEvent event)
    {
        c.setTaalKeuze(1);
        this.setLblStatus("Taal: Nederlands");
        refreshContent();
    }

    private void frans(ActionEvent event)
    {
        c.setTaalKeuze(3);
        this.setLblStatus("Langue: Français");
        refreshContent();
    }

    private void engels(ActionEvent event)
    {
        c.setTaalKeuze(2);
        this.setLblStatus("Language: English");
        refreshContent();
    }
    
    private void refreshContent()
    {
        if(content instanceof AanmeldenScherm)
        {
            ((AanmeldenScherm)content).refresh();
        }
        else
        {
            if(content instanceof RegistrerenScherm)
            {
                ((RegistrerenScherm)content).refresh();
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

    public void setLblStatus(String bericht)
    {
        this.lblStatus.setText(bericht);
    }

    public void setMenuItemNieuwSpel(boolean disabled)
    {
        this.mItemNieuwSpel.setDisable(disabled);
    }

    public void setMenuItemMaakSpelbord(boolean disabled)
    {
        this.mItemMaakSpelbord.setDisable(disabled);
    }

    public void setMenuAanpassenSpelbord(boolean disabled)
    {
        this.mItemAanpassenSpelbord.setDisable(disabled);
    }
}
