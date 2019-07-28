package fr.matthieulmr.elegars.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import fr.matthieulmr.elegars.R;
import fr.matthieulmr.elegars.controller.FileManager;
import fr.matthieulmr.elegars.controller.InitActivity;
import fr.matthieulmr.elegars.controller.MusicService;
import fr.matthieulmr.elegars.main;
import fr.matthieulmr.elegars.model.Constantes;
import fr.matthieulmr.elegars.model.Preference;

/**
 * Created by matthieulmr on 28/06/16.
 */
public class Config extends Activity {

    public Intent music = new Intent();
    Preference vPreference = FileManager.lirePref(Constantes.vSlotPreference);

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.config);

    }

    @Override
    public void onResume() {
        super.onResume();

        // On charge les fonts du système
        final AssetManager vAssetManager = this.getAssets();
        final Typeface vTypeFaceBeneg = Typeface.createFromAsset(vAssetManager, "fonts/EnchantedLand.otf");
        final Typeface vTypeFaceSketch = Typeface.createFromAsset(vAssetManager, "fonts/Sketch.ttf");

        InitActivity.newActivity(this, vTypeFaceBeneg, vTypeFaceSketch);

        Switch vSwitchMusique = (Switch) findViewById(R.id.switchMusique);
        vSwitchMusique.setTypeface(vTypeFaceBeneg);

        Button vBtnGestionSVG = (Button) findViewById(R.id.btnGestionSVG);
        vBtnGestionSVG.setTypeface(vTypeFaceBeneg);

        music.setClass(this, MusicService.class);

        if (vPreference != null) {
            boolean modeSilence = vPreference.isSilentmode();
            System.out.println("Etat musique : " + String.valueOf(modeSilence));
            vSwitchMusique.setChecked(modeSilence);
        }else{
            vSwitchMusique.setChecked(true);
        }

        vSwitchMusique.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean vEtat) {
                vPreference.setSilentmode(vEtat);
                System.out.println("Switch musique : " + String.valueOf(vEtat));
                FileManager.writePref(vPreference, Constantes.vSlotPreference);
                if (vEtat){
                    startService(music);
                }else if (!vEtat){
                    stopService(music);
                }
            }
        });

        vBtnGestionSVG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vActivityGestionSVG = new Intent(Config.this, GestionSauvegarde.class);
                startActivity(vActivityGestionSVG);
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
        FileManager.writePref(vPreference, Constantes.vSlotPreference);
        super.onDestroy();
    }
}
