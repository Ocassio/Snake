package ru.sig.snake.model.node;

import android.graphics.Canvas;
import android.hardware.Camera;

/**
 * Created by ocassio on 27.09.14.
 */
public abstract class FieldNode
{
    private int x;
    private int y;

    private static int width;
    private static int height;

    public FieldNode()
    {
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public static int getWidth()
    {
        return width;
    }

    public static void setWidth(int w)
    {
        width = w;
    }

    public static int getHeight()
    {
        return height;
    }

    public static void setHeight(int h)
    {
        height = h;
    }

    abstract protected void onDraw(Canvas viewCanvas);
}
