package com.bistu.store.service.ex;

public class AddMoneyException extends ServiceException {
    public AddMoneyException() {
        super();
    }

    public AddMoneyException(String message) {
        super(message);
    }

    public AddMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddMoneyException(Throwable cause) {
        super(cause);
    }

    protected AddMoneyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
