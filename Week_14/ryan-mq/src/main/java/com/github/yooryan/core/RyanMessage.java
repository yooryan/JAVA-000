package com.github.yooryan.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 消息体
 * @author linyunrui
 */
@Data
@AllArgsConstructor
public class RyanMessage<T> {

    private Map<String,Object> headers;

    private T body;


}
