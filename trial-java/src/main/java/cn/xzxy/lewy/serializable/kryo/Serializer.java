package cn.xzxy.lewy.serializable.kryo;

/**
 * 序列化接口（程序调用该接口来实现obj<->byte[]之间的序列化/反序列化）
 */
public interface Serializer {

    /**
     * 序列化
     * @param obj 对象
     * @param bytes 字节数组
     */
    public void serialize(Object obj, byte[] bytes);

    /**
     * 序列化
     *
     * @param obj 对象
     * @param bytes 字节数组
     * @param offset 偏移量
     * @param count
     */
    public void serialize(Object obj, byte[] bytes, int offset, int count);

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @return T<T>
     */
    public <T> T deserialize(byte[] bytes);


    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @param offset 偏移量
     * @param count
     * @return T<T>
     */
    public <T> T deserialize(byte[] bytes, int offset, int count);
}
