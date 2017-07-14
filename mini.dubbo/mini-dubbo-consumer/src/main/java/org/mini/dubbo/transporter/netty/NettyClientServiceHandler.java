package org.mini.dubbo.transporter.netty;

import org.mini.dubbo.support.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


	public class NettyClientServiceHandler extends SimpleChannelInboundHandler {
	    private Response response;

	    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object object) throws Exception {
	        response = (Response) object;
	    }

	    public Response getResponse() {
	        return response;
	    }

	    public void setResponse(Response response) {
	        this.response = response;
	    }
}
