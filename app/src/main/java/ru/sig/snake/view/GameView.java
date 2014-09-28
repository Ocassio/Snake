package ru.sig.snake.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import ru.sig.snake.controller.GameLogic;
import ru.sig.snake.model.Snake;
import ru.sig.snake.model.node.FieldNode;

/**
 * Created by Valentin Goncharov on 27.09.2014.
 */
public class GameView extends View
{
    private List<FieldNode> field;

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public GameView(Context context)
    {
        super(context);
    }

    public void setField(List<FieldNode> field)
    {
        this.field = field;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        calculateNodeDimensions(canvas.getWidth(), canvas.getHeight());

//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(Color.WHITE);
//        canvas.drawPaint(paint);

        for (FieldNode node : field)
        {
            node.draw(canvas);
        }
    }

    private void calculateNodeDimensions(int width, int height)
    {
        int min = (width < height
                ? width : height);
            FieldNode.setWidth(min / GameLogic.FIELD_WIDTH);
            FieldNode.setHeight(min / GameLogic.FIELD_HEIGHT);
    }
}
