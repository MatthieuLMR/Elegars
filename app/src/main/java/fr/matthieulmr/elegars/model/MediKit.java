package fr.matthieulmr.elegars.model;

/**
 * Created by matthieulmr on 15/06/16.
 */
public class MediKit {

    String nom;
    String description;
    int nbrPV;

    /**
     * Récupére le nom du medikit
     * @return -> String nom du medikit
     */
    public String getNom() {
        return nom;
    }

    /**
     * Fixe le nom du medikit
     * @param nom -> String nom du medikit
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupére la description du medikit
     * @return -> String description du medikit
     */
    public String getDescription() {
        return description;
    }

    /**
     * Fixe la description du medikit
     * @param description -> String description du medikit
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Récupére le nombre de PV en plus du medikit
     * @return  -> int nombre de pv
     */
    public int getNbrPV() {
        return nbrPV;
    }

    /**
     * Fixe le nombre de PV en plus du medikit
     * @param nbrPV -> int nombre de pv
     */
    public void setNbrPV(int nbrPV) {
        this.nbrPV = nbrPV;
    }
}
