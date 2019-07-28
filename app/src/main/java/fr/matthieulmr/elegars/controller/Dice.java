package fr.matthieulmr.elegars.controller;

import java.util.Random;

/**
 * Created by matthieulmr on 15/06/16.
 */
public class Dice {

    public static int diceLauncher(int nbr){
        return new Random().nextInt(nbr);
    }

}
