package ru.sig.snake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import ru.sig.snake.controller.GameLogic;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.model.node.SnakeNode;

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
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        FieldNode node = new SnakeNode().setX(0).setY(0);
        node.onDraw(canvas);

    }

    private void calculateNodeDimensions(int width, int height)
    {
        FieldNode.setWidth(width / GameLogic.FIELD_WIDTH);
        FieldNode.setHeight(height / GameLogic.FIELD_HEIGHT);
    }



}
