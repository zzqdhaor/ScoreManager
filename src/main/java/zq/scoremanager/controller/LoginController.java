package zq.scoremanager.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zq.scoremanager.Result;
import zq.scoremanager.bean.User;
import zq.scoremanager.local.Locals;
import zq.scoremanager.mapper.UserMapper;
import zq.scoremanager.security.LoginManager;
import zq.scoremanager.util.SecurityUtil;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private UserMapper mapper;

    @PostMapping("/login")
    public Result login(String no, String password) {
        User user = loginManager.tryLogin(no, password);
        if (user == null) {
            return Result.error("Login error!", null);
        }
        return Result.success(user.getRole() + "/" + user.getName());
    }

    @PostMapping("/login/change-password")
    public Result changePassword(String oldPass, String newPass) {
        User user = (User) Locals.servletRequest.get().getSession().getAttribute("user");
        if (user == null) {
            return Result.error("Not Login!", null);
        }
        int changed = mapper.changePasswordNotPrivilege(user.getNo(), SecurityUtil.shaEncode(oldPass), SecurityUtil.shaEncode(newPass));
        if (changed == 1) return Result.success(null);
        return Result.error("Error!", null);
    }

}