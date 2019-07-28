package fr.matthieulmr.elegars.model;

import fr.matthieulmr.elegars.controller.Dice;

import java.io.Serializable;
import java.util.List;

/**
 * Created by matthieulmr on 15/06/16.
 */
public class Personnage implements Serializable{

    private String nom;
    private String sexe;
    private String race;
    private int experience;
    private int level;

    private int habilite;
    private int vie;
    private int mana;
    private List<Arme> vLstArme;
    private List<Equipement> vLstEquipement;
    private List<MediKit> vLstMedikit;
    private int fa;

    private String PointSVG;

    public Personnage(){
        this.nom = "";
        this.level = 0;
    }

    public Personnage(String nom, int level){
        this.nom = nom;
        this.level = level;
    }

    /**
     * initialise une caractéristique du personnage en fonction du résultat du dé
     * @param vAttribut (String) : l'attribut à initialiser
     * @param vDice (int) : le résultat du dé
     */
    public void initCaracteristics(String vAttribut, String vRace, int vDice) {

        int vBonus = 0;

        switch(vAttribut) {
            case "habilite":
                switch (vRace) {
                    case "homme":
                        vBonus = 10;
                        break;
                    case "elfe":
                        vBonus = 15;
                        break;
                    case "nain":
                        vBonus = 10;
                        break;
                    default: //erreur dans l'attribut
                        break;
                }
                this.habilite = vDice + vBonus;
                break;
            case "vie":
                switch (vRace) {
                    case "homme":
                        vBonus = 15;
                        break;
                    case "elfe":
                        vBonus = 5;
                        break;
                    case "nain":
                        vBonus = 20;
                        break;
                    default: //erreur dans l'attribut
                        break;
                }
                this.vie = vDice + vBonus;
                break;
            case "mana":
                switch (vRace) {
                    case "homme":
                        vBonus = 5;
                        break;
                    case "elfe":
                        vBonus = 10;
                        break;
                    case "nain":
                        vBonus = 0;
                        break;
                    default: //erreur dans l'attribut
                        break;
                }
                this.mana = vDice + vBonus;
                break;
            default: //erreur dans l'attribut
                break;
        }
    }

    /**
     * Récupére le nom du personnage
     *
     * @return -> String nom du personnage
     */
    public String getNom() {
        return nom;
    }

    /**
     * Fixe le nom du personnage
     *
     * @param nom String nom du personnage
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupére le sexe du personnage
     *
     * @return -> String sexe du personnage
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * Fixe le sexe du personnage
     *
     * @param sexe String sexe du personnage
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * Récupére la race du personnage
     *
     * @return -> String race du personnage
     */
    public String getRace() {
        return race;
    }

    /**
     * Fixe la race du personnage
     *
     * @param race String race du personnage
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * Récupére l'expérience du personnage
     *
     * @return -> int expérience du personnage
     */


    public int getExperience() {
        return experience;
    }

    /**
     * Fixe l'expérience du personnage
     *
     * @param experience int expérience du personnage
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Récupére le level du personnage
     *
     * @return -> int level du personnage
     */
    public int getLevel() {
        return level;
    }

    /**
     * Fixe le level du personnage
     *
     * @param level int du personnage
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Récupére l'habilité du personnage
     *
     * @return -> int habilité du personnage
     */
    public int getHabilite() {
        return habilite;
    }

    /**
     * Fixe l'habilité du personnage
     *
     * @param habilite int habilité du personnage
     */
    public void setHabilite(int habilite) {
        this.habilite = habilite;
    }

    /**
     * Récupére la vie du personnage
     *
     * @return -> int vie du personnage
     */
    public int getVie() {
        return vie;
    }

    /**
     * Fixe la vie du personnage
     *
     * @param vie int vie du personnage
     */
    public void setVie(int vie) {
        this.vie = vie;
    }

    /**
     * Récupére le mana du personnage
     *
     * @return -> int mana du personnage
     */
    public int getMana() {
        return mana;
    }

    /**
     * Fixe le mana du personnage
     *
     * @param mana int mana du personnage
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Récupére la liste des armes du personnage
     *
     * @return -> java.util.List<fr.matthieulmr.elegars.model.Arme>
     */
    public List<Arme> getvLstArme() {
        return vLstArme;
    }

    /**
     * Fixe la liste des armes du personnage
     *
     * @param vLstArme java.util.List<fr.matthieulmr.elegars.model.Arme>
     */
    public void setvLstArme(List<Arme> vLstArme) {
        this.vLstArme = vLstArme;
    }

    /**
     * Récupére la liste d'équipement du personnage
     *
     * @return -> java.util.List<fr.matthieulmr.elegars.model.Equipement>
     */
    public List<Equipement> getvLstEquipement() {
        return vLstEquipement;
    }

    /**
     * Fixe la liste d'équipement du personnage
     *
     * @param vLstEquipement java.util.List<fr.matthieulmr.elegars.model.Equipement>
     */
    public void setvLstEquipement(List<Equipement> vLstEquipement) {
        this.vLstEquipement = vLstEquipement;
    }

    /**
     * Récupére la liste des médikit du personnage
     *
     * @return -> java.util.List<fr.matthieulmr.elegars.model.MediKit>
     */
    public List<MediKit> getvLstMedikit() {
        return vLstMedikit;
    }

    /**
     * Fixe la liste des médikit du personnage
     *
     * @param vLstMedikit java.util.List<fr.matthieulmr.elegars.model.MediKit>
     */
    public void setvLstMedikit(List<MediKit> vLstMedikit) {
        this.vLstMedikit = vLstMedikit;
    }

    /**
     * Getter : récupère la force d'attaque du personnage
     *
     * @return -> int
     */
    public int getFa() {
        return fa;
    }

    /**
     * permet de modifier la force d'attaque du personnage
     *
     * @param fa (int)
     */
    public void setFa(int fa) {
        this.fa = fa;
    }

    /**
     * détermine la Force d'attague d'un personnage en fonction de l'arme choisie
     * @param vArmChoosen (Arme) : arme choisit pour attaquer, null correspond aux main nues
     */
    public void determinateFa(Arme vArmChoosen, int vResultatDes) {

        this.fa = vResultatDes + this.habilite;

        if (vArmChoosen == null) {

            //cas de combat à main nu

            //-1 correspond au malus à main nu)
            this.fa --;

        }else {

            //on ajoute un bonus et on enlève un malus s'il y'en a
            this.fa += vArmChoosen.getBonus() - vArmChoosen.getMalus();
        }
    }

    /**
     * Récupére le point de sauvegarde
     *
     * @return -> String nom de l'activity
     */
    public String getPointSVG() {
        return PointSVG;
    }

    /**
     * Fixe le point de sauvegarde
     *
     * @param pointSVG String nom de l'activity
     */
    public void setPointSVG(String pointSVG) {
        PointSVG = pointSVG;
    }
}
