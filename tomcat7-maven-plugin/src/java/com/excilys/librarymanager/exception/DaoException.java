package com.excilys.librarymanager.exception;

public class DaoException extends Exception {
    public DaoException(String msg) {
        System.out.println(msg);
        getStackTrace();
    }
}