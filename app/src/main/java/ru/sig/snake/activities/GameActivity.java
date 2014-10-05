package ru.sig.snake.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ru.sig.snake.R;
import ru.sig.snake.controller.GameLogic;

import ru.sig.snake.controller.SnakeMusicPlayer;
import ru.sig.snake.view.GameView;


public class GameActivity extends Activity {

    private MenuItem pauseItem;
    private MenuItem resumeItem;

    private GameLogic gameLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GameView gameView = new GameView(this);
        setContentView(gameView);

        gameLogic = GameLogic.getInstance();
        gameLogic.setView(gameView);

        if (gameLogic.getState() == GameLogic.STATE_STOPPED)
        {
            gameLogic.startGame(0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        pauseItem = menu.findItem(R.id.action_pause);
        resumeItem = menu.findItem(R.id.action_resume);

        if (gameLogic.getState() == GameLogic.STATE_PAUSED)
        {
            pauseItem.setVisible(false);
            resumeItem.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_pause:
                pause();
                return true;

            case R.id.action_resume:
                resume();
                return true;

            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        gameLogic.exit();
    }

    @Override
    protected void onPause()
    {
        SnakeMusicPlayer.getInstance().pauseMusic();
        super.onPause();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        if (!hasFocus)
        {
            if (gameLogic.getState() == GameLogic.STATE_STARTED)
            {
                pause();
            }
        }
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onResume()
    {
        SnakeMusicPlayer.getInstance().resumeMusic();
        super.onResume();
    }

    protected void pause()
    {
        gameLogic.pause();
        pauseItem.setVisible(false);
        resumeItem.setVisible(true);
    }

    protected void resume()
    {
        gameLogic.resume();
        resumeItem.setVisible(false);
        pauseItem.setVisible(true);
    }
}
