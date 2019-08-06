package cn.smbms.servlce;

import java.sql.Connection;
import java.util.List;
import javax.annotation.Resource;

import cn.smbms.dao.user.RoleDao;
import cn.smbms.pojo.Role;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleDao roleDao;
	
	@Override
	public List<Role> getRoleList() {
		List<Role> roleList = null;
			roleList = roleDao.getRoleList();

		return roleList;
	}
	
}
