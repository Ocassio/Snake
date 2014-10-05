package ru.sig.snake.controller;

import android.app.Activity;
import android.media.MediaPlayer;

import java.util.Random;

import ru.sig.snake.R;

/**
 * Created by Alexander Ionov on 05.10.14.
 */
public class SnakeMusicPlayer
{
    private static SnakeMusicPlayer instance;

    private static final int TRACKS_COUNT = 2;
    private MediaPlayer mediaPlayer;

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

    private SnakeMusicPlayer() {}

    public void playMusic(Activity activity)
    {
        this.activity = activity;
        startMusic();
    }

    public void pauseMusic()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.pause();
        }
    }

    public void resumeMusic()
    {
        if (mediaPlayer != null)
        {
            mediaPlayer.start();
        }
    }

    public void stopMusic()
    {
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void startMusic()
    {
        startMusic(-1);
    }

    private void startMusic(int previousTrackId)
    {
        final int trackId = selectMusic(previousTrackId);
        if (mediaPlayer != null)
        {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(activity, trackId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mediaPlayer.release();
                startMusic(trackId);
            }
        });
        mediaPlayer.start();
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

        if (mediaPlayer != null)
        {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(activity, R.raw.udied);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer)
            {
                mediaPlayer.release();
            }
        });
    }
}
