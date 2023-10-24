package tw.intelegence.ncsist.sstp.netty.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.netty.service.NettyService;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.*;

public class ConnectController {


    public static boolean chkIpExist(String sourceIp){
        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        return ipIdMaps.get(sourceIp) != null;
    }

    public static boolean chkIpExist_Demo(String sourceId, String sourceIp){
        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        return ipIdMaps.get(sourceId + "-" + sourceIp) != null;
    }

    public static HashMap<String, String> setNettyIpIdCtx(String sourceIp, String sourceId, ChannelHandlerContext ctx){

        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
        String chkId = ipIdMaps.get(sourceIp);
        if(chkId == null){
            ipIdMaps.put(sourceIp, sourceId);
            idClients.put(sourceId, ctx);
        }else{
            ipIdMaps.replace(sourceIp, sourceId);
            idClients.replace(sourceId, ctx);
        }

        MsgDTO msgDTO = new MsgDTO();
        msgDTO.setCmd(NettyCode.CMD_CONNECT);
        msgDTO.setFrom("");
        msgDTO.setMsg(sourceId);
        
//        NettyService.sendClientMsg(msgDTO, ctx);
        NettyService.sendClientMsg(sourceId, msgDTO);

        return ipIdMaps;
    }

    public static void setNettyIpIdCtx_Demo(String username, String sourceIp, String sourceId, ChannelHandlerContext ctx){
        System.out.println("setNettyIpIdCtx_Demo ;; username : " + username + " ;; sourceIp : " + sourceIp + " ;; sourceId : " + sourceId);
        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
        HashMap<String, String> idsUserDemo = NettyService.getIdsUserDemo();
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();

        String chkId = ipIdMaps.get(sourceId + "-" + sourceIp);
        if(chkId == null){
            ipIdMaps.put(sourceId + "-" + sourceIp, sourceId);
            idClients.put(sourceId + "-" + sourceIp, ctx);
        }else{
            ipIdMaps.replace(sourceId + "-" + sourceIp, sourceId);
            idClients.replace(sourceId + "-" + sourceIp, ctx);
        }

        if(idsUserDemo.get(sourceId) == null){
            idsUserDemo.put(sourceId, username);
        }else{
            idsUserDemo.replace(sourceId, username);
        }

        boolean findKey = false;
        for(String key : idNettyMaps.keySet()){
            if(key.equals(sourceId + "-" + sourceIp)){
                findKey = true;
                break;
            }
        }

        if(!findKey){
            idNettyMaps.put(sourceId + "-" + sourceIp, null);
        }



        MsgDTO msgDTO = new MsgDTO();
        msgDTO.setCmd(NettyCode.CMD_CONNECT);
        msgDTO.setFrom("Demo REG");
        msgDTO.setMsg(sourceId);

//        NettyService.sendClientMsg_Demo(msgDTO, ctx);
        NettyService.sendClientMsg_Demo(sourceId, msgDTO);
    }

    public static boolean delIpId(String strIp){
        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        boolean isDeleted = false;
        if(chkIpExist(strIp)){
            ipIdMaps.remove(strIp);
            isDeleted = true;
        }
        return isDeleted;
    }

    public static boolean delIpId_Demo(String sourceId, String sourceIp){
        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        boolean isDeleted = false;
        if(chkIpExist(sourceId + "-" + sourceIp)){
            ipIdMaps.remove(sourceId + "-" + sourceIp);
            isDeleted = true;
        }
        return isDeleted;
    }

    public static boolean delIdNetty(String sourceId){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        return idNettyMaps.remove(sourceId) == null;
    }

    public static boolean delIdNetty_Demo(String sourceId, String sourceIp){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        return idNettyMaps.remove(sourceId + "-" + sourceIp) == null;
    }

    public static boolean delAllByIp(String sourceIp){
        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        boolean isDelete = false;
        String sourceId = ipIdMaps.get(sourceIp);
        if(sourceId != null && !sourceId.trim().isEmpty()) {
            isDelete = delIdNetty(sourceId);
            if(isDelete){
                isDelete = delIpId(sourceIp);
            }
        }
        return isDelete;
    }

    public static boolean delAll_Demo(String sourceId, String sourceIp){
        HashMap<String, String> ipIdMaps = NettyService.getIpIdMaps();
        boolean isDelete = false;
        String strId = ipIdMaps.get(sourceId + "-" + sourceIp);
        if(strId != null && !strId.trim().isEmpty()) {
            isDelete = delIdNetty_Demo(sourceId, sourceIp);
            if(isDelete){
                isDelete = delIpId_Demo(sourceId, sourceIp);
            }
        }
        return isDelete;
    }

    public static void setIdNettyDTO(String sourceId, NettyDTO nettyDTO){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();

        NettyDTO currentDTO = idNettyMaps.get(sourceId);
        ChannelHandlerContext ctx = idClients.get(sourceId);
        if(currentDTO == null){
            idNettyMaps.put(sourceId, nettyDTO);
        }else{
            idNettyMaps.replace(sourceId, nettyDTO);
        }
    }

    //for demo
    public static void setIdIpNettyDTO_Demo(String sourceId, String sourceIp, NettyDTO nettyDTO){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();

        NettyDTO currentDTO = idNettyMaps.get(sourceId+"-" + sourceIp);
        ChannelHandlerContext ctx = idClients.get(sourceId+"-" + sourceIp);
        if(currentDTO == null){
            idNettyMaps.put(sourceId+"-" + sourceIp, nettyDTO);
        }else{
            idNettyMaps.replace(sourceId+"-" + sourceIp, nettyDTO);
        }
    }

}
