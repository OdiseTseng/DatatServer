package tw.intelegence.ncsist.sstp.netty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
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
    private Map<Integer, HashMap<String, Integer>> teamUsers = new HashMap<>();

    private List<TeamDTO> teamDTOList = new ArrayList<>();

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

    public String getWaittingNameListStr(HashMap<String, NettyDTO> idNettyMaps){

        List<TeamDTO> teamDTOList = new ArrayList<>();
        TeamDTO teamDTO = new TeamDTO();
        for (String userCtxId : waitingUsers){
            NettyDTO nettyDTO = idNettyMaps.get(userCtxId);
            teamDTO.setCtxId(userCtxId);
            teamDTO.setTeam(0);
            teamDTO.setRole(0);
            teamDTO.setName(nettyDTO.getName());

            teamDTOList.add(teamDTO);

            teamDTO = new TeamDTO();
        }

        return DTOParser.parseDTOsToString(teamDTOList.toArray());
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

    public String delAllWaitingUser(){
        String message = "已清空所有使用者";
        waitingUsers = new ArrayList<>();

        return message;
    }

    public String addTeam(String ctxId, int teamNumber, String name, int role){

        String message;

        HashMap<String, Integer> list;

        if(teamUsers.get(teamNumber) == null){
            list = new HashMap<>();
//            list.put(ctxId, 1);
            list.put(ctxId, role);

            teamUsers.put(teamNumber, list);
            message = delWaitingUser(ctxId, name);
            message += ", 新增團隊(" + teamNumber + ")並加入團隊名單成功";
        }else{
            list = teamUsers.get(teamNumber);
            if(list.containsKey(ctxId)){
                message = "已存在於團隊名單中";
            }else{
//                list.put(ctxId, list.size() + 1);
                list.put(ctxId, role);
                message = delWaitingUser(ctxId, name);
                message += ", 加入團隊(" + teamNumber + ")名單成功";
            }
        }

        return message;
    }

    public String getTeamListStr(){

        if(teamDTOList.isEmpty()){
            TeamDTO teamDTO = new TeamDTO();
            for (int teamKey : teamUsers.keySet()){
                Map<String, Integer> teamUser = teamUsers.get(teamKey);
                for(String ctxIdKey : teamUser.keySet()){
                    String name = waitingUserIdNames.get(ctxIdKey);
                    teamDTO.setCtxId(ctxIdKey);
                    teamDTO.setTeam(teamKey);
                    teamDTO.setRole(0);
                    teamDTO.setName(name);

                    teamDTOList.add(teamDTO);

                    teamDTO = new TeamDTO();
                }

            }
        }

        return DTOParser.parseDTOsToString(teamDTOList.toArray());
    }

    public int delAllTeam(){
        String message = "已清空所有團隊";
        teamUsers = new HashMap<>();
        teamDTOList.clear();

//        sendToTeamUser(ctxId, NettyCode.TEAM_WAITING_COACH_DISPATCH);

        return NettyCode.TEAM_WAITING_COACH_DISPATCH;
    }

    public int reRangeTeam(Map<Integer, HashMap<String, Integer>> newTeamUsers){

        teamUsers = newTeamUsers;

        return NettyCode.TEAM_WAITING_COACH_DISPATCH;
    }

    public void updateTeam(String msg){
        TeamDTO[] teamDTOs = (TeamDTO[])DTOParser.parseStringToDTO(msg, TeamDTO[].class);

        if(teamDTOs != null){
            for (TeamDTO teamDTO: teamDTOs){
                int team = teamDTO.getTeam();
                int role = teamDTO.getRole();
                String ctxId = teamDTO.getCtxId();
                String name = teamDTO.getName();
//            boolean findKey = false;
//            for(int key :teamUsers.keySet()){
//
//            }
                HashMap<String, Integer> user;
                if(teamUsers.containsKey(team)){
                    user = teamUsers.get(team);
                    if(team != 0){
                        if(user.containsKey(ctxId)){
                            user.replace(ctxId, role);
                        }else {
                            user.put(ctxId, role);
                        }
                    }else{
                        user.remove(ctxId);
                    }

                }else{
                    user = new HashMap<>();
                    user.put(ctxId, role);
                    teamUsers.put(team, user);
                }
            }
        }
    }

    public MsgDTO treatMsgDTO(int cmd, String sourceCtxId, String from, String msg){
        System.out.println("team treatMsgDTO : cmd= " + cmd + " sourceCtxId= " + sourceCtxId+ " from= " + from + " msg= " + msg);


        MsgDTO msgDTO = new MsgDTO();
        switch(cmd){
            case NettyCode.TEAM_WAITING_UPDATE:                   //00
                msgDTO.setCmd(NettyCode.TEAM_WAITING_UPDATE);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT:                   //01
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_WAIT_TIMEOUT:           //02
                msgDTO.setCmd(NettyCode.TEAM_WAITING_WAIT_TIMEOUT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN:                   //03
                msg = addWaitingUser(sourceCtxId, from);
                msgDTO.setCmd(NettyCode.TEAM_WAITING_OTHER_JOIN);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_JOIN_FAIL:              //04
                msgDTO.setCmd(NettyCode.TEAM_WAITING_JOIN_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_GET_ALL:          //05
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_GET_ALL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH:         //06
                updateTeam(msg);
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL:         //07
                msgDTO.setCmd(NettyCode.TEAM_WAITING_COACH_DISPATCH_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_WAITING_NEXT:                   //07

                msgDTO.setCmd(NettyCode.TEAM_WAITING_NEXT);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_WAITING:            //08
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_WAITING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_STARTING:           //09
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_STARTING);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_STEP_FINISH:             //10
                msgDTO.setCmd(NettyCode.TEAM_COURSE_STEP_FINISH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FINISH:              //11
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FINISH);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_ALL_FAIL:                //12
                msgDTO.setCmd(NettyCode.TEAM_COURSE_ALL_FAIL);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;

            case NettyCode.TEAM_COURSE_DEL_TEAM:                //13
                msgDTO.setCmd(NettyCode.TEAM_COURSE_DEL_TEAM);
                msgDTO.setFrom(from);
                msgDTO.setMsg(msg);
                break;
        }

        return msgDTO;
    }
}
