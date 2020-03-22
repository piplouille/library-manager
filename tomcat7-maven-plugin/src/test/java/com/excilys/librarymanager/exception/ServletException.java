package com.excilys.librarymanager.exception;

public class ServletException extends RuntimeException {
    public ServletException(String msg) {
        System.out.println(msg);
        getStackTrace();
    }
}