package gui;

import domein.DomeinController;
import java.util.Scanner;
import gui.ConsoleApplicatie;
/**
 * Main-klasse. Dit is de klasse die de UI zal starten en weergeven/tekenen
 *
 * @author Yves
 */
public class SokobanApp
{
    public static void main(String args[])
    {
    
        DomeinController spelController = new DomeinController();
        gui.ConsoleApplicatie.ConsoleApplicatie(spelController);
    }
}
