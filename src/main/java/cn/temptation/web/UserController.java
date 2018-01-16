package cn.temptation.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import cn.temptation.dao.UserInfoMapper;
import cn.temptation.entity.UserInfo;

/**
 * ÓÃ»§¿ØÖÆÆ÷
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private UserInfoMapper userDao;

    @RequestMapping("/view")
    public String view() {
        return "main/login";
    }

    @RequestMapping("/indexview")
    public String index() {
        return "main/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(UserInfo model, HttpSession session) {
    	UserInfo user =userDao.selectByPrimaryKey(1);

        if (user == null || !user.getPassword().equals(model.getPassword())) {
            return new ModelAndView("redirect:/login.jsp");
        } else {
            session.setAttribute("user", user);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("index");
            return mav;
        }
    }
}