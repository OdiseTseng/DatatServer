package tw.intelegence.ncsist.sstp.netty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import lombok.Getter;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.HashMap;

public class NettyService {

    @Getter
    private static final HashMap<String, ChannelHandlerContext> idClients = new HashMap<>();

    @Getter
    private static final HashMap<String, String> idsUserDemo = new HashMap<>();

    @Getter
    private static final HashMap<String, String> ipIdMaps = new HashMap<>();

    @Getter
    private static final HashMap<String, NettyDTO> idNettyMaps = new HashMap<>();

    @Getter
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static NettyDTO getNettyDTO_Demo(String ctxId){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        return idNettyMaps.get(ctxId);
    }

    public static NettyDTO getNettyDTO(String ctxId){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        return idNettyMaps.get(ctxId);
    }
    public static void sendClientsMsg(String sourceCtxId, MsgDTO msgDTO){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
        ObjectMapper objectMapper = NettyService.getObjectMapper();

        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId);


        String msgJson;

        try {
            msgJson = objectMapper.writeValueAsString(msgDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        for(String key: idNettyMaps.keySet()){
            if(key.equals(sourceCtxId)){
                continue;
            }

            ChannelHandlerContext ctx = idClients.get(key);

            ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));

        }
    }

    public static void sendClientsMsg_Demo(String sourceCtxId, String sourceIp, MsgDTO msgDTO){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
        ObjectMapper objectMapper = NettyService.getObjectMapper();

        System.out.println("sendClientsMsg_Demo ;; sourceCtxId : " + sourceCtxId + " ;; sourceIp : " + sourceIp);
        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId+"-" + sourceIp);
        System.out.println("sendClientsMsg_Demo ;; nettyDTO : " + nettyDTO.toString());

        String msgJson;
        ChannelHandlerContext ctx;

        try {
            msgJson = objectMapper.writeValueAsString(msgDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        System.out.println("msgJson : " + msgJson);


        for(String key: idClients.keySet()){
            System.out.println("idClients key : " + key);
            ctx = idClients.get(key);

            ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));
        }
    }

    public static void sendClientMsg(String sourceCctxId, MsgDTO msgDTO){
        ObjectMapper objectMapper = NettyService.getObjectMapper();
        ChannelHandlerContext ctx = idClients.get(sourceCctxId);

        String msgJson;

        try {
            msgJson = objectMapper.writeValueAsString(msgDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));
    }

    public static void sendClientMsg_Demo(String sourceCctxId, MsgDTO msgDTO){
        ObjectMapper objectMapper = NettyService.getObjectMapper();
        ChannelHandlerContext ctx = idClients.get(sourceCctxId);

        String msgJson;

        try {
            msgJson = objectMapper.writeValueAsString(msgDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));
    }

    public static void sendClientsMsg(String strId, String msg){
        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
        NettyDTO nettyDTO = idNettyMaps.get(strId);


        String msgJson = "{\"cmd\":\"" + NettyCode.CMD_900003
                +"\",\"form\":" + nettyDTO.getName()
                +"\",\"msg\":" + msg
                +"\",\"}";

        for(String key: idNettyMaps.keySet()){
            if(key.equals(strId)){
                continue;
            }

            ChannelHandlerContext ctx = idClients.get(key);

            ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));

        }
    }

    public static MsgDTO getClientMsg(String msg){
        ObjectMapper objectMapper = NettyService.getObjectMapper();
        MsgDTO msgDTO;
        try {
            msgDTO = objectMapper.readValue(msg, MsgDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return msgDTO;
    }
}
