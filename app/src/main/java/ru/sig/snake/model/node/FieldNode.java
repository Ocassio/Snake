package ru.sig.snake.model.node;

import android.graphics.Canvas;

import ru.sig.snake.model.Direction;

/**
 * Created by ocassio on 27.09.14.
 */
public abstract class FieldNode
{
    private int x;
    private int y;
    private static float widthNode;
    private static float heightNode;

    public FieldNode(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public FieldNode setX(int x)
    {
        this.x = x;
        return this;
    }

    public int getY()
    {
        return y;
    }

    public FieldNode setY(int y)
    {
        this.y = y;
        return this;
    }

    public static float getWidth()
    {
        return widthNode;
    }

    public static void setWidth(float width)
    {
        widthNode = width;
    }

    public static float getHeight()
    {
        return heightNode;
    }

    public static void setHeight(float height)
    {
        heightNode = height;
    }

    protected float getLeftTopX()
    {
        return x * widthNode;
    }

    protected float getLeftTopY()
    {
        return y * heightNode;
    }

    public int getDirectionFrom(FieldNode node)
    {
        if (node.getX() < getX()) return Direction.EAST;
        if (node.getX() > getX()) return Direction.WEST;
        if (node.getY() < getY()) return Direction.SOUTH;
        if (node.getY() > getY()) return Direction.NORTH;
        return Direction.SAME_PLACE;
    }

    public int getXByDirection(int direction)
    {
        switch (direction)
        {
            case Direction.EAST:
                return getX() + 1;

            case Direction.WEST:
                return getX() - 1;

            default:
                return getX();
        }
    }

    public int getYByDirection(int direction)
    {
        switch (direction)
        {
            case Direction.SOUTH:
                return getY() + 1;

            case Direction.NORTH:
                return getY() - 1;

            default:
                return getY();
        }
    }

    abstract public void draw(Canvas viewCanvas);

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof FieldNode)
        {
            FieldNode node = (FieldNode) o;
            if (node.getX() == this.getX() && node.getY() == this.getY())
            {
                return true;
            }
        }
        return false;
    }
}
