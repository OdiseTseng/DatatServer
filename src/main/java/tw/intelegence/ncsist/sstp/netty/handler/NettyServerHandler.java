package tw.intelegence.ncsist.sstp.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.netty.controller.NettyMsgController;
import tw.intelegence.ncsist.sstp.utils.func.DTOParser;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    //
    private static int countsForTest = 1;
    private static final ChannelGroup channelOnlineGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);//查看上線
    private static final ChannelGroup channelGroup1 = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ChannelGroup channelGroup2 = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static HashMap<String, ChannelHandlerContext> clientContextMap = new HashMap<>();//先改成public看看
    private static HashMap<ChannelHandlerContext, ChannelGroup> clientChannelMap = new HashMap<>();//更改channel的時候可以操作
    //
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
        String clientRemoteAddress = ctx.channel().remoteAddress().toString();
        clientContextMap.put(clientRemoteAddress, ctx);//ip及ctx加入map
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

        nettyMsgController.delIdNetty(sourceCtxId);
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


        //更改for demo多機

        String channelInfo = getChannelGroupInfo(ctx, channelGroup1, channelGroup2);
        String clientRealMessage = byteBuf.toString(CharsetUtil.UTF_8);
        String clientmessage = channelInfo+"client"+ctx.channel().remoteAddress()+"的訊息:"+byteBuf.toString(CharsetUtil.UTF_8);
        standAloneController(ctx,byteBuf,clientRealMessage,clientmessage);
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
        nettyMsgController.treatMsg(sourceCtxId, message);
        System.out.println("channelRead : sourceCtxId " + sourceCtxId + " ; sourceIp : " + sourceIp + " ; message : " + message);
//
//        super.channelRead(ctx, msg);
        MsgDTO msgDTO = new MsgDTO();
        msgDTO.setCmd(NettyCode.CMD_KEEP_ALIVE);
        String msgString = DTOParser.parseDTOToString(msgDTO);
        ctx.writeAndFlush(msgString);

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

        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;
            if(idleStateEvent.state() == IdleState.READER_IDLE){
                nettyMsgController.delIdNetty(sourceCtxId);
                ctx.channel().close();
            }else if(IdleState.READER_IDLE.equals(idleStateEvent.state())){
                MsgDTO msgDTO = new MsgDTO();
                msgDTO.setCmd(NettyCode.CMD_KEEP_ALIVE);
                String msgString = DTOParser.parseDTOToString(msgDTO);
                ctx.writeAndFlush(msgString).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }else{
                super.userEventTriggered(ctx, evt);
            }
        }
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

        nettyMsgController.delIdNetty(sourceCtxId);
//        super.exceptionCaught(ctx, cause);
    }
    private void standAloneController(ChannelHandlerContext ctx, ByteBuf byteBuf, String clientRealMessage, String clientmessage) {

        if (Objects.equals(byteBuf.toString(CharsetUtil.UTF_8), "get")) {
            if (countsForTest == 1) {
                //預設為1
                System.out.println("1同學執行操作中，給1題目");
                System.out.println("countsForTest數量為:" + countsForTest);
                countsForTest += 1;
                clientRealMessage = "channel1";
            } else if (countsForTest == 2) {
                System.out.println("1同學執行操作中，封鎖2同學行動");
                System.out.println("countsForTest數量為:" + countsForTest);
                clientRealMessage = "channel2";
                countsForTest += 1;
            }
        } else if (Objects.equals(byteBuf.toString(CharsetUtil.UTF_8), "finish")) {
            if (countsForTest == 3) {
                System.out.println(countsForTest + ":countsForTest,,,1同學做完了，給1題目，讓2進去考試");
                countsForTest += 1;
                System.out.println("發送score1後,countsForTest數量為:" + countsForTest);
                for (ChannelHandlerContext clientContext : clientContextMap.values()) {
                    String clientToAllMessage = "score1";
                    ByteBuf responseByteBuf = clientContext.alloc().buffer();
                    responseByteBuf.writeBytes(clientToAllMessage.getBytes(CharsetUtil.UTF_8));
                    System.out.println("進入read map裡面的迴圈,準備開始傳送score內容到各ip(如果你是本人我就不給你了)聊天室內容");
                    clientContext.writeAndFlush(responseByteBuf);
                }
            } else if (countsForTest == 4) {
                System.out.println(countsForTest + ":countsForTest,,,1同學執行操作中，封鎖2同學行動");
                System.out.println("發送score2後,countsForTest數量為:" + countsForTest);
                for (ChannelHandlerContext clientContext : clientContextMap.values()) {
                    String clientToAllMessage = "score2";
                    ByteBuf responseByteBuf = clientContext.alloc().buffer();
                    responseByteBuf.writeBytes(clientToAllMessage.getBytes(CharsetUtil.UTF_8));
                    System.out.println("進入read map裡面的迴圈,準備開始傳送score內容到各ip(如果你是本人我就不給你了)聊天室內容");
                    clientContext.writeAndFlush(responseByteBuf);
                }
            }
        }
        ByteBuf responseByteBuf = ctx.alloc().buffer();
        String clientAddMessage = "";
        switch (clientRealMessage) {
//            case "getonlineusers" -> sendOnlineUsers();
            case "channel1":
                //有人想到chaneel1來 我先查看他有沒有註冊過map
                if (clientChannelMap.containsKey(ctx)) {
                    //看一下這個人ctx有沒有在map註冊過
                    ChannelGroup previousChannelGroup = clientChannelMap.get(ctx);
                    //用以確認此認註冊過的話，那是註冊哪裡?是1嗎?不是1的話我就幫你刪除之前的group
                    if (previousChannelGroup != channelGroup1) {
                        System.out.println("你原本是在" + previousChannelGroup + "不是1的話我就幫你刪除之前的group");
                        previousChannelGroup.remove(ctx.channel());
                        clientChannelMap.remove(ctx);
                    }
                }
                channelGroup1.add(ctx.channel());
                System.out.println("Client " + ctx.channel().remoteAddress() + " connected to Channel 1.");
                clientAddMessage = "CHANNEL1";
                clientChannelMap.put(ctx, channelGroup1);
                responseByteBuf = ctx.alloc().buffer();
                //傳送CHANNEL1給
                responseByteBuf.writeBytes(clientAddMessage.getBytes(CharsetUtil.UTF_8));
                ctx.writeAndFlush(responseByteBuf);
                break;
            case "channel2" :
                //有人想到channel2來
                if (clientChannelMap.containsKey(ctx)) {
                    //看一下這個人ctx有沒有在map註冊過
                    ChannelGroup previousChannelGroup = clientChannelMap.get(ctx);
                    if (previousChannelGroup != channelGroup2) {
                        //用以確認此認註冊過的話，那是註冊哪裡?是2嗎?
                        System.out.println("你原本是在" + previousChannelGroup + "不是2的話我就幫你刪除之前的group");
                        previousChannelGroup.remove(ctx.channel());
                        clientChannelMap.remove(ctx);
                    }
                }
                channelGroup2.add(ctx.channel());
                System.out.println("Client " + ctx.channel().remoteAddress() + " connected to Channel 2.");
                clientAddMessage = "CHANNEL2";
                clientChannelMap.put(ctx, channelGroup2);
                responseByteBuf = ctx.alloc().buffer();
                responseByteBuf.writeBytes(clientAddMessage.getBytes(CharsetUtil.UTF_8));
                ctx.writeAndFlush(responseByteBuf);
                break;

            default :
                //預設除了上述的話就是要傳訊息出來
                //如果你是群組1就去聽群組1的東西，如果妳都不是群組的人，那就在廣播裡面
                if (channelGroup1.contains(ctx.channel())) {
                    System.out.println("判斷是1");
                    processClientMessage(ctx, clientRealMessage, channelGroup1, "Channel 1");
                } else if (channelGroup2.contains(ctx.channel())) {
                    System.out.println("判斷是2");
                    processClient2Message(ctx, clientRealMessage, channelGroup2, "Channel 2");
                } else {
                    //將read收到的訊息傳送給大家(類似全頻聊天室)
                    for (ChannelHandlerContext clientContext : clientContextMap.values()) {
                        if (clientContext != ctx) {
                            String clientToAllMessage = clientmessage + " ";
                            responseByteBuf = clientContext.alloc().buffer();
                            responseByteBuf.writeBytes(clientToAllMessage.getBytes(CharsetUtil.UTF_8));
                            System.out.println("進入read map裡面的迴圈,準備開始傳送內容到各ip(如果你是本人我就不給你了)聊天室內容");
                            clientContext.writeAndFlush(responseByteBuf);
                        }
                    }
                }

        }
    }

    private void processClientMessage(ChannelHandlerContext ctx, String clientRealMessage, ChannelGroup channelGroup1, String s) {
        String clientToChannel1Message = s + ctx.channel().remoteAddress()+" : "+ clientRealMessage;
        System.out.println("C1 group"+channelGroup1);
        System.out.println(clientToChannel1Message);
        for (Map.Entry<ChannelHandlerContext, ChannelGroup> entry : clientChannelMap.entrySet()) {
            ChannelHandlerContext key = entry.getKey();
            ChannelGroup value = entry.getValue();
            if(key!=ctx && value.equals(channelGroup1)){
                System.out.println("進入channel1迴圈，準備把內容給予channel1的人(除了本人)"+value);
                ByteBuf responseByteBuf = key.alloc().buffer();
                responseByteBuf.writeBytes(clientToChannel1Message.getBytes(CharsetUtil.UTF_8));
                key.writeAndFlush(responseByteBuf);
            }
        }
    }
    private void processClient2Message(ChannelHandlerContext ctx, String clientRealMessage, ChannelGroup channelGroup2, String s) {
        String clientToChannel2Message = s + ctx.channel().remoteAddress()+" : "+ clientRealMessage;
        System.out.println("C2 group"+channelGroup2);
        System.out.println(clientToChannel2Message);
        for (Map.Entry<ChannelHandlerContext, ChannelGroup> entry : clientChannelMap.entrySet()) {
            ChannelHandlerContext key = entry.getKey();
            ChannelGroup value = entry.getValue();
            if(key!=ctx && value.equals(channelGroup2)){
                System.out.println("進入channel2迴圈，準備把內容給予channel2的人(除了本人)"+value);
                ByteBuf responseByteBuf = key.alloc().buffer();
                responseByteBuf.writeBytes(clientToChannel2Message.getBytes(CharsetUtil.UTF_8));
                key.writeAndFlush(responseByteBuf);
            }
        }
//                channelGroup.writeAndFlush(Unpooled.copiedBuffer(clientToChannel2Message, CharsetUtil.UTF_8));
    }
    private String getChannelGroupInfo(ChannelHandlerContext ctx, ChannelGroup channelGroup1, ChannelGroup channelGroup2) {
        if (channelGroup1.contains(ctx.channel())) {
            return "Channel group1:";
        } else if (channelGroup2.contains(ctx.channel())) {
            return "Channel group2:";
        } else {
            return "Channel open group:";
        }
    }
}
