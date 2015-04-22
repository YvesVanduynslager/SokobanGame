/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class ControlsSchermController extends GridPane implements Initializable, Refreshable
{
    @FXML
    private Label lblTitel, lblZ, lblQ, lblS, lblD;

    private DomeinController c;

    public ControlsSchermController(DomeinController c)
    {
        this.c = c;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlsScherm.fxml"));
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
        
        lblZ.getStyleClass().add("keys");
        lblQ.getStyleClass().add("keys");
        lblS.getStyleClass().add("keys");
        lblD.getStyleClass().add("keys");

        refresh();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @Override
    public final void refresh()
    {
        lblTitel.setText(c.getString("controls"));
    }
}
