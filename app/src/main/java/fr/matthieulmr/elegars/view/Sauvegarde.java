package fr.matthieulmr.elegars.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import fr.matthieulmr.elegars.R;
import fr.matthieulmr.elegars.controller.FileManager;
import fr.matthieulmr.elegars.controller.InitActivity;
import fr.matthieulmr.elegars.model.Constantes;
import fr.matthieulmr.elegars.model.Personnage;
import fr.matthieulmr.elegars.model.Slot;

/**
 * Created by matthieulmr on 16/06/16.
 */
public class Sauvegarde extends Activity {

    Boolean vSlot1Vide = false;
    Boolean vSlot2Vide = false;
    Boolean vSlot3Vide = false;
    Boolean vSlot4Vide = false;

    @Override
    public void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.sauvegarde);

    }

    @Override
    public void onResume(){
        super.onResume();

        // On charge les fonts du système
        final AssetManager vAssetManager = this.getAssets();
        final Typeface vTypeFaceBeneg = Typeface.createFromAsset(vAssetManager, "fonts/EnchantedLand.otf");
        final Typeface vTypeFaceSketch = Typeface.createFromAsset(vAssetManager, "fonts/Sketch.ttf");

        InitActivity.newActivity(this, vTypeFaceBeneg, vTypeFaceSketch);

        final Slot vSlot1 = new Slot(FileManager.getPath_app(), "1");
        final Slot vSlot2 = new Slot(FileManager.getPath_app(), "2");
        final Slot vSlot3 = new Slot(FileManager.getPath_app(), "3");
        final Slot vSlot4 = new Slot(FileManager.getPath_app(), "4");

        Button vBtn1 = (Button) findViewById(R.id.btn1);
        Button vBtn2 = (Button) findViewById(R.id.btn2);
        Button vBtn3 = (Button) findViewById(R.id.btn3);
        Button vBtn4 = (Button) findViewById(R.id.btn4);

        vBtn1.setTypeface(vTypeFaceBeneg);
        vBtn2.setTypeface(vTypeFaceBeneg);
        vBtn3.setTypeface(vTypeFaceBeneg);
        vBtn4.setTypeface(vTypeFaceBeneg);

        final Personnage vPerso1 = FileManager.lirePerso(vSlot1);
        final Personnage vPerso2 = FileManager.lirePerso(vSlot2);
        final Personnage vPerso3 = FileManager.lirePerso(vSlot3);
        final Personnage vPerso4 = FileManager.lirePerso(vSlot4);

        if(vPerso1 == null){vBtn1.setText("Vide"); vSlot1Vide = true;}else{vBtn1.setText("Partie de " + vPerso1.getNom());}
        if(vPerso2 == null){vBtn2.setText("Vide"); vSlot2Vide = true;}else{vBtn2.setText("Partie de " + vPerso2.getNom());}
        if(vPerso3 == null){vBtn3.setText("Vide"); vSlot3Vide = true;}else{vBtn3.setText("Partie de " + vPerso3.getNom());}
        if(vPerso4 == null){vBtn4.setText("Vide"); vSlot4Vide = true;}else{vBtn4.setText("Partie de " + vPerso4.getNom());}

        vBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class vPointSauvegarde = null;
                try {
                    if (vSlot1Vide){
                        Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                    }else {
                        vPointSauvegarde = Class.forName("fr.matthieulmr.elegars.view." + vPerso1.getPointSVG());
                        Constantes.vSlotEnCours = vSlot1;
                        Intent vActivityRace = new Intent(Sauvegarde.this, vPointSauvegarde);
                        startActivity(vActivityRace);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class vPointSauvegarde = null;
                try {
                    if (vSlot2Vide){
                        Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                    }else {
                        vPointSauvegarde = Class.forName("fr.matthieulmr.elegars.view." + vPerso2.getPointSVG());
                        Constantes.vSlotEnCours = vSlot2;
                        Intent vActivityRace = new Intent(Sauvegarde.this, vPointSauvegarde);
                        startActivity(vActivityRace);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                }

            }
        });

        vBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class vPointSauvegarde = null;
                try {
                    if (vSlot3Vide){
                        Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                    }else {
                        vPointSauvegarde = Class.forName("fr.matthieulmr.elegars.view." + vPerso3.getPointSVG());
                        Constantes.vSlotEnCours = vSlot3;
                        Intent vActivityRace = new Intent(Sauvegarde.this, vPointSauvegarde);
                        startActivity(vActivityRace);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                }

            }
        });

        vBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class vPointSauvegarde = null;
                try {
                    if (vSlot4Vide){
                        Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                    }else {
                        vPointSauvegarde = Class.forName("fr.matthieulmr.elegars.view." + vPerso4.getPointSVG());
                        Constantes.vSlotEnCours = vSlot4;
                        Intent vActivityRace = new Intent(Sauvegarde.this, vPointSauvegarde);
                        startActivity(vActivityRace);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Aucune sauvegarde présente", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
