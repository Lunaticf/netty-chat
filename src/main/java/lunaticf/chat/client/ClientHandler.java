package lunaticf.chat.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lunaticf.chat.codec.PacketCodeC;
import lunaticf.chat.protocol.LoginRequestPacket;
import lunaticf.chat.protocol.LoginResponsePacket;
import lunaticf.chat.protocol.Packet;
import lunaticf.chat.serialize.Serializer;

import java.util.UUID;

/**
 * @author lcf
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端开始登录");

        // 连接上，发送登录包
        LoginRequestPacket packet = new LoginRequestPacket();
        packet.setUserId(UUID.randomUUID().toString());
        packet.setPassword("123");
        packet.setUsername("lcf");

        ByteBuf encode = PacketCodeC.INSTANCE.encode(packet);

        ctx.channel().writeAndFlush(encode);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到登录响应");

        ByteBuf buf = (ByteBuf) msg;

        Packet decode = PacketCodeC.INSTANCE.decode(buf);

        if (decode instanceof LoginResponsePacket) {
            LoginResponsePacket packet = (LoginResponsePacket) decode;


            if (packet.isSuccess()) {
                System.out.println("客户端登录成功");
            } else {
                System.out.println("客户端登录失败" + packet.getReason());
            }
        }
    }
}
