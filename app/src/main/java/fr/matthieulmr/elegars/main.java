package fr.matthieulmr.elegars;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import fr.matthieulmr.elegars.controller.FileManager;
import fr.matthieulmr.elegars.controller.InitActivity;
import fr.matthieulmr.elegars.controller.MusicService;
import fr.matthieulmr.elegars.model.Constantes;
import fr.matthieulmr.elegars.model.Preference;
import fr.matthieulmr.elegars.model.Slot;
import fr.matthieulmr.elegars.view.Config;
import fr.matthieulmr.elegars.view.NewPartie;
import fr.matthieulmr.elegars.view.Sauvegarde;

import java.io.File;

/**
 * Created by matthieulmr on 15/06/16.
 */
public class main extends Activity {

    public Intent music = new Intent();
    public Preference vPreference;
    public String PATH_APP1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState == null) {
            savedInstanceState = new Bundle();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        PATH_APP1 = this.getFilesDir()+File.separator;
        savedInstanceState.putString(Constantes.PATH_APP, PATH_APP1);
        FileManager.setPath_app(PATH_APP1);

        Constantes.vSlotEnCours = null;
        Constantes.vSlotPreference = new Slot(FileManager.getPath_app(), "pref");
        vPreference = FileManager.lirePref(Constantes.vSlotPreference);

    }

    @Override
    public void onResume(){
        super.onResume();

        vPreference = FileManager.lirePref(Constantes.vSlotPreference);

        if (vPreference != null) {
            boolean modeSilence = vPreference.isSilentmode();

            if (modeSilence) {
                // On lance la musique de fond
                music.setClass(this, MusicService.class);
                startService(music);
            }
        }else {
            vPreference = new Preference();
            vPreference.setSilentmode(false);
            FileManager.writePref(vPreference, Constantes.vSlotPreference);
            music.setClass(this, MusicService.class);
            startService(music);
        }

        // On charge les fonts du systeme
        final AssetManager vAssetManager = this.getAssets();
        final Typeface vTypeFaceBeneg = Typeface.createFromAsset(vAssetManager, "fonts/EnchantedLand.otf");
        final Typeface vTypeFaceSketch = Typeface.createFromAsset(vAssetManager, "fonts/Sketch.ttf");

        InitActivity.newActivity(this, vTypeFaceBeneg, vTypeFaceSketch);

        // On récupére les boutons de l'accueil
        Button vBtnNewPartie = (Button) findViewById(R.id.btnNewPartie);
        Button vBtnContinuePartie = (Button) findViewById(R.id.btnConinuePartie);
        ImageButton vBtnConfig = (ImageButton) findViewById(R.id.btnConfig);

        // On applique la police sur les boutons
        vBtnNewPartie.setTypeface(vTypeFaceBeneg);
        vBtnContinuePartie.setTypeface(vTypeFaceBeneg);

        afficheButtonContinuePartie(PATH_APP1, vBtnContinuePartie);

        // On place un écouteur sur le bouton continuer une partie
        vBtnContinuePartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vActivitySVG = new Intent(main.this, Sauvegarde.class);
                startActivity(vActivitySVG);
            }
        });

        // On place un écouteur sur le bouton nouvelle partie
        vBtnNewPartie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vActivityNewPartie = new Intent(main.this, NewPartie.class);
                startActivity(vActivityNewPartie);
            }
        });

        // On place un écouteur sur le bouton gestionsvg
        vBtnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vActivityConfig = new Intent(main.this, Config.class);
                startActivity(vActivityConfig);
            }
        });
    }

    private void afficheButtonContinuePartie(String PATH_APP, Button vBtnContinuePartie){
        // On lance le test de la présence de Sauvegarde
        boolean vPresenceSVG = false;
        for (int i = 1; i < 5; i++) {
            Slot vSlot = new Slot(PATH_APP, String.valueOf(i));
            if (FileManager.testFile(vSlot)) {
                vPresenceSVG = true;
            }
        }
        if (!vPresenceSVG){
            vBtnContinuePartie.setVisibility(View.GONE);
        }else{
            vBtnContinuePartie.setVisibility(View.VISIBLE);
        }
    }

    // Mise en place de la musique

    private boolean mIsBound = false;
    private MusicService mServ;

    private ServiceConnection Scon = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }


        public void onServiceDisconnected(ComponentName componentName) {
            mServ = null;
        }
    };

    @Override
    public void onPause(){
        FileManager.writePref(vPreference, Constantes.vSlotPreference);
        super.onPause();
    }

    @Override
    public void onStop(){
        FileManager.writePref(vPreference, Constantes.vSlotPreference);
        super.onStop();
    }

    @Override
    public void onDestroy(){
        stopService(music);
        super.onDestroy();
    }
}
