package info.test.reminder.helper;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;

import info.test.reminder.R;

/**
 * Created by k.benamor on 28-09-2017.
 */

public class SoundHelper {

    //region Variables
    static SoundHelper     instance;
    static Context context;
    static AudioManager mAudioManager;
    static MediaPlayer mMediaPlayer;
    static Vibrator vibrator;
    //endregion

    //region Constructor
    public SoundHelper(Context _context) {
        this.context  = _context;
    }

    public static SoundHelper getInstance(Context context) {
        return instance == null ? new SoundHelper(context) : instance;
    }
    //endregion

    //region dialog Alert / Info Message
    public static void Start(int type) {

        vibrator      = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 2, 0); // TODO sound to be 9

        switch (type)
        {
            case 1:
                DoneSound();
                break;
            case 2:
                AlertSound();
                break;
            case 3:
                EndSound();
                break;
        }

        /*mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        /*switch (mAudioManager.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
                mMediaPlayer3.setVolume(0, 0);
                break;
        }*/

        //mMediaPlayer.start();*/
    }
    //endregion

    //region Done Sound
    public static void DoneSound(){
        //mMediaPlayer    = MediaPlayer.create(context, R.raw.next_beep);
        vibrateEvent();
    }
    //endregion

    //region Done Sound
    public static void AlertSound(){
        //mMediaPlayer    = MediaPlayer.create(context, R.raw.alert_beep);
        vibrateEventPattern();
    }
    //endregion

    //region Done Sound
    public static void EndSound(){
        //mMediaPlayer    = MediaPlayer.create(context, R.raw.end_beep);
    }
    //endregion

    //region vibrate Event
    public static void vibrateEvent()
    {
        vibrator.vibrate(500);
    }
    //endregion

    //region vibrate Event Pattern
    public static void vibrateEventPattern() {
        long[] pattern = {
                0,  // Start immediately
                200, 200, 200,     // s
                500
        };
        // Only perform getContext() pattern one time (-1 means "do not repeat")
        vibrator.vibrate(pattern, -1);
    }
    //endregion

}
//endregion
