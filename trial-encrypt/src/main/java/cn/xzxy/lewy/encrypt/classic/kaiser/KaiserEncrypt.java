package cn.xzxy.lewy.encrypt.classic.kaiser;

/**
 * 凯撒加密（对26个字母进行移位操作，很简单）
 * 可以被频率分析法破解
 */
public class KaiserEncrypt {

    public static void main(String[] args) {
        // 定义原文
        String input = "Kaiser Encrypt";
        // 把原文右边移动3位
        int key = 3;
        // 凯撒加密
        String cipher = kaiserEncrypt(input, key);
        System.out.println("After Encrypt: " + cipher);
        // 凯撒解密
        String plaint = kaiserDecrypt(cipher, key);
        System.out.println("After Decrypt: " + plaint);
    }

    /**
     * 解密
     *
     * @param cipher 密文
     * @param key    密钥
     * @return 密文
     */
    public static String kaiserDecrypt(String cipher, int key) {
        char[] chars = cipher.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            // 偏移数据
            b -= key;
            char newB = (char) b;
            sb.append(newB);
        }
        return sb.toString();
    }


    /**
     * 加密
     *
     * @param input 加密文本
     * @param key   密钥
     * @return 密文
     */
    public static String kaiserEncrypt(String input, int key) {
        // 把字符串变成字节数组
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            // 往右边移动3位
            b = b + key;
            char newB = (char) b;
            sb.append(newB);
        }
        return sb.toString();
    }
}