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
    protected MenuItem mItemNieuwSpel;
    @FXML
    private MenuItem mItemAfsluiten;
    @FXML
    protected MenuItem mItemAanpassenSpelbord;
    @FXML
    protected MenuItem mItemMaakSpelbord;
    @FXML
    private MenuItem mItemAanmelden;
    @FXML
    private MenuItem mItemRegistreren;
    @FXML
    private MenuItem mItemInfo;
    @FXML
    private MenuItem mItemNederlands, mItemFrans, mItemEngels;
    @FXML
    protected Label lblStatus;

    private DomeinController c;
    private GridPane content;
    //private AanmeldenScherm aanmelden;

    public StartScherm(DomeinController c)
    {
        this.c = c;

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
    }
    
    private void aanmelden(ActionEvent event)
    {
        //wisContent();
        content = new AanmeldenScherm(this, c);
        addContent(content);
    }
    
    private void registreren(ActionEvent event)
    {
        //wisContent();
        content = new RegistrerenScherm(this, c);
        addContent(content);
    }
    
    private void afsluiten(ActionEvent event)
    {
        Platform.exit();
    }
    
    private void nederlands(ActionEvent event)
    {
        
    }
    
    private void frans(ActionEvent event)
    {
        
    }
    
    private void engels(ActionEvent event)
    {
        
    }
    
    private void info(ActionEvent event)
    {
        
    }
    
    private void wisContent()
    {
        this.getChildren().clear();
    }
    
    private void addContent(GridPane content)
    {
        this.add(content,0,1);
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
