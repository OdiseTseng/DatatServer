package tw.intelegence.ncsist.sstp.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import tw.intelegence.ncsist.sstp.netty.controller.ConnectController;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

//        if(ConnectController.chkIpExist(sourceIp)){
        if(ConnectController.chkIpExist_Demo(sourceId, sourceIp)){

//            ConnectController.delAllByIp(sourceIp);
            ConnectController.delAll_Demo(sourceId, sourceIp);
        }
//            ConnectController.setNettyIpIdCtx(sourceIp, sourceId, ctx);
        ConnectController.setNettyIpIdCtx_Demo("", sourceIp, sourceId, ctx);


        System.out.println("channelRegistered : sourceId " + sourceId + " ; sourceIp : " + sourceIp);

        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        System.out.println("channelUnregistered : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        System.out.println("channelActive : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        System.out.println("channelInactive : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        System.out.println("channelRead : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        System.out.println("channelReadComplete : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        System.out.println("userEventTriggered : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceId = ctx.channel().id().toString();

        System.out.println("exceptionCaught : sourceId " + sourceId + " ; sourceIp : " + sourceIp);
        System.out.println("exceptionCaught : " + cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}
