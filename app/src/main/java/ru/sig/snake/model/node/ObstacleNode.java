package ru.sig.snake.model.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by Valentin Goncharov on 27.09.2014.
 */
public class ObstacleNode extends ImpassableNode
{
    public ObstacleNode(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void draw(Canvas viewCanvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        RectF rect = new RectF(
                getLeftTopX(),
                getLeftTopY(),
                getLeftTopX() + getWidth(),
                getLeftTopY() + getHeight());

        viewCanvas.drawRoundRect(
            rect,
            6,
            6,
            paint);
    }
}
