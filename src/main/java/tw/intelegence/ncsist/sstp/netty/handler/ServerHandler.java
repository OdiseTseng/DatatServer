package tw.intelegence.ncsist.sstp.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.netty.controller.ConnectController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    ConnectController connectController = new ConnectController();


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        if(ConnectController.chkIpExist(sourceIp)){
            ConnectController.delAllByIp(sourceIp);
        }else{
            ConnectController.setNettyIpId(sourceIp, sourceId);
        }


        System.out.println("channelRegistered : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        String sourceId = ctx.channel().id().toString();
        System.out.println("channelUnregistered : sourceIp " + sourceId + " ; " + ctx.channel().remoteAddress().toString());
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String sourceId = ctx.channel().id().toString();
        System.out.println("channelActive : sourceId " + sourceId + " ; " + ctx.channel().remoteAddress().toString());
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String sourceId = ctx.channel().id().toString();
        System.out.println("channelInactive : sourceId " + sourceId + " ; " + ctx.channel().remoteAddress().toString());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String sourceId = ctx.channel().id().toString();
        System.out.println("channelRead : sourceId " + sourceId + " ; " + ctx.channel().remoteAddress().toString());
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        String sourceId = ctx.channel().id().toString();
        System.out.println("channelReadComplete : sourceId " + sourceId + " ; " + ctx.channel().remoteAddress().toString());
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String sourceId = ctx.channel().id().toString();
        System.out.println("userEventTriggered : sourceId " + sourceId + " ; " + ctx.channel().remoteAddress().toString());
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String sourceId = ctx.channel().id().toString();
        System.out.println("exceptionCaught : sourceId " + sourceId + " ; " + ctx.channel().remoteAddress().toString());
        super.exceptionCaught(ctx, cause);
    }
}
