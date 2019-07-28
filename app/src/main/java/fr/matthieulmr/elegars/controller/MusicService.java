package fr.matthieulmr.elegars.controller;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;
import fr.matthieulmr.elegars.R;

/**
 * Created by matthieulmr on 28/06/16.
 */
public class MusicService extends Service implements MediaPlayer.OnErrorListener {

    private final IBinder mBinder = new ServiceBinder();

    MediaPlayer vMediaPlayer;
    private int length = 0;

    public MusicService() { }

    public class ServiceBinder extends Binder{
        public MusicService getService()
        {
            return MusicService.this;
        }
    }

    @Override
    public IBinder onBind(Intent arg){return mBinder;}

    @Override
    public void onCreate(){
        super.onCreate();

        vMediaPlayer = MediaPlayer.create(this, R.raw.theme);
        vMediaPlayer.setOnErrorListener(this);

        if(vMediaPlayer != null){
            vMediaPlayer.setLooping(true);
            vMediaPlayer.setVolume(30, 30);
        }

        vMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
                onError(mediaPlayer, what, extra);
                return true;
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        vMediaPlayer.start();
        return START_STICKY;
    }

    public void pauseMusic(){
        if (vMediaPlayer.isPlaying()){
            vMediaPlayer.pause();
            length=vMediaPlayer.getCurrentPosition();
        }
    }

    public void resumeMusic(){
        if(vMediaPlayer.isPlaying()==false){
            vMediaPlayer.seekTo(length);
            vMediaPlayer.start();
        }
    }

    public void stopMusic(){
        vMediaPlayer.release();
        vMediaPlayer.stop();
        vMediaPlayer = null;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(vMediaPlayer != null){
            try {
                vMediaPlayer.stop();
                vMediaPlayer.release();
            }finally {
                vMediaPlayer = null;
            }
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra){
        Toast.makeText(this, "Erreur de lancement de la musique", Toast.LENGTH_SHORT).show();
        if(vMediaPlayer != null){
            try {
                vMediaPlayer.stop();
                vMediaPlayer.release();
            }finally {
                vMediaPlayer = null;
            }
        }
        return false;
    }

}
