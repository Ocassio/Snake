package ru.sig.snake.controller;

import java.util.List;
import java.util.Timer;

import ru.sig.snake.model.Snake;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.view.GameView;


/**
 * Created by Alexander Ionov on 27.09.14.
 */
public class GameLogic
{
    private static final long DEFAULT_DELAY = 1000;
    private static final int START_SNAKE_SIZE = 4;

    private int POSITION_NODE_FREE = 0;
    private int POSITION_NODE_FOOD = 1;
    private int POSITION_NODE_OBSTACLE = 2;

    public static final int FIELD_WIDTH = 80;
    public static final int FIELD_HEIGHT = 80;

    private GameView snakeView;
    private Snake snake;
    private List<FieldNode> food;
    private List<FieldNode> obstacles;
    private int difficulty;


    public void startGame(int difficulty)
    {

        snake = new Snake(0,0,START_SNAKE_SIZE);  //todo: change coordinates to start




        Timer timer = new Timer();
        timer.schedule(new SnakeTimerTask(this), DEFAULT_DELAY);



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






}
