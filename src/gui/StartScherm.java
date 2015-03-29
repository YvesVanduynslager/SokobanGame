/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Yves
 */
public class StartScherm extends GridPane
{
    private DomeinController c;
    
    @FXML
    private Pane pnStart;
    @FXML
    private Button btnSpelen;
    @FXML
    private Button btnAanpassen;
    @FXML
    private Button btnMaken;
    @FXML
    private Button btnRegistreren;
    @FXML
    private Button btnAanmelden;
    @FXML
    private Label lblBericht;

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
        
        lblBericht = new Label();
        
        //btnAanmelden = new Button();
        //btnAanmelden.setText("Aanmelden");
        btnAanmelden.setOnAction(this::aanmelden);
        
        btnRegistreren = new Button();
        btnRegistreren.setText("Registreren");
    }
    
    private void aanmelden(ActionEvent event)
    {
        Stage stage = new Stage();
        stage.setTitle("Aanmelden");
        
        Scene scene = new Scene(new AanmeldenScherm(c));
        
        stage.setScene(scene);
        
        this.setDisable(true);
        
//        Stage stageStartScherm = (Stage)btnAanmelden.getScene().getWindow();
//        EventHandler handler = event -> event.consume();
//        stageStartScherm.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST,handler);
//        
//        stage.setOnCloseRequest(handler);
//
//        //luisteraar indien het subscherm gesloten wordt. 
//        //---------------------------------------------
//        stage.addEventHandler(WindowEvent.WINDOW_HIDING,
//                event -> {
//                    StartScherm.this.setDisable(false);
//                    lblBericht.setText(c.geefSpeler()[0] + " " + c.geefSpeler()[1]);
//                    stageStartScherm.removeEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, handler);
//                });

        // Het subscherm wordt niet kleiner dan het minimum scherm.
        //---------------------------------------------------------
        stage.setOnShown(e -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });

        stage.show();
    }    
}