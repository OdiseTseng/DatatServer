package tw.intelegence.ncsist.sstp.netty.service;

import lombok.Getter;
import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.model.TeamDTO;
import tw.intelegence.ncsist.sstp.utils.func.DTOParser;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NettyTeamService {

    @Getter
    private List<String> waitingUsers = new ArrayList<>();

    @Getter
    private Map<String, String> waitingUserIdNames = new HashMap<>();

    //          team              ctxId     role
//    private Map<Integer, HashMap<String, Integer>> teamUsers = new HashMap<>();

//    private List<TeamDTO> teamDTOList = new ArrayList<>();
    private Map<String, TeamDTO> teamDTOMap = new HashMap<>();

    public static String createTeam(){

        return "";
    }

    public String addWaitingUser(String ctxId, String name){

        String message = "已加入名單";

        if(waitingUsers.contains(ctxId)){
            message = "已存在於名單內";
        }else{
            waitingUsers.add(ctxId);
            waitingUserIdNames.put(ctxId, name);
        }

        return message;
    }
    public String delAllWaitingUser(){
        String message = "已清空所有使用者";
        waitingUsers = new ArrayList<>();

        return message;
    }

    public String delWaitingUser(String ctxId, String name){

        String message = "未存在於等待名單中";

        if(waitingUsers.contains(ctxId)){
            waitingUsers.remove(ctxId);
            waitingUserIdNames.remove(ctxId, name);
            message = "已從等待名單中刪除";
        }

        return message;
    }

    public String getTeamListStr(HashMap<String, NettyDTO> idNettyMaps){

//        List<TeamDTO> teamDTOList = new ArrayList<>();
//        TeamDTO teamDTO = new TeamDTO();
        for (String userCtxId : waitingUsers){

//            if(teamDTOMap.get(userCtxId) == null){
//                NettyDTO nettyDTO = idNettyMaps.get(userCtxId);
//                teamDTO.setCtxId(userCtxId);
//                teamDTO.setTeam(0);
//                teamDTO.setRole(0);
//                teamDTO.setName(nettyDTO.getName());
//
////                teamDTOList.add(teamDTO);
//                teamDTOMap.put(userCtxId, teamDTO);
//
//                teamDTO = new TeamDTO();
//            }
            System.out.println("userCtxId : " + userCtxId);
            if(teamDTOMap.get(userCtxId) == null){
                NettyDTO nettyDTO = idNettyMaps.get(userCtxId);
                String name = nettyDTO.getName();

                System.out.println("nettyDTO : " + nettyDTO);

                addTeam(userCtxId, 0 , 0 , name);
            }

        }

//        return DTOParser.parseDTOsToString(teamDTOList.toArray());
        return DTOParser.parseDTOsToString(teamDTOMap.values().toArray());
    }

    public String getTeamListStr(){

        return DTOParser.parseDTOsToString(teamDTOMap.values().toArray());
    }

    public String addTeam(String ctxId, int teamNumber, int role, String name){

        String message ="";
        TeamDTO teamDTO;
        if(teamDTOMap.containsKey(ctxId)){
            teamDTO = teamDTOMap.get(ctxId);
            teamDTO.setTeam(teamNumber);
            teamDTO.setRole(role);

            teamDTOMap.replace(ctxId, teamDTO);
            message += "加入團隊(" + teamNumber + ")名單成功";
        }else{
            teamDTO = new TeamDTO();
            teamDTO.setCtxId(ctxId);
            teamDTO.setTeam(teamNumber);
            teamDTO.setRole(role);
            teamDTO.setName(name);
            teamDTOMap.put(ctxId, teamDTO);
            message += "新增團隊(" + teamNumber + ")並加入團隊名單成功";
        }


//        HashMap<String, Integer> list;

//        if(teamUsers.get(teamNumber) == null){
//            list = new HashMap<>();
////            list.put(ctxId, 1);
//            list.put(ctxId, role);
//
//            teamUsers.put(teamNumber, list);
//            message = delWaitingUser(ctxId, name);
//            message += ", 新增團隊(" + teamNumber + ")並加入團隊名單成功";
//        }else{
//            list = teamUsers.get(teamNumber);
//            if(list.containsKey(ctxId)){
//                message = "已存在於團隊名單中";
//            }else{
////                list.put(ctxId, list.size() + 1);
//                list.put(ctxId, role);
//                message = delWaitingUser(ctxId, name);
//                message += ", 加入團隊(" + teamNumber + ")名單成功";
//            }
//        }

        return message;
    }

//    public String getTeamListStr(){
//
//        if(teamDTOList.isEmpty()){
//            TeamDTO teamDTO = new TeamDTO();
//            for (int teamKey : teamUsers.keySet()){
//                Map<String, Integer> teamUser = teamUsers.get(teamKey);
//                for(String ctxIdKey : teamUser.keySet()){
//                    String name = waitingUserIdNames.get(ctxIdKey);
//                    teamDTO.setCtxId(ctxIdKey);
//                    teamDTO.setTeam(teamKey);
//                    teamDTO.setRole(0);
//                    teamDTO.setName(name);
//
//                    teamDTOList.add(teamDTO);
//
//                    teamDTO = new TeamDTO();
//                }
//
//            }
//        }
//
//        return DTOParser.parseDTOsToString(teamDTOList.toArray());
//    }

    public int delAllTeam(){
        String message = "已清空所有團隊";
//        teamUsers = new HashMap<>();
//        teamDTOList.clear();
        teamDTOMap.clear();

//        sendToTeamUser(ctxId, NettyCode.TEAM_WAITING_COACH_DISPATCH);

        return NettyCode.TEAM_WAITING_COACH_DISPATCH;
    }

//    public int reRangeTeam(Map<Integer, HashMap<String, Integer>> newTeamUsers){
//
//        teamUsers = newTeamUsers;
//
//        return NettyCode.TEAM_WAITING_COACH_DISPATCH;
//    }

    public void updateTeamList(String msg){
        TeamDTO[] teamDTOs = (TeamDTO[])DTOParser.parseStringToDTO(msg, TeamDTO[].class);

        if(teamDTOs != null){
            for (TeamDTO sourceTeamDTO: teamDTOs){
                String ctxId = sourceTeamDTO.getCtxId();
                int team = sourceTeamDTO.getTeam();
                int role = sourceTeamDTO.getRole();
                String name = sourceTeamDTO.getName();
                
                TeamDTO targetTeamDTO = teamDTOMap.get(ctxId);
                targetTeamDTO.setTeam(team);
                targetTeamDTO.setRole(role);



//            boolean findKey = false;
//            for(int key :teamUsers.keySet()){
//
//            }
//                HashMap<String, Integer> user;
//                if(teamUsers.containsKey(team)){
//                    user = teamUsers.get(team);
//                    if(team != 0){
//                        if(user.containsKey(ctxId)){
//                            user.replace(ctxId, role);
//                        }else {
//                            user.put(ctxId, role);
//                        }
//                    }else{
//                        user.remove(ctxId);
//                    }
//
//                }else{
//                    user = new HashMap<>();
//                    user.put(ctxId, role);
//                    teamUsers.put(team, user);
//                }
            }
        }
    }

    public MsgDTO treatMsgDTO(int cmd, String sourceCtxId, String from, String msg){
        System.out.println("team treatMsgDTO : cmd= " + cmd + " sourceCtxId= " + sourceCtxId+ " from= " + from + " msg= " + msg);


        MsgDTO msgDTO = new MsgDTO();
        switch(cmd){
            case NettyCode.TEAM_WAITING_UPDATE:
                msgDTO.setCmd(NettyCode.TEAM_WAITING_UPDATE);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT:
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT_TIMEOUT:
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT_TIMEOUT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN:
                msg = addWaitingUser(sourceCtxId, from);
                msgDTO.setCmd(NettyCode.TEAM_WAITING_OTHER_JOIN);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN_FAIL:
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_GET_ALL:
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_GET_ALL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH:
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL:
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_ADD_TEAM:
            case NettyCode.TEAM_WAITING_COACH_UPD_TEAM:
            case NettyCode.TEAM_WAITING_COACH_UPD_ROLE:
                updateTeamList(msg);
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH);
                msgDTO.setFrom(from);
                msgDTO.setMsg("");
                break;

            case NettyCode.TEAM_WAITING_NEXT:
                updateTeamList(msg);
                msg = getTeamListStr();
                msgDTO.setCmd(NettyCode.TEAM_WAITING_NEXT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_WAITING:
//                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_WAITING);
                //有人傳出等待訊息
                System.out.println("//有人傳出等待訊息");
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_STARTING:
//                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_STARTING);
                System.out.println(" //學員1開始考試，讓其他人等");
                //學員1開始考試，讓其他人等
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_WAITING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_FINISH:
//                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_FINISH);
                //學員1完成作業內容，讓其他人進去考試
                System.out.println("//學員1完成作業內容，讓其他人進去考試");
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_STARTING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FINISH:
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FINISH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FAIL:
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_DEL_TEAM:
                msgDTO.setCmd(NettyCode.TEAM_COURSE_DEL_TEAM);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;
        }

        return msgDTO;
    }
}
