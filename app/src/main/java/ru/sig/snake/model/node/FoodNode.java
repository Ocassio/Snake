package ru.sig.snake.model.node;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Valentin Goncharov on 27.09.2014.
 */
public class FoodNode extends PassableNode {
    public FoodNode(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Canvas viewCanvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        viewCanvas.drawCircle(
                getLeftTopX() + getWidth() / 2,
                getLeftTopY() + getHeight() / 2,
                getWidth() / 2,
                paint);
    }
}