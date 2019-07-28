package fr.matthieulmr.elegars.view;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import fr.matthieulmr.elegars.R;
import fr.matthieulmr.elegars.controller.FileManager;
import fr.matthieulmr.elegars.controller.InitActivity;
import fr.matthieulmr.elegars.model.Personnage;
import fr.matthieulmr.elegars.model.Slot;

/**
 * Created by matthieulmr on 17/06/16.
 */
public class GestionSauvegarde extends Activity {

    @Override
    public void onCreate(Bundle saveInstanceState){

        super.onCreate(saveInstanceState);
        setContentView(R.layout.gestionsvg);

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

        Button vBtn1 = (Button) findViewById(R.id.btn1supp);
        Button vBtn2 = (Button) findViewById(R.id.btn2supp);
        Button vBtn3 = (Button) findViewById(R.id.btn3supp);
        Button vBtn4 = (Button) findViewById(R.id.btn4supp);

        vBtn1.setTypeface(vTypeFaceBeneg);
        vBtn2.setTypeface(vTypeFaceBeneg);
        vBtn3.setTypeface(vTypeFaceBeneg);
        vBtn4.setTypeface(vTypeFaceBeneg);

        Personnage vPerso1 = FileManager.lirePerso(vSlot1);
        Personnage vPerso2 = FileManager.lirePerso(vSlot2);
        Personnage vPerso3 = FileManager.lirePerso(vSlot3);
        Personnage vPerso4 = FileManager.lirePerso(vSlot4);

        if(vPerso1 == null){vBtn1.setText("Vide");}else{vBtn1.setText("Partie de " + vPerso1.getNom());}
        if(vPerso2 == null){vBtn2.setText("Vide");}else{vBtn2.setText("Partie de " + vPerso2.getNom());}
        if(vPerso3 == null){vBtn3.setText("Vide");}else{vBtn3.setText("Partie de " + vPerso3.getNom());}
        if(vPerso4 == null){vBtn4.setText("Vide");}else{vBtn4.setText("Partie de " + vPerso4.getNom());}

        vBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileManager.removeFile(vSlot1);
                finish();
                startActivity(getIntent());
            }
        });

        vBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileManager.removeFile(vSlot2);
                finish();
                startActivity(getIntent());
            }
        });

        vBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileManager.removeFile(vSlot3);
                finish();
                startActivity(getIntent());
            }
        });

        vBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileManager.removeFile(vSlot4);
                finish();
                startActivity(getIntent());
            }
        });

    }
}
