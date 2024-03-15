package cn.xzxy.lewy.basic.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * 测试 Netty-ByteBuf 自动扩容机制
 */
public class ByteBufCapacityTest {

    private static void byteBufCapacityExpansion() {
        // 不指定默认容量大小为16
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16);
        System.out.println("测试前的Buffer容量：" + buffer);
        // 使用StringBuffer来测试ByteBuf的自动扩容特性
        StringBuilder sb = new StringBuilder();
        // 往StringBuffer中插入17个字节的数据
        for (int i = 0; i < 17; i++) {
            sb.append("6");
        }
        // 将17个字节大小的数据写入缓冲区
        buffer.writeBytes(sb.toString().getBytes());
        ByteBufTest.printBuffer(buffer);
    }


    public static void main(String[] args) {
        byteBufCapacityExpansion();
    }

    /* *  运行结果：
     *
     * 测试前的Buffer容量：PooledUnsafeDirectByteBuf(ridx: 0, widx: 0, cap: 16)
     * ByteBuf缓冲区信息：{读取指针=0, 写入指针=17, 容量大小=64}
     *          +-------------------------------------------------+
     *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
     * +--------+-------------------------------------------------+----------------+
     * |00000000| 36 36 36 36 36 36 36 36 36 36 36 36 36 36 36 36 |6666666666666666|
     * |00000010| 36                                              |6               |
     * +--------+-------------------------------------------------+----------------+
     * */

}
