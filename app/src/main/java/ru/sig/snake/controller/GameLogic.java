package ru.sig.snake.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ru.sig.snake.R;
import ru.sig.snake.model.Snake;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.model.node.FoodNode;
import ru.sig.snake.view.GameView;


/**
 * Created by Alexander Ionov on 27.09.14.
 */
public class GameLogic
{
    private static final long DEFAULT_DELAY = 100;
    private static long SNAKE_SPEED = 200;
    private static final int START_SNAKE_SIZE = 4;

    private int resultOfMove;
    
    public static int FIELD_WIDTH = 40;      //count of nodes in width of display
    public static int FIELD_HEIGHT = 40;     //count of nodes in height of display

    private int SNAKE_FOUND_NOTHING = 0;            //this node is free
    private int SNAKE_FOUND_FOOD = 1;            //on this node food
    private int SNAKE_FOUND_OBSTACLE = 2;        //on this node obstacle or snakeNode

    private GameView snakeView;
    private Snake snake;
    private FieldNode food;
    private List<FieldNode> nodesToDraw;
    private int difficulty;
    public Activity activity;
    private Timer timer;
    private MediaPlayer mediaPlayer;

    public void startGame(int difficulty, final GameView snakeView)
    {
        snake = new Snake(1, 1, 10);
        nodesToDraw = new LinkedList<FieldNode>();
        this.snakeView = snakeView;
        snakeView.setSnake(snake);
    //    nodesToDraw.addAll(snake.getBody());
    //    snakeView.setNodesToDraw(nodesToDraw);
        generateFood();

        timer = new Timer();
        timer.schedule(new SnakeTimerTask(this),DEFAULT_DELAY,SNAKE_SPEED) ;


        this.activity = (Activity) snakeView.getContext();

        MediaPlayer mediaPlayer1 = MediaPlayer.create(activity,R.raw.nyancat);
        mediaPlayer1.start();

        snakeView.setOnTouchListener(new OnSwipeTouchListener(activity.getApplicationContext()) {
            public void onSwipeTop() {
                if (Snake.DIRECTION_SOUTH != snake.getDirection())
                    snake.setDirection(Snake.DIRECTION_NORTH);
            }

            public void onSwipeRight() {
                if (Snake.DIRECTION_WEST != snake.getDirection())
                    snake.setDirection(Snake.DIRECTION_EAST);
            }

            public void onSwipeLeft() {
                if (Snake.DIRECTION_EAST != snake.getDirection())
                    snake.setDirection(Snake.DIRECTION_WEST);
            }

            public void onSwipeBottom() {
                if (Snake.DIRECTION_NORTH != snake.getDirection())
                    snake.setDirection(Snake.DIRECTION_SOUTH);
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

    public void move()
    {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                snake.move();
                snakeView.invalidate();
                checkState();
            }
        });
    }

    private int checkState()
    {

        FieldNode snakeHead = snake.getHead();
        if (snakeHead.getX() == food.getX() & snakeHead.getY() == food.getY())
        {
            resultOfMove = SNAKE_FOUND_FOOD;
            snake.setSatiety(2);
            generateFood();
            SNAKE_SPEED += 20;
        }
        else if (isHeadCrashed())
        {
            resultOfMove = SNAKE_FOUND_OBSTACLE;
            timer.cancel();
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("You lose, motherfucker!");
            builder.setMessage("Do you want restart game?");
            builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startGame(0,snakeView);
                }
            });
            builder.setNegativeButton("Fuck you!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.create();
            builder.show();
        }
        return resultOfMove;
    }

    private void generateFood()
    {
        Random random = new Random();
        int foodx = random.nextInt(FIELD_WIDTH);
        int foody = random.nextInt(FIELD_HEIGHT);
        food = new FoodNode(foodx,foody);
  //      nodesToDraw.add(food);
        snakeView.setFood(food);

    }

    private boolean isHeadCrashed()
    {
        FieldNode headSnake = snake.getHead();

        if (headSnake.getX() == FIELD_WIDTH | headSnake.getY() == FIELD_HEIGHT
                | headSnake.getX() == -1 | headSnake.getY() == -1)
            return true;

        for (int i = 1; i < snake.getBody().size(); i++)
        {
            if (headSnake.getX() ==  snake.getBody().get(i).getX() & headSnake.getY() == snake.getBody().get(i).getY())
                return true;
        }

        return false;
    }

}
