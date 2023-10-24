package tw.intelegence.ncsist.sstp.netty.controller;

import io.netty.channel.ChannelHandlerContext;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.netty.service.NettyService;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamController {

    public static List<String> waitingUsers = new ArrayList<>();

    public static Map<Integer, HashMap<String, Integer>> teamUsers = new HashMap<>();

    public static String createTeam(){

        return "";
    }

    public static String addWaitingUser(String ctxId){

        String message = "已加入名單";

        if(waitingUsers.contains(ctxId)){
            message = "已存在於名單內";
        }else{
            waitingUsers.add(ctxId);
        }

        return message;
    }

    public static String delWaitingUser(String ctxId){

        String message = "未存在於等待名單中";

        if(waitingUsers.contains(ctxId)){
            waitingUsers.remove(ctxId);
            message = "已從等待名單中刪除";
        }

        return message;
    }

    public static String delAllWaitingUser(){
        String message = "已清空所有使用者";
        waitingUsers = new ArrayList<>();

        return message;
    }

    public static String addTeam(String ctxId, int teamNumber){

        String message;

//        ArrayList<String> list;
        HashMap<String, Integer> list;

        if(teamUsers.get(teamNumber) == null){
            list = new HashMap<>();
            list.put(ctxId, 1);
//            list = new ArrayList<>();
//            list.add(ctxId);

            teamUsers.put(teamNumber, list);
            message = delWaitingUser(ctxId);
            message += ", 新增團隊(" + teamNumber + ")並加入團隊名單成功";
        }else{
            list = teamUsers.get(teamNumber);
            if(list.get(ctxId) != null){
//                if(list.contains(ctxId)){
                message = "已存在於團隊名單中";
            }else{
//                list.add(ctxId);
                list.put(ctxId, list.size() + 1);
                message = delWaitingUser(ctxId);
                message += ", 加入團隊(" + teamNumber + ")名單成功";
            }
        }


        return message;
    }

    public static String delAllTeam(){
        String message = "已清空所有團隊";
        teamUsers = new HashMap<>();
        return message;
    }

    public static int reRangeTeam(Map<Integer, HashMap<String, Integer>> newTeamUsers){

        teamUsers = newTeamUsers;

        return NettyCode.TEAM_WAITING_COACH_DISPATCH;
    }



    public static void sendToTeamUser(String ctxId, int nettyCode){
        MsgDTO msgDTO = new MsgDTO();
        NettyDTO nettyDTO = null;
        int team = 0;
        
        if(ctxId != null && ctxId.trim().isEmpty()){
            nettyDTO = NettyService.getNettyDTO(ctxId);
            for(int teamKey : teamUsers.keySet()){
                if(teamUsers.get(teamKey).get(ctxId) != null){
                    team = teamKey;
                    break;
                }
            }
        }

        switch (nettyCode){
            case NettyCode.TEAM_WAITING_WAIT:   //1
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT);
//                msgDTO.setMsg();
                break;
            case NettyCode.TEAM_WAITING_WAIT_TIMEOUT: //2
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT_TIMEOUT);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_JOIN:   //3
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_JOIN_FAIL:  //4
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN_FAIL);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_COACH_DISPATCH:     //5
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL:    //6
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_NEXT:   //7
                msgDTO.setCmd(NettyCode.TEAM_WAITING_NEXT);
//                msgDTO.setMsg();
                msgDTO.setTeam(team);

                break;
            case NettyCode.TEAM_COURSE_STEP_WAITING:    //8
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_WAITING);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_STEP_STARTING:   //9
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_STARTING);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_STEP_FINISH: //10
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_FINISH);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_ALL_FINISH:  //11
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FINISH);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_ALL_FAIL:    //12
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FAIL);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_DEL_TEAM:    //13
                msgDTO.setCmd(NettyCode.TEAM_COURSE_DEL_TEAM);
//                msgDTO.setMsg();

                break;
        }

        if(nettyDTO != null){
            msgDTO.setFrom(nettyDTO.getName());
            msgDTO.setLevel(nettyDTO.getLevel());
            NettyService.sendClientMsg(ctxId, msgDTO);
        }

        for (String cCtxId : waitingUsers) {
            if (cCtxId.equals(ctxId)) {
                continue;
            }

            NettyService.sendClientMsg(cCtxId, msgDTO);

        }
    }

    public static void sendToTeamUser_Demo(String ctxId, String sourceIp, int nettyCode){
        MsgDTO msgDTO = new MsgDTO();
        int team = 0;
        NettyDTO nettyDTO = null;
        if(ctxId != null && ctxId.trim().isEmpty()){
            nettyDTO = NettyService.getNettyDTO(ctxId + "-" + sourceIp);
        }

        if(ctxId != null && ctxId.trim().isEmpty()){
            nettyDTO = NettyService.getNettyDTO(ctxId + "-" + sourceIp);
            for(int teamKey : teamUsers.keySet()){
                if(teamUsers.get(teamKey).get(ctxId) != null){
                    team = teamKey;
                    break;
                }
            }
        }

        switch (nettyCode){
            case NettyCode.TEAM_WAITING_WAIT:   //1
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT);
//                msgDTO.setMsg();
                break;
            case NettyCode.TEAM_WAITING_WAIT_TIMEOUT: //2
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT_TIMEOUT);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_JOIN:   //3
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_JOIN_FAIL:  //4
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN_FAIL);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_COACH_DISPATCH:     //5
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL:    //6
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_WAITING_NEXT:   //7
                msgDTO.setCmd(NettyCode.TEAM_WAITING_NEXT);
//                msgDTO.setMsg();
                msgDTO.setTeam(team);

                break;
            case NettyCode.TEAM_COURSE_STEP_WAITING:    //8
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_WAITING);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_STEP_STARTING:   //9
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_STARTING);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_STEP_FINISH: //10
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_FINISH);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_ALL_FINISH:  //11
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FINISH);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_ALL_FAIL:    //12
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FAIL);
//                msgDTO.setMsg();

                break;
            case NettyCode.TEAM_COURSE_DEL_TEAM:    //13
                msgDTO.setCmd(NettyCode.TEAM_COURSE_DEL_TEAM);
//                msgDTO.setMsg();

                break;
        }

        if(nettyDTO != null){
            msgDTO.setFrom(nettyDTO.getName());
            msgDTO.setLevel(nettyDTO.getLevel());
            NettyService.sendClientMsg(ctxId + "-" + sourceIp, msgDTO);
        }

        for (String cCtxId : waitingUsers) {
            if (cCtxId.equals(ctxId + "-" + sourceIp)) {
                continue;
            }

            NettyService.sendClientMsg(cCtxId, msgDTO);

        }
    }



}
