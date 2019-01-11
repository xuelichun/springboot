package com.bonc.utils;

import java.io.*;

/**
 * 
 * <p>Title: BONC - SerializableHelper </p>
 * 
 * <p>Description: 对象序列化的辅助类 </p>
 * 
 * <p>Copyright: Copyright BONC(c) 2013 - 2025 </p>
 * 
 * <p>Company: BONC 北京东方国科技股份有限公司 </p>
 * 
 * @author yangdx
 * @version 1.0.0
 */
public class SerializableHelper {

    /**
     * 反序列化
     *
     * @param bytes 反序列化字节数组
     * @return
     */
    public static Object deserialize(byte[] bytes) {

        Object result = null;

        if (isEmpty(bytes)) {
            return null;
        }

        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);

                try {

                    result = objectInputStream.readObject();
                }
                catch (ClassNotFoundException ex) {
                    throw new Exception("Failed to deserialize object type", ex);
                }
            }
            catch (Throwable ex) {
                throw new Exception("Failed to deserialize", ex);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 序列化
     *
     * @param object 需要序列化的对象
     * @return 序列化的字节数组
     */
    public static byte[] serialize(Object object) {

        byte[] result = null;

        if (object == null) {
            return new byte[0];
        }

        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(128);
            try {
                if (!(object instanceof Serializable)) {
                    throw new IllegalArgumentException(String.format("%s requires a Serializable payload  but received an object of type [%s]", com.bonc.utils.SerializableHelper.class.getSimpleName(), object.getClass().getName()));
                }

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
                objectOutputStream.writeObject(object);
                objectOutputStream.flush();

                result = byteStream.toByteArray();
            }
            catch (Throwable ex) {
                throw new Exception("Failed to serialize", ex);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    /**
     * 判断字节素组是否为空
     *
     * @param data 字节数组
     * @return 空为true，否则为flase
     */
    private static boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

}
