package fr.matthieulmr.elegars.controller;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import java.util.Objects;

/**
 * Created by matthieulmr on 16/06/16.
 */
public class InitActivity {

    public static void newActivity(Activity pActivity, Typeface vTypeFaceBeneg, Typeface vTypeFaceSketch) {

        // On récupére l'id de l'action bar
        int titleId = pActivity.getResources().getIdentifier("action_bar_title", "id", "android");

        // On récupére le textview de l'actionBar
//        TextView vView = (TextView) pActivity.findViewById(titleId);
//
//        // On modifie la taille, couleur et la font du texte actionbar
//        vView.setTextSize(45);
//        vView.setTextColor(Color.BLACK);
//        vView.setTypeface(vTypeFaceSketch);

        try {
            // On retire l'affichage de l'icone dans l'ActionBar
            Objects.requireNonNull(pActivity.getActionBar()).setDisplayShowHomeEnabled(false);
            // On change le fond de l'actionBar
            Objects.requireNonNull(pActivity.getActionBar()).setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 250, 225)));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

    }
}
