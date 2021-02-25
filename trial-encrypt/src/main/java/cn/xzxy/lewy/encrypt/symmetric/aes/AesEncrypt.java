package cn.xzxy.lewy.encrypt.symmetric.aes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 对称加密
 */
public class AesEncrypt {
    public static void main(String[] args) throws Exception {
        String input = "今天把密码学看完";
        System.out.println("加密前: " + input);
        // 定义密钥
        // 区别于 DES 必须是8个字节的密钥，AES密钥必须是16个字节
        String key = "1993110619931106";
        // 定义加密模式和填充模式
        String transformation = "AES";
        // 定义加密类型
        String algorithm = "AES";
        // 指定获取密钥的算法
        String cipherText = encryptDES(input, key, transformation, algorithm);
        System.out.println("加密后: " + cipherText);

        String plaintText = decryptDES(cipherText, key, transformation, algorithm);
        System.out.println("解密后: " + plaintText);
    }

    /**
     * 解密
     *
     * @param encryptDES     密文
     * @param key            密钥
     * @param transformation 加密算法
     * @param algorithm      加密类型
     * @return
     */
    private static String decryptDES(String encryptDES, String key, String transformation, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(transformation);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        //Cipher.DECRYPT_MODE:表示解密
        // 解密规则
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 解密，传入密文
        byte[] bytes = cipher.doFinal(Base64.decode(encryptDES));

        return new String(bytes);
    }

    /**
     * 使用DES加密数据
     *
     * @param input          : 原文
     * @param key            : 密钥(DES,密钥的长度必须是8个字节)
     * @param transformation : 获取Cipher对象的算法
     * @param algorithm      : 获取密钥的算法
     * @return : 密文
     * @throws Exception e
     */
    private static String encryptDES(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 创建加密规则
        // 第一个参数key的字节
        // 第二个参数表示加密算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 初始化加密模式和算法
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 输出加密后的数据
        return Base64.encode(bytes);
    }
}