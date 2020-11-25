package com.padc.demo.core.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/sikkerhed/login")
    public String show_login_page(){
        return "/sikkerhed/login";
    }
}
