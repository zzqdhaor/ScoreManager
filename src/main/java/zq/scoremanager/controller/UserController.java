package zq.scoremanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zq.scoremanager.Result;
import zq.scoremanager.bean.Page;
import zq.scoremanager.bean.User;
import zq.scoremanager.mapper.UserMapper;
import zq.scoremanager.service.UserService;
import zq.scoremanager.util.SecurityUtil;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserService service;

    @PostMapping("/change-password")
    public Result changePassword(String no, String newPass) {
        mapper.changePassword(no, SecurityUtil.shaEncode(newPass));
        return Result.success(null);
    }

    @GetMapping("/get-users-by-page")
    @Cacheable(value = "users", key = "#pageNum")
    public Result getAllUsersByPage(int pageNum) {
        Page<User> page = service.getUserPage(pageNum);
        return Result.success(page);
    }

    @PostMapping("/add-new-user")
    @CacheEvict(value = "users", allEntries = true)
    public Result registerUser(String no, String name, int role) {
        mapper.addUser(no, name, SecurityUtil.shaEncode("12345"), role);
        return Result.success(null);
    }

    @PostMapping("/delete-user")
    @CacheEvict(value = "users", allEntries = true)
    public Result deleteUser(String no) {
        mapper.deleteUserByNo(no);
        return Result.success(null);
    }

}
