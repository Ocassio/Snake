package ru.sig.snake.controller;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ru.sig.snake.model.Snake;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.view.GameView;


/**
 * Created by Alexander Ionov on 27.09.14.
 */
public class GameLogic
{
    private static final long DEFAULT_DELAY = 0;
    private static final long DEFAULT_TIMER_PERIOD = 300;
    private static final int START_SNAKE_SIZE = 4;

    private int POSITION_NODE_FREE = 0;            //this node is free
    private int POSITION_NODE_FOOD = 1;            //on this node food
    private int POSITION_NODE_OBSTACLE = 2;        //on this node obstacle or snakeNode

    public static final int FIELD_WIDTH = 40;      //count of nodes in width of display
    public static final int FIELD_HEIGHT = 40;     //count of nodes in height of display

    private GameView snakeView;
    private Snake snake;
    private List<FieldNode> food;
    private List<FieldNode> obstacles;
    private int difficulty;
    public Activity activity;

    public void startGame(int difficulty, final GameView snakeView, final Activity activity)
    {
        this.snakeView = snakeView;

        Timer timer = new Timer();
        timer.schedule(new SnakeTimerTask(this),1000,DEFAULT_TIMER_PERIOD) ;

        this.activity= (Activity) snakeView.getContext();


        snakeView.setOnTouchListener(new OnSwipeTouchListener(activity.getApplicationContext())
        {
            public void onSwipeTop() {
                Toast.makeText(activity.getApplicationContext(), "top", Toast.LENGTH_SHORT).show();
                snakeView.getSnake().setDirection(Snake.DIRECTION_NORTH);
            }
            public void onSwipeRight() {
                Toast.makeText(activity.getApplicationContext(), "right", Toast.LENGTH_SHORT).show();
                snakeView.getSnake().setDirection(Snake.DIRECTION_EAST);
            }
            public void onSwipeLeft() {
                Toast.makeText(activity.getApplicationContext(), "left", Toast.LENGTH_SHORT).show();
                snakeView.getSnake().setDirection(Snake.DIRECTION_WEST);
            }
            public void onSwipeBottom() {
                Toast.makeText(activity.getApplicationContext(), "bottom", Toast.LENGTH_SHORT).show();
                snakeView.getSnake().setDirection(Snake.DIRECTION_SOUTH);
            }
        });

    }

    public void pause()
    {

    }

    public void resume()
    {

    }

    public void exit()
    {

    }

    public int move()
    {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                snakeView.getSnake().move();
                snakeView.invalidate();
            }
        });

        return checkState();
    }

    private int checkState()
    {
        return 0;           //compare positions of current snake nodes and food/obstacle nodes and return result (0,1,2)
    }

    public void changeDirection(int direction)
    {
        snake.setDirection(direction);
    }

    public boolean checkTouchDirection(View view, MotionEvent event)
    {
        return true;
    }
}
