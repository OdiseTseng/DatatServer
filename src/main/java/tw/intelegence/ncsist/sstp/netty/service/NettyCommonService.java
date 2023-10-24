package tw.intelegence.ncsist.sstp.netty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;

public class NettyCommonService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public MsgDTO toMsgDTO(String msg){
        MsgDTO msgDTO;
        try {
            msgDTO = objectMapper.readValue(msg, MsgDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return msgDTO;
    }

    public MsgDTO createMsgDTO(int cmdCode, String msg, String name, long level, int team){
        MsgDTO msgDTO = new MsgDTO();
        msgDTO.setCmd(cmdCode);
        msgDTO.setMsg(msg);
        msgDTO.setFrom(name);
        msgDTO.setLevel(level);
        msgDTO.setTeam(team);

        return msgDTO;
    }

//    public static void sendClientsMsg_Demo(String sourceCtxId, String sourceIp, MsgDTO msgDTO){
//        HashMap<String, NettyDTO> idNettyMaps = NettyService.getIdNettyMaps();
//        HashMap<String, ChannelHandlerContext> idClients = NettyService.getIdClients();
//        ObjectMapper objectMapper = NettyService.getObjectMapper();
//
//        System.out.println("sendClientsMsg_Demo ;; sourceCtxId : " + sourceCtxId + " ;; sourceIp : " + sourceIp);
//        NettyDTO nettyDTO = idNettyMaps.get(sourceCtxId+"-" + sourceIp);
//        System.out.println("sendClientsMsg_Demo ;; nettyDTO : " + nettyDTO.toString());
//
//        String msgJson;
//        ChannelHandlerContext ctx;
//
//        try {
//            msgJson = objectMapper.writeValueAsString(msgDTO);
//        } catch (JsonProcessingException e) {
//            System.out.println("JsonProcessingException : " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//        System.out.println("msgJson : " + msgJson);
//
//
//        for(String key: idClients.keySet()){
//            System.out.println("idClients key : " + key);
//            ctx = idClients.get(key);
//
//            ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));
//        }
//    }

//    public static void sendClientMsg_Demo(String sourceCtxId, MsgDTO msgDTO){
//        ObjectMapper objectMapper = NettyService.getObjectMapper();
//        ChannelHandlerContext ctx = idClients.get(sourceCtxId);
//
//        String msgJson;
//
//        try {
//            msgJson = objectMapper.writeValueAsString(msgDTO);
//        } catch (JsonProcessingException e) {
//            System.out.println("JsonProcessingException : " + e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//        ctx.writeAndFlush(Unpooled.copiedBuffer(msgJson, CharsetUtil.UTF_8));
//    }

    public NettyDTO toNettyDTO(String msg){
        NettyDTO nettyDTO;
        try {
            nettyDTO = objectMapper.readValue(msg, NettyDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return nettyDTO;
    }

    public String parseDTOToMsgJson(Object objDTO){
        String msgJson;

        try {
            msgJson = objectMapper.writeValueAsString(objDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        return msgJson;
    }
}
