package com.bistu.store.service.ex;
/**密码验证失败异常*/
public class PassWordNotMatchException extends ServiceException {
    public PassWordNotMatchException() {
        super();
    }

    public PassWordNotMatchException(String message) {
        super(message);
    }

    public PassWordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassWordNotMatchException(Throwable cause) {
        super(cause);
    }

    protected PassWordNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
