package com.kaishengit.exception;

/**
 * 数据库访问异常
 * Created by 帅比 on 2016/11/30.
 */
public class DataAccessException extends RuntimeException {

    private static final long serialVersionUID = 5227803783619562547L;

    public DataAccessException(){}
    public DataAccessException(String message) {
        super(message);
    }
    public DataAccessException(String message,Throwable th) {
        super(message, th);
    }
    public  DataAccessException(Throwable th) {
        super(th);
    }
}
