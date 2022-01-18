package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registerForm")
    public String showSignupForm(@ModelAttribute User user) {

        return "registerForm";
    }

    @PostMapping("/registerForm")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/homepage";
    }

    @GetMapping("/userProfile/")
    public String userHome() {
//        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  //this grabs the logged in user
//        model.addAttribute("viewUser", loggedinUser);
//        userId = loggedinUser.getId();
//        User userProfile = userDao.getById(userId);
//        userDao.getById(userId);

        return "userProfile";
    }



    @GetMapping("/userSettings/{userId}")
    public String updateUser(@PathVariable Long userId, Model model ) {
        User updateUser = userDao.getById(userId);
        model.addAttribute("user", updateUser);
        return "userSettings";

    }

    @PostMapping("/userSettings")
    public String saveUserUpdate(
            @RequestParam(name = "userId") Long id,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "dateOfBirth") String dateOfBirth,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "phoneNumber") Long phoneNumber,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "state") String state,
            @RequestParam(name = "zipcode") int zipcode
    ) {
        User updateTheUser = userDao.getById(id);
        updateTheUser.setFirstName(firstName);
        updateTheUser.setLastName(lastName);
        updateTheUser.setUsername(username);
        updateTheUser.setDateOfBirth(dateOfBirth);
        updateTheUser.setEmail(email);
        updateTheUser.setPhoneNumber(phoneNumber);
        updateTheUser.setCity(city);
        updateTheUser.setState(state);
        updateTheUser.setZipCode(zipcode);

        userDao.save(updateTheUser);

        return "redirect:/userProfile";
    }

    @PostMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userDao.deleteById(userId);

        return "redirect:/homepage";
    }


}

