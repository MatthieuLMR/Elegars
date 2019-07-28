package fr.matthieulmr.elegars.view;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import fr.matthieulmr.elegars.model.Squelette;
import org.w3c.dom.Text;

/**
 * Created by matthieulmr on 28/06/16.
 */
public class PremierCombat extends Activity {

    Personnage vPerso = FileManager.lirePerso(Constantes.vSlotEnCours);
    Squelette vSquelette = new Squelette();

    Boolean vFin = false;

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.premiercombat);

    }

    @Override
    public void onResume() {
        super.onResume();

        vPerso = FileManager.lirePerso(Constantes.vSlotEnCours);
        vSquelette.initSquellette(5);

        // On charge les fonts du système
        final AssetManager vAssetManager = this.getAssets();
        final Typeface vTypeFaceBeneg = Typeface.createFromAsset(vAssetManager, "fonts/EnchantedLand.otf");
        final Typeface vTypeFaceSketch = Typeface.createFromAsset(vAssetManager, "fonts/Sketch.ttf");

        InitActivity.newActivity(this, vTypeFaceBeneg, vTypeFaceSketch);

        ImageView vImageAvatarEnemi = (ImageView) findViewById(R.id.imageAvatarEnemi);
        vImageAvatarEnemi.setBackgroundResource(R.drawable.squelette_guerrier);

        final TextView vTextVieWidgetEnemi = (TextView) findViewById(R.id.textVieBarProgressWidgetEnemi);
        vTextVieWidgetEnemi.setTypeface(vTypeFaceBeneg);
        vTextVieWidgetEnemi.setTextSize(18);

        final TextView vTextViewTextCombat = (TextView) findViewById(R.id.txtCombat);

        TextView vTextManaEnemi = (TextView) findViewById(R.id.textManaBarProgressWidgetEnemi);
        vTextManaEnemi.setTypeface(vTypeFaceBeneg);
        vTextManaEnemi.setTextSize(18);
        vTextManaEnemi.setText(vTextManaEnemi.getText() + " : 0");

        ImageView vImageAvatar = (ImageView) findViewById(R.id.imageAvatar);
        switch (vPerso.getSexe()){
            case ("homme") :

                switch (vPerso.getRace()) {
                    case ("homme") :
                        vImageAvatar.setBackgroundResource(R.drawable.avatar_chevalier_homme);
                        break;
                    case ("elfe") :
                        vImageAvatar.setBackgroundResource(R.drawable.avatar_elfe_homme);
                        break;
                    case ("nain") :
                        vImageAvatar.setBackgroundResource(R.drawable.avatar_nain_homme);
                        break;
                    default: //
                }

                break;
            case ("femme") :

                switch (vPerso.getRace()) {
                    case ("homme") :
                        vImageAvatar.setBackgroundResource(R.drawable.avatar_chevalier_femme);
                        break;
                    case ("elfe") :
                        vImageAvatar.setBackgroundResource(R.drawable.avatar_elfe_femme);
                        break;
                    case ("nain") :
                        vImageAvatar.setBackgroundResource(R.drawable.avatar_nain_femme);
                        break;
                    default: //
                }

                break;
            default: //
        }

        final TextView vTextVieWidgetPerso = (TextView) findViewById(R.id.textVieBarProgressWidgetPerso);
        vTextVieWidgetPerso.setTypeface(vTypeFaceBeneg);
        vTextVieWidgetPerso.setTextSize(18);
        vTextVieWidgetPerso.setText(vTextVieWidgetPerso.getText() + " : " + String.valueOf(vPerso.getVie()));
        final ProgressBar vProgressBarVie = (ProgressBar) findViewById(R.id.progressBarVie);

        final ProgressBar vProgressBarMana = (ProgressBar) findViewById(R.id.progressBarMana);
        TextView vTextManaWidgetPerso = (TextView) findViewById(R.id.textManaBarProgressWidgetPerso);
        vTextManaWidgetPerso.setTypeface(vTypeFaceBeneg);
        vTextManaWidgetPerso.setTextSize(18);
        vTextManaWidgetPerso.setText(vTextManaWidgetPerso.getText() + " : " + String.valueOf(vPerso.getMana()));

        TextView vNamePersoWidget = (TextView) findViewById(R.id.textNamePersoWidget);

        vNamePersoWidget.setText(vPerso.getNom());
        vNamePersoWidget.setTypeface(vTypeFaceBeneg);
        vNamePersoWidget.setTextSize(23);

        final ProgressBar vProgressBarVieSquelette = (ProgressBar) findViewById(R.id.progressBarVieEnemi);
        vTextVieWidgetEnemi.setTypeface(vTypeFaceBeneg);
        vTextVieWidgetEnemi.setTextSize(18);
        vTextVieWidgetEnemi.setText(vTextVieWidgetEnemi.getText() + " : " + String.valueOf(vSquelette.getVie()));

        vProgressBarVie.setMax(vPerso.getVie());
        vProgressBarVie.setProgress(vPerso.getVie());

        vProgressBarMana.setMax(vPerso.getMana());
        vProgressBarMana.setProgress(vPerso.getMana());

        vProgressBarVieSquelette.setMax(vSquelette.getVie());
        vProgressBarVieSquelette.setProgress(vSquelette.getVie());

        final ImageButton vBtnDes = (ImageButton) findViewById(R.id.btnDes10);

        final RotateAnimation vAnimDes = new RotateAnimation(0 , 359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        vAnimDes.setRepeatCount(8);
        vAnimDes.setDuration(100);

        vBtnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LanceDes(vProgressBarVieSquelette, vProgressBarVie, vTextVieWidgetPerso, vTextVieWidgetEnemi, vTextViewTextCombat);
                vBtnDes.startAnimation(vAnimDes);
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

    public void LanceDes(final ProgressBar vProgressBarVieSquelette, final ProgressBar vProgressBarVie, final TextView vTextVieWidgetPerso, final TextView vTextVieWidgetEnemi, final TextView vTxtCombat) {
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

                        if(i == 10) {vFin = true;}

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run () {

                                String vTextCombat;

                                if (vFin){
                                    vPerso.determinateFa(null, resultat);
                                    int resultatDesSquelette = Dice.diceLauncher(10);
                                    vSquelette.determinateFa(null, resultatDesSquelette);

                                    vTextCombat = "La force d'attaque se termine en ajoutant le résultat du dès plus votre habilité.\n";
                                    vTextCombat += "Vu qu'il s'agît d'un combat à main nue, un malus de -1 est appliqué\n\n";
                                    vTextCombat += "Votre force d'attaque : \n\n"
                                            + "Habilité " + vPerso.getHabilite() + " + " + resultat + " = "
                                            + vPerso.getFa() + "\n\n";
                                    vTextCombat += "Force d'attaque du squelette : \n\n"
                                            + "Habilité " + vSquelette.getHabilite() + " + " + resultatDesSquelette + " = "
                                            + vSquelette.getFa() + "\n\n";


                                    if (vPerso.getFa() > vSquelette.getFa()) {
                                        vProgressBarVieSquelette.setProgress(vProgressBarVieSquelette.getProgress() - 1);
                                        vSquelette.setVie(vProgressBarVieSquelette.getProgress());
                                        vTextVieWidgetEnemi.setText("Vie : " + vSquelette.getVie());
                                        vTextCombat += "Votre force d'attaque est supérieur\n";
                                        vTextCombat += "Le squelette perd un point de vie";
                                    } else if (vSquelette.getFa() > vPerso.getFa()) {
                                        vProgressBarVie.setProgress(vProgressBarVie.getProgress() - 1);
                                        vPerso.setVie(vProgressBarVie.getProgress());
                                        vTextVieWidgetPerso.setText("Vie : " + vPerso.getVie());
                                        vTextCombat += "La force d'attaque du squelette est supérieur\n";
                                        vTextCombat += "Vous perdez un point de vie";
                                    }

                                    vTxtCombat.setText(vTextCombat);

                                    if (vProgressBarVie.getProgress() == 0) {
                                        Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
                                    }

                                    if (vProgressBarVieSquelette.getProgress() == 0) {
                                        Toast.makeText(getApplicationContext(), "Bravo le squelette est mort !!", Toast.LENGTH_SHORT).show();
                                    }

                                    vFin = false;
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
