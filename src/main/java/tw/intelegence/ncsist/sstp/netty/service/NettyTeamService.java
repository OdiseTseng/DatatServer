package tw.intelegence.ncsist.sstp.netty.service;

import tw.intelegence.ncsist.sstp.model.MsgDTO;
import tw.intelegence.ncsist.sstp.model.NettyDTO;
import tw.intelegence.ncsist.sstp.utils.text.NettyCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NettyTeamService {

    public List<String> waitingUsers = new ArrayList<>();

    public Map<Integer, HashMap<String, Integer>> teamUsers = new HashMap<>();

    public static String createTeam(){

        return "";
    }

    public String addWaitingUser(String ctxId){

        String message = "已加入名單";

        if(waitingUsers.contains(ctxId)){
            message = "已存在於名單內";
        }else{
            waitingUsers.add(ctxId);
        }

        return message;
    }

    public String delWaitingUser(String ctxId){

        String message = "未存在於等待名單中";

        if(waitingUsers.contains(ctxId)){
            waitingUsers.remove(ctxId);
            message = "已從等待名單中刪除";
        }

        return message;
    }

    public String delAllWaitingUser(){
        String message = "已清空所有使用者";
        waitingUsers = new ArrayList<>();

        return message;
    }

    public String addTeam(String ctxId, int teamNumber){

        String message;

        HashMap<String, Integer> list;

        if(teamUsers.get(teamNumber) == null){
            list = new HashMap<>();
            list.put(ctxId, 1);

            teamUsers.put(teamNumber, list);
            message = delWaitingUser(ctxId);
            message += ", 新增團隊(" + teamNumber + ")並加入團隊名單成功";
        }else{
            list = teamUsers.get(teamNumber);
            if(list.containsKey(ctxId)){
                message = "已存在於團隊名單中";
            }else{
                list.put(ctxId, list.size() + 1);
                message = delWaitingUser(ctxId);
                message += ", 加入團隊(" + teamNumber + ")名單成功";
            }
        }

        return message;
    }

    public int delAllTeam(){
        String message = "已清空所有團隊";
        teamUsers = new HashMap<>();

//        sendToTeamUser(ctxId, NettyCode.TEAM_WAITING_COACH_DISPATCH);

        return NettyCode.TEAM_WAITING_COACH_DISPATCH;
    }

    public int reRangeTeam(Map<Integer, HashMap<String, Integer>> newTeamUsers){

        teamUsers = newTeamUsers;

        return NettyCode.TEAM_WAITING_COACH_DISPATCH;
    }
}
