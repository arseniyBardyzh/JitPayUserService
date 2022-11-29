package com.jitpay.userservice.repository.storage.user.exception;

public class UserRepositoryException extends Exception{

    public static final String USER_NOT_FIND_EXCEPTION = "User with identifier %s not found";
    public static final String EMAIL_USER_EXCEPTION = "User with email %s is already exist\nUse another email to create new user";
    public UserRepositoryException() {
    }

    public UserRepositoryException(String message) {
        super(message);
    }

    public UserRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRepositoryException(Throwable cause) {
        super(cause);
    }

    public UserRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
