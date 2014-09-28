package ru.sig.snake.model;

import android.graphics.Canvas;

import java.util.LinkedList;
import java.util.List;

import ru.sig.snake.exceptions.InvalidDirectionException;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.model.node.HeadSnakeNode;
import ru.sig.snake.model.node.SnakeNode;
import ru.sig.snake.view.GameView;

/**
 * Created by Alexander Ionov on 27.09.14.
 */
public class Snake
{
    public static final int DIRECTION_NORTH = 0;
    public static final int DIRECTION_EAST = 1;
    public static final int DIRECTION_SOUTH = 2;
    public static final int DIRECTION_WEST = 3;

    private GameView gameView;
    private final List<FieldNode> body;
    private int direction = DIRECTION_EAST;
    private int satiety = 0;

    public Snake(int x, int y, int length)
    {

        body = new LinkedList<FieldNode>();
        setSatiety(length);
        setDirection(direction);


        body.add(new HeadSnakeNode(x,y));

        for (int i = 1; i < length; i++)
        {
            body.add(new SnakeNode(x - i,y));
        }

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
        int x;
        int y;

        switch (direction)
        {
            case DIRECTION_NORTH:
                x = getHead().getX();
                y = getHead().getY() - 1;
                break;

            case DIRECTION_EAST:
                x = getHead().getX() + 1;
                y = getHead().getY();
                break;

            case DIRECTION_SOUTH:
                x = getHead().getX();
                y = getHead().getY() + 1;
                break;

            case DIRECTION_WEST:
                x = getHead().getX() - 1;
                y = getHead().getY();
                break;

            default:
                throw new InvalidDirectionException();
        }

     //   body.add(0, new SnakeNode(getHead().getX(), getHead().getY()));    //for what?
        body.remove(body.size() - 1);
        body.add(0, new HeadSnakeNode(x ,y));

        if (satiety > 0)
        {
            satiety--;
        }
        else
        {
            body.remove(body.size() - 1);
        }
    }

    public void drawSnake(Canvas canvas)
    {
        body.get(0).onDraw(canvas);
        body.get(body.size() - 1).onDraw(canvas);

    }

}
