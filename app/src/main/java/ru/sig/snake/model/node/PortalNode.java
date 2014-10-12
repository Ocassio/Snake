package ru.sig.snake.model.node;

import android.graphics.Canvas;

/**
 * Created by Alexander Ionov on 12.10.14.
 */
public class PortalNode extends ImpassableNode
{
    public static final int TYPE_UNIVERSAL = 0;
    public static final int TYPE_ENTRY = 1;
    public static final int TYPE_EXIT = 2;

    private final int type;
    private final int direction;

    private PortalNode linkedNode;

    public PortalNode(int x, int y, int direction)
    {
        this(x, y, direction, TYPE_UNIVERSAL);
    }

    public PortalNode(int x, int y, int direction, int type)
    {
        super(x, y);
        this.type = type;
        this.direction = direction;
    }

    public int getType()
    {
        return type;
    }

    public int getDirection()
    {
        return direction;
    }

    public PortalNode getLinkedNode()
    {
        return linkedNode;
    }

    public void setLinkedNode(PortalNode linkedNode)
    {
        this.linkedNode = linkedNode;
    }

    @Override
    public void draw(Canvas viewCanvas)
    {
        //----------Concept-----------
        //****************************
        //*                          *
        //*                          *
        //*            *             *
        //*            *             *
        //*            *             *
        //*    *       *       *     *
        //*      *     *     *       *
        //*        *   *   *         *
        //*          * * *           *
        //*            *             *
        //*                          *
        //*                          *
    }
}
