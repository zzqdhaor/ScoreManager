package zq.scoremanager.security;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import zq.scoremanager.bean.User;
import zq.scoremanager.local.Locals;
import zq.scoremanager.mapper.UserMapper;
import zq.scoremanager.util.SecurityUtil;

@Component
public class DefaultLoginManager implements LoginManager {

    @Autowired
    private UserMapper mapper;

    @Override
    public User tryLogin(String no, String password) {
        User user = mapper.getUserByUsernameAndPassword(no, SecurityUtil.shaEncode(password));
        HttpSession session = Locals.servletRequest.get().getSession(user != null);
        if (session != null) session.setAttribute("user", user);
        return user;
    }

    @Override
    public String getName() {
        return ((User) Locals.servletRequest.get().getAttribute("user")).getName();
    }

}
