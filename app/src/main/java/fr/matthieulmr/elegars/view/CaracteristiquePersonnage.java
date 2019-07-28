package fr.matthieulmr.elegars.view;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.*;
import fr.matthieulmr.elegars.R;
import fr.matthieulmr.elegars.controller.Dice;
import fr.matthieulmr.elegars.controller.FileManager;
import fr.matthieulmr.elegars.controller.InitActivity;
import fr.matthieulmr.elegars.model.Constantes;
import fr.matthieulmr.elegars.model.Personnage;

/**
 * Created by matthieulmr on 20/06/16.
 */
public class CaracteristiquePersonnage extends Activity {

    Personnage vPerso = FileManager.lirePerso(Constantes.vSlotEnCours);

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.caracteristiquepersonnage);

    }

    @Override
    public void onResume() {
        super.onResume();

        vPerso = FileManager.lirePerso(Constantes.vSlotEnCours);

        // On charge les fonts du système
        final AssetManager vAssetManager = this.getAssets();
        final Typeface vTypeFaceBeneg = Typeface.createFromAsset(vAssetManager, "fonts/EnchantedLand.otf");
        final Typeface vTypeFaceSketch = Typeface.createFromAsset(vAssetManager, "fonts/Sketch.ttf");

        InitActivity.newActivity(this, vTypeFaceBeneg, vTypeFaceSketch);

        final TextView vTextHautCaracteristique = (TextView) findViewById(R.id.textHautCaracteristique);
        final TextView vTextVie = (TextView) findViewById(R.id.textVie);
        final TextView vTextHabilite = (TextView) findViewById(R.id.textHabilite);
        final TextView vTextMana = (TextView) findViewById(R.id.textMana);
        final TextView vTextClicDes = (TextView) findViewById(R.id.textClicDes);
        final TextView vTextResultat = (TextView) findViewById(R.id.textResultat);
        final TextView vTextVieMax = (TextView) findViewById(R.id.textVieMax);
        final TextView vTextHabiliteMax = (TextView) findViewById(R.id.textHabiliteMax);
        final TextView vTextManaMax = (TextView) findViewById(R.id.textManaMax);
        final ProgressBar vProgressBarVie = (ProgressBar) findViewById(R.id.ProgressBarVie);
        final ProgressBar vProgressBarHabilite = (ProgressBar) findViewById(R.id.ProgressBarHabilite);
        final ProgressBar vProgressBarMana = (ProgressBar) findViewById(R.id.ProgressBarMana);
        final Button vBtnContinue = (Button) findViewById(R.id.iBtnCaracteristiqueContinue);

        vTextHautCaracteristique.setTypeface(vTypeFaceBeneg);
        vTextVie.setTypeface(vTypeFaceBeneg);
        vTextHabilite.setTypeface(vTypeFaceBeneg);
        vTextMana.setTypeface(vTypeFaceBeneg);
        vTextClicDes.setTypeface(vTypeFaceBeneg);
        vTextResultat.setTypeface(vTypeFaceBeneg);
        vTextVieMax.setTypeface(vTypeFaceBeneg);
        vTextHabiliteMax.setTypeface(vTypeFaceBeneg);
        vTextManaMax.setTypeface(vTypeFaceBeneg);
        vBtnContinue.setTypeface(vTypeFaceBeneg);
        vProgressBarVie.setProgress(0);
        vProgressBarHabilite.setProgress(0);
        vProgressBarMana.setProgress(0);

        switch (vPerso.getRace()) {
            case "homme":
                vProgressBarHabilite.setMax(20);
                vTextHabiliteMax.setText("/20");
                vProgressBarMana.setMax(15);
                vTextManaMax.setText("/15");
                vProgressBarVie.setMax(25);
                vTextVieMax.setText("/25");
                break;
            case "elfe":
                vProgressBarHabilite.setMax(25);
                vTextHabiliteMax.setText("/25");
                vProgressBarMana.setMax(20);
                vTextManaMax.setText("/20");
                vProgressBarVie.setMax(15);
                vTextVieMax.setText("/15");
                break;
            case "nain":
                vProgressBarHabilite.setMax(20);
                vTextHabiliteMax.setText("/20");
                vProgressBarMana.setMax(10);
                vTextManaMax.setText("/10");
                vProgressBarVie.setMax(30);
                vTextVieMax.setText("/30");
                break;
            default: //
        }

        if (vPerso.getVie() != 0) {
            vProgressBarVie.setProgress(vPerso.getVie());
            vTextVieMax.setText(String.valueOf(vPerso.getVie()) + vTextVieMax.getText().toString());
            vTextVieMax.setVisibility(View.VISIBLE);
            vProgressBarVie.setVisibility(View.VISIBLE);

            vProgressBarHabilite.setProgress(vPerso.getHabilite());
            if (vPerso.getHabilite() != 0) {
                vTextHabiliteMax.setText(String.valueOf(vPerso.getHabilite()) + vTextHabiliteMax.getText().toString());
            }
            vTextHabiliteMax.setVisibility(View.VISIBLE);
            vProgressBarHabilite.setVisibility(View.VISIBLE);

            vProgressBarMana.setProgress(vPerso.getMana());
            if (vPerso.getMana() != 0) {
                vTextManaMax.setText(String.valueOf(vPerso.getMana()) + vTextManaMax.getText().toString());
            }
            vTextManaMax.setVisibility(View.VISIBLE);
            vProgressBarMana.setVisibility(View.VISIBLE);
        }

        final ImageButton vBtnDes = (ImageButton) findViewById(R.id.btnDes10);

        final RotateAnimation vAnimDes = new RotateAnimation(0, 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        vAnimDes.setRepeatCount(8);
        vAnimDes.setDuration(100);

        vBtnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LanceDes(vBtnContinue, vTextVieMax, vTextHabiliteMax, vTextManaMax, vTextResultat, vProgressBarVie, vProgressBarHabilite, vProgressBarMana, vPerso);
                vBtnDes.startAnimation(vAnimDes);
            }
        });

        vBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vPerso.getVie() == 0 || vPerso.getHabilite() == 0 || vPerso.getMana() == 0) {
                    Toast.makeText(getApplicationContext(), "Vous n'avez pas terminé", Toast.LENGTH_SHORT).show();
                } else {
                    FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
                    Intent vActivityCombat = new Intent(CaracteristiquePersonnage.this, PremierCombat.class);
                    startActivity(vActivityCombat);
                }
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        onStop();
    }

    @Override
    public void onStop() {
        super.onStop();
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        vPerso.setPointSVG(this.getClass().getSimpleName());
        FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
    }

    public void LanceDes(final Button vBtnContinue, final TextView vTextVieMax, final TextView vTextHabiliteMax, final TextView vTextManaMax, final TextView vTextResultat, final ProgressBar vProgressBarVie, final ProgressBar vProgressBarHabilite, final ProgressBar vProgressBarMana, final Personnage vPerso) {
        new Thread() {
            public void run() {
                try {
                    int i = 0;
                    while (i < 10) {
                        Thread.sleep(75);
                        final int resultat = Dice.diceLauncher(10);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView vTextDes = (TextView) findViewById(R.id.textDes10);
                                vTextDes.setText(String.valueOf(resultat));
                            }
                        });
                        i++;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView vTextDes = (TextView) findViewById(R.id.textDes10);
                            if (!vTextDes.getText().toString().equals("")) {
                                int resultat = Integer.parseInt(vTextDes.getText().toString());
                                if (vProgressBarVie.getProgress() == 0) {
                                    vPerso.initCaracteristics("vie", vPerso.getRace(), resultat);
                                    FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
                                    vProgressBarVie.setVisibility(View.VISIBLE);
                                    vTextVieMax.setText(String.valueOf(vPerso.getVie()) + vTextVieMax.getText().toString());
                                    vTextVieMax.setVisibility(View.VISIBLE);
                                    vProgressBarVie.setProgress(vPerso.getVie());
                                    vTextResultat.setText("Resultat : " + resultat + " + le bonus " + vPerso.getRace() + " = " + vPerso.getVie());
                                    vTextVieMax.setVisibility(View.VISIBLE);
                                } else if (vProgressBarHabilite.getProgress() == 0) {
                                    vPerso.initCaracteristics("habilite", vPerso.getRace(), resultat);
                                    FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
                                    vProgressBarHabilite.setVisibility(View.VISIBLE);
                                    vTextHabiliteMax.setVisibility(View.VISIBLE);
                                    vTextHabiliteMax.setText(String.valueOf(vPerso.getHabilite() + vTextHabiliteMax.getText().toString()));
                                    vProgressBarHabilite.setProgress(vPerso.getHabilite());
                                    vTextResultat.setText("Resultat : " + resultat + " + le bonus " + vPerso.getRace() + " = " + vPerso.getHabilite());
                                } else if (vProgressBarMana.getProgress() == 0) {
                                    vPerso.initCaracteristics("mana", vPerso.getRace(), resultat);
                                    FileManager.writePerso(vPerso, Constantes.vSlotEnCours);
                                    vProgressBarMana.setVisibility(View.VISIBLE);
                                    vTextManaMax.setVisibility(View.VISIBLE);
                                    vTextManaMax.setText(String.valueOf(vPerso.getMana()) + vTextManaMax.getText().toString());
                                    vProgressBarMana.setProgress(vPerso.getMana());
                                    vTextResultat.setText("Resultat : " + resultat + " + le bonus " + vPerso.getRace() + " = " + vPerso.getMana());
                                } else {
                                    Toast.makeText(getApplicationContext(), "Caractéristiques déjà générées", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
