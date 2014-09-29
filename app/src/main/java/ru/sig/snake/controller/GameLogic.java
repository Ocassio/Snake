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
    private static final long DEFAULT_SPEED = 200;
    private static final long SPEED_CHANGE_STEP = 20;

    private long snakeSpeed;
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

    //TODO: Maybe we should use singleton instead?
    private static final int TRACKS_COUNT = 2;
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

        snakeSpeed = DEFAULT_SPEED;
        timer = new Timer();
        timer.schedule(new SnakeTimerTask(this), DEFAULT_DELAY, snakeSpeed) ;

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

        startMusic();
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
                if (snakeSpeed > SPEED_CHANGE_STEP)
                {
                    snakeSpeed -= SPEED_CHANGE_STEP;
                }
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
        playGameOverSound();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setCancelable(false);
        builder.setTitle(activity.getString(R.string.loseTitle));
        builder.setMessage(activity.getString(R.string.loseMessage));
        builder.setPositiveButton(activity.getString(R.string.yes),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startGame(0);
            }
        });
        builder.setNegativeButton(activity.getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finish();
                dialogInterface.cancel();
            }
        });
        builder.create();
        builder.show();
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

    private void playGameOverSound()
    {
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
