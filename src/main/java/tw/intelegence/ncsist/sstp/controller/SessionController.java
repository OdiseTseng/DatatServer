package tw.intelegence.ncsist.sstp.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.intelegence.ncsist.sstp.utils.text.ServerCode;

@Controller
public class SessionController {

    @Operation(summary = "session check", description = "檢查使用者Session存在否")
    @GetMapping("/checkSession")
    @ResponseBody
//    public String checkSession(HttpServletRequest request) {
    public int checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Pass 'false' to prevent creating a new session if one doesn't exist

        if (session != null && !session.isNew()) {
            // HttpSession exists and is not new, which means it's still valid
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
                return ServerCode.SESSION_ACTIVED;
            }
        } else {
            // HttpSession doesn't exist or is new (expired)
            return ServerCode.SESSION_DOESNT_EXIST;
        }
    }
}
