package com.padc.demo.core;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "velkommen";
    }

    @GetMapping("/velkommen")
    public String show_menu_deltager(){
        return "velkommen";
    }


}
