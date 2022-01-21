package com.takeandtrade.capstone.controllers;

import com.takeandtrade.capstone.models.Rating;
import com.takeandtrade.capstone.models.Review;
import com.takeandtrade.capstone.models.User;
import com.takeandtrade.capstone.repositories.ItemRepository;
import com.takeandtrade.capstone.repositories.RatingRepository;
import com.takeandtrade.capstone.repositories.ReviewRepository;
import com.takeandtrade.capstone.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private final ItemRepository itemDao;
    private final ReviewRepository reviewDao;
    private final RatingRepository ratingDao;


    public UserController(UserRepository userDao,
                          PasswordEncoder passwordEncoder, ItemRepository itemDao, ReviewRepository reviewDao, RatingRepository ratingDao) {

        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.itemDao = itemDao;
        this.reviewDao = reviewDao;
        this.ratingDao = ratingDao;

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

    @GetMapping("/viewPosterProfile/{posterId}")
    public String showPosterProfile(Model model, @PathVariable Long posterId){
                User poster = userDao.getById(posterId);

                model.addAttribute("userItems",poster.getItems());
                model.addAttribute("reviews", poster.getWrittenReviews());
        return "/users/viewPosterProfile";
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

    @GetMapping("/myReviews/{userId}")
    public String showMyReviews(Model model, @PathVariable long userId) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User consumerUser = userDao.getById(loggedInUser.getId());//user that is writing the review on the item they borrowed
        model.addAttribute("loggedInUser", consumerUser);

        User producerUser = userDao.getById(userId);//
        model.addAttribute("producerUser", producerUser );

        model.addAttribute("user", producerUser);
        model.addAttribute("userReview", new Review());

        model.addAttribute("reviews", reviewDao.findAll());

        List<Rating> ratingList = ratingDao.findAll();
        model.addAttribute("ratings", ratingList);

        return "users/myReviews";
    }


}

