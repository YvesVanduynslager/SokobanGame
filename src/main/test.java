/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.Spel;
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
        Spel s = sp.geefSpel("easy");
        s.start();
        System.out.println(s.getHuidigSpelbord().toString());
        s.start();
        System.out.println(s.getHuidigSpelbord().toString());
        s.start();
        System.out.println(s.getHuidigSpelbord().toString());
    }
}