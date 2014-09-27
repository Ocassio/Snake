package ru.sig.snake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Valentin Goncharov on 27.09.2014.
 */
public class GameView extends View
{
    private int fieldWidth = 1;
    private int fieldHeight = 1;

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GameView(Context context)
    {
        super(context);
    }

    public void setFieldWidth(int fieldWidth)
    {
        this.fieldWidth = fieldWidth;
    }

    public void setFieldHeight(int fieldHeight)
    {
        this.fieldHeight = fieldHeight;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        calculateNodeDimensions(canvas.getWidth(), canvas.getHeight());
        super.onDraw(canvas);
    }

    private void calculateNodeDimensions(int width, int height)
    {

    }
}
