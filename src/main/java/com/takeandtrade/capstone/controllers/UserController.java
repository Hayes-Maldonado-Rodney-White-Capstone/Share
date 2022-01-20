package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.ItemRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private final ItemRepository itemDao;


    public UserController(UserRepository userDao,
                          PasswordEncoder passwordEncoder, ItemRepository itemDao) {

        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.itemDao = itemDao;
    }

    @GetMapping("/registerForm")
    public String showSignupForm(@ModelAttribute User user) {

        return "/users/registerForm";
    }

    @PostMapping("/registerForm")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/homepage";
    }

    @GetMapping("/userProfile")
    public String userHome(Model model) {
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User itemOwner = userDao.getById(loggedinUser.getId());
        model.addAttribute("itemOwner", itemOwner);

        model.addAttribute("viewUser", loggedinUser);
        model.addAttribute("items", itemDao.findAll());

//        User userProfile = userDao.getById(id);
//        model.addAttribute("viewProfile", userProfile);

        return "/users/userProfile";
    }



    @GetMapping("/userEdit")
    public String updateUser(Model model) {
        User updateUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", updateUser);
        return "/users/userEdit";

    }

    @PostMapping("/userEdit/{userId}")
    public String saveUserUpdate(@PathVariable long userId,
        @RequestParam(name = "firstName") String firstName,
        @RequestParam(name = "lastName") String lastName,
        @RequestParam(name = "username") String username,
        @RequestParam(name = "email") String email,
        @RequestParam(name = "phoneNumber") Long phoneNumber,
        @RequestParam(name = "city") String city,
        @RequestParam(name = "state") String state,
        @RequestParam(name = "zipcode") String zipcode,
        @RequestParam(name = "password") String password

    ) {
        User updateTheUser = userDao.getById(userId);
        updateTheUser.setFirstName(firstName);
        updateTheUser.setLastName(lastName);
        updateTheUser.setUsername(username);
        updateTheUser.setEmail(email);
        updateTheUser.setPhoneNumber(phoneNumber);
        updateTheUser.setCity(city);
        updateTheUser.setState(state);
        updateTheUser.setZipcode(Integer.parseInt(zipcode));
        String hash = passwordEncoder.encode(updateTheUser.getPassword());
        updateTheUser.setPassword(hash);
//        updateTheUser.setPassword(password);

        userDao.save(updateTheUser);

        return "redirect:/userProfile";
    }

    @PostMapping("/user/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userDao.deleteById(userId);

        return "redirect:/homepage";
    }


}

