package com.bistu.store.service.ex;

/**用户名未找到异常*/
public class UserRecordNotFoundException extends ServiceException {
    public UserRecordNotFoundException() {
        super();
    }

    public UserRecordNotFoundException(String message) {
        super(message);
    }

    public UserRecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRecordNotFoundException(Throwable cause) {
        super(cause);
    }

    protected UserRecordNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

