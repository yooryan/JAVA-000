package com.github.yooryan.protocol;

import lombok.Data;

/**
 * 自定义协议
 * @author linyunrui
 */
@Data
public class RpcfxProtocol {

    /**
     * 数据大小
     */
    private int length;

    /**
     * 数据内容
     */
    private byte[] contents;

}
