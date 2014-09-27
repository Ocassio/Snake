package ru.sig.snake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import ru.sig.snake.model.node.FieldNode;

/**
 * Created by Valentin Goncharov on 27.09.2014.
 */
public class GameView extends View
{

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GameView(Context context)
    {
        super(context);
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
