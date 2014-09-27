package ru.sig.snake.model.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Valentin Goncharov on 27.09.2014.
 */
public class SnakeNode extends FieldNode
{

    public SnakeNode(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void onDraw(Canvas viewCanvas)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);

        viewCanvas.drawCircle((getX() + getWidth())/2,
                (getX() + getWidth())/2,
                (getY() + getHeight())/2,paint);
    }
}
