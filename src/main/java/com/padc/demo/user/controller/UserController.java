package com.padc.demo.user.controller;

import com.padc.demo.core.IService;
import com.padc.demo.tournament.domain.Tournament;
import com.padc.demo.user.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Controller
public class UserController {

    private final IService<User> iService;

    public UserController(IService<User> iService) {
        this.iService = iService;
    }

    @GetMapping("/bruger/opret_bruger")
    public String showCreateUser(User user, Model model){
        model.addAttribute("user", user);
        return "/bruger/opret_bruger";
    }

    @PostMapping("/bruger/opret_bruger")
    public String createUser(@RequestParam String password2, @Valid User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "/bruger/opret_bruger";
        }
        if(!password2.equals(user.getPassword())){
            String kode_igen = "Skriv venligst den samme kode igen";
            model.addAttribute("kode_igen", kode_igen);
            return "/bruger/opret_bruger";
        }
        iService.save(user);
        long id = user.getUserId();
        return "redirect:/bruger/bruger_side/" + id;
    }

    @GetMapping("/bruger/bruger_side/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {

        try
        {
            User user = iService.findById(id);
            model.addAttribute("user", user);
            return "/bruger/bruger_side";
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            entityNotFoundException.printStackTrace(); // Goes to System.err
            entityNotFoundException.printStackTrace(System.out);
            return "/forsider/menu_deltager";
        }
    }

}
