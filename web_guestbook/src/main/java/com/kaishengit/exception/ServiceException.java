package com.kaishengit.exception;

/**
 * Created by 帅比 on 2016/11/23.
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable th) {
        super(th);
    }

    public ServiceException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
