package lunaticf.chat.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author lcf
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(serialize = false)
    private Byte version = 1;

    /**
     * 获取命令
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
