package ru.sig.snake.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ru.sig.snake.R;
import ru.sig.snake.controller.GameLogic;
//import ru.sig.snake.controller.SnakeMediaPlayer;

import ru.sig.snake.view.GameView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView gameView = new GameView(this);
        GameLogic gameLogic = new GameLogic(gameView);
        setContentView(gameView);
        gameLogic.startGame(0);
        /*SnakeMediaPlayer snakeMediaPlayer = new SnakeMediaPlayer(this);
        snakeMediaPlayer.execute();*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
