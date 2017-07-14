package org.mini.dubbo.transporter.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ExecutionException;

import org.mini.dubbo.support.Request;

public class NettyClient {
	 public void connectAndExecute(Request request, final NettyClientServiceHandler handler) throws InterruptedException, ExecutionException {
	        EventLoopGroup group = new NioEventLoopGroup();
	        try{
	            Bootstrap bootstrap = new Bootstrap();
	            bootstrap.group(group);
	            bootstrap.channel(NioSocketChannel.class);
	            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
	                protected void initChannel(SocketChannel socketChannel) throws Exception {
	                    ChannelPipeline pipeline = socketChannel.pipeline();
	                    pipeline.addLast(new ObjectEncoder());
	                    pipeline.addLast(new ObjectDecoder(1024*1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
	                    pipeline.addLast(handler);
	                }
	            });
	            bootstrap.option(ChannelOption.TCP_NODELAY, true);
	            ChannelFuture future = bootstrap.connect("127.0.0.1", 3347).sync();
	            Channel channel = future.channel();
	            channel.writeAndFlush(request).sync();
	            channel.closeFuture().sync();
	        }finally {
	            group.shutdownGracefully();
	        }
	    }
}
