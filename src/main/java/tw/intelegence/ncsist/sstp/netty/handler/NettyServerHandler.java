package tw.intelegence.ncsist.sstp.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import tw.intelegence.ncsist.sstp.netty.controller.NettyMsgController;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    NettyMsgController nettyMsgController = new NettyMsgController();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        System.out.println("channelRegistered : ");
        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

//        if(ConnectController.chkIpExist(sourceIp)){
//        if(ConnectController.chkIpExist_Demo(sourceCtxId, sourceIp)){
//
////            ConnectController.delAllByIp(sourceIp);
//            ConnectController.delAll_Demo(sourceCtxId, sourceIp);
//        }
//            ConnectController.setNettyIpIdCtx(sourceIp, sourceCtxId, ctx);
//        ConnectController.setNettyIpIdCtx_Demo("", sourceIp, sourceCtxId, ctx);

        //for demo
//        nettyMsgController.setNettyIdCtx(ctx, sourceCtxId + "-" + sourceIp);
        nettyMsgController.setFirstNettyIdCtx(ctx, sourceCtxId);


        System.out.println("sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp);

//        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered : ");
        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

        nettyMsgController.delIdNetty(sourceCtxId);

        System.out.println("channelUnregistered : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp);
//        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive : ");

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

        System.out.println("channelActive : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp);
//        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive : ");

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

        System.out.println("channelInactive : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp);
//        super.channelInactive(ctx);

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead : ");

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

        ByteBuf byteBuf = (ByteBuf) msg;
        String message = byteBuf.toString(CharsetUtil.UTF_8);

        nettyMsgController.treatMsg(sourceCtxId, message);

//        NettyMsgController.
//        getClientMsg();

//        int messageCode = Integer.parseInt(message);
//        int messageType = messageCode / 10000;
//        switch (messageType){
//            case 89:
//
//                break;
//            case 90:
////                ConnectController.
//                break;
//            case 91:
////                NettyMsgController.se(sourceCtxId, messageCode);
//                break;
//        }

        System.out.println("channelRead : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp + " ; message : " + message);
//        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete : ");

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

        System.out.println("channelReadComplete : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp);
//        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("userEventTriggered : ");

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

        System.out.println("userEventTriggered : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp);
//        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught : ");

        String sourceIp = ctx.channel().remoteAddress().toString();
        System.out.println("sourceIp : " + sourceIp);
        sourceIp = sourceIp.replace("/", "");
        sourceIp = sourceIp.split(":")[0];
        System.out.println("new sourceIp : " + sourceIp);
        String sourceCtxId = ctx.channel().id().toString();

        System.out.println("exceptionCaught : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp);
        System.out.println("exceptionCaught : " + cause.getMessage());
//        super.exceptionCaught(ctx, cause);
    }
}
