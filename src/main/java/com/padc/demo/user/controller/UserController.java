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

    /**
     * We check if the same password is typed twice and there are not errors in validation
     * We also check (firstname, lastname and email) if the user already is created
     * @param password2 user has to type the password twice. RequestParam reads it from the http-POST-request
     *                  body and controller sends the user back to the page, if the password does not match
     * @param user
     * @param bindingResult checks if input is valid in relation to validation annotations NotBlank etc.
     * @param model transfers the data from controller to the htt page
     * @return redirect to the user page. Redirect because we don't need the information anymore, so
     * we reload the page without it
     */
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

    /**
     * @param id user id is tranferred in path so we can show the new user on the user page.
     *           PathVariable reads the id from the path.
     *           The user has not logged in, so we can't yet use the spring security to give the id.
     * @param model transfers the data from controller to the htt page
     * @return When the user is created, user has to login, so we direct to the velcome page
     */
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

    /**
     * securitycontext gives UserDetailHandler, which gives access to information about the user
     * The tag looks like this on the html page: th:text="${userDetailHandler.getUser().getFirstName()}
     * We can use it to show the details on the profile page.
     * @param model transfers the data from controller to the htt page
     * @return the page which shows the profile
     */
    @GetMapping("/bruger/min_profil")
    public String showMyProfile(Model model){
        model.addAttribute("userDetailHandler", securitycontext.getUserDetailHandler());
        return "/bruger/min_profil";
    }


    /**
     * securitycontext gives UserDetailHandler, which gives the user
     * (we could also send the userDetailHandler to the html page and get access as in showMyProfil())
     * We can use it to show the details on the profile page.
     * @param model transfers the data from controller to the htt page
     * @return the page that shows user details so that user can update them
     */
    @GetMapping("/bruger/opdater_bruger")
    public String showUpdateUser(Model model){
        model.addAttribute("user", securitycontext.getUserDetailHandler().getUser());
        return "/bruger/opdater_bruger";
    }

    /**
     * @param password2 user has to type the password twice also when updating. RequestParam reads it
     *                  from the http-POST-request body and controller sends the user back to the page,
     *                  if the password does not match
     * @param user
     * @param bindingResult checks if input is valid in relation to validation annotations NotBlank etc.
     * @param model transfers the data from controller to the htt page
     * @return when the user has updated, user has to login again to see updated profile
     */
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
        return "/login";
    }

    /**
     * When deleted, spring security gets the id from the UserDetailHandler,
     * that keeps track the current user details
     * @return login page
     */
    @GetMapping("/bruger/slet_bruger")
    public String deleteUser(){
        Long id = securitycontext.getUserDetailHandler().getId();
        userService.deleteByID(id);
        return "/login";
    }
}
