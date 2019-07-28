package fr.matthieulmr.elegars.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import fr.matthieulmr.elegars.R;
import fr.matthieulmr.elegars.controller.FileManager;
import fr.matthieulmr.elegars.controller.InitActivity;
import fr.matthieulmr.elegars.model.Constantes;
import fr.matthieulmr.elegars.model.Personnage;

/**
 * Created by matthieulmr on 21/06/16.
 */
public class RacePersonnage extends Activity {

    private boolean vHomme = false;
    private boolean vElfe = false;
    private boolean vNain = false;

    Personnage vPerso = FileManager.lirePerso(Constantes.vSlotEnCours);

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.racepersonnage);

    }

    @Override
    public void onResume(){
        super.onResume();

        vPerso = FileManager.lirePerso(Constantes.vSlotEnCours);

        // On charge les fonts du système
        final AssetManager vAssetManager = this.getAssets();
        final Typeface vTypeFaceBeneg = Typeface.createFromAsset(vAssetManager, "fonts/EnchantedLand.otf");
        final Typeface vTypeFaceSketch = Typeface.createFromAsset(vAssetManager, "fonts/Sketch.ttf");

        InitActivity.newActivity(this, vTypeFaceBeneg, vTypeFaceSketch);

        TextView vTextChoixRace = (TextView) findViewById(R.id.textChoixRace);
        vTextChoixRace.setTypeface(vTypeFaceBeneg);

        Button viBtnRaceContinue = (Button) findViewById(R.id.iBtnRaceContinue);
        viBtnRaceContinue.setTypeface(vTypeFaceBeneg);

        final ImageButton vIBtnRaceHomme = (ImageButton) findViewById(R.id.iBtnRaceHomme);
        final ImageButton vIBtnRaceElfe = (ImageButton) findViewById(R.id.iBtnRaceElfe);
        final ImageButton vIBtnRaceNaine = (ImageButton) findViewById(R.id.iBtnRaceNain);

        final TextView vTextDefinitionRAce = (TextView) findViewById(R.id.definitionRace);
        vTextDefinitionRAce.setTypeface(vTypeFaceBeneg);

        switch (vPerso.getSexe()) {
            case ("homme") :
                vIBtnRaceHomme.setImageResource(R.drawable.silhouette_homme);
                vIBtnRaceElfe.setImageResource(R.drawable.silhouette_elfe);
                vIBtnRaceNaine.setImageResource(R.drawable.silhouette_nain);
                break;
            case ("femme") :
                vIBtnRaceHomme.setImageResource(R.drawable.silhouette_femme);
                vIBtnRaceElfe.setImageResource(R.drawable.silhouette_femme_elfe);
                vIBtnRaceNaine.setImageResource(R.drawable.silhouette_naine);
                break;
            default: //
        }

        if (vPerso.getRace() != null) {
            switch (vPerso.getRace()) {
                case ("homme"):
                    vIBtnRaceHomme.setBackgroundResource(R.drawable.border_image_button);
                    vIBtnRaceElfe.setBackgroundColor(Color.TRANSPARENT);
                    vIBtnRaceNaine.setBackgroundColor(Color.TRANSPARENT);
                    vTextDefinitionRAce.setText(R.string.descriptionHumain);
                    vTextDefinitionRAce.setVisibility(View.VISIBLE);
                    vHomme = true;
                    vElfe = false;
                    vNain = false;
                    break;
                case ("elfe"):
                    vIBtnRaceHomme.setBackgroundColor(Color.TRANSPARENT);
                    vIBtnRaceElfe.setBackgroundResource(R.drawable.border_image_button);
                    vIBtnRaceNaine.setBackgroundColor(Color.TRANSPARENT);
                    vTextDefinitionRAce.setText(R.string.descriptionElfe);
                    vTextDefinitionRAce.setVisibility(View.VISIBLE);
                    vHomme = false;
                    vElfe = true;
                    vNain = false;
                    break;
                case ("nain"):
                    vIBtnRaceHomme.setBackgroundColor(Color.TRANSPARENT);
                    vIBtnRaceElfe.setBackgroundColor(Color.TRANSPARENT);
                    vIBtnRaceNaine.setBackgroundResource(R.drawable.border_image_button);
                    vTextDefinitionRAce.setText(R.string.descriptionNain);
                    vTextDefinitionRAce.setVisibility(View.VISIBLE);
                    vHomme = false;
                    vElfe = false;
                    vNain = true;
                    break;
                default://
            }
        }

        vIBtnRaceHomme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vPerso.setRace("homme");
                vIBtnRaceHomme.setBackgroundResource(R.drawable.border_image_button);
                vIBtnRaceElfe.setBackgroundColor(Color.TRANSPARENT);
                vIBtnRaceNaine.setBackgroundColor(Color.TRANSPARENT);
                vTextDefinitionRAce.setText(R.string.descriptionHumain);
                vTextDefinitionRAce.setVisibility(View.VISIBLE);
                vHomme = true;
                vElfe = false;
                vNain = false;
            }
        });

        vIBtnRaceElfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vPerso.setRace("elfe");
                vIBtnRaceHomme.setBackgroundColor(Color.TRANSPARENT);
                vIBtnRaceElfe.setBackgroundResource(R.drawable.border_image_button);
                vIBtnRaceNaine.setBackgroundColor(Color.TRANSPARENT);
                vTextDefinitionRAce.setText(R.string.descriptionElfe);
                vTextDefinitionRAce.setVisibility(View.VISIBLE);
                vHomme = false;
                vElfe = true;
                vNain = false;
            }
        });

        vIBtnRaceNaine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vPerso.setRace("nain");
                vIBtnRaceHomme.setBackgroundColor(Color.TRANSPARENT);
                vIBtnRaceElfe.setBackgroundColor(Color.TRANSPARENT);
                vIBtnRaceNaine.setBackgroundResource(R.drawable.border_image_button);
                vTextDefinitionRAce.setText(R.string.descriptionNain);
                vTextDefinitionRAce.setVisibility(View.VISIBLE);
                vHomme = false;
                vElfe = false;
                vNain = true;
            }
        });

        viBtnRaceContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vHomme || vElfe || vNain) {
                    FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
                    Intent vActivityCaracteristique = new Intent(RacePersonnage.this, CaracteristiquePersonnage.class);
                    startActivity(vActivityCaracteristique);
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez choisir une race", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onPause(){
        super.onPause();
        onStop();
    }

    @Override
    public void onStop(){
        super.onStop();
        onDestroy();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        vPerso.setPointSVG(this.getClass().getSimpleName());
        FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
    }
}
