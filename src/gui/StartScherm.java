/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class StartScherm extends GridPane
{
    @FXML
    private Pane pnStart;
    @FXML
    private Label lblTitel;

    private DomeinController c;
    private Menubalk menu;

    public StartScherm(DomeinController c)
    {
        menu = new Menubalk(this);
        this.c = c;
        this.add(menu, 0, 0);

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
    }
}
