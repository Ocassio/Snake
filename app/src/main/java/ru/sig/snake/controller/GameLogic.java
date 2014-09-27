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

    public static final int FIELD_WIDTH = 40;
    public static final int FIELD_HEIGHT = 40;

    private GameView snakeView;
    private Snake snake;
    private List<FieldNode> food;
    private List<FieldNode> obstacles;
    private int difficulty;

    public void startGame(int difficulty)
    {
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
        return 0;
    }

    private boolean checkState()
    {
        return false;
    }

    public void changeDirection(int x, int y)
    {

    }
}
