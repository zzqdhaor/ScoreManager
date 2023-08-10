package zq.scoremanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zq.scoremanager.bean.Page;
import zq.scoremanager.bean.User;
import zq.scoremanager.mapper.UserMapper;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    public Page<User> getUserPage(int pageNum) {
        List<User> users = mapper.getUsersByPage((pageNum - 1) * 10, 10);
        Page<User> page = new Page<>();
        page.setIndices(users);
        //判断是否整除,若不整除则页数加一
        int totalNum = mapper.getTotalNum();
        int totalPageNum = totalNum / 10;
        if (totalNum % 10 != 0) totalPageNum++;
        page.setPageNum(totalPageNum);
        return page;
    }

}
