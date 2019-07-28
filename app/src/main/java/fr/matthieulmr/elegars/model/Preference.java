package fr.matthieulmr.elegars.model;

import java.io.Serializable;

/**
 * Created by matthieulmr on 28/06/16.
 */
public class Preference implements Serializable {

    public boolean silentmode;

    /**
     * Récupére le boolean silent mode
     *
     * @return -> boolean
     */
    public boolean isSilentmode() {
        return silentmode;
    }

    /**
     * Fixe le boolean silent mode
     *
     * @param silentmode boolean
     */
    public void setSilentmode(boolean silentmode) {
        this.silentmode = silentmode;
    }
}
