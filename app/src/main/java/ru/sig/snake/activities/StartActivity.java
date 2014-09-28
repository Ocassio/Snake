package ru.sig.snake.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ru.sig.snake.R;

/**
 * Created by Aleksandr Shindin on 28.09.2014.
 */
public class StartActivity extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view)
    {
        Intent intent = new Intent(StartActivity.this,GameActivity.class);
        startActivity(intent);
    }
}
