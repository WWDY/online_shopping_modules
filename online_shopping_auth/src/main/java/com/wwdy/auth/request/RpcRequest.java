package com.wwdy.auth.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @author wwdy
 * @date 2022/2/22 14:26
 */
public abstract class RpcRequest<T>{

    /**
     * 返回类型 class
     * @return TypeReference
     */
    @JsonIgnore
    public abstract TypeReference<T> getResponseClass();
}
