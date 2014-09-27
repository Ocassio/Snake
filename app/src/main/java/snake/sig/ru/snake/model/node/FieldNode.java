package snake.sig.ru.snake.model.node;

import android.hardware.Camera;

/**
 * Created by ocassio on 27.09.14.
 */
public abstract class FieldNode
{
    private int x;
    private int y;
    private int width;
    private int height;

    public FieldNode()
    {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    abstract protected void onDraw();
}
