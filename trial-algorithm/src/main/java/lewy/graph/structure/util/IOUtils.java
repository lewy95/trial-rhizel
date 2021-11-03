package lewy.graph.structure.util;

import java.io.IOException;
import java.util.Scanner;

public class IOUtils {

    public IOUtils() {}

    /*
     * 读取一个输入字符
     */
    public static char readChar() {
        char ch = '0';

        do {
            try {
                ch = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')));

        return ch;
    }

    /*
     * 读取一个输入字符
     */
    public static int readInt() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
