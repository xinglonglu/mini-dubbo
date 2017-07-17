package org.mini.dubbo.transport;

import org.mini.dubbo.transport.netty.ChannelHandler;
import org.mini.dubbo.transport.netty.NettyServer;

public class Transporters {
    //绑定，监听
    public static Server bind(String address, int port, ChannelHandler handler) throws InterruptedException {
        Server server = new NettyServer(address,port,handler);
        return server;
    }
}
