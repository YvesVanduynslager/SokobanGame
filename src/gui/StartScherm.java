/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
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
    private AanmeldenScherm aanmelden;
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
    protected Label lblStatus;

    private DomeinController c;
    private GridPane content;

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
        mItemAfsluiten.setOnAction(this::afsluiten);
    }
    
    protected void aanmelden(ActionEvent event)
    {
        aanmelden = new AanmeldenScherm(this, c);
        this.add(aanmelden, 0, 1);
        
    }
    
    protected void afsluiten(ActionEvent event)
    {
        
    }
}
