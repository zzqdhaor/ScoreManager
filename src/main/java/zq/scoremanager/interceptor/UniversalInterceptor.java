package zq.scoremanager.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import zq.scoremanager.local.Locals;

public class UniversalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Locals.servletRequest.set(request);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
