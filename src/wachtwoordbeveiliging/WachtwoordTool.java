package wachtwoordbeveiliging;

import java.util.Scanner;

/**
 * Deze tool is gemaakt omwille van de manier waarop ik de wachtwoord security
 * heb geimplementeerd. Deze tool is bedoelt om gemakkelijk de gehashte
 * wachtwoorden te genereren om in the geven in de sql-files. Er wordt om een
 * wachtwoord string gevraagd. Deze string is het wachtwoord dat word ingegeven
 * in de tekst vakken in de GUI. De string die daarna wordt terug gegeven is de
 * string zoals die in de database zal worden opgeslagen.
 *
 * @author Michiel
 */
public class WachtwoordTool
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String passw, passw_hashed, salt;
        salt = "$2a$10$RV4IhXXJFyL3EmzvS4sqHu";
        System.out.println("Geef het te beveiligen wachtwoord in: ");
        passw = scanner.next();
        passw_hashed = BCrypt.hashpw(passw, salt);
        System.out.println("Het beveiligde wachtwoord is: ");
        System.out.println(passw_hashed);
    }
}
