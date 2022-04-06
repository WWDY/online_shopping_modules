package com.wwdy.front.exception;

/**
 * @author wwdy
 * @date 2022/3/19 17:03
 */
public class NotFoundRecordException extends RuntimeException {
    public NotFoundRecordException(String message) {
        super(message);
    }
}
