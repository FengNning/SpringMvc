package Test;


import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.servlce.RoleService;
import cn.smbms.servlce.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.ws.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SpringMybatis {
    //logger
    private Logger logger = Logger.getLogger(SpringMybatis.class);

    //模糊查询
    @Test
    public void Text1(){

        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        List<Role> userList =new ArrayList<>();
      RoleService userService= (RoleService) context.getBean("userService");

      for(Role u:userList){
          logger.debug("姓名"+u.getId());
      }
    }


}
