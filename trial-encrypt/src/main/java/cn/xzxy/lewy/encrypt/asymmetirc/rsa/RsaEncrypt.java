package cn.xzxy.lewy.encrypt.asymmetirc.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 加密（文件存储密钥，更符合一般情形）
 */
public class RsaEncrypt {

    private final static String PUBLIC_KEY_PATH = "trial-encrypt/data/rsa/mfs.pub";
    private final static String PRIVATE_KEY_PATH = "trial-encrypt/data/rsa/mfs.pri";


    public static void main(String[] args) throws Exception {
        String input = "mfs@931106";
        String algorithm = "RSA";
        //生成密钥对并保存在本地文件中
        // generateKeyToFile(algorithm, PUBLIC_KEY_PATH, PRIVATE_KEY_PATH);

        // 读取私钥
        PrivateKey privateKey = getPrivateKey(algorithm);
        // System.out.println(privateKey);
        // 读取公钥key
        PublicKey publicKey = getPublicKey(algorithm);
        // System.out.println(publicKey);

        // 进行加密
        String cipherText = encryptRSA(algorithm, privateKey, input);
        System.out.println("cipherText >>> ");
        System.out.println(cipherText);
        // 进行解密
        String plainText = decryptRSA(algorithm, publicKey, cipherText);
        System.out.println("plainText >>> ");
        System.out.println(plainText);
    }

    /**
     * 读取公钥
     *
     * @param algorithm 算法
     * @return PublicKey
     */
    public static PublicKey getPublicKey(String algorithm) throws Exception {
        String publicKeyString = FileUtils.readFileToString(new File(PUBLIC_KEY_PATH), Charset.defaultCharset());
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建公钥规则
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        // 返回公钥对象
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 读取私钥
     *
     * @param algorithm 算法
     * @return PrivateKey
     */
    public static PrivateKey getPrivateKey(String algorithm) throws Exception {
        String privateKeyString = FileUtils.readFileToString(new File(PRIVATE_KEY_PATH), Charset.defaultCharset());
        // 创建key的工厂
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        // 创建私钥key的规则
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        // 返回私钥对象
        return keyFactory.generatePrivate(keySpec);
    }


    /**
     * 解密数据
     *
     * @param algorithm : 算法
     * @param encrypted : 密文
     * @param publicKey : 密钥
     * @return : plainText
     * @throws Exception e
     */
    private static String decryptRSA(String algorithm, Key publicKey, String encrypted) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 公钥解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        // 使用base64进行转码
        byte[] decode = Base64.decode(encrypted);
        // 使用私钥进行解密
        byte[] bytesNew = cipher.doFinal(decode);
        return new String(bytesNew);
    }


    /**
     * 使用密钥加密数据
     *
     * @param algorithm  : 算法
     * @param input      : 原文
     * @param privateKey : 密钥
     * @return : cipherText
     * @throws Exception e
     */
    private static String encryptRSA(String algorithm, Key privateKey, String input) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对加密进行初始化: 参数一：加密模式 参数二：使用公钥加密or私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // 使用私钥进行加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.encode(bytes);
    }

    /**
     * 保存公钥和私钥，把公钥和私钥保存到根目录
     *
     * @param algorithm 算法
     * @param pubPath   公钥路径
     * @param priPath   私钥路径
     */
    private static void generateKeyToFile(String algorithm, String pubPath, String priPath) throws Exception {
        // 创建密钥对生成器对象 >>> KeyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥的字节数组
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        // 使用base64进行编码
        String privateEncodeString = Base64.encode(privateKeyEncoded);
        String publicEncodeString = Base64.encode(publicKeyEncoded);
        // 把公钥和私钥保存到根目录
        FileUtils.writeStringToFile(new File(pubPath), publicEncodeString, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateEncodeString, Charset.forName("UTF-8"));
    }
}