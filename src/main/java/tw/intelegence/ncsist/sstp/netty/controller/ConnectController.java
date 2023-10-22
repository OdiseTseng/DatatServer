package tw.intelegence.ncsist.sstp.netty.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.*;

public class ConnectController {
//    private static List<ChannelHandlerContext> clients = new ArrayList<>();
//    private static HashMap<String, ChannelHandlerContext> userClients = new HashMap<>();
    private static HashMap<String, ChannelHandlerContext> idClients = new HashMap<>();

    private static  HashMap<String, String> ipIdMaps = new HashMap<>();
    private static  HashMap<String, NettyDTO> idNettyMaps = new HashMap<>();

    private static ObjectMapper objectMapper = new ObjectMapper();


    public static boolean chkIpExist(String strIp){

        return ipIdMaps.get(strIp) != null;
    }

    public static HashMap<String, String> setNettyIpId(String strIp, String strId){
        String chkId = ipIdMaps.get(strIp);
        if(chkId != null){
            ipIdMaps.put(strIp, strId);
        }else{
            ipIdMaps.replace(strIp, strId);
        }

        return ipIdMaps;
    }

    public static boolean delIpId(String strIp){
        boolean isDeleted = false;
        if(chkIpExist(strIp)){
            ipIdMaps.remove(strIp);
            isDeleted = true;
        }
        return isDeleted;
    }

    public static boolean delIdNetty(String sourceId){
        return idNettyMaps.remove(sourceId) == null;
    }

    public static boolean delAllByIp(String sourceIp){
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

    public static void setIdNettyDTO(String sourceId, NettyDTO nettyDTO, ChannelHandlerContext ctx){
        NettyDTO currentDTO = idNettyMaps.get(sourceId);
        if(currentDTO == null){
            idNettyMaps.put(sourceId, nettyDTO);
            idClients.put(sourceId, ctx);
        }else{
            idNettyMaps.replace(sourceId, nettyDTO);
        }
    }

    //for demo
    public static void setIdIpNettyDTO_Demo(String sourceId, String sourceIp, NettyDTO nettyDTO, ChannelHandlerContext ctx){
        NettyDTO currentDTO = idNettyMaps.get(sourceId);
        if(currentDTO == null){
            idNettyMaps.put(sourceId+"-" + sourceIp, nettyDTO);
            idClients.put(sourceId+"-" + sourceIp, ctx);
        }else{
            idNettyMaps.replace(sourceId+"-" + sourceIp, nettyDTO);
        }
    }


    public static NettyDTO getNettyDTO_Demo(String id){
        return idNettyMaps.get(id);
    }

    public static NettyDTO getNettyDTO(String id){
        return idNettyMaps.get(id);
    }
    public static void sendClientsMsg(String sourceId, MsgDTO msgDTO){
        NettyDTO nettyDTO = idNettyMaps.get(sourceId);
        for(String key: idNettyMaps.keySet()){
            if(key.equals(sourceId)){
                continue;
            }

            String msgJson;

            try {
                msgJson = objectMapper.writeValueAsString(msgDTO);
            } catch (JsonProcessingException e) {
                System.out.println("JsonProcessingException : " + e.getMessage());
                throw new RuntimeException(e);
            }

            ChannelHandlerContext ctx = idClients.get(key);

            ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));

        }
    }

    public static void sendClientsMsg(String strId, String msg){
        NettyDTO nettyDTO = idNettyMaps.get(strId);
       for(String key: idNettyMaps.keySet()){
           if(key.equals(strId)){
               continue;
           }

           String msgJson = "{\"cmd\":\"" + NettyCode.CMD_900003
                   +"\",\"form\":" + nettyDTO.getName()
                   +"\",\"msg\":" + msg
                   +"\",\"}";

           ChannelHandlerContext ctx = idClients.get(key);

           ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));

       }
    }

    public static MsgDTO getClientMsg(String msg){
        MsgDTO msgDTO;
        try {
            msgDTO = objectMapper.readValue(msg, MsgDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return msgDTO;
    }

    public static void sendClientsCmd(){

    }

}
