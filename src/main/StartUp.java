package main;

import domein.DomeinController;
import gui.ConsoleApplicatie;

/**
 * Main-klasse. Dit is de klasse die de UI zal starten en weergeven/tekenen
 *
 * @author Yves
 */
public class StartUp
{
    public static void main(String args[])
    {
        DomeinController controller = new DomeinController();
        ConsoleApplicatie app = new ConsoleApplicatie(controller);
        app.toonHoofdmenu();
    }
}
