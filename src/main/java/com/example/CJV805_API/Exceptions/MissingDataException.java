package com.example.CJV805_API.Exceptions;

public class MissingDataException extends RuntimeException{
    public MissingDataException (String str)
    {
        // calling the constructor of parent Exception
        super("Missing Data! : " + str);
    }
}
