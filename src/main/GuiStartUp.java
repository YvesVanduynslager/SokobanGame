package main;

/* IMPORTS */
import javafx.application.Application;
import javafx.stage.Stage;
import domein.DomeinController;
import gui.StartSchermController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.WindowEvent;

/**
 * Main-klasse. Dit is de klasse die de UI zal starten en weergeven/tekenen
 *
 * @author Yves
 */
public class GuiStartUp extends Application
{
    @Override
    public void start(Stage stage)
    {
        DomeinController domeinController = new DomeinController();
        Scene scene = new Scene(new StartSchermController(domeinController));
        
        scene.getStylesheets().add("/css/style.css");

        stage.setScene(scene);
        stage.setTitle("Sokoban Mario");
        stage.getIcons().add(new Image("/images/box1.png"));
        
        stage.setOnShown(e -> {
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
        });
        
        
        stage.setOnCloseRequest((WindowEvent t) ->
        {
            Platform.exit();
            System.exit(0);
        });
        stage.setResizable(false);
        
        stage.show();
    }
    
    /**
     * Main methode die wordt uitgevoerd bij start van het programma.
     * @param args arguments ingesteld in IDE.
     */
    public static void main(String args[])
    {
        Application.launch(GuiStartUp.class, args);
    }
}