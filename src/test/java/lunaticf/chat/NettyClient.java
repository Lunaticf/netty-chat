package lunaticf.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author lcf
 * @date 2020-02-19 02:07
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });

        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();

//        while (true) {
//            channel.writeAndFlush(new Date() + ": hello world!");
//            Thread.sleep(2000);
//        }
    }


    static class ClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(((ByteBuf)msg).toString(Charset.forName("utf-8")));

            byte[] bytes = "你也好".getBytes(Charset.forName("utf-8"));

            ByteBuf buffer = ctx.alloc().buffer();

            buffer.writeBytes(bytes);

            ctx.writeAndFlush(buffer);

        }
    }
}
