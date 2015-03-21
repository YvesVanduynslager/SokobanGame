package main;

/* IMPORTS */
import javafx.application.Application;
import javafx.stage.Stage;
import domein.DomeinController;
import gui.ConsoleApplicatie;

/**
 * Main-klasse. Dit is de klasse die de UI zal starten en weergeven/tekenen
 *
 * @author Yves
 */
public class StartUp extends Application
{
    @Override
    public void start(Stage primaryStage){}
    
    /**
     * Main methode die wordt uitgevoerd bij start van het programma.
     * @param args arguments ingesteld in IDE.
     */
    public static void main(String args[])
    {
        DomeinController controller = new DomeinController();
        ConsoleApplicatie app = new ConsoleApplicatie(controller);
        app.toonHoofdmenu();
    }
}
