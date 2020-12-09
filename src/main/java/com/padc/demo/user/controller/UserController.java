package com.padc.demo.user.controller;

import com.padc.demo.core.security.Securitycontext;
import com.padc.demo.user.domain.User;
import com.padc.demo.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final Securitycontext securitycontext;

    public UserController(UserService userService, Securitycontext securitycontext) {
        this.userService = userService;
        this.securitycontext = securitycontext;
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

        List<User> alUsers = userService.findAll();

        boolean createdAlready = false;
        String brugernavn = "";
        for(User u : alUsers){
            if(u.getFirstName().equals(user.getFirstName())
                    && u.getLastName().equals(user.getLastName())
                    && u.getEmailAddress().equals(user.getEmailAddress())){
                createdAlready = true;
                brugernavn = u.getUsername();
                break;
            }
        }
        if(createdAlready){
            String created = "Brugeren er allerede oprettet med brugernavnet: " + brugernavn;
            model.addAttribute("created", created);
            return "/bruger/opret_bruger";
        }

        userService.save(user);
        Long id = user.getId();
        return "redirect:/bruger/bruger_side/" + id;
    }

    //når bugeren er oprettet, skla brugeren logge ind igen
    @GetMapping("/bruger/bruger_side/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        try
        {
            User user = userService.findById(id);
            model.addAttribute("user", user);
            return "/bruger/bruger_side";
        }
        catch (EntityNotFoundException entityNotFoundException)
        {
            entityNotFoundException.printStackTrace(); // Goes to System.err
            entityNotFoundException.printStackTrace(System.out);
            return "/velkommen";
        }
    }

    @GetMapping("/bruger/min_profil")
    public String showMyProfile(Model model){

        model.addAttribute("userDetailHandler", securitycontext.getUserDetailHandler());
        return "/bruger/min_profil";
    }


    @GetMapping("/bruger/opdater_bruger")
    public String showUpdateUser(Model model){
        model.addAttribute("user", securitycontext.getUserDetailHandler().getUser());
        return "/bruger/opdater_bruger";
    }

    @PostMapping("/bruger/opdater_bruger")
    public String updateUser(@RequestParam String password2, @Valid User user, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "/bruger/opdater_bruger";
        }
        if(!password2.equals(user.getPassword())){
            String kode_igen = "Skriv venligst den samme kode igen";
            model.addAttribute("kode_igen", kode_igen);
            return "/bruger/opdater_bruger";
        }

        userService.save(user);

        //when user has updated, user has to login again to see updated profile
        return "/login";
    }

    @GetMapping("/bruger/slet_bruger")
    public String deleteUser(){
        Long id = securitycontext.getUserDetailHandler().getId();
        userService.deleteByID(id);
        return "/login";
    }


}
