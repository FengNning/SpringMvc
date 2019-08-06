package cn.smbms.servlce;

import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;



    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(int id) {
        return userMapper.delete(id);
    }

    @Override
    public User login(String userCode, String userPassword) {
        User user=null;
        user= (User) userMapper.login(userCode,userPassword);
        if(user!=null){
            if(!user.getUserPassword().equals(userPassword)){
                user=null;
            }
        }
       return user;
    }

    @Override
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) {
        return userMapper.getUserList(userName,userRole,currentPageNo,pageSize);
    }

    @Override
    public User getUserById(String id) {
        return null;
    }

    @Override
    public int getUserCount(String queryUserName, int queryUserRole) {
        return userMapper.getUserCount(queryUserName,queryUserRole);
    }
}
