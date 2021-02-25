package cn.xzxy.lewy.encrypt.asymmetirc.ecc;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class CurveEncrypt {

    private static byte[] generatedCryptoKey(byte[] severPublicKey, byte[] clientPublicKey) {
        byte[] severPrivateKey = new byte[32];
        for (int i = 0; i < 32; i++) {
            severPrivateKey[i] = (byte) (Math.random() * 256);
        }
        Curve.keygen(severPublicKey, null, severPrivateKey);

        byte[] shareKey = new byte[32];
        Curve.curve(shareKey, severPrivateKey, clientPublicKey);
        return shareKey;
    }

    public static void main(String[] args) {
        // mock clientPublicKey
        String clientPublicKey = "ymyytlyzlyjsyhmy0228zmfjlrthxlyf";
        // mock severPublicKey
        String severPublicKey = "1y2m3s4l5h6x7t8f9r0z-1106mfanshu";

        // generate ShareKey
        byte[] shareKey = generatedCryptoKey(severPublicKey.getBytes(), clientPublicKey.getBytes());
        System.out.println(Base64.encode(shareKey));
    }
}
