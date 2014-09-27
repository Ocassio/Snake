package ru.sig.snake.exceptions;

/**
 * Created by Alexander Ionov on 28.09.14.
 */
public class InvalidDirectionException extends RuntimeException
{
    public InvalidDirectionException()
    {
        super();
    }

    public InvalidDirectionException(String message)
    {
        super(message);
    }
}
