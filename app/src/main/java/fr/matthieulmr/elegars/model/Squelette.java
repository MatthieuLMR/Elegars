package fr.matthieulmr.elegars.model;

import fr.matthieulmr.elegars.controller.Dice;

/**
 * Created by matthieulmr on 17/07/16.
 */
public class Squelette extends Personnage {

    public void initSquellette(int vLevel) {
        this.setLevel(vLevel);
        this.setHabilite(Dice.diceLauncher(10) + vLevel);
        this.setVie(Dice.diceLauncher(10) + vLevel);
    }
}
