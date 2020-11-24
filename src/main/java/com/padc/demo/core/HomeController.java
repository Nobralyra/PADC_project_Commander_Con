package com.padc.demo.core;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {

        // Get authenticated user name from SecurityContext
        SecurityContext context = SecurityContextHolder.getContext();

        model.addAttribute("message", "You are logged in as "
                + context.getAuthentication().getName());
        return "/test";//"index";
    }

    @GetMapping("/test")
    public String show_menu_deltager(){
        return "/test";
    }




    //disse to, hvis jeg ikek bruger configure()
/*
    @GetMapping("/")
    public String show_frontpage(){
        System.out.println("den første");
        return "/test";
    }

    @GetMapping("/test")
    public String show_menu_deltager(){
        return "/test";
    }
*/


    /* disse fungerer ikke - jeg kommer ikke ind
    //dette, hvis jeg redirecter i securityConfig, skal være login, hvis jeg ikek bruger configure
    @GetMapping("/login")
    public String show_login_page(){
        System.out.println("kommer jeg her");
        //skal være login, hvis jeg skal til post
        return "/login";}

    //den lader ikke til at have nogen betydning, når jeg bruger securityConfig
    @PostMapping("/login")
    public String login(){
        System.out.println("hvad med her");
        return "/test";
    }*/




}
