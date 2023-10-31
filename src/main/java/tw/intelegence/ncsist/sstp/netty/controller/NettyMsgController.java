package tw.intelegence.ncsist.sstp.netty.controller;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.netty.service.NettyCommonService;
import tw.intelegence.ncsist.sstp.netty.service.NettyTeamService;
import tw.intelegence.ncsist.sstp.utils.func.CodeDecoder;
import tw.intelegence.ncsist.sstp.utils.func.DTOParser;
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

    public void setFirstNettyIdCtx(ChannelHandlerContext ctx, String sourceCtxId){

        System.out.println("setNettyIdCtx :: sourceCtxId : " + sourceCtxId);
        HashMap<String, ChannelHandlerContext> idClients = getIdClients();
        if(idClients.containsKey(sourceCtxId)){
            idClients.replace(sourceCtxId, ctx);
        }else{
            idClients.put(sourceCtxId, ctx);
        }

        sendFirstClientMsg(ctx, sourceCtxId);
        sendFirstToOtherClientsMsg(sourceCtxId, NettyCode.CMD_OTHER_CONNECT);
    }

    public void delIdNetty(String sourceCtxId){
        System.out.println("delIdNetty : " + sourceCtxId);
//        HashMap<String, NettyDTO> idNettyMaps = getIdNettyMaps();
        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId);
        boolean isDelete = idNettyMaps.remove(sourceCtxId, nettyDTO);

        System.out.println("isDelete : " + isDelete);
        if(isDelete){
            System.out.println("chk : " + idNettyMaps.get(sourceCtxId));
            idClients.remove(sourceCtxId);

            sendToOtherClientsMsg(sourceCtxId, NettyCode.CMD_OTHER_LOGOUT, nettyDTO.getName());

        }
    }

    public void setIdNettyDTO(String sourceCtxId, NettyDTO nettyDTO, MsgDTO msgDTO){
        System.out.println("setIdNettyDTO ::: sourceCtxId : " + sourceCtxId + " ;;; nettyDTO : " + nettyDTO.toString() + " ;;; msgDTO : " + msgDTO.toString());
        if(idNettyMaps.containsKey(sourceCtxId)){
            idNettyMaps.replace(sourceCtxId, nettyDTO);
        }else{
            idNettyMaps.put(sourceCtxId, nettyDTO);
        }

        sendToOtherClientsMsg(sourceCtxId, NettyCode.CMD_OTHER_LOGIN, sourceCtxId);

        msgDTO.setMsg(DTOParser.parseDTOToString(nettyDTO));
        System.out.println(" chkMsg : " + DTOParser.parseDTOToString(msgDTO));

        sendMsg(sourceCtxId, msgDTO);
    }

    public void sendMsg(String sourceCtxId, MsgDTO msgDTO){
        System.out.println("sendMsg ::: sourceCtxId : " + sourceCtxId + " ;;; msgDTO : " + msgDTO.toString());
        String msgString = DTOParser.parseDTOToString(msgDTO);
        sendStringMsg(sourceCtxId, msgString);
    }

    private void sendStringMsg(String sourceCtxId, String msg){
        System.out.println("sendStringMsg ::: sourceCtxId : " + sourceCtxId + " ;;; msg : " + msg);
        ChannelHandlerContext ctx = idClients.get(sourceCtxId);
        if(ctx != null){
            ctx.writeAndFlush(Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        }
    }

    public void sendFirstClientMsg(ChannelHandlerContext ctx, String sourceCtxId){
        System.out.println("sendFirstClientMsg ::: ctx : " + ctx + " ;;; sourceCtxId : " + sourceCtxId);

        MsgDTO msgDTO = nettyCommonService.createMsgDTO(NettyCode.CMD_CONNECT, sourceCtxId);

        String msgString = DTOParser.parseDTOToString(msgDTO);
        ctx.writeAndFlush(Unpooled.copiedBuffer(msgString, CharsetUtil.UTF_8));
    }

    public void sendFirstToOtherClientsMsgDTO(String sourceCtxId, MsgDTO msgDTO){
        System.out.println("sendFirstClientMsg ::: sourceCtxId : " + sourceCtxId + " ;;; msgDTO : " + msgDTO.toString());
        for(String ctxId: idNettyMaps.keySet()){
            if(ctxId.equals(sourceCtxId)){
                continue;
            }

            sendMsg(ctxId, msgDTO);
        }
    }

    public void sendFirstToOtherClientsMsg(String sourceCtxId, int cmdCode){
        System.out.println("sendFirstToOtherClientsMsg ::: sourceCtxId : " + sourceCtxId + " ;;; cmdCode : " + cmdCode);

        MsgDTO msgDTO = nettyCommonService.createMsgDTO(cmdCode);

        for(String ctxId: idNettyMaps.keySet()){
            if(ctxId.equals(sourceCtxId)){
                continue;
            }

            sendMsg(ctxId, msgDTO);
        }
    }



    public void sendToOtherClientsMsgDTO(String sourceCtxId, MsgDTO msgDTO){
        System.out.println("sendToOtherClientsMsgDTO ::: sourceCtxId : " + sourceCtxId + " ;;; msgDTO : " + msgDTO.toString());

//        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId);

        for(String ctxId: idNettyMaps.keySet()){
            if(ctxId.equals(sourceCtxId)){
                continue;
            }

            sendMsg(ctxId, msgDTO);
        }
    }

    public void sendToOtherClientsMsg(String sourceCtxId, int cmdCode, String msg){
        System.out.println("sendToOtherClientsMsg ::: sourceCtxId : " + sourceCtxId + " ;;; cmdCode : " + cmdCode + " ;;; msg : " + msg);
        NettyDTO nettyDTO;
        MsgDTO msgDTO;
        if(idNettyMaps.containsKey(sourceCtxId)){
            nettyDTO = idNettyMaps.get(sourceCtxId);
            msgDTO = nettyCommonService.createMsgDTO(cmdCode, msg, nettyDTO.getName(), nettyDTO.getLevel());
        }else{
            msgDTO = nettyCommonService.createMsgDTO(cmdCode, "" , msg);
        }

        for(String ctxId: idNettyMaps.keySet()){
            if(ctxId.equals(sourceCtxId)){
                continue;
            }

            sendMsg(ctxId, msgDTO);
        }
    }

    public NettyDTO getNettyDTO(String sourceCtxId) {
        System.out.println("getNettyDTO ::: sourceCtxId : " + sourceCtxId);
        return idNettyMaps.get(sourceCtxId);
    }

    public void sendClientsMsg(String sourceCtxId, String msg){
        System.out.println("sendClientsMsg ::: sourceCtxId : " + sourceCtxId + " ;;; msg : " + msg);
        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId);


        String msgString = "{\"cmd\":\"" + NettyCode.CMD_890001
                +"\",\"form\":" + nettyDTO.getName()
                +"\",\"msg\":" + msg
                +"\",\"}";

        for(String ctxId: idNettyMaps.keySet()){
            if(ctxId.equals(sourceCtxId)){
                continue;
            }

            sendStringMsg(ctxId, msgString);
        }
    }

    public void treatMsg(String sourceCtxId, String sourceMsg){
        System.out.println("treatMsg ::: sourceCtxId : " + sourceCtxId + " ;;; sourceMsg : " + sourceMsg);
        MsgDTO msgDTO = DTOParser.toMsgDTO(sourceMsg);

        int cmd = msgDTO.getCmd();
        int cmdType = cmd / 10000;
        String msg = msgDTO.getMsg();
        String from = msgDTO.getFrom();
        long level = msgDTO.getLevel();
        int team = msgDTO.getTeam();

        System.out.println("cmd : "  + cmd);
        System.out.println("cmdType : "  + cmdType);
        System.out.println("msg : "  + msg);
        System.out.println("from : "  + from);
        System.out.println("level : "  + level);
        System.out.println("team : "  + team);

        MsgDTO returnMsgDTO;
        String decodeMsg = "";

        switch (cmdType) {
            case NettyCode.CMD_NORMAL:
            case NettyCode.CMD:
                decodeMsg = CodeDecoder.getCodeMsg("CMD", cmd);
                returnMsgDTO = nettyCommonService.treatMsgDTO(cmd, sourceCtxId, from, msg);

                sendToOtherClientsMsgDTO(sourceCtxId, returnMsgDTO);
                break;

            case NettyCode.TEAM:
                decodeMsg = CodeDecoder.getCodeMsg("TEAM", cmd);
                returnMsgDTO = nettyTeamService.treatMsgDTO(cmd, sourceCtxId, from, msg);

                sendToOtherClientsMsgDTO(sourceCtxId, returnMsgDTO);

                switch (cmd){
                    case NettyCode.TEAM_WAITING_JOIN:
                        returnMsgDTO.setCmd(cmd);
                        sendMsg(sourceCtxId, returnMsgDTO);
                        break;
                    case NettyCode.TEAM_WAITING_COACH_GET_ALL:
                        msg = nettyTeamService.getTeamListStr(idNettyMaps);
                        System.out.println("new Msg: " + msg);
                        returnMsgDTO.setMsg(msg);

                        sendMsg(sourceCtxId, returnMsgDTO);
                        break;
                    case NettyCode.TEAM_WAITING_COACH_ADD_TEAM:
                    case NettyCode.TEAM_WAITING_COACH_UPD_TEAM:
                    case NettyCode.TEAM_WAITING_COACH_UPD_ROLE:
                        msg = nettyTeamService.getTeamListStr();
                        System.out.println("new Msg: " + msg);
                        returnMsgDTO.setMsg(msg);

                        sendMsg(sourceCtxId, returnMsgDTO);
                        break;
                }

//                if(cmd == NettyCode.TEAM_WAITING_COACH_DISPATCH){
//                    msg = nettyTeamService.getTeamListStr(idNettyMaps);
//                    System.out.println("new Msg: " + msg);
//                    returnMsgDTO.setMsg(msg);
//
//                    sendMsg(sourceCtxId, returnMsgDTO);
//                }

                break;
        }

//        System.out.println("decodeMsg : " + decodeMsg);
    }
}
