package cn.xzxy.lewy.heartbeat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 演示心跳机制的客户端（会发送心跳包）
 */
public class ClientA {
    public static void main(String[] args) {
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap client = new Bootstrap();
        try {
            client.group(worker);
            client.channel(NioSocketChannel.class);
            // 打开长连接配置
            client.option(ChannelOption.SO_KEEPALIVE, true);
            // 指定一个自定义的初始化器
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    // 配置如果3s内未触发写事件，就会触发写闲置事件
                    pipeline.addLast("IdleStateHandler", new IdleStateHandler(0, 3, 0, TimeUnit.SECONDS));
                    pipeline.addLast("Encoder", new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast("Decoder", new StringDecoder(CharsetUtil.UTF_8));
                    // 装载自定义的客户端心跳处理器
                    pipeline.addLast("HeartbeatHandler", new HeartbeatClientHandler());
                }
            });
            client.connect("127.0.0.1", 8888).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
