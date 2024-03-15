package cn.xzxy.lewy.basic.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.util.internal.StringUtil;

public class ByteBufTest {

    // 测试 ByteBuf 的 read、get、mark 功能
    private static void bufferReader(){
        // 分配一个初始容量为10的缓冲区
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);

        // 向缓冲区中写入10个字符（占位十个字节）
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i);
        }
        buffer.writeBytes(sb.toString().getBytes());

        // 使用read方法读取前5个字节数据
        printBuffer(buffer);
        buffer.readBytes(5);
        printBuffer(buffer);

        // 再使用get方法读取后五个字节数据
        buffer.getByte(5);
        printBuffer(buffer);

        // 使用mark标记一下读取指针，然后再使用read方法读取数据
        buffer.markReaderIndex();
        buffer.readBytes(5);
        printBuffer(buffer);

        // 此时再通过reset方法，使读取指针恢复到前面的标记位置
        buffer.resetReaderIndex();
        printBuffer(buffer);

    }

    // 打印ByteBuf中数据的方法
    public static void printBuffer(ByteBuf buffer) {
        // 读取ByteBuffer已使用的字节数
        int byteSize = buffer.readableBytes();
        // 基于byteSize来计算显示的行数
        int rows = byteSize / 16 + (byteSize % 15 == 0 ? 0 : 1) + 4;
        // 创建一个StringBuilder用来显示输出
        StringBuilder sb = new StringBuilder(rows * 80 * 2);
        // 获取缓冲区的容量、读/写指针信息放入StringBuilder
        sb.append("ByteBuf缓冲区信息：{");
        sb.append("读取指针=").append(buffer.readerIndex()).append(", ");
        sb.append("写入指针=").append(buffer.writerIndex()).append(", ");
        sb.append("容量大小=").append(buffer.capacity()).append("}");

        // 利用Netty框架自带的格式化方法、Dump方法输出缓冲区数据
        sb.append(StringUtil.NEWLINE);
        ByteBufUtil.appendPrettyHexDump(sb, buffer);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        /*
         * ByteBuf缓冲区信息：{读取指针=0, 写入指针=10, 容量大小=10}
         *          +-------------------------------------------------+
         *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
         * +--------+-------------------------------------------------+----------------+
         * |00000000| 30 31 32 33 34 35 36 37 38 39                   |0123456789      |
         * +--------+-------------------------------------------------+----------------+
         * ByteBuf缓冲区信息：{读取指针=5, 写入指针=10, 容量大小=10}
         *          +-------------------------------------------------+
         *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
         * +--------+-------------------------------------------------+----------------+
         * |00000000| 35 36 37 38 39                                  |56789           |
         * +--------+-------------------------------------------------+----------------+
         *
         * ByteBuf缓冲区信息：{读取指针=5, 写入指针=10, 容量大小=10}
         *          +-------------------------------------------------+
         *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
         * +--------+-------------------------------------------------+----------------+
         * |00000000| 35 36 37 38 39                                  |56789           |
         * +--------+-------------------------------------------------+----------------+
         */

        /*
         * ByteBuf缓冲区信息：{读取指针=10, 写入指针=10, 容量大小=10}
         *
         * ByteBuf缓冲区信息：{读取指针=5, 写入指针=10, 容量大小=10}
         *          +-------------------------------------------------+
         *          |  0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f |
         * +--------+-------------------------------------------------+----------------+
         * |00000000| 35 36 37 38 39                                  |56789           |
         * +--------+-------------------------------------------------+----------------+
         */
        bufferReader();


    }
}
