package ru.sig.snake.controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import ru.sig.snake.R;
import ru.sig.snake.model.Snake;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.model.node.FoodNode;
import ru.sig.snake.model.node.ObstacleNode;
import ru.sig.snake.model.node.SnakeNode;
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
    
    public static final int FIELD_WIDTH = 40;      //count of nodes in width of display
    public static final int FIELD_HEIGHT = 40;     //count of nodes in height of display

    private int SNAKE_FOUND_NOTHING = 0;            //this node is free
    private int SNAKE_FOUND_FOOD = 1;            //on this node food
    private int SNAKE_FOUND_OBSTACLE = 2;        //on this node obstacle or snakeNode

    private GameView snakeView;
    private List<FieldNode> field;
    private Snake snake;
    private int difficulty;
    public Activity activity;
    private Timer timer;
    private MediaPlayer mediaPlayer;

    public GameLogic(final GameView snakeView)
    {
        this.snakeView = snakeView;
        this.activity = (Activity) snakeView.getContext();
    }

    public void startGame(int difficulty)
    {
        this.field = new ArrayList<FieldNode>();
        this.snakeView.setField(field);

        snake= new Snake(field, 10, 10, 14);

        timer = new Timer();
        timer.schedule(new SnakeTimerTask(this), DEFAULT_DELAY, SNAKE_SPEED) ;

        generateFood();

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

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                MediaPlayer mediaPlayer1 = MediaPlayer.create(activity, R.raw.nyancat);
                mediaPlayer1.start();
            }
        }).start();
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
                FieldNode result = snake.move();
                snakeView.invalidate();
                checkState(result);
            }
        });
    }

    private int checkState(FieldNode moveResult)
    {
        if (moveResult != null)
        {
            if (moveResult instanceof FoodNode)
            {
                resultOfMove = SNAKE_FOUND_FOOD;
                snake.setSatiety(2);
                generateFood();
                SNAKE_SPEED -= 20;
            }
            else if (moveResult instanceof SnakeNode || moveResult instanceof ObstacleNode)
            {
                resultOfMove = SNAKE_FOUND_OBSTACLE;
            }
        }
        else
        {
            resultOfMove = SNAKE_FOUND_NOTHING;
        }
        if (isSnakeReachedBorder())
        {
            resultOfMove = SNAKE_FOUND_OBSTACLE;
        }

        if (resultOfMove == SNAKE_FOUND_OBSTACLE)
        {
            gameover();
        }

        return resultOfMove;
    }

    private void generateFood()
    {
        Random random = new Random();
        if (field.size() <= FIELD_HEIGHT * FIELD_WIDTH)
        {
            FoodNode node;
            do
            {
                int foodx = random.nextInt(FIELD_WIDTH);
                int foody = random.nextInt(FIELD_HEIGHT);
                node = new FoodNode(foodx, foody);
            }
            while (field.contains(node));

            field.add(node);
        }
    }

    private boolean isSnakeReachedBorder()
    {
        FieldNode headSnake = snake.getHead();

        if (headSnake.getX() == FIELD_WIDTH | headSnake.getY() == FIELD_HEIGHT
                | headSnake.getX() == -1 | headSnake.getY() == -1)
            return true;

        return false;
    }

    private void gameover()
    {
        timer.cancel();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setTitle("You lose, motherfucker!");
        builder.setMessage("Do you want restart game?");
        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startGame(0);
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

}
