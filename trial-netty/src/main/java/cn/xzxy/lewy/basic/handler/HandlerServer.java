package cn.xzxy.lewy.basic.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class HandlerServer {
    public static void main(String[] args) {
        // 0.准备工作：创建一个事件循环组、一个ServerBootstrap服务端
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();

        server
            // 1.绑定前面创建的事件循环组
            .group(group)
            // 2.声明通道类型为服务端NIO通道
            .channel(NioServerSocketChannel.class)
            // 3.通过ChannelInitializer完成通道的初始化工作
            .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel nsc) {
                    // 4.获取通道的ChannelPipeline处理器链表
                    ChannelPipeline pipeline = nsc.pipeline();
                    // 5.基于pipeline链表向通道上添加入站处理器
                    pipeline.addLast("In-①", new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg)
                            throws Exception {
                            System.out.println("俺是第一个入站处理器...");
                            super.channelRead(ctx, msg);
                        }
                    });
                    pipeline.addLast("In-②", new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg)
                            throws Exception {
                            System.out.println("我是第二个入站处理器...");
                            super.channelRead(ctx, msg);
                        }
                    });
                    pipeline.addLast("In-③", new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) {
                            System.out.println("朕是第三个入站处理器...");
                        }
                    });
                }
            })
            // 为当前启动的服务端绑定IP和端口地址
            .bind("127.0.0.1", 8888);
    }
}
