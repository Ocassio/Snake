package ru.sig.snake.model;

import java.util.LinkedList;
import java.util.List;

import ru.sig.snake.model.node.FieldNode;

/**
 * Created by Alexander Ionov on 27.09.14.
 */
public class Snake
{
    public static final int DIRECTION_NORTH = 0;
    public static final int DIRECTION_EAST = 1;
    public static final int DIRECTION_SOUTH = 2;
    public static final int DIRECTION_WEST = 3;


    private final List<FieldNode> body;
    private int direction = DIRECTION_EAST;
    private int satiety = 0;

    public Snake(int x, int y, int length)
    {
        body = new LinkedList<FieldNode>();
        setSatiety(length);
        setDirection(direction);

    }

    public FieldNode getHead()
    {
        return body.get(0);
    }

    public List<FieldNode> getBody()
    {
        return body;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setSatiety(int satiety)
    {
        this.satiety = satiety;
    }

    public void move()
    {

    }

}
