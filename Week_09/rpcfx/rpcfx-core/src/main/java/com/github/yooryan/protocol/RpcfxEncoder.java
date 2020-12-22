package com.github.yooryan.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author linyunrui
 */
public class RpcfxEncoder extends MessageToByteEncoder<RpcfxProtocol> {


    @Override
    protected void encode(ChannelHandlerContext ctx, RpcfxProtocol msg, ByteBuf out) throws Exception {
      out.writeInt(msg.getLength());
      out.writeBytes(msg.getContents());
    }
}
