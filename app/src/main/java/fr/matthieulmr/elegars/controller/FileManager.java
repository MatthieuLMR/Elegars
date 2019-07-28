package fr.matthieulmr.elegars.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import fr.matthieulmr.elegars.model.Personnage;
import fr.matthieulmr.elegars.model.Preference;
import fr.matthieulmr.elegars.model.Slot;

/**
 * Created by matthieulmr on 15/06/16.
 */
public class FileManager {

    private static String path_app;

    /**
     * teste si le fichier de sauvegarde passé en paramètre est présent
     * @param vSlot (Slot)
     * @return -> boolean vrai s'il existe, faux sinon
     */
    public static boolean testFile(Slot vSlot){
        File vFile = new File(vSlot.getPath());
        return vFile.exists();
    }

    /**
     * teste si le fichier de sauvegarde passé en paramètre est vide
     * @param vSlot (Slot)
     * @return -> boolean vrai s'il est vide, faux sinon
     */
    public static boolean emptyFile(Slot vSlot){
        File vFile = new File(vSlot.getPath());
        return vFile.length() == 0;
    }

    /**
     * supprime le fihier de sauvegarde passé en paramètre
     * @param vSlot (Slot)
     * @return -> vrai si ce fichier est bien effacer, faux sinon
     */
    public static boolean removeFile(Slot vSlot){
        File vFile = new File(vSlot.getPath());
        return vFile.delete();
    }

    /**
     * récupère une valeur dans un fichier en spécifiant le fichier et la clé
     * @param vKey (String)
     * @param vSlot (Slot)
     */
    public static String getValueInFile(String vKey, Slot vSlot) {

        File vFile = new File(vSlot.getPath());

        //mot lu par le scanner
        String vReadWord;

        if (!FileManager.testFile(vSlot)) {

            //cas ou le fichier n'existe pas on retourne une chaine vide
            return "";

        }else{

            //cas ou le fichier existe

            try {
                Scanner vSc = new Scanner(vFile);

                //tant qu'il y a des lignes à lire
                while (vSc.hasNext()) {

                    //récupération de la ligne
                    vReadWord = vSc.nextLine();

                    //découpage
                    String[] vSplitLine = vReadWord.split(":");

                    //si la 1ère partie correspond
                    //on retourne la 2ème partie
                    //sinon on continue la lecture
                    if (vSplitLine[0].equals(vKey)) {
                        return vSplitLine[1];
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        //on retourne une chaine vide si aucune des clé ne correspond
        return "";
    }

    /**
     * écrit un text dasn un fichier
     * @param vMessage (String) : le texte à écrire
     * @param vSlot (Slot) : le fichier où l'on souhaite écrire
     * @param vNewLine (boolean) : si vrai, rajoute un retour à la ligne
     */
    public static void writeInFile(String vMessage, Slot vSlot, boolean vNewLine) {

        try {

            //ouverture d'un fichier en écriture
            FileOutputStream output = new FileOutputStream(vSlot.getPath());

            //test du paramètre de retour à la ligne
            vMessage += (vNewLine) ? "\n" : "";

            //écriture du message dans le fichier
            output.write(vMessage.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * écrit un perso dans un fichier
     * @param vPers (Personnage) : le personnage à écrire
     * @param vSlot (Slot) : le fichier où l'on souhaite écrire
     */
    public static void writePerso(Personnage vPers, Slot vSlot){
        try {
            //ouverture d'un fichier de type object stream
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(vSlot.getPath()));
            // ecriture dans le fichier
            output.writeObject(vPers);
            // on referme le fichier
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * lecture d'un perso dans le fichier
     * @param vSlot (Slot) : le fichier où l'on veut lire
     * @return vPerso (Personnage) : retourne le personnage lu
     */
    public static Personnage lirePerso(Slot vSlot){
        try{
            // ouverture d'un fichier de type object stream
            if(testFile(vSlot)) {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(vSlot.getPath()));
                // lecture de l'objet du fichier
                Personnage vPerso = (Personnage) input.readObject();
                // on referme le fichier
                input.close();
                return vPerso;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * écrit les préférence dans un fichier
     * @param vPreference (Preference) : les préférence à écrire
     * @param vSlot (Slot) : le fichier où l'on souhaite écrire
     */
    public static void writePref(Preference vPreference, Slot vSlot){
        try {
            //ouverture d'un fichier de type object stream
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(vSlot.getPath()));
            // ecriture dans le fichier
            output.writeObject(vPreference);
            // on referme le fichier
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * lecture des préférence dans le fichier
     * @param vSlot (Slot) : le fichier où l'on veut lire
     * @return vPreference (Preference) : retourne les preferences lu
     */
    public static Preference lirePref(Slot vSlot){
        try{
            // ouverture d'un fichier de type object stream
            if(testFile(vSlot)) {
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(vSlot.getPath()));
                // lecture de l'objet du fichier
                Preference vPreference = (Preference) input.readObject();
                // on referme le fichier
                input.close();
                return vPreference;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * renvoi un tableau contenant les noms des fichiers de sauvegarde existant ou non vide
     * @return -> ArrayList<String> : tableau des noms fichiers de sauvegardes
     */
    public static ArrayList<String> getAllExistFile() {

        ArrayList<String> vListSlot = new ArrayList<>();
        for(int i = 1; i <= 4; i++) {
            Slot tempSlot = new Slot(FileManager.path_app, String.valueOf(i));
            if (FileManager.testFile(tempSlot) && !FileManager.emptyFile(tempSlot)) {
                vListSlot.add("btn" + i);
            }
        }
        return vListSlot;
    }


    /**
     * permet de modifier le chemin de l'application
     *
     * @param path_app (String)
     */
    public static void setPath_app(String path_app) {
        FileManager.path_app = path_app;
    }

    /**
     * Getter : récupère
     *
     * @return -> String
     */
    public static String getPath_app() {
        return path_app;
    }


}
