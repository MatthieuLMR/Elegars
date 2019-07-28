package fr.matthieulmr.elegars.model;

/**
 * Created by matthieulmr on 15/06/16.
 */
public class Equipement {

    String nom;
    String Description;

    /**
     * Récupére le nom de l'équipement
     * @return -> String nom de l'équipement
     */
    public String getNom() {
        return nom;
    }

    /**
     * Fixe le nom de l'équipement
     * @param nom -> String nom de l'équipement
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupére la description de l'équipement
     * @return -> String description de l'équipement
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Fixe la description de l'équipement
     * @param description -> String description de l'équipement
     */
    public void setDescription(String description) {
        Description = description;
    }
}
