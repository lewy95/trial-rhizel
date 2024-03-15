package cn.xzxy.lewy.halfPackage;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

// 演示半包问题的服务端
public class HalfPackageServer {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();

        server.group(group);
        server.channel(NioServerSocketChannel.class);
        // 调整服务端的接收窗口大小为四字节
        server.option(ChannelOption.SO_RCVBUF, 4);
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                socketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    // 演示粘包、半包问题的通用初始化器
                    @Override
                    public void channelReadComplete(ChannelHandlerContext ctx)
                        throws Exception {
                        // 在这里直接输出通道内的数据信息
                        System.out.println(ctx.channel());
                        super.channelReadComplete(ctx);
                    }
                });
            }
        });
        server.bind("127.0.0.1", 8888);
    }
}

