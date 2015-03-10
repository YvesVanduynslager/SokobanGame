package gui;

import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author Yves
 */
public class SpeelSpel
{
    DomeinController controller;
    public SpeelSpel(DomeinController controller)
    {
        this.controller = controller;
    }
    
    public void startSpelSpelUI()
    {
        String spelnamen[] = controller.geefSpelNamen();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Kies een spel uit de volgende mogelijkheden:");
        for(String spelnaam : spelnamen)
        {
            System.out.println(spelnaam);
        }
        
        System.out.println("Uw keuze: ");
        controller.selecteerSpel(scanner.next());        
    }
}
