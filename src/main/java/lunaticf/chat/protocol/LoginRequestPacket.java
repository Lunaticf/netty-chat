package lunaticf.chat.protocol;

import lombok.Data;
import lunaticf.chat.protocol.Command;
import lunaticf.chat.protocol.Packet;

/**
 * @author lcf
 */
@Data
public class LoginRequestPacket extends Packet {

    private String userId;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
