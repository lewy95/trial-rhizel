package cn.xzxy.lewy.encrypt.symmetric.des;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Des 对称加密
 */
public class DesEncrypt {

    public static void main(String[] args) throws Exception {
        String input = "YMYYSYLY"; // 8个字节
        System.out.println("加密前: " + input);
        // 定义密钥key，如果使用des进行加密，那么密钥必须是8个字节
        String key = "19931106";
        // 指定加密类型：
        String algorithm = "DES";
        // 指定加密模式和填充模式：以下举三种说明：
        // 模式一：CBC 无填充模式 DES/CBC/NoPadding 这种情况下原文必须是8个字节的整数倍
        // String transformation = "DES/CBC/NoPadding";
        // 模式二：CBC 有填充模式 DES/CBC/PKCS5Padding
        // String transformation = "DES/CBC/PKCS5Padding";
        // 模式三：ECB 填充模式   DES/ECB/PKCS5Padding 默认地，即指定为 DES 就是采用这种方式，此外 ECB模式不需要指定 IV 向量
        // String transformation = "DES";
        String transformation = "DES/ECB/PKCS5Padding";

        // String cipherText = encryptDES_CBC(input, key, transformation, algorithm);
        String cipherText = encryptDES_ECB(input, key, transformation, algorithm);
        System.out.println("加密后: " + cipherText);

        // String plaintText = decryptDES_CBC(cipherText, key, transformation, algorithm);
        String plaintText = decryptDES_ECB(cipherText, key, transformation, algorithm);
        System.out.println("解密后: " + plaintText);
    }

    /**
     * 解密
     *
     * @param encryptDES     密文
     * @param key            密钥
     * @param transformation 加密算法
     * @param algorithm      加密类型
     * @return 原文
     */
    private static String decryptDES_CBC(String encryptDES, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        // 创建iv向量
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        //Cipher.DECRYPT_MODE:表示解密
        // 解密规则
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
        // 解密，传入密文
        return new String(cipher.doFinal(Base64.decode(encryptDES)));
    }

    /**
     * 解密
     *
     * @param encryptDES     密文
     * @param key            密钥
     * @param transformation 加密算法
     * @param algorithm      加密类型
     * @return 原文
     */
    private static String decryptDES_ECB(String encryptDES, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        // 解密规则
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 解密，传入密文
        return new String(cipher.doFinal(Base64.decode(encryptDES)));
    }

    /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(DES 密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception e
     */
    private static String encryptDES_CBC(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 创建iv向量，iv向量，是使用到CBC加密模式
        // 在使用iv向量进行加密的时候，iv的字节也必l须是8个字节
        IvParameterSpec iv = new IvParameterSpec("19931106".getBytes());
        // 初始化加密模式和算法
        cipher.init(Cipher.ENCRYPT_MODE, sks, iv);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 输出加密后的数据
        // 注意：如果不用base64进行一次转码，可能会出现乱码的情况，因为无法在ascii表找到，比如负数
        return Base64.encode(bytes);
    }

    /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(DES 密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception e
     */
    private static String encryptDES_ECB(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 输出加密后的数据
        // 注意：如果不用base64进行一次转码，可能会出现乱码的情况，因为无法在ascii表找到，比如负数
        return Base64.encode(bytes);
    }
}