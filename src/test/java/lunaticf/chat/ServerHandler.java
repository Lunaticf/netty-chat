package lunaticf.chat;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;


/**
 * @author lcf
 * @date 2020-02-19 11:58
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("收到客户端连接");
        ByteBuf byteBuffer = ctx.alloc().buffer();
        byteBuffer.writeBytes("hello".getBytes( "utf-8"));
        ctx.writeAndFlush(byteBuffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(((ByteBuf)msg).toString(Charset.forName("utf-8")));
    }
}
