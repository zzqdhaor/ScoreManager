package zq.scoremanager.mapper;

import org.apache.ibatis.annotations.Mapper;
import zq.scoremanager.bean.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User getUserByUsernameAndPassword(String no, String password);

    List<User> getUsersByPage(int start, int count);

    void changePassword(String no, String password);
    void addUser(String no, String name, String password, int role);
    int getTotalNum();
    void deleteUserByNo(String no);
    int changePasswordNotPrivilege(String no, String oldPass, String newPass);
}
