package zq.scoremanager.security;

import jakarta.servlet.http.HttpServletRequest;
import zq.scoremanager.bean.User;

public interface LoginManager {

    User tryLogin(String no, String password);


    String getName();
}
