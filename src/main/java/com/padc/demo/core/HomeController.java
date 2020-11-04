package com.padc.demo.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/forsider/menu_deltager")
    public String show_menu_deltager(){
        return "/forsider/menu_deltager";
    }
}
