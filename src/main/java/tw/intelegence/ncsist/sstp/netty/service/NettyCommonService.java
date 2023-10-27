package tw.intelegence.ncsist.sstp.netty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

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

    public MsgDTO createMsgDTO(int cmdCode){
        return createMsgDTO(cmdCode, "");
    }

    public MsgDTO createMsgDTO(int cmdCode, String msg){
        return createMsgDTO(cmdCode, msg, "");
    }

    public MsgDTO createMsgDTO(int cmdCode, String msg, String fromName){
        return createMsgDTO(cmdCode, msg, fromName, 0);
    }

    public MsgDTO createMsgDTO(int cmdCode, String msg, String fromName, long level){
        return createMsgDTO(cmdCode, msg, fromName, level, 0);
    }

    public MsgDTO createMsgDTO(int cmdCode, String msg, String fromName, long level, int team){
        MsgDTO msgDTO = new MsgDTO();
        msgDTO.setCmd(cmdCode);
        msgDTO.setMsg(msg);
        msgDTO.setFrom(fromName);
        msgDTO.setLevel(level);
        msgDTO.setTeam(team);

        return msgDTO;
    }

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

    public MsgDTO treatMsgDTO(int cmd, String sourceCtxId, String from, String msg){

        System.out.println("common treatMsgDTO : cmd= " + cmd + " sourceCtxId= " + sourceCtxId+ " from= " + from + " msg= " + msg);

        MsgDTO msgDTO = new MsgDTO();

        switch(cmd){
            case NettyCode.CMD_NORMAL_MSG:  //89 001
                msgDTO.setCmd(NettyCode.CMD_NORMAL_OTHER_MSG);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.CMD_CONNECT:     //90 001
                msgDTO.setCmd(NettyCode.CMD_OTHER_CONNECT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.CMD_DISCONNECT:  //90 002
                msgDTO.setCmd(NettyCode.CMD_OTHER_DISCONNECT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.CMD_LOGIN:       //90  003
                msgDTO.setCmd(NettyCode.CMD_OTHER_LOGIN);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.CMD_LOGOUT:      //90 004
                msgDTO.setCmd(NettyCode.CMD_OTHER_LOGOUT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;
        }

        return msgDTO;
    }
}
