package cn.xzxy.lewy.encrypt.asymmetirc.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * RSA（简单加解密）
 * 非对称加密：私钥加密公钥解密 或者 公钥加密私钥解密
 */
public class RsaEncryptTest {
    public static void main(String[] args) throws Exception {
        String input = "mfs";
        String algorithm = "RSA";
        // 创建密钥对生成器对象 >>> KeyPairGenerator
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate(); // 生成私钥
        PublicKey publicKey = keyPair.getPublic(); // 生成公钥
        // 获取私钥的字节数组
        // byte[] privateKeyEncoded = privateKey.getEncoded();
        // 获取公钥字节数组
        // byte[] publicKeyEncoded = publicKey.getEncoded();
        // 防止乱码，使用base64进行编码
        // String privateEncodeString = Base64.encode(privateKeyEncoded);
        // String publicEncodeString = Base64.encode(publicKeyEncoded);
        // 打印公钥和私钥
        // System.out.println(privateEncodeString);
        // System.out.println(publicEncodeString);

        // 创建加密对象：
        Cipher cipher = Cipher.getInstance(algorithm);
        // 对加密进行初始化：参数一：加密的模式 参数二：使用公钥还是私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey); // 私钥加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 密文
        System.out.println(Base64.encode(bytes));

        // 再使用私钥解密，会报错
        // Exception in thread "main" javax.crypto.BadPaddingException: Decryption error
        // cipher.init(Cipher.DECRYPT_MODE, privateKey);
        // byte[] bytesByPrivate = cipher.doFinal(bytes);
        // System.out.println(new java.lang.String(bytesByPrivate));

        // 使用公钥解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] bytesByPublic = cipher.doFinal(bytes);
        System.out.println(new String(bytesByPublic));

    }
}