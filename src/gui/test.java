/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Spel;
import domein.Spelbord;
import persistentie.SpelMapper;

/**
 *
 * @author Yves
 */
public class test
{
    public static void main(String[] args)
    {
        SpelMapper sp = new SpelMapper();
        Spel s = sp.geefSpel("makkelijk");
        Spelbord[] borden = s.getSpelborden();
        System.out.print(borden[0].toString());
    }
}
