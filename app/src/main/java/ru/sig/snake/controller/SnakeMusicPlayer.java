package ru.sig.snake.controller;

import android.app.Activity;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.sig.snake.R;

/**
 * Created by Alexander Ionov on 05.10.14.
 */
public class SnakeMusicPlayer
{
    private static SnakeMusicPlayer instance;

    private static final float MUSIC_VOLUME = 0.5f;
    private static final float SOUND_VOLUME = 1f;

    private static final int TRACKS_COUNT = 2;
    private MediaPlayer music;
    private final List<MediaPlayer> sounds;

    private Activity activity;

    public static SnakeMusicPlayer getInstance()
    {
        if (instance == null)
        {
            synchronized (SnakeMusicPlayer.class)
            {
                if (instance == null)
                {
                    instance = new SnakeMusicPlayer();
                }
            }
        }

        return instance;
    }

    private SnakeMusicPlayer()
    {
        sounds = new ArrayList<MediaPlayer>();
    }

    public void playMusic(Activity activity)
    {
        this.activity = activity;
        startMusic();
    }

    public void pauseMusic()
    {
        if (music != null)
        {
            music.pause();
        }
    }

    public void resumeMusic()
    {
        if (music != null)
        {
            music.start();
        }
    }

    public void stopMusic()
    {
        music.release();
        music = null;
    }

    private void startMusic()
    {
        startMusic(-1);
    }

    private void startMusic(int previousTrackId)
    {
        final int trackId = selectMusic(previousTrackId);
        if (music != null)
        {
            music.release();
        }
        music = MediaPlayer.create(activity, trackId);
        music.setVolume(MUSIC_VOLUME, MUSIC_VOLUME);
        music.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mediaPlayer.release();
                startMusic(trackId);
            }
        });
        music.start();
    }

    private int selectMusic()
    {
        return selectMusic(-1);
    }

    private int selectMusic(int previousTrackId)
    {
        Random random = new Random();
        int trackId;
        do
        {
            int choice = random.nextInt(TRACKS_COUNT);
            switch (choice)
            {
                case 1:
                    trackId = R.raw.sunnyglade;
                    break;

                default:
                    trackId = R.raw.nyancat;
                    break;
            }
        }
        while (previousTrackId != -1 && TRACKS_COUNT > 1 && previousTrackId == trackId);

        return trackId;
    }

    public void playGameOverSound(Activity activity)
    {
        this.activity = activity;

        if (music != null)
        {
            music.release();
        }
        music = MediaPlayer.create(activity, R.raw.udied);
        music.setVolume(MUSIC_VOLUME, MUSIC_VOLUME);
        music.start();
        music.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mediaPlayer.release();
            }
        });
    }

    public void playEatSound(Activity activity)
    {
        this.activity = activity;

        final MediaPlayer sound = MediaPlayer.create(activity, R.raw.eatsound);
        sounds.add(sound);
        sound.setVolume(SOUND_VOLUME, SOUND_VOLUME);
        sound.start();
        sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                sound.release();
                sounds.remove(sound);
            }
        });
    }
}
