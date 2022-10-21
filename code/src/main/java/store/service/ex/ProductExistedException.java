package com.bistu.store.service.ex;

public class ProductExistedException extends ServiceException {
    public ProductExistedException() {
        super();
    }

    public ProductExistedException(String message) {
        super(message);
    }

    public ProductExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductExistedException(Throwable cause) {
        super(cause);
    }

    protected ProductExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
