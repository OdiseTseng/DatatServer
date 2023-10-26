package tw.intelegence.ncsist.sstp.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.intelegence.ncsist.sstp.session.SessionContext;

@Controller
public class SessionController {

    @Operation(summary = "session check", description = "檢查使用者Session存在否")
    @GetMapping("/checkSession")
    @ResponseBody
    public int checkSession(HttpServletRequest request) {

        String sessionId = request.getRequestedSessionId();
        System.out.println("sessionId : " + sessionId);

        sessionId = sessionId.split("=")[1];

        System.out.println("sessionId : " + sessionId);

        SessionContext sessionContext = SessionContext.getInstance();

        return sessionContext.checkSessionExpired(sessionId);
    }
}
