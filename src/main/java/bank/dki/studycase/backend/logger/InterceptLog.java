package bank.dki.studycase.backend.logger;

import bank.dki.studycase.backend.service.LoggingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InterceptLog implements HandlerInterceptor {
    private final LoggingService loggingService;

    @Autowired
    public InterceptLog(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals(HttpMethod.GET.name())
                || request.getMethod().equals(HttpMethod.DELETE.name())
                || request.getMethod().equals(HttpMethod.PUT.name()))    {
            loggingService.displayReq(request,null);
        }
        return true;
    }
}
