package com.wwdy.auth.exception;

/**
 * @author wwdy
 * @date 2022/2/24 17:43
 */
public class CodeErrorException extends RuntimeException{
    public CodeErrorException(String message) {
        super(message);
    }
}
