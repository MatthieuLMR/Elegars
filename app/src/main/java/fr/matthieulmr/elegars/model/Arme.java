package fr.matthieulmr.elegars.model;

/**
 * Created by matthieulmr on 15/06/16.
 */
public class Arme {

    private String nom;
    private String description;
    private String ulrImage;
    private int munitions;
    private int bonus;
    private int malus;

    /**
     * Récupére le nom de l'arme
     * @return -> String nom de l'arme
     */
    public String getNom() {
        return nom;
    }

    /**
     * Fixe le nom de l'arme
     * @param nom -> String nom de l'arme
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupére la description de l'arme
     * @return -> String description de l'arme
     */
    public String getDescription() {
        return description;
    }

    /**
     * Fixe la description de l'arme
     * @param description -> String description de l'arme
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Récupére l'url de l'image de l'arme
     * @return  -> String url de l'image de l'arme
     */
    public String getUlrImage() {
        return ulrImage;
    }

    /**
     * Fixe l'url de l'image de l'arme
     * @param ulrImage -> String url de l'image de l'arme
     */
    public void setUlrImage(String ulrImage) {
        this.ulrImage = ulrImage;
    }

    /**
     * Récupére le bonus de l'arme
     * @return -> int bonus de l'arme
     */
    public int getBonus() {
        return bonus;
    }

    /**
     * Fixe le bonus de l'arme
     * @param bonus -> int bonus de l'arme
     */
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    /**
     * Récupére le malus de l'arme
     * @return -> int malus de l'arme
     */
    public int getMalus() {
        return malus;
    }

    /**
     * Fixe le malus de l'arme
     * @param malus -> int malus de l'arme
     */
    public void setMalus(int malus) {
        this.malus = malus;
    }

    /**
     * Récupére le nombre de munition du personnage
     *
     * @return -> int nombre de munition
     */
    public int getMunitions() {
        return munitions;
    }

    /**
     * Fixe le nombre de munition du personnage
     *
     * @param munitions int nombre du personnage
     */
    public void setMunitions(int munitions) {
        this.munitions = munitions;
    }
}
