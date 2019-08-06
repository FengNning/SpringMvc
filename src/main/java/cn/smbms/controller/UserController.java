package cn.smbms.controller;



import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.servlce.RoleService;
import cn.smbms.servlce.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger=Logger.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = "/login.html")
    public String login(){
        logger.debug("UserController welcome SMBMS==================");
        return "login";
    }

    /*@RequestMapping(value = "/dologin.html",method = RequestMethod.POST)
    public String doLogin(@RequestParam String userCode,@RequestParam String userPassword){
        logger.debug("用户名"+userCode);
        logger.debug("密码"+userPassword);
        User user=userService.login(userCode,userPassword);
        if(null!=user){
            return "redirect:/user/main.html";
        }else{
            return "login";
        }
    }*/

    @RequestMapping(value = "/dologin.html",method = RequestMethod.POST)
    public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpSession session, HttpServletRequest request){
        logger.debug("用户名"+userCode);
        logger.debug("密码"+userPassword);
        User user=userService.login(userCode,userPassword);
        if(null!=user){
            session.setAttribute(Constants.USER_SESSION,user);
            return "redirect:/user/main.html";
        }else{
            request.setAttribute("error","用户名或者密码不正确");
            return "login";
        }
    }

    /*@RequestMapping(value = "/main.html")
    public String main(){
        return "frame";
    }*/


    @RequestMapping(value = "/main.html")
    public String main(HttpSession session){
        if(session.getAttribute(Constants.USER_SESSION)==null){
            return "redirect:/user/login.html";
        }
        return "frame";
    }

    @RequestMapping(value = "/userlist.html")
    public String getUserList(Model model,
                              @RequestParam(value = "queryname",required = false)String queryUserName,
                              @RequestParam(value = "queryUserRole",required = false) String queryUserRole,
                              @RequestParam(value = "pageIndex",required = false)String pageIndex) {

        logger.info("getUserList ====> queryUserName: " + queryUserName);
        logger.info("getUserList ====> queryUserRole: " + queryUserRole);
        logger.info("getUserList ====> pageIndex: " + pageIndex);

        int _queryUserRole = 0;
        List<User> userList = null;
        int pageSize = Constants.pageSize;

        int currentPageNo = 1;
        if (queryUserName == null) {
            queryUserName = "";
        }
        if (queryUserRole != null && !queryUserRole.equals("")) {
            _queryUserRole = Integer.parseInt(queryUserRole);
        }
        if (pageIndex != null) {

            try {
                currentPageNo = Integer.valueOf(pageIndex);
            }catch (NumberFormatException e){
                return "redirect:/user/syserror.html";
            }
        }

        int totalCount=userService.getUserCount(queryUserName,_queryUserRole);

        PageSupport page=new PageSupport();
        page.setCurrentPageNo(currentPageNo);
        page.setPageSize(pageSize);
        page.setTotalCount(totalCount);
        int totalPageCount=page.getTotalPageCount();
        if(currentPageNo<1){
            currentPageNo=1;
        }else if(currentPageNo>totalPageCount){
            currentPageNo=totalPageCount;
        }
        int num = (currentPageNo-1)*pageSize;
        userList = userService.getUserList(queryUserName,_queryUserRole,num,pageSize);
        model.addAttribute("userList", userList);
        List<Role> roleList = null;
        roleList=roleService.getRoleList();
        model.addAttribute("roleList",roleList);
        model.addAttribute("queryUserName",queryUserName);
        model.addAttribute("queryUserRole",queryUserRole);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("currentPageNo", currentPageNo);

        return "userlist";
    }




}
