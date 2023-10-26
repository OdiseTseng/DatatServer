package tw.intelegence.ncsist.sstp.session;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


@WebListener
public class SessionListener implements HttpSessionListener {

    private SessionContext ctx = SessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("sessionCreated : ");
//        HttpSessionListener.super.sessionCreated(se);
        ctx.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("sessionDestroyed : ");
//        HttpSessionListener.super.sessionDestroyed(se);
        ctx.delSession(se.getSession());
    }
}
