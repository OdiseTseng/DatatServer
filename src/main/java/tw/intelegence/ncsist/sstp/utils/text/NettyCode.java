package tw.intelegence.ncsist.sstp.utils.text;

public class NettyCode {




    //CMD NORMAL
    public static final int CMD_NORMAL = 89;
    public static  final int CMD_NORMAL_MSG = 890001;
    public static final String CMD_890001 = "CMD_NORMAL_MSG";

    public static  final int CMD_NORMAL_OTHER_MSG = 890002;
    public static final String CMD_890002 = "CMD_NORMAL_OTHER_MSG";

    //CMD
    public static final int CMD = 90;
    public static final int CMD_CONNECT = 900001;
    public static final String CMD_900001 = "CMD_CONNECT";
    public static final int CMD_DISCONNECT = 900002;
    public static final String CMD_900002 = "CMD_DISCONNECT";
    public static  final int CMD_LOGIN = 900003;
    public static final String CMD_900003 = "CMD_LOGIN";
    public static final int CMD_LOGOUT = 900004;
    public static final String CMD_900004 = "CMD_LOGOUT";

    public static final int CMD_OTHER_CONNECT = 900005;
    public static final String CMD_900005 = "CMD_OTHER_CONNECT";
    public static final int CMD_OTHER_DISCONNECT = 900006;
    public static final String CMD_900006 = "CMD_OTHER_DISCONNECT";
    public static final int CMD_OTHER_LOGIN = 900007;
    public static final String CMD_900007 = "CMD_OTHER_LOGIN";
    public static final int CMD_OTHER_LOGOUT = 900008;
    public static final String CMD_900008 = "CMD_OTHER_LOGOUT";


    //TEAM  -------------------------------------------------------------------------------
    public static final int TEAM = 91;
    public static final int TEAM_WAITING_UPDATE = 910000;
    public static final String TEAM_910000 = "TEAM_WAITING_UPDATE";

    public static final int TEAM_WAITING_WAIT = 910001;
    public static final String TEAM_910001 = "TEAM_WAITING_WAIT";

    public static  final int TEAM_WAITING_WAIT_TIMEOUT = 910002;
    public static final String TEAM_910002 = "TEAM_WAITING_WAIT_TIMEOUT";

    public static  final int TEAM_WAITING_JOIN = 910003;
    public static final String TEAM_910003 = "TEAM_WAITING_JOIN";

    public static  final int TEAM_WAITING_JOIN_FAIL = 910004;
    public static final String TEAM_910004 = "TEAM_WAITING_JOIN_FAIL";

    public static  final int TEAM_WAITING_OTHER_JOIN = 910005;
    public static final String TEAM_910005 = "TEAM_WAITING_OTHER_JOIN";

    //------------------------------

    public static  final int TEAM_WAITING_COACH_GET_ALL = 910101;
    public static final String TEAM_910101 = "TEAM_WAITING_COACH_GET_ALL";

    public static  final int TEAM_WAITING_COACH_DISPATCH = 910102;
    public static final String TEAM_910102 = "TEAM_WAITING_COACH_DISPATCH";

    public static  final int TEAM_WAITING_COACH_DISPATCH_FAIL = 910103;
    public static final String TEAM_910103 = "TEAM_WAITING_COACH_DISPATCH_FAIL";

    public static  final int TEAM_WAITING_NEXT = 910104;
    public static final String TEAM_910104 = "TEAM_WAITING_NEXT";

    public static final int TEAM_WAITING_COACH_ADD_TEAM = 910105;
    public static final String TEAM_910105 = "TEAM_WAITING_COACH_ADD_TEAM";

    public static final int TEAM_WAITING_COACH_UPD_TEAM = 910106;
    public static final String TEAM_910106 = "TEAM_WAITING_COACH_UPD_TEAM";

    public static final int TEAM_WAITING_COACH_UPD_ROLE = 910107;
    public static final String TEAM_910107 = "TEAM_WAITING_COACH_UPD_ROLE";


    //------------------------------
    public static  final int TEAM_COURSE_STEP_WAITING = 910201;
    public static final String TEAM_910201 = "TEAM_COURSE_STEP_WAITING";

    public static  final int TEAM_COURSE_STEP_STARTING = 910202;
    public static final String TEAM_910202 = "TEAM_COURSE_STEP_STARTING";

    public static  final int TEAM_COURSE_STEP_FINISH = 910203;
    public static final String TEAM_910203 = "TEAM_COURSE_STEP_FINISH";

    public static  final int TEAM_COURSE_ALL_FINISH = 910204;
    public static final String TEAM_910204 = "TEAM_COURSE_ALL_FINISH";

    public static  final int TEAM_COURSE_ALL_FAIL = 910205;
    public static final String TEAM_910205 = "TEAM_COURSE_ALL_FAIL";

    //------------------------------
    public static  final int TEAM_COURSE_DEL_TEAM = 910301;
    public static final String TEAM_910301 = "TEAM_COURSE_DEL_TEAM";
}
