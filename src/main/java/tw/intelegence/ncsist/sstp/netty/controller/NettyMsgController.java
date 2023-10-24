package tw.intelegence.ncsist.sstp.netty.controller;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.netty.service.NettyCommonService;
import tw.intelegence.ncsist.sstp.netty.service.NettyTeamService;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.*;

public class NettyMsgController {


    @Getter
    private static final HashMap<String, ChannelHandlerContext> idClients = new HashMap<>();

    @Getter
    private static final HashMap<String, String> idsUserDemo = new HashMap<>();

    @Getter
    private static final HashMap<String, String> ipIdMaps = new HashMap<>();

    @Getter
    private static final HashMap<String, NettyDTO> idNettyMaps = new HashMap<>();

    private static final NettyCommonService nettyCommonService = new NettyCommonService();
    private static final NettyTeamService nettyTeamService = new NettyTeamService();


//    public static boolean chkIpExist(String sourceIp){
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        return ipIdMaps.get(sourceIp) != null;
//    }
//
//    public static boolean chkIpExist_Demo(String sourceId, String sourceIp){
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        return ipIdMaps.get(sourceId + "-" + sourceIp) != null;
//    }

    public static void setNettyIdCtx(ChannelHandlerContext ctx, String sourceCtxId){

//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        HashMap<String, ChannelHandlerContext> idClients = getIdClients();
        boolean chkId = idClients.containsKey(sourceCtxId);
//        String chkId = ipIdMaps.get(sourceIp);
        if(chkId){
//            ipIdMaps.put(sourceIp, sourceCtxId);
            idClients.put(sourceCtxId, ctx);
        }else{
//            ipIdMaps.replace(sourceIp, sourceCtxId);
            idClients.replace(sourceCtxId, ctx);
        }

//        NettyService.sendClientMsg(msgDTO, ctx);
//        NettyCommonService.sendFirstClientMsg(ctx, msgDTO);
        sendFirstClientMsg(ctx, sourceCtxId);
//        NettyService.sendClientMsg(sourceCtxId, msgDTO);
    }

//    public static HashMap<String, String> setNettyIpIdCtx(String sourceIp, String sourceId, ChannelHandlerContext ctx){
//
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
//        String chkId = ipIdMaps.get(sourceIp);
//        if(chkId == null){
//            ipIdMaps.put(sourceIp, sourceId);
//            idClients.put(sourceId, ctx);
//        }else{
//            ipIdMaps.replace(sourceIp, sourceId);
//            idClients.replace(sourceId, ctx);
//        }
//
//        MsgDTO msgDTO = new MsgDTO();
//        msgDTO.setCmd(NettyCode.CMD_CONNECT);
//        msgDTO.setFrom("");
//        msgDTO.setMsg(sourceId);
//
////        NettyService.sendClientMsg(msgDTO, ctx);
//        NettyService.sendClientMsg(sourceId, msgDTO);
//
//        return ipIdMaps;
//    }

//    public static void setNettyIpIdCtx_Demo(String username, String sourceIp, String sourceId, ChannelHandlerContext ctx){
//        System.out.println("setNettyIpIdCtx_Demo ;; username : " + username + " ;; sourceIp : " + sourceIp + " ;; sourceId : " + sourceId);
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
//        HashMap<String, String> idsUserDemo = NettyService.getIdsUserDemo();
//        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
//
//        String chkId = ipIdMaps.get(sourceId + "-" + sourceIp);
//        if(chkId == null){
//            ipIdMaps.put(sourceId + "-" + sourceIp, sourceId);
//            idClients.put(sourceId + "-" + sourceIp, ctx);
//        }else{
//            ipIdMaps.replace(sourceId + "-" + sourceIp, sourceId);
//            idClients.replace(sourceId + "-" + sourceIp, ctx);
//        }
//
//        if(idsUserDemo.get(sourceId) == null){
//            idsUserDemo.put(sourceId, username);
//        }else{
//            idsUserDemo.replace(sourceId, username);
//        }
//
//        boolean findKey = false;
//        for(String key : idNettyMaps.keySet()){
//            if(key.equals(sourceId + "-" + sourceIp)){
//                findKey = true;
//                break;
//            }
//        }
//
//        if(!findKey){
//            idNettyMaps.put(sourceId + "-" + sourceIp, null);
//        }
//
//
//
//        MsgDTO msgDTO = new MsgDTO();
//        msgDTO.setCmd(NettyCode.CMD_CONNECT);
//        msgDTO.setFrom("Demo REG");
//        msgDTO.setMsg(sourceId);
//
////        NettyService.sendClientMsg_Demo(msgDTO, ctx);
//        NettyService.sendClientMsg_Demo(sourceId, msgDTO);
//    }

//    public static boolean delIpId(String sourceIp){
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        boolean isDeleted = false;
//        if(chkIpExist(sourceIp)){
//            ipIdMaps.remove(sourceIp);
//            isDeleted = true;
//        }
//        return isDeleted;
//    }

//    public static boolean delIpId_Demo(String sourceId, String sourceIp){
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        boolean isDeleted = false;
//        if(chkIpExist(sourceId + "-" + sourceIp)){
//            ipIdMaps.remove(sourceId + "-" + sourceIp);
//            isDeleted = true;
//        }
//        return isDeleted;
//    }


//    public static boolean delIdNetty(String sourceId){
//        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
//        return idNettyMaps.remove(sourceId) == null;
//    }
    public static void delIdNetty(String sourceCtxId){
        boolean isDelete = false;
//        HashMap<String, NettyDTO> idNettyMaps = getIdNettyMaps();
        isDelete = idNettyMaps.remove(sourceCtxId) == null;
        if(isDelete){
//            HashMap<String, ChannelHandlerContext> idClients = getIdClients();
            idClients.remove(sourceCtxId);

            sendToOtherClientsMsg(sourceCtxId, NettyCode.CMD_LOGOUT);

        }
//        return isDelete;
    }

//    public static boolean delIdNetty_Demo(String sourceId, String sourceIp){
//        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
//        return idNettyMaps.remove(sourceId + "-" + sourceIp) == null;
//    }

//    public static boolean delAllByIp(String sourceIp){
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        boolean isDelete = false;
//        String sourceId = ipIdMaps.get(sourceIp);
//        if(sourceId != null && !sourceId.trim().isEmpty()) {
//            isDelete = delIdNetty(sourceId);
//            if(isDelete){
//                isDelete = delIpId(sourceIp);
//            }
//        }
//        return isDelete;
//    }

//    public static boolean delAll_Demo(String sourceId, String sourceIp){
//        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
//        boolean isDelete = false;
//        String strId = ipIdMaps.get(sourceId + "-" + sourceIp);
//        if(strId != null && !strId.trim().isEmpty()) {
//            isDelete = delIdNetty_Demo(sourceId, sourceIp);
//            if(isDelete){
//                isDelete = delIpId_Demo(sourceId, sourceIp);
//            }
//        }
//        return isDelete;
//    }

    public static void setIdNettyDTO(String sourceCtxId, NettyDTO nettyDTO, MsgDTO msgDTO){
//        HashMap<String, NettyDTO> idNettyMaps = getIdNettyMaps();
//        HashMap<String, ChannelHandlerContext> idClients = getIdClients();

        NettyDTO currentDTO = idNettyMaps.get(sourceCtxId);
//        ChannelHandlerContext ctx = idClients.get(sourceCtxId);
        if(currentDTO == null){
            idNettyMaps.put(sourceCtxId, nettyDTO);
        }else{
            idNettyMaps.replace(sourceCtxId, nettyDTO);
        }

        senMsg(sourceCtxId, msgDTO);
    }

    public static void senMsg(String sourceCtxId, MsgDTO msgDTO){

        String msgJson = nettyCommonService.parseDTOToMsgJson(msgDTO);
        sendMsg(sourceCtxId, msgJson);
    }

    private static void sendMsg(String sourceCtxId, String msg){
        ChannelHandlerContext ctx = idClients.get(sourceCtxId);
        ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
    }

    //for demo
//    public static void setIdIpNettyDTO_Demo(String sourceId, String sourceIp, NettyDTO nettyDTO){
//        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
//        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
//
//        NettyDTO currentDTO = idNettyMaps.get(sourceId+"-" + sourceIp);
//        ChannelHandlerContext ctx = idClients.get(sourceId+"-" + sourceIp);
//        if(currentDTO == null){
//            idNettyMaps.put(sourceId+"-" + sourceIp, nettyDTO);
//        }else{
//            idNettyMaps.replace(sourceId+"-" + sourceIp, nettyDTO);
//        }
//    }

    public static void sendFirstClientMsg(ChannelHandlerContext ctx, String sourceCtxId){
        MsgDTO msgDTO = new MsgDTO();
        msgDTO.setCmd(NettyCode.CMD_CONNECT);
        msgDTO.setFrom("");
        msgDTO.setMsg(sourceCtxId);

        String msgJson = nettyCommonService.parseDTOToMsgJson(msgDTO);
        ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));
    }

//    public static void sendToOtherClientsMsg(String sourceCtxId, MsgDTO msgDTO){
////        HashMap<String, NettyDTO> idNettyMaps = NettyCommonService.getIdNettyMaps();
////        HashMap<String, ChannelHandlerContext> idClients = NettyCommonService.getIdClients();
//
//        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId);
//
//        String msgJson = nettyCommonService.parseDTOToMsgJson(msgDTO);
//
//        for(String ctxId: idNettyMaps.keySet()){
//            if(ctxId.equals(sourceCtxId)){
//                continue;
//            }
//
//            sendMsg(ctxId, msgJson);
//        }
//    }

    public static void sendToOtherClientsMsg(String sourceCtxId, int cmdCode, String msg){
//        HashMap<String, NettyDTO> idNettyMaps = NettyCommonService.getIdNettyMaps();
//        HashMap<String, ChannelHandlerContext> idClients = NettyCommonService.getIdClients();

        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId);

        MsgDTO msgDTO = nettyCommonService.createMsgDTO(cmdCode, msg, nettyDTO.getName(), nettyDTO.getLevel(), 0);

        String msgJson = nettyCommonService.parseDTOToMsgJson(msgDTO);

        for(String ctxId: idNettyMaps.keySet()){
            if(ctxId.equals(sourceCtxId)){
                continue;
            }

            sendMsg(ctxId, msgJson);
        }
    }

    public static NettyDTO getNettyDTO(String sourceCtxId) {
//        HashMap<String, NettyDTO> idNettyMaps = NettyCommonService.getIdNettyMaps();
        return idNettyMaps.get(sourceCtxId);
    }

    public static void sendClientsMsg(String sourceCtxId, String msg){
//        HashMap<String, NettyDTO> idNettyMaps = NettyCommonService.getIdNettyMaps();
//        HashMap<String, ChannelHandlerContext> idClients = NettyCommonService.getIdClients();
        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId);


        String msgJson = "{\"cmd\":\"" + NettyCode.CMD_890001
                +"\",\"form\":" + nettyDTO.getName()
                +"\",\"msg\":" + msg
                +"\",\"}";

        for(String ctxId: idNettyMaps.keySet()){
            if(ctxId.equals(sourceCtxId)){
                continue;
            }
            sendMsg(ctxId, msgJson);
        }
    }
}
