package com.example.CJV805_API.Exceptions;

public class InvalidEmailFormatException extends RuntimeException{

    public InvalidEmailFormatException ()
    {
        // calling the constructor of parent Exception
        super("Invalid Email!");
    }
}
