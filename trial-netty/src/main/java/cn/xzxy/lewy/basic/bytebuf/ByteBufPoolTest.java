package cn.xzxy.lewy.basic.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class ByteBufPoolTest {

    private static void byteBufferIsPooled(){
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(16);
        System.out.println(buffer.getClass());
    }

    public static void main(String[] args) {
        // 查看创建的缓冲区是否使用了池化技术
        byteBufferIsPooled();
    }

}
