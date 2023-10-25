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

    public static  final int TEAM_WAITING_COACH_DISPATCH = 910005;
    public static final String TEAM_910005 = "TEAM_WAITING_COACH_DISPATCH";

    public static  final int TEAM_WAITING_COACH_DISPATCH_FAIL = 910006;
    public static final String TEAM_910006 = "TEAM_WAITING_COACH_DISPATCH_FAIL";

    public static  final int TEAM_WAITING_NEXT = 910007;
    public static final String TEAM_910007 = "TEAM_WAITING_NEXT";

    public static  final int TEAM_COURSE_STEP_WAITING = 910008;
    public static final String TEAM_910008 = "TEAM_COURSE_STEP_WAITING";

    public static  final int TEAM_COURSE_STEP_STARTING = 910009;
    public static final String TEAM_910009 = "TEAM_COURSE_STEP_STARTING";

    public static  final int TEAM_COURSE_STEP_FINISH = 910010;
    public static final String TEAM_910010 = "TEAM_COURSE_STEP_FINISH";

    public static  final int TEAM_COURSE_ALL_FINISH = 910011;
    public static final String TEAM_910011 = "TEAM_COURSE_ALL_FINISH";

    public static  final int TEAM_COURSE_ALL_FAIL = 910012;
    public static final String TEAM_910012 = "TEAM_COURSE_ALL_FAIL";

    public static  final int TEAM_COURSE_DEL_TEAM = 910013;
    public static final String TEAM_910013 = "TEAM_COURSE_DEL_TEAM";
}
