package com.excilys.librarymanager.exception;

public class ServletException extends Exception {
    public ServletException(String msg) {
        System.out.println(msg);
        getStackTrace();
    }
}