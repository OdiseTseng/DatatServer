package tw.intelegence.ncsist.sstp.session;

import jakarta.servlet.http.HttpSession;
import tw.intelegence.ncsist.sstp.utils.text.ServerCode;

import java.util.HashMap;

public class SessionContext {
    private static SessionContext instance;

    private HashMap<String, HttpSession> sessionMap;
    private HashMap<String, Long> sessionTimeMap;
    private SessionContext(){

        sessionMap = new HashMap<>();
        sessionTimeMap = new HashMap<>();
    }

    public static SessionContext getInstance(){
        if (instance == null){
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session){
        if(session != null){
            sessionMap.put(session.getId(), session);
            sessionTimeMap.put(session.getId(), System.currentTimeMillis());
        }
    }

    public synchronized void delSession(HttpSession session){
        if (session != null){
            sessionMap.remove(session.getId());
            sessionTimeMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionId){
        if(sessionId == null){
            return null;
        }

        return sessionMap.get(sessionId);
    }

    public synchronized int checkSessionExpired(String sessionId){
        HttpSession session = null;
        if(sessionId != null && sessionMap.containsKey(sessionId)){
            session = sessionMap.get(sessionId);

            if(session == null){
                return ServerCode.SESSION_NOT_EXIST;
            }
        }

        long currentTime = System.currentTimeMillis();
        long lastAccessTime = session.getLastAccessedTime();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println("currentTime : " + currentTime);
        System.out.println("lastAccessTime : " + lastAccessTime);
        System.out.println("maxInactiveInterval : " + maxInactiveInterval * 1000);

        if (currentTime - lastAccessTime > maxInactiveInterval * 1000) {
            // HttpSession has expired
            return ServerCode.SESSION_HAS_EXPIRED;
        } else {
            // HttpSession is still active
            return ServerCode.SESSION_ACTIVE;
        }
    }
}
