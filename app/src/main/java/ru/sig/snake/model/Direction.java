package ru.sig.snake.model;

import ru.sig.snake.exceptions.InvalidDirectionException;

/**
 * Created by Alexander Ionov on 12.10.14.
 */
public final class Direction
{
    public static final int SAME_PLACE = -1;
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;

    private Direction() {}

    public static int getInvertedDirection(int direction)
    {
        switch (direction)
        {
            case (NORTH):
                return SOUTH;

            case (SOUTH):
                return NORTH;

            case (WEST):
                return EAST;

            case (EAST):
                return WEST;

            case SAME_PLACE:
                return SAME_PLACE;

            default:
                throw new InvalidDirectionException();
        }
    }
}
