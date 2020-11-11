package com.padc.demo.user.controller;

import com.padc.demo.core.IService;
import com.padc.demo.tournament.domain.Tournament;
import com.padc.demo.user.domain.User;
import javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

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

    //RequestParam giver bekræftelse af, at brugeren skriver den samme kode to gange
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
    /*user story 2: når man er lukket ind, kan man gå ind på sin profil.
     userId kommer fra login, men det har vi ikke implementeret endnu.
     Så her bliver jeg efter oprettelsen sendt til en side, hvor jeg kan se
     den profil, jeg lige har oprettet.
     Så nu kan jeg ikke lave min profil-side, fordi vi skulle bruge
     brugerid fra login.
    */
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
