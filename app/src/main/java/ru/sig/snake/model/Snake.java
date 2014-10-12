package ru.sig.snake.model;

import java.util.List;

import ru.sig.snake.controller.GameLogic;
import ru.sig.snake.exceptions.InvalidDirectionException;
import ru.sig.snake.model.node.FieldNode;
import ru.sig.snake.model.node.HeadSnakeNode;
import ru.sig.snake.model.node.SnakeNode;

/**
 * Created by Alexander Ionov on 27.09.14.
 */
public class Snake
{
    private final List<FieldNode> field;

    private HeadSnakeNode head;
    private SnakeNode tail;

    private int direction = Direction.EAST;
    private int satiety = 0;

    public Snake(List<FieldNode> field, int x, int y, int length)
    {
        this.field = field;

        head = new HeadSnakeNode(x, y);
        field.add(new HeadSnakeNode(x,y));

        SnakeNode nextPointer = head;
        for (int i = 1; i < length; i++)
        {
            SnakeNode node = new SnakeNode(x - i, y);

            nextPointer.previous = node;
            node.next = nextPointer;

            nextPointer = node;
            field.add(node);
        }

        tail = nextPointer;
    }

    public HeadSnakeNode getHead()
    {
        return head;
    }

    public FieldNode getTail()
    {
        return tail;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setSatiety(int satiety)
    {
        this.satiety = satiety;
    }

    public FieldNode move()
    {
        FieldNode result = null;
        int x;
        int y;

        switch (direction)
        {
            case Direction.NORTH:
                x = getHead().getX();
                y = getHead().getY() - 1;
                break;

            case Direction.EAST:
                x = getHead().getX() + 1;
                y = getHead().getY();
                break;

            case Direction.SOUTH:
                x = getHead().getX();
                y = getHead().getY() + 1;
                break;

            case Direction.WEST:
                x = getHead().getX() - 1;
                y = getHead().getY();
                break;

            default:
                throw new InvalidDirectionException();
        }

        if (x == GameLogic.FIELD_WIDTH)
        {
            x -= GameLogic.FIELD_WIDTH;
        }
        else if (x == -1)
        {
            x += GameLogic.FIELD_WIDTH;
        }
        if (y == GameLogic.FIELD_HEIGHT)
        {
            y -= GameLogic.FIELD_HEIGHT;
        }
        else if (y == -1)
        {
            y += GameLogic.FIELD_HEIGHT;
        }

        HeadSnakeNode newHead = new HeadSnakeNode(x, y);
        SnakeNode oldHead = new SnakeNode(getHead().getX(), getHead().getY());

        head.previous.next = oldHead;
        field.remove(head);
        field.add(oldHead);

        oldHead.next = newHead;
        newHead.previous = oldHead;
        if (field.contains(newHead))
        {
            result = field.get(field.indexOf(newHead));
            field.remove(result);
        }
        field.add(newHead);
        head = newHead;

        if (satiety > 0)
        {
            satiety--;
        }
        else
        {
            tail.next.previous = null;
            field.remove(tail);
            tail = tail.next;
        }

        return result;
    }
}
