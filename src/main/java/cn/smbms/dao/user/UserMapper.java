package cn.smbms.dao.user;

import java.sql.Connection;
import java.util.List;

import cn.smbms.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
	/**
	 * 查询用户列表(参数：对象入参)
	 * @return
	 */

	//登录
	public User login(@Param("userCode") String userCode, @Param("userPassword") String userPassword);

	//通过条件查询-userList
	public List<User> getUserList(@Param("userName")String userName,@Param("userRole")int userRole,@Param("currentPageNo")int currentPageNo,@Param("pageSize") int pageSize);

	//通过userId获取user
	public User getUserById(String id);

	// 通过条件查询-用户表记录数
	public int getUserCount(@Param("userName")String userName,@Param("userRole")int userRole);

	// 增加
	public int add(User user);

	//修改
	public int update(User user);

	//删除
	public int delete(int id);
}
