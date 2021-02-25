package cn.xzxy.lewy.encrypt.classic;

import cn.xzxy.lewy.encrypt.classic.kaiser.KaiserEncrypt;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 频率分析法破解凯撒加密
 * 不知道密钥的情况下（即不知道移动多少位）破解凯撒加密
 */
public class FrequencyAnalysis {
    //英文里出现次数最多的字符
    private static final char MAGIC_CHAR = 'e';
    //破解生成的最大文件数
    private static final int DE_MAX_FILE = 4;

    public static void main(String[] args) throws Exception {
        // 对文件进行凯撒加密
        int key = 3; // 右移3位
        // encryptFile("trial-encrypt/data/kaiser/article.txt", "trial-encrypt/data/kaiser/article_en.txt", key);

        // 测试1，统计原文字符个数
        // printCharCount("trial-encrypt/data/kaiser/article.txt");
        // 测试2，统计密文字符个数
        // printCharCount("trial-encrypt/data/kaiser/article_en.txt");

        // 读取加密后的文件
        String cipher = file2String("trial-encrypt/data/kaiser/article_en.txt");
        // 解密（会生成多个备选文件）
        decryptCaesarCode(cipher, "trial-encrypt/data/kaiser/article_de.txt");
    }

    private static void printCharCount(String path) throws IOException {
        String data = file2String(path);
        List<Entry<Character, Integer>> mapList = getMaxCountChar(data);
        for (Entry<Character, Integer> entry : mapList) {
            //输出前几位的统计信息
            System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");
        }
    }

    /**
     * 破解凯撒密码
     *
     * @param input 原文
     */
    private static void decryptCaesarCode(String input, String destPath) {
        int deCount = 0;//当前解密生成的备选文件数
        //获取出现频率最高的字符信息（出现次数越多越靠前）
        List<Entry<Character, Integer>> mapList = getMaxCountChar(input);
        for (Entry<Character, Integer> entry : mapList) {
            //限制解密文件备选数
            if (deCount >= DE_MAX_FILE) {
                break;
            }

            //输出前几位的统计信息
            System.out.println("字符'" + entry.getKey() + "'出现" + entry.getValue() + "次");

            ++deCount;
            //出现次数最高的字符跟MAGIC_CHAR的偏移量即为秘钥
            int key = entry.getKey() - MAGIC_CHAR;
            System.out.println("猜测key = " + key + "(参考ascii表，减去key)，解密生成第" + deCount + "个备选文件" + "\n");
            String decrypt = KaiserEncrypt.kaiserDecrypt(input, key);

            String fileName = destPath.substring(0, destPath.length() - 4) + "_" + deCount + ".txt";
            string2File(decrypt, fileName);
        }
    }

    public static void encryptFile(String srcFile, String destFile, int key) throws IOException {
        String artile = file2String(srcFile);
        //加密文件
        String encryptData = KaiserEncrypt.kaiserEncrypt(artile, key);
        //保存加密后的文件
        string2File(encryptData, destFile);
    }

    // 统计String里出现最多的字符
    private static List<Entry<Character, Integer>> getMaxCountChar(String data) {
        Map<Character, Integer> map = new HashMap<>();
        char[] array = data.toCharArray();
        for (char c : array) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                Integer count = map.get(c);
                map.put(c, count + 1);
            }
        }

        //输出统计信息
		/*for (Entry<Character, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "出现" + entry.getValue() +  "次");
		}*/

        //获取获取最大值
        int maxCount = 0;
        for (Entry<Character, Integer> entry : map.entrySet()) {
            //不统计空格
            if (/*entry.getKey() != ' ' && */entry.getValue() > maxCount) {
                maxCount = entry.getValue();
            }
        }

        //map转换成list便于排序
        List<Entry<Character, Integer>> mapList = new ArrayList<>(map.entrySet());
        //根据字符出现次数排序
        Collections.sort(mapList, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return mapList;
    }

    // -------------以下是工具方法-------------
    private static void print(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte aByte : bytes) {
            sb.append(aByte).append(" ");
        }
        System.out.println(sb);
    }

    private static String file2String(String path) throws IOException {
        FileReader reader = new FileReader(new File(path));
        char[] buffer = new char[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = reader.read(buffer)) != -1) {
            sb.append(buffer, 0, len);
        }
        return sb.toString();
    }

    private static void string2File(String data, String path) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(new File(path));
            writer.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
