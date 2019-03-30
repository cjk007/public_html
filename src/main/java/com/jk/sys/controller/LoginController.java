package com.jk.sys.controller;

//import com.jk.room.entry.User;
import com.jk.sys.common.domain.Tree;
import com.jk.sys.domain.MenuDO;
import com.jk.sys.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    //@Autowired
    //MenuService menuService;

    @RequestMapping(value = "index")
    String index(Model model) {//, Principal principal
       //UsernamePasswordAuthenticationToken token= (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
       // User user = (User)token.getPrincipal();
        
		/*List<Tree<MenuDO>> menus = menuService.listMenuTree(1L);//getUserId()
        model.addAttribute("menus", menus);
        */
		
		//model.addAttribute("name", user.getName());//principal.getName()
        //model.addAttribute("username", user.getAccount());
        return "index";//b/5/user， redirect:test/book55
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }
    
    @GetMapping("/")
    String defaultPage() {
        return "redirect:/signin";
    }
    
    @GetMapping("signin")
    public String signin() {
        return "signin/signin";
    }
}
