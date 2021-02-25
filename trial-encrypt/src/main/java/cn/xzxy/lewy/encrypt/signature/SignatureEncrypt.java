package cn.xzxy.lewy.encrypt.signature;

import cn.xzxy.lewy.encrypt.asymmetirc.rsa.RsaEncrypt;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Signature 数字签名
 */
public class SignatureEncrypt {

    private final static String USED_ALGORITHM = "sha256withrsa";

    public static void main(String[] args) throws Exception {

        String input = "mfs@931106";

        PublicKey publicKey = RsaEncrypt.getPublicKey("RSA");
        PrivateKey privateKey = RsaEncrypt.getPrivateKey("RSA");
        // 获取数字签名
        String signatureData = getSignature(input, privateKey);
        System.out.println(signatureData);
        // 校验签名
        boolean bool = verifySignature(input, publicKey, signatureData);
        System.out.println(bool);

    }

    /**
     * 校验签名
     *
     * @param input         表示原文
     * @param publicKey     公钥key
     * @param signatureData 签名密文
     * @return boolean
     */
    private static boolean verifySignature(String input, PublicKey publicKey, String signatureData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(USED_ALGORITHM);
        // 初始化校验
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验数据
        return signature.verify(Base64.decode(signatureData));
    }

    /**
     * 生成数字签名
     *
     * @param input      表示原文
     * @param privateKey 私钥key
     * @return cipher
     */
    private static String getSignature(String input, PrivateKey privateKey) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(USED_ALGORITHM);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 开始签名
        byte[] sign = signature.sign();
        // 使用base64进行编码
        return Base64.encode(sign);
    }
}