package cn.xzxy.lewy.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 演示心跳机制的服务端
 */
public class Server {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();

        server.group(group);
        server.channel(NioServerSocketChannel.class);
        // 在这里开启了长连接配置，以及配置了自定义的初始化器
        server.childOption(ChannelOption.SO_KEEPALIVE, true);
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {
                ChannelPipeline pipeline = socketChannel.pipeline();
                // 配置如果5s内未触发读事件，就会触发读闲置事件
                pipeline.addLast("IdleStateHandler", new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));
                pipeline.addLast("Encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast("Decoder", new StringDecoder(CharsetUtil.UTF_8));
                // 装载自定义的服务端心跳处理器
                pipeline.addLast("HeartbeatHandler", new HeartbeatServerHandler());
            }
        });
        server.bind("127.0.0.1", 8888);
    }
}
