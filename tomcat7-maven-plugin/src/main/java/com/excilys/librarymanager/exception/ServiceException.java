package com.excilys.librarymanager.exception;

public class ServiceException extends Exception
{
    public ServiceException(String msg) {
        System.out.println(msg);
        getStackTrace();
    }
}