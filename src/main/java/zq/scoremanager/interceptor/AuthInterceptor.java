package zq.scoremanager.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import zq.scoremanager.bean.User;
import zq.scoremanager.local.Locals;

public class AuthInterceptor implements HandlerInterceptor {

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.startsWith("/api")) return true;
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            if (uri.equals("/api/login")) return true;
            response.getWriter().write("Not Authorized!");
            return false;
        }
        if (matcher.match("/api/teacher/**", uri)) {
            if (user.getRole() != 1) {
                response.getWriter().write("Not Authorized!");
                return false;
            }
        }
        if (matcher.match("/api/student/**", uri)) {
            if (((User) request.getSession().getAttribute("user")).getRole() != 2) {
                response.getWriter().write("Not Authorized!");
                return false;
            }
        }
        if (matcher.match("/api/user/**", uri)) {
            if (((User) request.getSession().getAttribute("user")).getRole() != 0) {
                response.getWriter().write("Not Authorized!");
                return false;
            }
        }
        return true;
    }

}
