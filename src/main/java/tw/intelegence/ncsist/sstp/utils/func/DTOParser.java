package tw.intelegence.ncsist.sstp.utils.func;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
