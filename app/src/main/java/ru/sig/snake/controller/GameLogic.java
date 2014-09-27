package ru.sig.snake.controller;

import java.util.List;

import ru.sig.snake.model.Snake;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.view.GameView;


/**
 * Created by Alexander Ionov on 27.09.14.
 */
public class GameLogic
{
    private GameView snakeView;
    private Snake snake;
    private int fieldHeight;
    private int fieldWidth;
    private List<FieldNode> food;
    private List<FieldNode> obstacles;
    private SnakeTimer timer;
    private int difficulty;

    public void startGame(int difficulty)
    {

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

    private int move()
    {
        return Integer.parseInt(null);
    }

    private boolean checkState()
    {
        return false;
    }

    public void changeDirection(int x, int y)
    {

    }






}
