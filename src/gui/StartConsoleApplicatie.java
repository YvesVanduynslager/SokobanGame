package gui;

import domein.DomeinController;

/**
 * Main-klasse. Dit is de klasse die de UI zal starten en weergeven/tekenen
 *
 * @author Yves
 */
public class StartConsoleApplicatie
{
    public static void main(String args[])
    {
        DomeinController spelController = new DomeinController();
        ConsoleApplicatie.StartUI(spelController);
    }
}
