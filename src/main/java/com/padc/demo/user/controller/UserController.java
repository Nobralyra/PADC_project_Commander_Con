package com.padc.demo.user.controller;

import com.padc.demo.core.IService;
import com.padc.demo.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {

    private final IService<User> iService;

    public UserController(IService<User> iService) {
        this.iService = iService;
    }

    @GetMapping("/bruger/opret_bruger")
    public String showCreateUser(User user, Model model){
        String password2 = "";
        model.addAttribute("user", user);
        model.addAttribute("password2", password2);
        return "/bruger/opret_bruger";
    }

    @PostMapping("/bruger/opret_bruger")
    public String createUser(@Valid User user, BindingResult b, Model model){
        if(b.hasErrors()){
            model.addAttribute("bindingResult", b);
            return "/bruger/opret_bruger";
        }
        iService.save(user);
        //vi mangler forsiden, så denne fører ikke til noget endnu
        return "redirect:/forsider/menu_deltager";
    }

}
