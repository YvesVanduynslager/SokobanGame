
package main;

/* IMPORTS */
import javafx.application.Application;
import javafx.stage.Stage;
import domein.DomeinController;
import cui.ConsoleApplicatie;

/**
 * Main-klasse. Dit is de klasse die de UI zal starten en weergeven/tekenen
 *
 * @author Yves
 */
public class ConsoleStartUp extends Application
{
    @Override
    public void start(Stage stage)
    {
        
    }
    
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