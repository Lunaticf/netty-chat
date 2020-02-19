package lunaticf.chat.serialize;

/**
 * @author lcf
 */
public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 获取序列化算法类型
     */
    byte getSerializerAlgorithm();


    /**
     * 序列化
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     */
    <T> T deserialize(Class<T> clazz, byte[] data);

}
