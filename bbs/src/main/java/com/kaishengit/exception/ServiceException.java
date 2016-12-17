package com.kaishengit.exception;

/**
 * Created by bayllech on 2016/12/17.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ServiceException() {}
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message,Throwable th) {
        super(message,th);
    }

    public ServiceException(Throwable th) {
        super(th);
    }
}
