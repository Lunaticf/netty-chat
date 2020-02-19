package lunaticf.chat.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lunaticf.chat.codec.PacketCodeC;
import lunaticf.chat.protocol.LoginRequestPacket;
import lunaticf.chat.protocol.LoginResponsePacket;
import lunaticf.chat.protocol.Packet;

/**
 * @author lcf
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(buf);

        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket requestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());

            // 验证用户名密码
            if (valid(requestPacket)) {
                loginResponsePacket.setSuccess(true);
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
            }

            // 编码发送出去
            ByteBuf res = PacketCodeC.INSTANCE.encode(loginResponsePacket);
            ctx.channel().writeAndFlush(res);
            System.out.println("服务端返回登录响应");
        }
    }

    private boolean valid(LoginRequestPacket requestPacket) {
        return true;
    }
}
