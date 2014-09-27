package ru.sig.snake.model.node;

import android.graphics.Canvas;
import android.hardware.Camera;

/**
 * Created by ocassio on 27.09.14.
 */
public abstract class FieldNode
{
    private float x;
    private float y;
    private static float widthNode;
    private static float heightNode;

    public FieldNode()
    {
    }

    public float getX()
    {
        return x;
    }

    public FieldNode setX(int x)
    {
        this.x = x;
        return this;
    }

    public float getY()
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

    public float getHeight()
    {
        return heightNode;
    }

    public static void setHeight(float height)
    {
        heightNode = height;
    }

    abstract public void onDraw(Canvas viewCanvas);
}
