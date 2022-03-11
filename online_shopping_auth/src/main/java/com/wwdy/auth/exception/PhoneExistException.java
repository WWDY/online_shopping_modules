package com.wwdy.auth.exception;

/**
 * @author wwdy
 * @date 2022/3/9 18:10
 */
public class PhoneExistException extends RuntimeException{
    public PhoneExistException(String message){
        super(message);
    }
}
