package com.jk.sys.controller;

import com.jk.sys.domain.User;
import com.jk.sys.common.utils.PageUtils;
import com.jk.sys.common.utils.Query;
import com.jk.sys.common.utils.UserQuery;
import com.jk.sys.domain.RoleDO;
import com.jk.sys.domain.UserDO;
import com.jk.sys.domain.UserType;
import com.jk.sys.service.RoleService;
import com.jk.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController{
    private String prefix="sys/user"  ;

    @Autowired
    UserService userService;
    @Autowired
	RoleService roleService;

    @GetMapping("/")
    String user(Model model) {
        return prefix + "/user";
    }

    @GetMapping("/list")
    @ResponseBody
    PageUtils list(@RequestParam Map<String, Object> params) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	PageUtils pageUtil = null;
    	auth.getAuthorities().stream().forEach(authori -> System.out.println(authori));
    	String username = ((User)auth.getPrincipal()).getAccount();
    		//展示所有用户
    		// 查询列表数据
    	if (username.equals("admin"))
    		params.put("userType1", "ADMIN");
		params.put("username", username);
        UserQuery query = new UserQuery(params);
        List<UserDO> sysUserList = userService.list(query);
        int total = userService.count(query);
        pageUtil = new PageUtils(sysUserList, total);
    	
    	
        
//        	System.out.println(pageUtil.getRows());
        return pageUtil;
    }
    
    
    @GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}

}
