package fr.matthieulmr.elegars.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.matthieulmr.elegars.R;
import fr.matthieulmr.elegars.controller.FileManager;
import fr.matthieulmr.elegars.controller.InitActivity;
import fr.matthieulmr.elegars.controller.MusicService;
import fr.matthieulmr.elegars.model.Constantes;
import fr.matthieulmr.elegars.model.Personnage;
import fr.matthieulmr.elegars.model.Slot;

/**
 * Created by matthieulmr on 17/06/16.
 */
public class NewPartie extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.newpartie);

        // On charge les fonts du système
        final AssetManager vAssetManager = this.getAssets();
        final Typeface vTypeFaceBeneg = Typeface.createFromAsset(vAssetManager, "fonts/EnchantedLand.otf");
        final Typeface vTypeFaceSketch = Typeface.createFromAsset(vAssetManager, "fonts/Sketch.ttf");

        InitActivity.newActivity(this, vTypeFaceBeneg, vTypeFaceSketch);

        Slot vSlot1 = new Slot(FileManager.getPath_app(), "1");
        Slot vSlot2 = new Slot(FileManager.getPath_app(), "2");
        Slot vSlot3 = new Slot(FileManager.getPath_app(), "3");
        Slot vSlot4 = new Slot(FileManager.getPath_app(), "4");

        TextView vTextWelcome = (TextView) findViewById(R.id.textCreationPerso);
        vTextWelcome.setTypeface(vTypeFaceBeneg);
        vTextWelcome.setText(R.string.welcome);

        Slot vSlot = null;
        if(!FileManager.testFile(vSlot1)){
            vSlot = vSlot1;
        }else if (!FileManager.testFile(vSlot2)){
            vSlot = vSlot2;
        }else if (!FileManager.testFile(vSlot3)){
            vSlot = vSlot3;
        }else if (!FileManager.testFile(vSlot4)){
            vSlot = vSlot4;
        }else {
            vTextWelcome.setText(R.string.svgFull);
        }

        if(vSlot != null){
            final Slot vSlotEnCours = vSlot;
            TextView vTextAnswerName = (TextView) findViewById(R.id.textAnswerName);
            vTextAnswerName.setVisibility(View.VISIBLE);
            vTextAnswerName.setTypeface(vTypeFaceBeneg);
            vTextAnswerName.setText(R.string.answerName);

            final EditText vEditNameHero = (EditText) findViewById(R.id.editNameHero);
            vEditNameHero.setVisibility(View.VISIBLE);

            Button vBtnValideName = (Button) findViewById(R.id.btnValideName);
            vBtnValideName.setVisibility(View.VISIBLE);
            vBtnValideName.setTypeface(vTypeFaceBeneg);
            vBtnValideName.setText(R.string.valider);

            vBtnValideName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(vEditNameHero.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(), "Veuillez entrer un nom", Toast.LENGTH_SHORT).show();
                    }else {
                        Personnage vPerso = new Personnage(vEditNameHero.getText().toString(), 0);
                        vPerso.setNom(vEditNameHero.getText().toString());
                        FileManager.writePerso(vPerso, vSlotEnCours);
                        Constantes.vSlotEnCours = vSlotEnCours;
                        Intent vActivitySexePerso = new Intent(NewPartie.this, SexePersonnage.class);
                        startActivity(vActivitySexePerso);
                    }
                }
            });

        }
    }
}
