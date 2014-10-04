package ru.sig.snake.exceptions;

/**
 * Created by Alexander Ionov on 04.10.14.
 */
public class NoViewIsSetException extends RuntimeException
{
    public NoViewIsSetException()
    {
        super();
    }

    public NoViewIsSetException(String message)
    {
        super(message);
    }
}
