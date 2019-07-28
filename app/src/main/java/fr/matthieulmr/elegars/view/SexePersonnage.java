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
public class SexePersonnage extends Activity {

    private Boolean vHomme = false;
    private Boolean vFemme = false;

    Personnage vPerso = FileManager.lirePerso(Constantes.vSlotEnCours);

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.sexepersonnage);

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

        TextView vTextChoixSexe = (TextView) findViewById(R.id.textChoixSexe);
        vTextChoixSexe.setTypeface(vTypeFaceBeneg);

        TextView vTextHomme = (TextView) findViewById(R.id.textHomme);
        vTextHomme.setTypeface(vTypeFaceBeneg);

        TextView vTextFemme = (TextView) findViewById(R.id.textFemme);
        vTextFemme.setTypeface(vTypeFaceBeneg);

        Button vIBtnSexeContinue = (Button) findViewById(R.id.iBtnSexeContinue);
        vIBtnSexeContinue.setTypeface(vTypeFaceBeneg);

        final ImageButton vIBtnHomme = (ImageButton) findViewById(R.id.iBtnHomme);
        final ImageButton vIBtnFemme = (ImageButton) findViewById(R.id.iBtnFemme);

        if (vPerso.getSexe() != null) {
            switch (vPerso.getSexe()) {
                case ("homme"):
                    vIBtnHomme.setBackgroundResource(R.drawable.border_image_button);
                    vIBtnFemme.setBackgroundColor(Color.TRANSPARENT);
                    vHomme = true;
                    vFemme = false;
                    break;
                case ("femme"):
                    vIBtnFemme.setBackgroundResource(R.drawable.border_image_button);
                    vIBtnHomme.setBackgroundColor(Color.TRANSPARENT);
                    vFemme = true;
                    vHomme = false;
                    break;
                default: //
            }
        }

        vIBtnHomme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vIBtnHomme.setBackgroundResource(R.drawable.border_image_button);
                vIBtnFemme.setBackgroundColor(Color.TRANSPARENT);
                vPerso.setSexe("homme");
                vHomme = true;
                vFemme = false;
            }
        });

        vIBtnFemme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vIBtnFemme.setBackgroundResource(R.drawable.border_image_button);
                vIBtnHomme.setBackgroundColor(Color.TRANSPARENT);
                vPerso.setSexe("femme");
                vFemme = true;
                vHomme = false;
            }
        });

        vIBtnSexeContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vHomme || vFemme) {
                    FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
                    Intent vActivityRace = new Intent(SexePersonnage.this, RacePersonnage.class);
                    startActivity(vActivityRace);
                }else{
                    Toast.makeText(getApplicationContext(), "Veuillez choisir un sexe", Toast.LENGTH_SHORT).show();
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
