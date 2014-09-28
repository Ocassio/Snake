package ru.sig.snake.controller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aleksandr Shindin on 27.09.2014.
 */
public class SnakeTimerTask extends TimerTask
{
    private final GameLogic logic;

    public SnakeTimerTask(GameLogic logic)
    {
        this.logic = logic;

    }

    @Override
    public void run()
    {


        logic.move();
    }
}
