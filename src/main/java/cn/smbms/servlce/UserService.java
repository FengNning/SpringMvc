package cn.smbms.servlce;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {


    //登录
    public User login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

    //通过条件查询-userList
    public List<User> getUserList(String userName,int userRole,int currentPageNo, int pageSize);

    //通过userId获取user
    public User getUserById(String id);

    // 通过条件查询-用户表记录数
    public int getUserCount(String queryUserName,int queryUserRole);

    // 增加
    public int add(User user);

    //修改
    public int update(User user);

    //删除
    public int delete(int id);
}
