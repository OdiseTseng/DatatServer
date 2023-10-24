package tw.intelegence.ncsist.sstp.utils.text;

public class ServerCode {

    //SESSION CODE
    public static final int SESSION_NOT_EXIST = 1; //Session doesn't exist or has expired
    public static String SESSION_1 = "SESSION_NOT_EXIST";
    public static final int SESSION_HAS_EXPIRED = 2; //Session has expired exist or has expired
    public static String SESSION_2 = "SESSION_HAS_EXPIRED";
    public static final int SESSION_ACTIVE = 3; //Session is still actived exist or has expired
    public static String SESSION_3 = "SESSION_ACTIVE";



    //INFO
    public static final String INFO = "INFO";
    public static final String INFO_SERVER_CONNECTED = "INFO_SERVER_CONNECTED";
    public static final String INFO_CLIENT_CONNECTED = "INFO_CLIENT_CONNECTED";
    public static final int INFO_LOGIN_FAIL = 800001;
    public static final String INFO_800001 = "INFO_LOGIN_FAIL";

    public static final int INFO_LOGIN_SUCCESS = 800002;
    public static final String INFO_800002 = "INFO_LOGIN_SUCCESS";
    public static final String INFO_LOGIN_REMIND = "INFO_LOGIN_REMIND";
    public static final String INFO_LOGOUT_REMIND = "INFO_LOGOUT_REMIND";
    public static final String INFO_SESSION_TIMEOUT = "INFO_SESSION_TIMEOUT";
}
