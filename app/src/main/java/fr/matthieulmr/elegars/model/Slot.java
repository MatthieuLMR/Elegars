package fr.matthieulmr.elegars.model;

import java.io.File;

/**
 * Created by the_con on 16/06/16.
 */
public class Slot {

    /**
     * chemin du fichier de Sauvegarde
     */
    private String path;

    /**
     * nom du fichier
     */
    private String slot;

    /**
     * constructeur
     * @param vPath (String) : chemin ou enregistrer le fichier
     * @param vSlot (String) : le nom du fichier
     */
    public Slot(String vPath, String vSlot) {

        //initialisation des valeurs
        this.path = vPath + File.separator + vSlot;
        this.slot = vSlot;
    }

    /**
     * Getter : récupère le chemin du fichier
     *
     * @return -> String
     */
    public String getPath() {
        return path;
    }

    /**
     * permet de modifier le chemin du fichier
     *
     * @param path (String)
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Getter : récupère le nom du fichier
     *
     * @return -> String
     */
    public String getSlot() {
        return slot;
    }

    /**
     * permet de modifier le nom du fichier
     *
     * @param slot (String)
     */
    public void setSlot(String slot) {
        this.slot = slot;
    }
}
