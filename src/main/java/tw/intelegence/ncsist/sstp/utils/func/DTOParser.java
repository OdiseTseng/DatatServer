package tw.intelegence.ncsist.sstp.utils.func;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;

public class DTOParser {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static String parseDTOToString(Object objDTO){
        String msgString;

        try {
            msgString = objectMapper.writeValueAsString(objDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        return msgString;
    }

    public static String parseDTOsToString(Object[] objDTOs){
        System.out.println("parseDTOsToString : ");
        String msgString;

        try {
            msgString = objectMapper.writeValueAsString(objDTOs);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }

        return msgString;
    }

    public static MsgDTO toMsgDTO(String msg){
        MsgDTO msgDTO;
        try {
            msgDTO = objectMapper.readValue(msg, MsgDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return msgDTO;
    }

    public static NettyDTO toNettyDTO(String msg){
        NettyDTO nettyDTO;
        try {
            nettyDTO = objectMapper.readValue(msg, NettyDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return nettyDTO;
    }

    //
    //多物件範例 TeamDTO[] teamDTOS = (TeamDTO[])DTOParser.parseStringToDTO(msg, TeamDTO[].class);
    public static Object parseStringToDTO(String msg, Class target) {
        Object object = null;
        try {
            object = objectMapper.readValue(msg, target);
        }catch (JsonProcessingException e){
            System.out.println("JsonProcessingException : " + e.getMessage());
        }
        return object;
    }
}
